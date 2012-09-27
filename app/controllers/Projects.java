package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import secure.Secured;
import views.html.projects.*;

@Security.Authenticated(Secured.class)
public class Projects  extends Controller{
	
	public static Result index(){
		return ok(index.render());
	}

}
