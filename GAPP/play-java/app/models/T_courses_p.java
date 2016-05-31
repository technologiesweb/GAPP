package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class T_courses_p extends Model {

    @Id
    public int CP_Id;
    public int T_COURSES_T_CT_id;
    
    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date CP_EndingDate = new Date();
    public Date CP_StartingDate = new Date();
    
    public static Finder<Long, T_courses_p> find = new Finder<Long,T_courses_p>(Long.class, T_courses_p.class);
}