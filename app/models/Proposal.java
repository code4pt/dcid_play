package models;

import java.util.Date;
import javax.persistence.*;

import play.db.ebean.Model;

/**
 * This class represents a Proposal created by a User and voted by other Users.
 * A Proposal stores the proposer's arguments and the number of votes.
 */
@SuppressWarnings("serial")
@Entity
public class Proposal extends Model {

	// TODO make all fields private
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
	public String tag;
	
	
	/* ====== *
     * Finder *
     * ====== */
	public static Finder<Long, Proposal> find = new Finder<Long, Proposal>(
			Long.class, Proposal.class);
	
	/* ================== *
     * Constructor (init) *
     * ================== */
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
		p.proposer = User.find.where().eq("email", userId).findUnique();	// TODO use long ID not email
		p.save();
		return p;
	}
	
	
	/* =================== *
     * Getters and Setters *
     * =================== */
	
	/**
	 * @return the Proposal's Proposal's numeric ID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the Proposal's title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the Proposal's problem
	 */
	public String getProblem() {
		return problem;
	}

	/**
	 * @return the Proposal's solution
	 */
	public String getSolution() {
		return solution;
	}

	/**
	 * @return the Proposal's benefits
	 */
	public String getBenefits() {
		return benefits;
	}

	/**
	 * @return the Proposal's number of views
	 */
	public int getViews() {
		return views;
	}

	/**
	 * @return the Proposal's number of upvotes
	 */
	public int getUpvotes() {
		return upvotes;
	}

	/**
	 * @return the Proposal's number of downvotes
	 */
	public int getDownvotes() {
		return downvotes;
	}

	/**
	 * @return the timestamp when the Proposal was created
	 */
	public Date getTimestamp() {
		return timestamp;
	}
	
	/**
	 * @param desiredFormat A SimpleDateFormat string specifying how the timestamp show be formatted  
	 * @return a timestamp formatted according to the input
	 */
	public String getTimestamp(String desiredFormat) {
		return new java.text.SimpleDateFormat(desiredFormat).format(getTimestamp());
	}

	/**
	 * @return the User that created the Proposal
	 */
	public User getProposer() {
		return proposer;
	}

	public String getTag() {
		return tag;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param problem the problem to set
	 */
	public void setProblem(String problem) {
		this.problem = problem;
	}

	/**
	 * @param solution the solution to set
	 */
	public void setSolution(String solution) {
		this.solution = solution;
	}

	/**
	 * @param benefits the benefits to set
	 */
	public void setBenefits(String benefits) {
		this.benefits = benefits;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	/* ============= *
     * Other methods *
     * ============= */
	
	/**
	 * @return the Proposal's score, i.e. the difference between the upvotes and downvotes 
	 */
	public int getScore() {
		return upvotes - downvotes;
	}
	
	/**
	 * Returns a summary of the proposal. 
	 * @param maxChars the maximum number of characters allowed for the summary
	 * @return Proposal's summary
	 */
	public String getSummary(int maxChars) {
		String summary = getSolution();
		if(summary.length() < maxChars) {
			return summary;
		}
		else {
			int indexOfLastCompleteWord = summary.lastIndexOf(" ", maxChars-3);	// -3 will provide space for the "..."
			return summary.substring(0, indexOfLastCompleteWord) + "...";
		}	
	}
	
	/**
	 * @param views increments the number of views
	 */
	public void incrementViews() {
		this.views++;
	}

	/**
	 * @return Increments the number of upvotes and then returns the total number of downvotes.
	 */
	public int incrementUpvotes() {
		return ++upvotes;
	}
	
	/**
	 * @return Increments the number of downvotes and then returns the total number of downvotes.
	 */
	public int incrementDownvotes() {
		return ++downvotes;
	}
	
	public String toString() {
        return "Proposal(id=" + getId() + ", title=" + getTitle() + ", proposer=" + getProposer().getName() + ")";
    }
	
	/* ============== *
     * Static methods *
     * ============== */
	
	/**
	 * @param proposalId Identifier of the Proposal to consider
	 * @param userId The identifier of a User
	 * @return true if that User is the author of the considered proposal; false otherwise.
	 */
	public boolean isProposedBy(Long proposalId, String userId) {
		String proposersName = find.byId(proposalId).getProposer().getName();
		return proposersName.compareTo(userId) == 0 ? true : false;
	}
	
	/**
	 * 
	 * @param proposalId Identifier of Proposal to edit
	 * @param newProblem New value to set
	 * @return The value of field <em>problem</em> after the edit
	 */
	public static String editProblem(Long proposalId, String newProblem) {
		Proposal proposalToUpdate = find.byId(proposalId);
		proposalToUpdate.setProblem(newProblem);
		proposalToUpdate.update();
		return proposalToUpdate.getProblem();
	}
	
}