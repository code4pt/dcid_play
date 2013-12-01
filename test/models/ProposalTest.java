package models;

import models.*;

import org.junit.*;

import com.avaje.ebean.Ebean;

import static org.junit.Assert.*;
import play.libs.Yaml;
import play.test.WithApplication;
import static play.test.Helpers.*;

public class ProposalTest extends WithApplication {

//	@Before
//	public void setUp() {
//		start(fakeApplication(inMemoryDatabase()));
//	}
//
//	// creation and retrieval of a proposal
//	@Test
//	public void createAndRetrieveProposal() {
//		new User("bob@gmail.com", "Bob", "secret").save();
//		Proposal prop = Proposal.createAndSave("Build a new park", "There is none.", "We build it.", "We get happy.", "TestTag", "bob@gmail.com");
//		
//		assertNotNull(prop);							// test retrieval
//		assertEquals("Build a new park", prop.title);	// test correct creation and storage
//		assertEquals("Bob", prop.proposer.getName());		// test correct storage of User
//	}
//	
//	// test the calculation of (positive and negative) scores
//	@Test
//	public void calculateScore() {
//		new User("bob@gmail.com", "Bob", "secret").save();
//		Proposal prop = Proposal.createAndSave("Build a new park", "There is none.", "We build it.", "We get happy.", "TestTag", "bob@gmail.com");
//		
//		prop.upvotes = 300;
//		prop.downvotes = 100;		
//		assertEquals(200, prop.getScore());		// score = upvotes - downvotes		
//		prop.upvotes = 100;						// switch values...
//		prop.downvotes = 300;
//		assertEquals(-200, prop.getScore());	// and test negative score (they are acceptable)
//	}
}