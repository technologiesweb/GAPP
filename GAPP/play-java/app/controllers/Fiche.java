package controllers;

import play.mvc.*;
import play.data.Form;
import views.html.*;

public class Fiche extends Controller {

    public Result fiches() {
        return ok(Fiche_personnes.render());     
    }

}
