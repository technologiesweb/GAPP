package controllers;

import play.mvc.*;
import play.data.Form;
import views.html.*;
import models.*;

public class Profil extends Controller {

    public Result profil() {
        return ok(Mon_Profil.render(
            T_member_p.find.byId(request().username()), 
            "Mon Profil"
        ));  
    }

}
