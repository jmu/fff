package controllers;

import java.util.Date;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import secure.Secured;
import views.html.projects.*;
import models.Menu;

@Security.Authenticated(Secured.class)
public class Projects  extends Controller{
	
	public static Result index(int menuPage, String menuDay) {
        
		return ok(index.render(Menu.afterDay(menuPage,99,"name", "desc",new Date())));
	}

    

}
