package models;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;

/**
 * This class represents a Proposal created by a User and voted by other Users.
 * A Proposal stores the proposer's arguments and the number of votes.
 */
@Entity
public class Proposal extends Model {

	@Id
	public Long id;
	public String title;
	@Lob
	public String problem;
	@Lob
	public String solution;
	@Lob
	public String benefits;
	public Long upvotes;
	public Long downvotes;
	public Long views;
	public Date timestamp;	// date and time of creation // TODO investigate joda-time.sourceforge.net
	@OneToOne
	public User proposer;
	@OneToOne
	public Tag tag;	// TODO ManyToMany
	
	public Proposal(String title, String problem, String solution, String benefits, User proposer, Tag tag) {
		this.title = title;
		this.problem = problem;
		this.solution = solution;
		this.benefits = benefits;
		this.views = this.upvotes = this.downvotes = 0L;
		this.timestamp = new Date();
		this.proposer = proposer;
		this.tag = tag;
	}

	public static Model.Finder<Long, Proposal> find = new Model.Finder(
			Long.class, Proposal.class);

	public static Proposal create(String title, String problem, String solution, String benefits, String proposerName, String tagName) {
		Proposal p = new Proposal(title, problem, solution, benefits, User.find.ref(proposerName), Tag.find.ref(tagName));
		p.save();
		return p;
	}
	
	/**
	 * @return the score, i.e. the difference between the upvotes and downvotes 
	 */
	public static Long getScore(Long proposalId) {
		Proposal p = find.where().eq("id", proposalId).findUnique();
		return p.upvotes - p.downvotes;
	}
}