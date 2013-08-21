package controllers;

import play.mvc.*;

@Security.Authenticated(Secured.class)
public class Proposals extends Controller {
	
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