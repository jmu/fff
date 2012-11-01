package secure;

import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;
import controllers.Application;
import controllers.routes;
import models.Role;

public class SecuredAdmin extends Security.Authenticator {
    
    @Override
    public String getUsername(Context ctx) {
        if (Role.ROLE_ADMIN.equals(ctx.session().get(Application.USER_ROLE))) {
            return ctx.session().get(Application.USER_ID);
        }
        //return null fro not authenticated.
        return null;
    }
    
    @Override
    public Result onUnauthorized(Context ctx) {
        return redirect(routes.Application.login());
    }
    
    // Access rights
   /* 
    public static boolean isMemberOf(Long project) {
        return Project.isMember(
            project,
            Context.current().request().username()
        );
    }
    
    public static boolean isOwnerOf(Long task) {
        return Task.isOwner(
            task,
            Context.current().request().username()
        );
    }*/
    
}
