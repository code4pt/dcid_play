package controllers;

import play.*;
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
     * Inner class containing all login related with an User's login.
     */
    public static class Login {    	
        public String email;
        public String password;
        
        public String validate() {
            if (User.authenticate(email, password) == null) {
              return "Invalid login. Please, double-check your email and password.";
            }
            return null;
        }
    }
    
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
        flash("msgSuccess", "You've been logged out");
        return redirect(
            routes.Application.login()
        );
    }
    
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
    
    /**
     * Given a Login form, tries to authenticates a user.
     */
    public static Result authenticate() {
        Form<Login> loginForm = form(Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        } else {
            session().clear();
            session("email", loginForm.get().email);
            return redirect(
                routes.Application.index()
            );
        }
    }
    
}
