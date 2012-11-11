package controllers;

import models.Foodorder;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.projects.myOrder;

public class MyFoodorder extends Controller {

	public static Result GO_HOME = redirect(routes.MyFoodorder.list(0,
			"orderAt", "desc", ""));

	public static Result list(int page, String sortBy, String order,
			String filter) {
		if(session(Application.USER_KEY_ID) == null	)
			return redirect(routes.Application.login());
		long id = Long.valueOf(session(Application.USER_KEY_ID));
		return ok(myOrder.render(Foodorder.myPage(page, 10, sortBy, order, id),
				sortBy, order,filter, User.findByEmail(session(Application.USER_ID))));
	}
}
