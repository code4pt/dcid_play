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
	public String problem;
	public String solution;
	public String benefits;
	public Date timestamp;	// date and time of creation // TODO investigate joda-time.sourceforge.net
	public int views;
	public int upvotes;
	public int downvotes;
	@OneToOne
	public User proposer;
	
	public Proposal(String title, String problem, String solution, String benefits, User proposer) {
		this.title = title;
		this.problem = problem;
		this.solution = solution;
		this.benefits = benefits;
		this.timestamp = new Date();
		this.views = this.upvotes = this.downvotes = 0;
		this.proposer = proposer;
	}

	public static Model.Finder<Long, Proposal> find = new Model.Finder(
			Long.class, Proposal.class);	
	
	public static Proposal createAndSave(String title, String problem, String solution, String benefits, String userId) {
		User proposer = User.find.ref(userId);
		Proposal p = new Proposal(title, problem, solution, benefits, proposer);
		p.save();
		return p;
	}
	
	
	/**
	 * @return Increments the number of upvotes and then returns the total number of downvotes.
	 */
	public int voteUp() {
		return ++upvotes;
	}
	
	/**
	 * @return Increments the number of downvotes and then returns the total number of downvotes.
	 */
	public int voteDown() {
		return ++downvotes;
	}
	
	/**
	 * @return the score, i.e. the difference between the upvotes and downvotes 
	 */
	public int getScore() {
		return upvotes - downvotes;
	}
	
	
	public String toString() {
        return "Proposal(" + id + ") | title: " + title + " | proposer: " + proposer.email;
    }
}