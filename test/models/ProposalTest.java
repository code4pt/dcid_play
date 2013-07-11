package models;

import java.util.List;

import models.*;

import org.junit.*;

import com.avaje.ebean.Ebean;

import static org.junit.Assert.*;
import play.libs.Yaml;
import play.test.WithApplication;
import static play.test.Helpers.*;

public class ProposalTest extends WithApplication {

	@Before
	public void setUp() {
		start(fakeApplication(inMemoryDatabase()));
	}

	// creation and retrieval of a proposal without requiring a User class
	@Test
	public void createAndRetrieveProposalMock() {
		new Proposal("Do something", "A problem.", "We solve it.", "We get happy.", null).save();
		new Proposal("Do something awesome", "Another problem.", "We solve it.", "We get happy.", null).save();
		
		Proposal firstProp = Proposal.find.where().eq("title", "Do something").findUnique();
		Proposal secondProp = Proposal.find.where().eq("title", "Do something awesome").findUnique();
		
		assertNotNull(firstProp);						// test retrieval
		assertEquals("Do something", firstProp.title);	// test correct creation and storage
		assertEquals("1", firstProp.id.toString());		// test automatic id
		assertEquals("2", secondProp.id.toString());	// test sequentiality
	}
	
	// creation and retrieval of a proposal without requiring User and Tag
	@Test
	public void createAndRetrieveProposal() {
		new User("bob@gmail.com", "Bob", "secret").save();
		Proposal prop = Proposal.createAndSave("Build a new park", "There is none.", "We build it.", "We get happy.", "bob@gmail.com");
		
		assertNotNull(prop);							// test retrieval
		assertEquals("Build a new park", prop.title);	// test correct creation and storage
		System.out.println("proposer:"+ prop.proposer);
		System.out.println("proposer.name:" + prop.proposer.name);
		assertEquals("Bob", prop.proposer.name);		// test correct storage of User
	}
	
	// test the calculation of (positive and negative) scores
	@Test
	public void calculateScore() {
		new User("bob@gmail.com", "Bob", "secret").save();
		Proposal prop = Proposal.createAndSave("Build a new park", "There is none.", "We build it.", "We get happy.", "bob@gmail.com");
		
		prop.upvotes = 300;
		prop.downvotes = 100;		
		assertEquals(200, prop.getScore());		// score = upvotes - downvotes		
		prop.upvotes = 100;						// switch values...
		prop.downvotes = 300;
		assertEquals(-200, prop.getScore());	// and test negative score (they are acceptable)
	}
}