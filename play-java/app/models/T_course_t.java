package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;

@Entity
public class T_course_t extends Model {

    @Id
    public Integer CT_id;
    public String CT_name;
    
    public T_course_t(Integer CT_id, String CT_name) {
        this.CT_id = CT_id;
        this.CT_name = CT_name;

    }
    
     public static Finder<Integer, T_course_t> find = new Finder(Integer.class, T_course_t.class);

    
    public static T_course_t create(Integer CT_id, String CT_name) {
        T_course_t t_course_t = new T_course_t(CT_id, CT_name);
        t_course_t.save();
        return t_course_t;
    }
    
}

