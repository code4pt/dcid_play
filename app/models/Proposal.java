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
	public int views;
	public int upvotes;
	public int downvotes;
	public Date timestamp;	// date and time of creation // TODO investigate joda-time.sourceforge.net
	@OneToOne
	public User proposer;
	
	/**
	 * Finder
	 */
	public static Model.Finder<Long, Proposal> find = new Model.Finder(
			Long.class, Proposal.class);	
	
	/**
	 * Constructor (init)
	 */
	private Proposal(String title, String problem, String solution, String benefits) {
		this.title = title;
		this.problem = problem;
		this.solution = solution;
		this.benefits = benefits;
		this.timestamp = new Date();
		this.views = this.upvotes = this.downvotes = 0;
		this.proposer = null;
	}
	
	/**
	 * Creates a new Proposal (using a private constructor), initializes all
	 * fields, saves the proposal, and returns it.
	 * @param title
	 * @param problem
	 * @param solution
	 * @param benefits
	 * @param userId Identifier of the proposer
	 * @return The new proposal created
	 */
	public static Proposal createAndSave(String title, String problem, String solution, String benefits, String userId) {
		Proposal p = new Proposal(title, problem, solution, benefits);
		//p.proposer = User.find.ref(userId);
		p.proposer = User.find.where().eq("email", userId).findUnique();
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