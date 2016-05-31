package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import models.*;
import views.html.*;

public class Test extends Controller {
    
    public Result index() {
        return ok(seeCompetences.render( 
            T_competence_p.find.all()
        )); 
    }
    

}

