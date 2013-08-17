package models;

import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;

/**
 * Represents a real person capable of voting and proposing.
 */
@Entity
public class User extends Model {

	@Id
	public String email;	// TODO Id should be the citizen number (Long)
	private String name;
	public String password;

	public User(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
	}
	
	public String getName() {
		return name;	// TODO if(!privacy) return name; else return "A citizen"; 
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public static Finder<String, User> find = new Finder<String, User>(
			String.class, User.class);

	public static User authenticate(String email, String password) {
		return find.where().eq("email", email).eq("password", password).findUnique();
	}

	@Override
	public String toString() {
		return "User (email=" + email + ", name=" + name + ")";
	}
	
}