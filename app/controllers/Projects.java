package controllers;

import models.Menu;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import secure.Secured;
import views.html.projects.index;
import buz.DateUtils;

@Security.Authenticated(Secured.class)
public class Projects  extends Controller{
	
	public static Result index(int menuPage, String menuDay) {
		return ok(index.render(Menu.afterDay(menuPage,99,"name", "desc",DateUtils.today()),User.findByEmail(session(Application.USER_ID))));
	}

    

}
