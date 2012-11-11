package controllers;

import models.Payment;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.projects.myPayment;


public class MyPayment extends Controller {

	public static Result GO_HOME = redirect(routes.MyPayment.list(0,
			"orderAt", "desc", ""));

	public static Result list(int page, String sortBy, String order,
			String filter) {
		if(session(Application.USER_KEY_ID) == null	)
			return redirect(routes.Application.login());
		long id = Long.valueOf(session(Application.USER_KEY_ID));
		return ok(myPayment.render(Payment.myPage(page, 10, sortBy, order, id),
				sortBy, order,filter, User.findByEmail(session(Application.USER_ID))));
	}
}
