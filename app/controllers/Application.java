package controllers;

import play.*;
import play.mvc.*;
import views.html.*;
import models.Proposal;
import models.Tag;
import models.User;

/**
 * Main controller.
 */
public class Application extends Controller {
  
    public static Result index() {
        return ok(index.render(
    		Proposal.find.all(),
    		User.find.all(),
    		Tag.find.all()
		));
    }
  
}
