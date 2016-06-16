package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import models.*;
import views.html.*;

public class NoteController extends Controller {

    @Security.Authenticated(Secured.class)
    public Result addNote() {
        return ok(
            addNotes.render(form(AddNotes.class))
        );
    }
    
    public static class AddNotes {
        public String name;
        public int value;
        
        public String validate() {
            if (T_note_p.create(value, name) == null) {
                return "Note invalide";
            }
            return null;
        }
    }
    
    public Result create() {
        Form<AddNotes> noteForm = form(AddNotes.class).bindFromRequest();
        if (noteForm.hasErrors()) {
            return badRequest(addNotes.render(noteForm));
        } else {
            return redirect(
                routes.NoteController.seeNotes()
            );
        }
    }
    
    
        // Voir les notes
    
    @Security.Authenticated(Secured.class)
    public Result seeNotes() {
        return ok(showNotes.render( 
            T_note_p.find.all()
        )); 
    }
    
    
    // Exporter les notes
     @Security.Authenticated(Secured.class)
    public Result exportNotes() {
        return ok(exportNotes.render( 
            "Exporter les notes",
            T_mem_status_t.searchStatusByMember(T_member_p.searchIdByLogin(session().get("login"))),
            T_member_p.searchNameByLogin(session().get("login"))
        )); 
    }

}

