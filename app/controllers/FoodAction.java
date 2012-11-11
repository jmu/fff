package controllers;

import java.util.Map;

import models.Food;
import models.Restaurant;
import models.User;

import org.codehaus.jackson.JsonNode;

import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import secure.Secured;
import views.html.projects.foodView;

@Security.Authenticated(Secured.class)
public class FoodAction extends Controller {

	public static Result list(Long restaurantId) {

		Map<String, String> restOptions = Restaurant.options();
		Long id = restaurantId;
		if (!restOptions.containsKey(String.valueOf(restaurantId))) {
			for (String key : restOptions.keySet()) {
				id = Long.valueOf(key);
				break;
			}

		}

		return ok(foodView.render(id, restOptions,
				Food.byRestaurant(0, 99, "name", "", id),
				User.findByEmail(session(Application.USER_ID))));
	}

	public static Result getFoodJson(Long id) {
		Food food = Food.find.ref(id);
		JsonNode json = null;
		try {
			json = Json.toJson(food);
		} catch (Exception e) {
			Logger.error("get Food by Id" + id, e);
			return badRequest();
		}
		return ok(json);
	}
}
