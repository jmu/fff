package controllers.admin;

import controllers.admin.routes;
import models.Restaurant;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import secure.SecuredAdmin;
import views.html.admin.restaurant.createForm;
import views.html.admin.restaurant.editForm;
import views.html.admin.restaurant.list;

@Security.Authenticated(SecuredAdmin.class)
public class Restaurants extends Controller {
	public static Result GO_HOME = redirect(routes.Restaurants.list(0, "name",
			"asc", ""));

	public static Result list(int page, String sortBy, String order,
			String filter) {
		return ok(list.render(Restaurant.page(page, 10, sortBy, order, filter),
				sortBy, order, filter));
	}

	public static Result edit(Long id) {
		Form<Restaurant> restaurantForm = form(Restaurant.class).fill(
				Restaurant.find.byId(id));
		return ok(editForm.render(id, restaurantForm));
	}

	public static Result update(Long id) {
		Form<Restaurant> restaurantForm = form(Restaurant.class)
				.bindFromRequest();
		if (restaurantForm.hasErrors()) {
			return badRequest(editForm.render(id, restaurantForm));
		}
		Restaurant r = restaurantForm.get();
		if (r.isAvailable == null) {
			r.isAvailable = false;
		}
		restaurantForm.get().update(id);
		flash("success", "Restaurant " + restaurantForm.get().name
				+ " has been updated");
		return GO_HOME;
	}

	public static Result create() {
		Form<Restaurant> restaurantForm = form(Restaurant.class);
		return ok(createForm.render(restaurantForm));
	}

	public static Result save() {
		Form<Restaurant> restaurantForm = form(Restaurant.class)
				.bindFromRequest();
		if (restaurantForm.hasErrors()) {
			return badRequest(createForm.render(restaurantForm));
		}
		Restaurant r = restaurantForm.get();
		if (r.isAvailable == null) {
			r.isAvailable = false;
		}
		r.save();
		flash("success", "Restaurant " + restaurantForm.get().name
				+ " has been created");
		return GO_HOME;
	}

	public static Result delete(Long id) {
		Restaurant.find.ref(id).delete();
		flash("success", "Restaurant has been deleted");
		return GO_HOME;
	}

}
