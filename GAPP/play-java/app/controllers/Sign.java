package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import models.*;
import views.html.*;

public class Sign extends Controller {

    public Result signIn() {
        return ok(
            sign.render(form(SignIn.class))
        );
    }
    
    public static class SignIn {
        public String login;
        public String password;
        
        public String validate() {
            if (T_member_p.create(login, password) == null) {
                return "Login ou mot de passe invalide";
            }
            return null;
        }
    }
    
    public Result create() {
        Form<SignIn> signForm = form(SignIn.class).bindFromRequest();
        if (signForm.hasErrors()) {
            return badRequest(sign.render(signForm));
        } else {
            return redirect(
                routes.Profil.profil()
            );
        }
    }
    

}