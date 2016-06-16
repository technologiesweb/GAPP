package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import models.*;
import views.html.*;

public class ConnectionController extends Controller {

    public Result login() {
        return ok(
            login.render(form(Login.class))
        );
    }
    
    public static class Login {
        public String login;
        public String password;
        
        public String validate() {
            if (T_member_p.authenticate(login, password) == null) {
                return "Login ou mot de passe invalide ";
            } 
            return null;
        }
    }
    
    public Result authenticate() {
        Form<Login> loginForm = form(Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        } else {
            session().clear();
            session("login", loginForm.get().login);
            return redirect(
                routes.SkillsMatrixController.seeSkillsMatrix()
            );
        }
    }
    
    
    public Result logout() {
        session().clear();
        flash("Vous avez été déconnecté");
        return redirect(
            routes.ConnectionController.login()
        );
    }
    
    
}