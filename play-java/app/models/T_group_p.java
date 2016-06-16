package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;

@Entity
public class T_group_p extends Model {

    @Id
    public Integer GP_id;
    public Integer GP_id_student;
    public Integer GP_type;
    
    public T_group_p(Integer GP_id_student, Integer GP_type) {
      this.GP_id_student = GP_id_student;
      this.GP_type = GP_type;
    }
    
     public static Finder<Integer, T_group_p> find = new Finder(Integer.class, T_group_p.class);

    
    public static T_group_p create(Integer GP_id_student, Integer GP_type) {
        T_group_p t_group_p = new T_group_p(GP_id_student, GP_type);
        t_group_p.save();
        return t_group_p;
    }
    
    public static List<String> searchGroups() {
       List<String> groups  = new ArrayList<String>();
        for(T_group_p t_group_p: T_group_p.find.select("GP_type").findList()) {
            for(T_group_t t_group_t: T_group_t.find.select("GT_id").findList()) {
                if(t_group_p.GP_type == t_group_t.GT_id) {
                    groups.add(t_group_t.GT_name);
                }
            }
                
        }
        return groups;
    }

}

