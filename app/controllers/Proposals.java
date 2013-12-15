package controllers;

import java.util.*;

import static play.data.Form.form;
import play.mvc.*;
import models.*;
import views.html.snippets.*;

@Security.Authenticated(Secured.class)
public class Proposals extends Controller {
	
	public static Result createProposal() {
	    Proposal newProposal = Proposal.createAndSave("New proposal",
	    		"", "", "",
	    		request().username());
	    
	    return ok(displayProposalDetail.render(newProposal));
	}
//	public static Result propose() {
//	    Proposal newProposal = Proposal.createAndSave(title, problem, solution, benefits,
//	    		request().username())
//	    		
//	    		
//	    		.create(
//	        "New project",
//	        form().bindFromRequest().get("group"),
//	        request().username()
//	    );
//	    return ok(item.render(newProject));
//	}

}