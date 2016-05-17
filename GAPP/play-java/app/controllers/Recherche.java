package controllers;

import play.mvc.*;
import play.data.Form;
import views.html.*;

public class Recherche extends Controller {

	public Result recherche() {
    	return ok(Rechercher.render());
    }

}
