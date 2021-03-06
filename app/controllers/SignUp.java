package controllers;

import java.util.Date;

import models.Role;
import models.User;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.signup.form;

public class SignUp extends Controller {

	/**
	 * Defines a form wrapping the User class.
	 */
	final static Form<User> signupForm = new Form(User.class);

	/**
	 * Display a blank form.
	 */
	public static Result blank() {
		return ok(form.render(signupForm));
	}

	/**
	 * Display a form pre-filled with an existing account.
	 */
	public static Result edit() {
		/*
		 * User existingUser = new User( "fakeuser", "fake@gmail.com", "secret",
		 * new User.Profile("France", null, 30) ); return
		 * ok(form.render(signupForm.fill(existingUser)));
		 */
		return null;
	}

	/**
	 * Handle the form submission.
	 */
	public static Result submit() {
		Form<User> filledForm = signupForm.bindFromRequest();

		/*
		 * // Check accept conditions
		 * if(!"true".equals(filledForm.field("accept").value())) {
		 * filledForm.reject("accept",
		 * "You must accept the terms and conditions"); }
		 */
		// Check repeated password
		if (!filledForm.field("password").valueOr("").isEmpty()) {
			if (!filledForm.field("password").valueOr("")
					.equals(filledForm.field("repeatPassword").value())) {
				filledForm.reject("repeatPassword", "Password don't match");
			}
		}

		// Check if the username is valid if(!filledForm.hasErrors()) {
		if (filledForm.get().userName.equals("admin")
				|| filledForm.get().userName.equals("guest")) {
			filledForm.reject("username", "This username not allowed ");
		}

		if (User.findByEmail(filledForm.get().email) != null) {
			filledForm.reject("email", "Email already taken");
		}

		if (filledForm.hasErrors()) {
			Logger.error(filledForm.errors().toString());

			return badRequest(form.render(filledForm));
		} else {
			User created = filledForm.get();
			if (created.role == null) {
				created.role = Role.findByName(Role.ROLE_USER);
			}
			if (created.regAt == null) {
				created.regAt = new Date();
			}
			created.save();
			return redirect(routes.Application.index());
		}

	}

}
