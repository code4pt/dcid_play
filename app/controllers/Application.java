package controllers;

import play.*;
import play.data.Form;
import static play.data.Form.*;
import play.mvc.*;
import views.html.*;
import models.Proposal;
import models.User;
import models.Tag;

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
