package controllers;

import play.mvc.*;
import play.data.Form;
import views.html.*;
import models.*;

public class Deadline extends Controller {

    public Result deadline() {
        return ok(ajouter_une_deadline.render(
            T_member_p.find.byId(request().username()), 
            "Ajout deadline"
            ));     
    }

}
