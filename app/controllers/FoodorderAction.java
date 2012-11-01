package controllers;

import java.util.Date;

import models.Foodorder;
import models.Menu;
import models.User;
import play.Logger;
import play.data.Form;
import play.db.ebean.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import secure.Secured;
import views.html.foodorder.createForm;
import views.html.foodorder.editForm;

@Security.Authenticated(Secured.class)
public class FoodorderAction extends Controller {
	public static Result GO_HOME = redirect(routes.Application.login());

	public static Result create(Long menuId) {
		Foodorder fo = new Foodorder();
		Menu menu = new Menu();
		menu.id = menuId;
		fo.menu = menu;
		fo.user = User.findByEmail(session(Application.USER_ID));
		Form<Foodorder> foodForm = form(Foodorder.class).fill(fo);
		return ok(createForm.render(foodForm));
	}

	@Transactional
	public static Result save() {
		Form<Foodorder> foodorderForm = form(Foodorder.class).bindFromRequest();
		if (foodorderForm.hasErrors()) {
			return badRequest(createForm.render(foodorderForm));
		}
		User user = User.findByEmail(session(Application.USER_ID));
		Date creatAt = new Date();
		Foodorder r = foodorderForm.get();
		if(user == null || user.id != r.user.id ){
			Logger.error("bad user request");
			return badRequest(createForm.render(foodorderForm));
		}
		if (r.menu != null && r.menu.id == null) {
			Menu menu = new Menu();
			menu.generateMenuCode(null, 2);
			menu.createdAt = creatAt;
			menu.isAvailable = true;
			menu.deal = false;
			menu.save();
			r.menu = menu;
		}
		r.orderAt = creatAt;
		r.save();
		flash("success", "Foodorder " + " has been created");
		return GO_HOME;
	}

	public static Result edit(Long id) {
		Form<Foodorder> foodorderForm = form(Foodorder.class).fill(
				Foodorder.find.byId(id));
		return ok(editForm.render(id, foodorderForm));
	}

	public static Result update(Long id) {
		Form<Foodorder> foodorderForm = form(Foodorder.class).bindFromRequest();
		if (foodorderForm.hasErrors()) {
			return badRequest(editForm.render(id, foodorderForm));
		}
		foodorderForm.get().update(id);
		flash("success", "Foodorder " + " has been updated");
		return GO_HOME;
	}

	public static Result delete(Long id) {
		Foodorder.find.ref(id).delete();
		flash("success", "Foodorder has been deleted");
		return GO_HOME;
	}
}
