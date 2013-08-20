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
     * Home page
     */
    @Security.Authenticated(Secured.class)
    public static Result index() {
        return ok(index.render(
    		Proposal.find.all(),
    		User.find.all(),
    		Tag.find.all()
		));
    }
    
    /**
     * Login page
     */
    public static Result login() {
    	return ok(login.render(
    		form(Login.class)
		));
    }
    
    public static Result logout() {
        session().clear();
        flash("successMsg", "You've been logged out");
        return redirect(
            routes.Application.login()
        );
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
