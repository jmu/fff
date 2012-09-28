package controllers.admin;

import controllers.admin.routes;
import models.Restaurant;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.restaurant.createForm;
import views.html.admin.restaurant.editForm;
import views.html.admin.restaurant.list;

public class Restaurants extends Controller {
	public static Result GO_HOME = redirect(routes.Restaurants.list(0, "name",
			"asc", ""));

	// public static Result list() {
	// return ok(list.render());
	// }

	public static Result list(int page, String sortBy, String order,
			String filter) {
		return ok(list.render(Restaurant.page(page, 10, sortBy, order, filter),
				sortBy, order, filter));
	}

	public static Result edit(Long id) {
		Form<Restaurant> computerForm = form(Restaurant.class).fill(
				Restaurant.find.byId(id));
		return ok(editForm.render(id, computerForm));
	}

	public static Result update(Long id) {
		Form<Restaurant> computerForm = form(Restaurant.class)
				.bindFromRequest();
		if (computerForm.hasErrors()) {
			return badRequest(editForm.render(id, computerForm));
		}
		computerForm.get().update(id);
		flash("success", "Restaurant " + computerForm.get().name
				+ " has been updated");
		return GO_HOME;
	}

	public static Result create() {
		Form<Restaurant> computerForm = form(Restaurant.class);
		return ok(createForm.render(computerForm));
	}

	public static Result save() {
		Form<Restaurant> computerForm = form(Restaurant.class)
				.bindFromRequest();
		if (computerForm.hasErrors()) {
			return badRequest(createForm.render(computerForm));
		}
		Restaurant r = computerForm.get();
		if (r.isAvailable == null) {
			r.isAvailable = false;
		}
		r.save();
		flash("success", "Restaurant " + computerForm.get().name
				+ " has been created");
		return GO_HOME;
	}

	public static Result delete(Long id) {
		Restaurant.find.ref(id).delete();
		flash("success", "Restaurant has been deleted");
		return GO_HOME;
	}

}
