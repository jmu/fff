package controllers.admin;

import controllers.admin.routes;
import models.Menu;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import secure.Secured;
import views.html.admin.menu.createForm;
import views.html.admin.menu.editForm;
import views.html.admin.menu.list;

@Security.Authenticated(Secured.class)
public class Menus extends Controller {
	public static Result GO_HOME = redirect(routes.Menus.list(0, "name",
			"asc", ""));


	public static Result list(int page, String sortBy, String order,
			String filter) {
		return ok(list.render(Menu.page(page, 10, sortBy, order, filter),
				sortBy, order, filter));
	}

	public static Result edit(Long id) {
		Form<Menu> menuForm = form(Menu.class).fill(
				Menu.find.byId(id));
		return ok(editForm.render(id, menuForm));
	}

	public static Result update(Long id) {
		Form<Menu> menuForm = form(Menu.class)
				.bindFromRequest();
		if (menuForm.hasErrors()) {
			return badRequest(editForm.render(id, menuForm));
		}
        Menu menu = menuForm.get();
        if (!menu.deal) {
            menu.closedAt = null;
        } 
        
        if (menu.deal && menu.closedAt == null) {
            menu.closedAt = new java.util.Date();
        }
        
		menu.update(id);
		flash("success", "Menu " + menuForm.get().name
				+ " has been updated");
		return GO_HOME;
	}

	public static Result create() {
		Form<Menu> menuForm = form(Menu.class);
		return ok(createForm.render(menuForm));
	}

	public static Result save() {
		Form<Menu> menuForm = form(Menu.class)
				.bindFromRequest();
		if (menuForm.hasErrors()) {
			return badRequest(createForm.render(menuForm));
		}
        Menu newMenu = menuForm.get();
        newMenu.createdAt = new java.util.Date();
		newMenu.save();
		flash("success", "Menu " + menuForm.get().name
				+ " has been created");
		return GO_HOME;
	}

	public static Result delete(Long id) {
		Menu.find.ref(id).delete();
		flash("success", "Menu has been deleted");
		return GO_HOME;
	}

}
