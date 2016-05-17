package controllers;

import play.mvc.*;
import play.data.Form;
import views.html.*;

public class Deadline extends Controller {

    public Result deadline() {
        return ok(ajouter_une_deadline.render());     
    }

}
