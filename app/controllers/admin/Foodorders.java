package controllers.admin;

import java.util.Date;

import models.Foodorder;
import models.Menu;
import play.data.Form;
import play.db.ebean.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.foodorder.createForm;
import views.html.admin.foodorder.editForm;
import views.html.admin.foodorder.list;

public class Foodorders extends Controller {
    public static Result GO_HOME = redirect(routes.Foodorders.list(0, "user_name",
                "asc", ""));

    public static Result list(int page, String sortBy, String order,
            String filter) {
        return ok(list.render(Foodorder.page(page, 10, sortBy, order, filter),
                    sortBy, order, filter));
    }

    public static Result edit(Long id) {
        Form<Foodorder> foodorderForm = form(Foodorder.class).fill(
                Foodorder.find.byId(id));
        return ok(editForm.render(id, foodorderForm));
    }

    public static Result update(Long id) {
        Form<Foodorder> foodorderForm = form(Foodorder.class)
            .bindFromRequest();
        if (foodorderForm.hasErrors()) {
            return badRequest(editForm.render(id, foodorderForm));
        }
        foodorderForm.get().update(id);
        flash("success", "Foodorder " + " has been updated");
        return GO_HOME;
    }

    public static Result create() {
        Form<Foodorder> foodorderForm = form(Foodorder.class);
        Foodorder fo = new Foodorder();
        fo.quantity = 1L;
        fo.discount = 0D;
        fo.price = 0D;
       
        return ok(createForm.render(foodorderForm.fill(fo)));
    }
    
    @Transactional
    public static Result save() {
        Form<Foodorder> foodorderForm = form(Foodorder.class)
            .bindFromRequest();
        if (foodorderForm.hasErrors()) {
            return badRequest(createForm.render(foodorderForm));
        }
        Date creatAt = new Date();
        Foodorder r = foodorderForm.get();
        if (r.menu != null && r.menu.id == null){
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

    public static Result delete(Long id) {
        Foodorder.find.ref(id).delete();
        flash("success", "Foodorder has been deleted");
        return GO_HOME;
    }

}
