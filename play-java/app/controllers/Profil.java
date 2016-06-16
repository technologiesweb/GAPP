package controllers;

import play.mvc.*;
import play.data.Form;
import views.html.*;
import models.*;

public class Profil extends Controller {

    @Security.Authenticated(Secured.class)
    public Result profil(String name) {
        return ok(Mon_Profil.render(
            name, 
            "Mon Profil"
        ));  
    }

}
