package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import models.*;
import views.html.*;

public class SearchController extends Controller {
    
      @Security.Authenticated(Secured.class)
	public Result search() {
    	return ok(
    	    searchStudentAndGroup.render(form(SearchNameOrGroup.class), T_member_p.search())
    	);
    }
    
    public static class SearchNameOrGroup {
        public String studentOrGroup;
        
        public String validate() {
            if (!T_member_p.verifySearch(studentOrGroup)) {
                return "Recherche invalide !" ;
            }
            
            return null;
        }
    }
    
     @Security.Authenticated(Secured.class)
    public Result redirectPage() {
        Form<SearchNameOrGroup> searchNameOrGroupForm = form(SearchNameOrGroup.class).bindFromRequest();
        if (searchNameOrGroupForm.hasErrors()) {
            return badRequest(searchStudentAndGroup.render(searchNameOrGroupForm, T_member_p.search()));
        } else {
            return redirect(
                routes.Profil.profil(searchNameOrGroupForm.get().studentOrGroup)
            );
        }
    }
    
    //----------------------------------------------------------------------------------------
    
    @Security.Authenticated(Secured.class)
	public Result searchByName() {
	            return ok(
    	            searchStudent.render(
    	                "Recherche un élève",
                        T_mem_status_t.searchStatusByMember(T_member_p.searchIdByLogin(session().get("login"))),
                        T_member_p.searchNameByLogin(session().get("login")),
    	                form(SearchName.class), 
    	                T_member_p.searchByName())
    	        );
    }
    
    public static class SearchName {
        public String student;
    
        public String validate() {
            if(student == null) {
                student = "Béclin Tina";
            }
        
            if (!T_member_p.verifySearchByName(student)) {
                return "Recherche invalide ! : " + student ;
            }
            
            return null;
        }
    }
    
     @Security.Authenticated(Secured.class)
    public Result redirectPage2() {
        Form<SearchName> searchNameForm = form(SearchName.class).bindFromRequest();
        if (searchNameForm.hasErrors()) {
            return badRequest(searchStudent.render(
                "Recherche un élève",
                T_mem_status_t.searchStatusByMember(T_member_p.searchIdByLogin(session().get("login"))),
                T_member_p.searchNameByLogin(session().get("login")),
                searchNameForm, 
                T_member_p.searchByName()));
        } else {
            return redirect(
                routes.Profil.profil(searchNameForm.get().student)
            );
        }
    }
    
}