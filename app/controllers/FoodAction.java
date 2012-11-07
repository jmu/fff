package controllers;

import java.util.Map;

import models.Food;
import models.Restaurant;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.projects.foodView;

//@Security.Authenticated(Secured.class)
public class FoodAction extends Controller {

    public static Result list(Long restaurantId){
       
        Map<String,String> restOptions = Restaurant.options();
        Long id = restaurantId;
        if(!restOptions.containsKey(String.valueOf(restaurantId))){
            for(String key :restOptions.keySet()){
            	id = Long.valueOf(key);
            	break;
            }
        	
        }

        return ok(foodView.render(id, restOptions, Food.byRestaurant(0, 99, "name", "", id)));
    }
}
