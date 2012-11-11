package controllers;

import models.Role;
import models.User;
import models.Usergroup;
import play.Logger;
import play.cache.Cached;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.login;

public class Application extends Controller {

	public static final String USER_ID = "userid";
	public static final String USER_ROLE = "userrole";
	public static final String USER_GROUP_ID = "usergroupid";
	public static final String USER_GROUP_NAME = "usergroupname";
	public static final String USER_KEY_ID = "userkeyid";

	public static Result index() {
		return redirect(routes.Application.login());
	}

	@Cached(key = "LoginPage", duration = 60)
	public static Result login() {
		if (session().containsKey(USER_ID)) {
			return redirect(routes.Projects.index(0, ""));
		}
		return ok(login.render(form(Login.class)));
	}

	public static Result logout() {
		session().clear();
		flash("success", "You've been logged out");
		return redirect(routes.Application.login());
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
			String userid = loginForm.get().email;
			String roleName = Role.ROLE_USER;
			User user = User.findByEmail(userid);
			Role role = user.role;
			Usergroup ug = user.usergroup;
			if (ug != null) {
				session(USER_GROUP_ID, ug.id.toString());
				session(USER_GROUP_NAME, ug.name);
			}
			if (role != null) {
				roleName = role.name;
			}
			session(USER_ID, userid);
			session(USER_KEY_ID, user.id.toString());
			session(USER_ROLE, roleName);
			Logger.info(userid + " is Login.");
			return redirect(routes.Projects.index(0, ""));
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
