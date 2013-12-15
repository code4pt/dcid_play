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
        if (User.find.findRowCount() == 0) {					// if DB is empty
            Ebean.save((List) Yaml.load("ptagora-data.yml"));	// populate it
        }
    }
}