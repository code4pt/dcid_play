package controllers;

import static play.data.Form.form;
import models.*;
import play.mvc.*;
import views.html.snippets.*;

@Security.Authenticated(Secured.class)
public class Proposals extends Controller {

	public static Result createProposal() {
		Proposal newProposal = Proposal.createAndSave("New proposal",
				"", "", "",
				request().username());

		return ok(displayProposalDetail.render(newProposal));
	}

	public static Result editProblem(Long proposalId) {
		if (Secured.isAllowedToEdit(proposalId)) {
			return ok(
					Proposal.editProblem(proposalId, form().bindFromRequest().get("username"))
					);
		} else {
			return forbidden();
		}
	}

}