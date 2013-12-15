package controllers;

import models.*;
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
    
    /* ============== *
     * Helper methods *
     * ============== */
    
    /**
     * @return the User logged in, otherwise null.
     */
    public static User getLoggedInUser() {
    	String loggedInUsername = Context.current().request().username();
    	User loggedInUser = null;
    	if(loggedInUsername != null) {
    		loggedInUser  = User.find.byId(loggedInUsername);
    	}
    	return loggedInUser;
    }
    
    /**
     * @param proposalId The Proposal's identifier
     * @return true if the currently logged in User is allowed to edit the specified proposal
     */
    public static boolean isAllowedToEdit(Long proposalId) {
        return Proposal.isProposedBy(proposalId, Context.current().request().username());
    }
}