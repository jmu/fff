package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import models.Food;
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
	private static SimpleDateFormat sdf = new SimpleDateFormat("ddHHmm");
	public static Result GO_HOME = redirect(routes.Application.login());

	public static Result create(Long menuId, Long foodId) {
		Foodorder fo = new Foodorder();
		Menu menu = new Menu();
		menu.id = menuId;
		fo.menu = menu;
		fo.user = User.findByEmail(session(Application.USER_ID));
		Food food = new Food();
		food.id = foodId;
		fo.food = food;
		fo.quantity = 1L;
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
		final Date createAt = new Date();
		Foodorder r = foodorderForm.get();
		if (user == null || user.id != r.user.id) {
			Logger.error("bad user request");
			return badRequest(createForm.render(foodorderForm));
		}
		if (r.menu != null && (r.menu.id == null || r.menu.id < 1)) {
			Menu menu = new Menu();
			// menu.generateMenuCode(null, 2);
			menu.name = user.userName + " Menu"+ sdf.format(createAt);
//			int count = 1;
//			Map<String, String> menuOptions = Menu.todayOptions();
//			while (menuOptions.containsValue(menu.name + count)) {
//				count++;
//			}
//
//			menu.name = menu.name + count;
			menu.createdAt = createAt;
			menu.dateFor = createAt;
			menu.isAvailable = true;
			menu.deal = false;
			menu.save();
			r.menu = menu;
		}
		r.orderAt = createAt;
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

	public static Result toggleLock(Long id) {
		Menu menu = Menu.find.ref(id);
		if (menu == null) {
			return badRequest();
		}
		menu.isAvailable = !menu.isAvailable;
		menu.save();
		flash("success", "Menu has been updated");
		return GO_HOME;
	}

	@Transactional
	public static Result deal(Long menuid) {
		Menu menu = Menu.find.byId(menuid);
		if (menu == null) {
			return badRequest();
		}
		List<Foodorder> orderList = Foodorder.getAllFoodorders(menuid);
		for (Foodorder order : orderList) {
			order.deal = true;
			order.update();
		}
		menu.deal = true;
		menu.closedAt = new Date();
		menu.update();
		flash("success", "Menu has been change to dealed");
		return GO_HOME;
	}
}
