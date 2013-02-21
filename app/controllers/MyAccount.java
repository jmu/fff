package controllers;

import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.projects.myAccount;

public class MyAccount extends Controller{


    public static Result edit() {
        Form<User> userForm = new Form(User.class).fill(
                User.findById.byId(Long.valueOf(session(Application.USER_KEY_ID))));
        //clean the sha1 password
        userForm.get().password = null;
        return ok(myAccount.render(userForm));
    }

    public static Result update() {
        Form<User> userForm = new Form(User.class)
            .bindFromRequest();
		if (!userForm.field("password").valueOr("").isEmpty()) {
			if (!userForm.field("password").valueOr("")
					.equals(userForm.field("repeatPassword").value())) {
				userForm.reject("repeatPassword", "Password don't match");
			}
		}
        if (userForm.hasErrors()) {
            return badRequest(myAccount.render(userForm));
        }

        userForm.get().update(Long.valueOf(session(Application.USER_KEY_ID)));
        flash("success", "User " + userForm.get().userName
                + " has been updated");
        return redirect(routes.Application.index());
    }
}
