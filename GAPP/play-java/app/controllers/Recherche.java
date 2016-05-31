package controllers;

import play.mvc.*;
import play.data.Form;
import views.html.*;
import models.*;

public class Recherche extends Controller {

	public Result recherche() {
    	return ok(Rechercher.render(
    	    T_member_p.find.byId(request().username()), 
            "Recherche"
    	    ));
    }

}
