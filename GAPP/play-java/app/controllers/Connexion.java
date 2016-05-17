package controllers;

import play.mvc.*;
import play.data.Form;
import views.html.*;

public class Connexion extends Controller {

    public Result connex() {
        return ok(connexion.render());     
    }

}
