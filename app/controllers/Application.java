package controllers;

import play.data.Form;
import static play.data.Form.*;
import play.mvc.*;
import views.html.*;
import models.*;

/**
 * Main controller.
 */
public class Application extends Controller {
	
	/**
     * Inner class containing all login control related with an User's login.
     */
    public static class Login {    	
        public String ffEmail;		// form field Email
        public String ffPassword;	// form field Password
        
        public String validate() {
            if (User.authenticate(ffEmail, ffPassword) == null) {
              return "Invalid login. Please, double-check your email and password.";
            }
            return null;
        }
    }
    
    /* ============== *
     * Helper methods *
     * ============== */
    
    /**
     * @return the User logged in, otherwise null.
     */
    public static User getLoggedInUser() {
    	String loggedInUsername = request().username();
    	User loggedInUser = null;
    	if(loggedInUsername != null) {
    		loggedInUser  = User.find.byId(request().username());
    	}
    	return loggedInUser;
    }
    
    
    /* ================ *
     * Proposal related *
     * ================ */
    
    /**
     * View that lists existing Proposals. 
     */
    @Security.Authenticated(Secured.class)
    public static Result proposalList() {
        return ok(proposalList.render(
    		getLoggedInUser(),
    		Proposal.find.all()
		));
    }
    
    @Security.Authenticated(Secured.class)
    public static Result proposalDetail(Long proposalId) {
        return ok(proposalDetail.render(
    		getLoggedInUser(),
    		Proposal.find.byId(proposalId)
		));
    }
    
    /* ====================== *
     * Authentication related *
     * ====================== */
    
    /**
     * Home page
     */
    public static Result index() {
        return ok(index.render());
    }
    
    /**
     * Log in view.
     */
    public static Result login() {
		return ok(login.render(
			form(Login.class)
		));
    }
    
    /**
     * Log out view.
     */
    @Security.Authenticated(Secured.class)
    public static Result logout() {
        session().clear();
        flash("msgLogin", "Success, you've been logged out.");
        return redirect(
            routes.Application.login()
        );
    }
    
    /**
     * Given a Login form, tries to authenticate a user.
     */
    public static Result authenticate() {
        Form<Login> loginForm = form(Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        } else {
            session().clear();
            session("email", loginForm.get().ffEmail);
            return redirect(
                routes.Application.proposalList()
            );
        }
    }
    
}