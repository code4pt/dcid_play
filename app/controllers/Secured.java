package controllers;

import models.Proposal;
import play.mvc.*;
import play.mvc.Http.*;

public class Secured extends Security.Authenticator {

    @Override
    public String getUsername(Context ctx) {
        return ctx.session().get("email");
    }

    @Override
    public Result onUnauthorized(Context ctx) {
        return redirect(routes.Application.login());
    }
    
    public static boolean isAllowedToEdit(Long proposalId) {
        Proposal proposal = Proposal.find.byId(proposalId);
        return proposal.isProposedBy(proposalId, Context.current().request().username());
    }
}