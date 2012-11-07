package controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import secure.Secured;
import views.html.projects.*;
import models.Menu;

@Security.Authenticated(Secured.class)
public class Projects  extends Controller{
	
	public static Result index(int menuPage, String menuDay) {
		Date today = new Date();
		Calendar gc= GregorianCalendar.getInstance();
		gc.setTime(today);
		gc.set(Calendar.HOUR_OF_DAY, 0);
		gc.set(Calendar.MINUTE, 0);
		gc.set(Calendar.SECOND, 0);
		gc.set(Calendar.MILLISECOND, 0);
		
		today = gc.getTime();
        
		return ok(index.render(Menu.afterDay(menuPage,99,"name", "desc",today)));
	}

    

}
