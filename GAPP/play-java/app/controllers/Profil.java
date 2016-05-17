package controllers;

import play.mvc.*;
import play.data.Form;
import views.html.*;

public class Profil extends Controller {

    public Result profil() {
        return ok(Mon_Profil.render());     
    }

}
