package controllers;

import models.User;
import play.Logger;
import play.cache.Cached;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.login;

public class Application extends Controller {

	public static Result index() {
		return redirect(routes.Application.login());
	}
	
	@Cached(key = "LoginPage",duration = 60)
	public static Result login() {
		return ok(login.render(form(Login.class)));
	}

	public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(
            routes.Application.login()
        );
	}

	/**
	 * Handle login form submission.
	 */
	public static Result authenticate() {
		Form<Login> loginForm = form(Login.class).bindFromRequest();
		if (loginForm.hasErrors()) {
			Logger.error(loginForm.errors().toString());
			return badRequest(login.render(loginForm));
		} else {
			session("userid", loginForm.get().email);
			Logger.info(loginForm.get().email + " is Login.");
			return redirect(routes.Projects.index());
		}
	}

	public static class Login {

		public String email;
		public String password;

		public String validate() {
			if (User.authenticate(email, password) == null) {
				return "Invalid user or password";
			}
			return null;
		}

	}

}