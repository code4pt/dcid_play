package models;

import java.util.List;

import javax.persistence.*;

import org.apache.commons.lang.NotImplementedException;

import play.db.ebean.*;

import com.avaje.ebean.*;

/**
 * A Tag is a subject or theme that classifies a specimen (like a
 * Proposal). Its main objective is to catalog/group similar specimens,
 * easing their search.
 */
@Entity
public class Tag extends Model {

	@Id
	public String name;
	public String description;

	public Tag(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public static Finder<String, Tag> find = new Finder<String, Tag>(
			String.class, Tag.class);
	
	public static Tag findOrCreateByName(String name) {
	    Tag tag = find.where().eq("name", name).findUnique();
	    if(tag == null) {
	        tag = new Tag(name, "No description.");	// TODO i18d
	    }
	    return tag;
	}

}