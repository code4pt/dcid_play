package models;

import models.*;

import org.junit.*;

import com.avaje.ebean.Ebean;

import static org.junit.Assert.*;
import play.libs.Yaml;
import play.test.WithApplication;
import static play.test.Helpers.*;

public class UserTest extends WithApplication {

	@Before
	public void setUp() {
		start(fakeApplication(inMemoryDatabase()));
	}

	// creation and retrieval of user
	@Test
	public void createAndRetrieveUser() {
		new User("bob@gmail.com", "Bob", "secret").save();

		User bob = User.find.where().eq("email", "bob@gmail.com").findUnique();

		assertNotNull(bob);				// successfully retrieved
		assertEquals("Bob", bob.getName());	// correct user retrieved
	}

	@Test
	public void authenticateUser() {
		new User("bob@gmail.com", "Bob", "secret").save();

		assertNotNull(User.authenticate("bob@gmail.com", "secret"));	// test correct auth
		assertNull(User.authenticate("bob@gmail.com", "badpassword"));	// test wrong password
		assertNull(User.authenticate("tom@gmail.com", "secret"));		// test inexistent user
	}
}