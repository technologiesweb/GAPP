package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;

@Entity
public class T_group_t extends Model {

    @Id
    public Integer GT_id;
    public String GT_name;
    
    public T_group_t(String GT_name) {
      this.GT_name = GT_name;
    }
    
     public static Finder<Integer, T_group_t> find = new Finder(Integer.class, T_group_t.class);

    
    public static T_group_t create(String GT_name) {
        T_group_t t_group_t = new T_group_t(GT_name);
        t_group_t.save();
        return t_group_t;
    }

}

