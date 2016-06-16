package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import models.*;
import views.html.*;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

public class CourseController extends Controller {
    
    // Ajouter un cours
    
     @Security.Authenticated(Secured.class)
	public Result addCourse() {
		return ok(
		addCourses.render(
		    "Ajouter une deadline",
            T_mem_status_t.searchStatusByMember(T_member_p.searchIdByLogin(session().get("login"))),
            T_member_p.searchNameByLogin(session().get("login")),
		    form(AddCourse.class), 
		    T_course_t.find.all(),
		    T_group_t.find.all()
		));
	}
	
	public static class AddCourse {
	    public Integer type;
        public String name;
        public String description;
        public String ending_date;
        public Integer group;
        
        public String validate() {
        try {
            Date ending_date1 = new SimpleDateFormat("yyyy-MM-dd").parse(ending_date);
            java.sql.Date ending_date2 = new java.sql.Date(ending_date1.getTime());
            
            if (T_course_p.create(name, description, ending_date2, type, group) == null) {
                return "course invalide";
            }
            return null;
            
        } catch(ParseException pe) {
                return ("ERROR: Cannot parse \"" + pe + "\"");
        }
            
        }
        
	}
	
	public Result create() {
		Form<AddCourse> courseForm = form(AddCourse.class).bindFromRequest();
		if (courseForm.hasErrors()) {
		    return badRequest(addCourses.render(
		        "Ajouter une deadline",
                T_mem_status_t.searchStatusByMember(T_member_p.searchIdByLogin(session().get("login"))),
                T_member_p.searchNameByLogin(session().get("login")),
		        courseForm, 
		        T_course_t.find.all(),
		        T_group_t.find.all()
		    ));
		} else {
		    return redirect(
		        routes.CourseController.seeCourses()
		    );
		}
	}
	
	
	// Voir les cours
	
	 @Security.Authenticated(Secured.class)
    public Result seeCourses() {
        return ok(showCourses.render(
            "Voir les deadlines",
            T_mem_status_t.searchStatusByMember(T_member_p.searchIdByLogin(session().get("login"))),
            T_member_p.searchNameByLogin(session().get("login")),
            T_course_p.find.all(),
            T_course_t.find.all(),
            T_group_t.find.all(),
            T_course_p.dates()
        )); 
    }

}