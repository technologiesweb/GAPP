package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;

@Entity
public class T_course_l extends Model {

    @Id
    public Integer CL_id;
    public Integer CL_id_course;
    public Integer CL_id_group;
    
    public T_course_l(Integer CL_id_course, Integer CL_id_group) {
      this.CL_id_course = CL_id_course;
      this.CL_id_group = CL_id_group;

    }
    
     public static Finder<Integer, T_course_l> find = new Finder(Integer.class, T_course_l.class);

    
    public static T_course_l create(Integer CL_id_course, Integer CL_id_group) {
        T_course_l t_course_l = new T_course_l(CL_id_course, CL_id_group);
        t_course_l.save();
        return t_course_l;
    }
    
}

