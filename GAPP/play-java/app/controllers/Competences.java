package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import models.*;
import views.html.*;

public class Competences extends Controller {
    
    public Result addCompetences() {
        return ok(
           Ajout_Competences.render(form(AddCompetence.class))
        );
    }
    
    public static class AddCompetence {
        public int id;
        public String name;
        public int parentId;
        public String description;
        public int coeff;
        
        public String validate() {
            if (T_competence_p.create(id, name, parentId, description, coeff) == null) {
                return "Un champs n'est pas valide";
            }
            return null;
        }
    }
    
    public Result create() {
        Form<AddCompetence> competenceForm = form(AddCompetence.class).bindFromRequest();
        if (competenceForm.hasErrors()) {
            return badRequest(Ajout_Competences.render(competenceForm));
        } else {
            return redirect(
                routes.Profil.profil()
            );  
        }
    }


}

