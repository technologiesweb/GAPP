package controllers;

import play.mvc.*;
import play.data.Form;
import views.html.*;
import models.*;

public class Exporter extends Controller {

    /*public Result index() {
        return ok(Ajout_Competences.render(
            ));  
    }*/
    public Result deadline() {
        return ok(ajouter_une_deadline.render(
            session().get("login"), 
            "Ajout deadline"
            ));     
    }
    
    public Result addPerson(){    	
    	//return redirect(routes.HomeController.index());
    	return ok("Got request 2" +  "!");
    	
    }

}
