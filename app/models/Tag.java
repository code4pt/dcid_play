package models;

import javax.persistence.*;

import play.db.ebean.*;

/**
 * Represents a Tag that classifies and groups Proposals with the same theme.
 */
@SuppressWarnings("serial")
@Entity
public class Tag extends Model {

	@Id
	public Long id;
	public String title;
	public String description;	
	
	/* ====== *
     * Finder *
     * ====== */
	public static Finder<Long, Tag> find = new Finder<Long, Tag>(
			Long.class, Tag.class);
	
	
	/* ================== *
     * Constructor (init) *
     * ================== */
	public Tag(String title, String description) {
		this.title = title;
		this.description = description;
	}
	
	/**
	 * Creates a new Tag (using a private constructor), initializes all
	 * fields, saves the tag, and returns it.
	 * @param title	
	 * @param description 
	 * @return The new tag created
	 */
	public static Tag createAndSave(String title, String description) {
		// TODO make title id and String titleWithoutWhitespace = title.replace(" ", "-");
		Tag t = new Tag(title, description);
		t.save();
		return t;
	}
	
	
	/* =================== *
     * Getters and Setters *
     * =================== */
	
	public Long getId() {
		return id;
	}
	
	public String getTitle() {
		return title; 
	}
	
	public String getDescription() {
		return description; 
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
	

	/* ============= *
     * Other methods *
     * ============= */

	@Override
	public String toString() {
		return "Tag (title=" + getTitle() + ")";
	}
	
}