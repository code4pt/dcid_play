package models;

import javax.persistence.*;

import play.db.ebean.*;

/**
 * Represents a real person capable of voting and proposing.
 */
@SuppressWarnings("serial")
@Entity
public class User extends Model {

	@Id
	public String email;	// TODO Id should be the citizen number (Long)
	private String name;
	public String password;
	
	
	/* ====== *
     * Finder *
     * ====== */
	public static Finder<String, User> find = new Finder<String, User>(
			String.class, User.class);
	
	
	/* ================== *
     * Constructor (init) *
     * ================== */
	public User(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
	}
	
	/**
	 * Creates a new User (using a private constructor), initializes all
	 * fields, saves the user, and returns it.
	 * @param email
	 * @param name
	 * @param password
	 * @return The new user created
	 */
	public static User createAndSave(String email, String name, String password) {
		User u = new User(email, name, password);
		u.save();
		return u;
	}
	
	
	/* =================== *
     * Getters and Setters *
     * =================== */
	
	public String getName() {
		return name;	// TODO if(!privacy) return name; else return "A citizen"; 
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	

	/* ============= *
     * Other methods *
     * ============= */

	public static User authenticate(String email, String password) {
		return find.where().eq("email", email).eq("password", password).findUnique();
	}

	@Override
	public String toString() {
		return "User (email=" + getEmail() + ", name=" + getName() + ")";
	}
	
}