package controllers.admin;

import controllers.admin.routes;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import secure.SecuredAdmin;
import views.html.admin.user.createForm;
import views.html.admin.user.editForm;
import views.html.admin.user.list;

@Security.Authenticated(SecuredAdmin.class)
public class Users extends Controller {
    public static Result GO_HOME = redirect(routes.Users.list(0, "userName",
                "asc", ""));


    public static Result list(int page, String sortBy, String order,
            String filter) {
        return ok(list.render(User.page(page, 10, sortBy, order, filter),
                    sortBy, order, filter));
    }

    public static Result edit(Long id) {
        Form<User> userForm = new Form(User.class).fill(
                User.findById.byId(id));
        //clean the sha1 password
        userForm.get().password = null;
        return ok(editForm.render(id, userForm));
    }

    public static Result update(Long id) {
        Form<User> userForm = new Form(User.class)
            .bindFromRequest();
        if(!userForm.hasErrors()) {
            if(userForm.get().role.id == null) {
                userForm.reject("role.id", "Role is Null");
            }
        }
        if (userForm.hasErrors()) {
            return badRequest(editForm.render(id, userForm));
        }
        if (userForm.get().accountLocked == null) {
            userForm.get().accountLocked = false;
        }
        userForm.get().update(id);
        flash("success", "User " + userForm.get().userName
                + " has been updated");
        return GO_HOME;
    }

    public static Result create() {
        Form<User> userForm = new Form(User.class);
        return ok(createForm.render(userForm));
    }

    public static Result save() {
        Form<User> userForm = new Form(User.class)
            .bindFromRequest();
        if(!userForm.hasErrors()) {
            if(userForm.get().role.id == null) {
                userForm.reject("role.id", "Role is Null");
            }
        }
        if (userForm.hasErrors()) {
            return badRequest(createForm.render(userForm));
        }

        User newUser = userForm.get();
        if (newUser.regAt == null) {
            newUser.regAt = new java.util.Date();
        }
        newUser.save();
        flash("success", "User " + userForm.get().userName
                + " has been created");
        return GO_HOME;
    }

    public static Result delete(Long id) {
        User.findById.ref(id).delete();
        flash("success", "User has been deleted");
        return GO_HOME;
    }

}
