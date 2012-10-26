package controllers.admin;

import models.Foodtype;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import secure.Secured;
import views.html.admin.foodtype.createForm;
import views.html.admin.foodtype.editForm;
import views.html.admin.foodtype.list;

@Security.Authenticated(Secured.class)
public class Foodtypes extends Controller {
	public static Result GO_HOME = redirect(routes.Foodtypes.list(0, "name",
			"asc", ""));

	public static Result list(int page, String sortBy, String order,
			String filter) {
		return ok(list.render(Foodtype.page(page, 10, sortBy, order, filter),
				sortBy, order, filter));
	}

	public static Result edit(Long id) {
		Form<Foodtype> foodtypeForm = form(Foodtype.class).fill(
				Foodtype.find.byId(id));
		return ok(editForm.render(id, foodtypeForm));
	}

	public static Result update(Long id) {
		Form<Foodtype> foodtypeForm = form(Foodtype.class)
				.bindFromRequest();
		if (foodtypeForm.hasErrors()) {
			return badRequest(editForm.render(id, foodtypeForm));
		}
		foodtypeForm.get().update(id);
		flash("success", "Foodtype " + foodtypeForm.get().name
				+ " has been updated");
		return GO_HOME;
	}

	public static Result create() {
		Form<Foodtype> foodtypeForm = form(Foodtype.class);
		return ok(createForm.render(foodtypeForm));
	}

	public static Result save() {
		Form<Foodtype> foodtypeForm = form(Foodtype.class)
				.bindFromRequest();
		if (foodtypeForm.hasErrors()) {
			return badRequest(createForm.render(foodtypeForm));
		}
		Foodtype r = foodtypeForm.get();
		
		r.save();
		flash("success", "Foodtype " + foodtypeForm.get().name
				+ " has been created");
		return GO_HOME;
	}

	public static Result delete(Long id) {
		Foodtype.find.ref(id).delete();
		flash("success", "Foodtype has been deleted");
		return GO_HOME;
	}
	
}
