package controllers.admin;

import controllers.admin.routes;
import models.Role;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import secure.SecuredAdmin;
import views.html.admin.role.createForm;
import views.html.admin.role.editForm;
import views.html.admin.role.list;

@Security.Authenticated(SecuredAdmin.class)
public class Roles extends Controller {
	public static Result GO_HOME = redirect(routes.Roles.list(0, "name",
			"asc", ""));

	public static Result list(int page, String sortBy, String order,
			String filter) {
		return ok(list.render(Role.page(page, 10, sortBy, order, filter),
				sortBy, order, filter));
	}

	public static Result edit(Long id) {
		Form<Role> roleForm = new Form(Role.class).fill(
				Role.find.byId(id));
		return ok(editForm.render(id, roleForm));
	}

	public static Result update(Long id) {
		Form<Role> roleForm = new Form(Role.class)
				.bindFromRequest();
		if (roleForm.hasErrors()) {
			return badRequest(editForm.render(id, roleForm));
		}
		roleForm.get().update(id);
		flash("success", "Role " + roleForm.get().name
				+ " has been updated");
		return GO_HOME;
	}

	public static Result create() {
		Form<Role> roleForm = new Form(Role.class);
		return ok(createForm.render(roleForm));
	}

	public static Result save() {
		Form<Role> roleForm = new Form(Role.class)
				.bindFromRequest();
		if (roleForm.hasErrors()) {
			return badRequest(createForm.render(roleForm));
		}
		Role r = roleForm.get();
		
		r.save();
		flash("success", "Role " + roleForm.get().name
				+ " has been created");
		return GO_HOME;
	}

	public static Result delete(Long id) {
		Role.find.ref(id).delete();
		flash("success", "Role has been deleted");
		return GO_HOME;
	}

}
