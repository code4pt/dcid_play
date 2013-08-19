import play.*;
import play.libs.*;

import com.avaje.ebean.Ebean;

import models.*;

import java.util.*;

/**
 * Checks is database is empty, and if so populates with some test data.
 */
public class Global extends GlobalSettings {
    
	@Override
    public void onStart(Application app) {
    	// load data to populate the database
		@SuppressWarnings("unchecked")
		Map<String,List<Object>> populateData = (Map<String,List<Object>>) Yaml.load("test-data.yml");
    	
    	// save all entities by type
        Ebean.save(populateData.get("users"));
        Ebean.save(populateData.get("proposals"));
        Ebean.save(populateData.get("tags"));
    	
    	// save all relationships between entities
        for(Object tag: populateData.get("tags")) {
            Ebean.saveManyToManyAssociations(tag, "proposals");
        }
    }
    
}