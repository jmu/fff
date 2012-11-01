package controllers.admin;

import models.Usergroup;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import secure.SecuredAdmin;
import views.html.admin.usergroup.createForm;
import views.html.admin.usergroup.editForm;
import views.html.admin.usergroup.list;

@Security.Authenticated(SecuredAdmin.class)
public class Usergroups extends Controller {
	public static Result GO_HOME = redirect(routes.Usergroups.list(0, "name",
			"asc", ""));

	public static Result list(int page, String sortBy, String order,
			String filter) {
		return ok(list.render(Usergroup.page(page, 10, sortBy, order, filter),
				sortBy, order, filter));
	}

	public static Result edit(Long id) {
		Form<Usergroup> usergroupForm = form(Usergroup.class).fill(
				Usergroup.find.byId(id));
		return ok(editForm.render(id, usergroupForm));
	}

	public static Result update(Long id) {
		Form<Usergroup> usergroupForm = form(Usergroup.class)
				.bindFromRequest();
		if (usergroupForm.hasErrors()) {
			return badRequest(editForm.render(id, usergroupForm));
		}
		usergroupForm.get().update(id);
		flash("success", "Usergroup " + usergroupForm.get().name
				+ " has been updated");
		return GO_HOME;
	}

	public static Result create() {
		Form<Usergroup> usergroupForm = form(Usergroup.class);
		return ok(createForm.render(usergroupForm));
	}

	public static Result save() {
		Form<Usergroup> usergroupForm = form(Usergroup.class)
				.bindFromRequest();
		if (usergroupForm.hasErrors()) {
			return badRequest(createForm.render(usergroupForm));
		}
		Usergroup r = usergroupForm.get();
		
		r.save();
		flash("success", "Usergroup " + usergroupForm.get().name
				+ " has been created");
		return GO_HOME;
	}

	public static Result delete(Long id) {
		Usergroup.find.ref(id).delete();
		flash("success", "Usergroup has been deleted");
		return GO_HOME;
	}
	
}
