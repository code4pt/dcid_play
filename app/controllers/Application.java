package controllers;

import static play.data.Form.*;
import play.data.Form;
import models.*;
import play.mvc.*;
import views.html.*;

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
    
    /* ==================== *
     * User related methods *
     * ==================== */
    
    @Security.Authenticated(Secured.class)
    public static Result userAccount() {
        return ok(
        		userAccount.render(Secured.getLoggedInUser()
		));
    }
    
    
    /* ====================== *
     * Authentication related *
     * ====================== */
    
    /**
     * Home page
     */
    public static Result index() {
        return ok(
        		index.render()
		);
    }
    
    /**
     * Log in view.
     */
    public static Result login() {
		return ok(
				login.render(form(Login.class))
		);
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
            		routes.Proposals.proposalList()
            );
        }
    }
    
}