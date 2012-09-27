package controllers.admin;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.*;

public class Restaurant extends Controller{
	public static Result list(){
		return ok(admin_main.render());
	}
}
