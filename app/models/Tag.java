
package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import play.db.ebean.*;

/**
 * A Tag is a subject/theme that classifies a specimen (like a Proposal).
 * Its main objective is to catalog/group similar specimens, easing their
 * search.
 */
@SuppressWarnings("serial")
@Entity
public class Tag extends Model {

	@Id
	public String name;
	public String desc;
	@ManyToMany(mappedBy="tags")
	public List<Proposal> taggedProposals;

	
	/* ====== *
     * Finder *
     * ====== */
	public static Finder<String, Tag> find = new Finder<String, Tag>(
			String.class, Tag.class);
	
	
	/* ================== *
     * Constructor (init) *
     * ================== */
	public Tag(String name, String description) {
		this.name = name;
		this.desc = description;
		this.taggedProposals = new ArrayList<Proposal>();
	}
    
	
	/* =================== *
     * Getters and Setters *
     * =================== */
	
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
    
    
    /* ============= *
     * Other methods *
     * ============= */
 
    public String toString() {
        return "Tag (name=" + name + ", taggedProposals=" + taggedProposals.size() + ")";
    }

}