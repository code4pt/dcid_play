package controllers;

import static play.data.Form.form;
import models.*;
import play.mvc.*;
import views.html.*;
import views.html.snippets.*;

@Security.Authenticated(Secured.class)
public class Proposals extends Controller {
	
	/* ============== *
	 * Helper methods *
	 * ============== */
	
	private static String getUsernameFromRequest() {
		return request().username();
	}
	
	private static String getUsernameFromForm() {
		return form().bindFromRequest().get("username");		
	}

	/* ================== *
	 * Controls for views *
	 * ================== */
	
    public static Result proposalList() {
        return ok(
        		proposalList.render(Secured.getLoggedInUser(), Proposal.find.all()
		));
    }
    
    public static Result proposalDetail(Long proposalId) {
        return ok(
        		proposalDetail.render(Secured.getLoggedInUser(), Proposal.find.byId(proposalId))
		);
    }
    
    /* ==================== *
	 * Controls for actions *
	 * ==================== */
	
	public static Result createProposal() {
		Proposal newProposal = Proposal.createAndSave("New proposal", "", "", "", getUsernameFromRequest());
		return ok(
				displayProposalDetail.render(newProposal)
		);
	}

	public static Result editProblem(Long proposalId) {
		if (Secured.isAllowedToEdit(proposalId))
			return ok(
					Proposal.editProblem(proposalId, getUsernameFromForm())
			);
		else
			return forbidden();
	}
	
	public static Result deleteProposal(Long proposalId) {
		if(Secured.isAllowedToEdit(proposalId)) {
			Proposal.delete(proposalId, getUsernameFromForm());
			return ok();
		} else {
			return forbidden();
		}
	}

}