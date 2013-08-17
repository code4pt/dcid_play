
package models;

import java.util.List;

import javax.persistence.*;

import play.db.ebean.*;

import com.avaje.ebean.*;

/**
 * A Tag is a subject/theme that classifies a specimen (like a Proposal).
 * Its main objective is to catalog/group similar specimens, easing their
 * search.
 */
@Entity
public class Tag extends Model {

	@Id
	public String name;
	public String desc;
	@ManyToMany
	public List<Proposal> taggedProposals;

	/**
	 * Finder
	 */
	public static Finder<String, Tag> find = new Finder<String, Tag>(
			String.class, Tag.class);
	
	/**
	 * Constructor
	 * @param name
	 * @param description
	 */
	public Tag(String name, String description) {
		this.name = name;
		this.desc = description;
	}
    
	/**
	 * @return All proposals tagged with that Tag
	 */
    public List<Proposal> getTaggedProposals() {
    	return taggedProposals;
    }
    
    /**
     * Tags a specific Proposal with the current Tag.
     * @param prop Proposal object to tag
     */
    public void tagProposal(Proposal prop) {
    	taggedProposals.add(prop);
    }
 
    public String toString() {
        return "Tag (name=" + name +")";
    }

}