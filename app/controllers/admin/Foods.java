package controllers.admin;

import models.Food;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import secure.SecuredAdmin;
import views.html.admin.food.createForm;
import views.html.admin.food.editForm;
import views.html.admin.food.list;

@Security.Authenticated(SecuredAdmin.class)
public class Foods extends Controller {
	public static Result GO_HOME = redirect(routes.Foods.list(0, "name",
			"asc", ""));

	public static Result list(int page, String sortBy, String order,
			String filter) {
		return ok(list.render(Food.page(page, 10, sortBy, order, filter),
				sortBy, order, filter));
	}

	public static Result edit(Long id) {
		Form<Food> foodForm = new Form(Food.class).fill(
				Food.find.byId(id));
		return ok(editForm.render(id, foodForm));
	}

	public static Result update(Long id) {
		Form<Food> foodForm = new Form(Food.class)
				.bindFromRequest();
		if (foodForm.hasErrors()) {
			return badRequest(editForm.render(id, foodForm));
		}
		Food r = foodForm.get();
		r.update(id);
		flash("success", "Food " + foodForm.get().name
				+ " has been updated");
		return GO_HOME;
	}

	public static Result create() {
		Form<Food> foodForm = new Form(Food.class);
		return ok(createForm.render(foodForm));
	}

	public static Result save() {
		Form<Food> foodForm = new Form(Food.class)
				.bindFromRequest();
		if (foodForm.hasErrors()) {
			return badRequest(createForm.render(foodForm));
		}
		Food r = foodForm.get();
		r.save();
		flash("success", "Food " + foodForm.get().name
				+ " has been created");
		return GO_HOME;
	}

	public static Result delete(Long id) {
		Food.find.ref(id).delete();
		flash("success", "Food has been deleted");
		return GO_HOME;
	}
	

}
