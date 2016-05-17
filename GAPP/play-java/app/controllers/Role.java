package controllers;

import play.mvc.*;
import play.data.Form;
import views.html.*;

public class Role extends Controller {

    public Result index() {
        return ok(Ajout_Competences.render());     
    }
    public Result deadline() {
        return ok(ajouter_une_deadline.render());     
    }
    
    public Result addPerson(){    	
    	//return redirect(routes.HomeController.index());
    	return ok("Got request 2" +  "!");
    	
    }

}
