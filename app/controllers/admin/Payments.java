package controllers.admin;

import controllers.admin.routes;
import models.Payment;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.payment.createForm;
import views.html.admin.payment.editForm;
import views.html.admin.payment.list;

public class Payments extends Controller {
	public static Result GO_HOME = redirect(routes.Payments.list(0, "user_name",
			"asc", ""));

	public static Result list(int page, String sortBy, String order,
			String filter) {
		return ok(list.render(Payment.page(page, 10, sortBy, order, filter),
				sortBy, order, filter));
	}

	public static Result edit(Long id) {
		Form<Payment> paymentForm = form(Payment.class).fill(
				Payment.find.byId(id));
		return ok(editForm.render(id, paymentForm));
	}

	public static Result update(Long id) {
		Form<Payment> paymentForm = form(Payment.class)
				.bindFromRequest();
        if(!paymentForm.hasErrors()) {
            if(paymentForm.get().user.id == null) {
                paymentForm.reject("user.id", "User is Null");
            }
        }
		if (paymentForm.hasErrors()) {
			return badRequest(editForm.render(id, paymentForm));
		}
		paymentForm.get().update(id);
		flash("success", "Payment "  
				+ " has been updated");
		return GO_HOME;
	}

	public static Result create() {
		Form<Payment> paymentForm = form(Payment.class);
		return ok(createForm.render(paymentForm));
	}

	public static Result save() {
		Form<Payment> paymentForm = form(Payment.class)
				.bindFromRequest();
        if(!paymentForm.hasErrors()) {
            if(paymentForm.get().user.id == null) {
                paymentForm.reject("user.id", "User is Null");
            }
        }
		if (paymentForm.hasErrors()) {
			return badRequest(createForm.render(paymentForm));
		}
		Payment r = paymentForm.get();
	    r.createdAt = new java.util.Date();	
		r.save();
		flash("success", "Payment "  
				+ " has been created");
		return GO_HOME;
	}

	public static Result delete(Long id) {
		Payment.find.ref(id).delete();
		flash("success", "Payment has been deleted");
		return GO_HOME;
	}

}
