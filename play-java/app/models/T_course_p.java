package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;

import java.text.SimpleDateFormat;
import java.sql.Date;

@Entity
public class T_course_p extends Model {

    @Id
    public Integer CP_id;
    public String CP_name;
    public String CP_description;
    public Date CP_ending_date;
    public Integer CP_type;
    public Integer CP_id_group;
    
    public T_course_p(String CP_name, String CP_description, Date CP_ending_date, Integer CP_type, Integer CP_id_group) {
      this.CP_name = CP_name;
      this.CP_description = CP_description;
      this.CP_ending_date = CP_ending_date;
      this.CP_type = CP_type;
      this.CP_id_group = CP_id_group;

    }
    
     public static Finder<Integer, T_course_p> find = new Finder(Integer.class, T_course_p.class);

    
    public static T_course_p create(String CP_name, String CP_description, Date CP_ending_date, Integer CP_type, Integer CP_id_group) {
        T_course_p t_course_p = new T_course_p(CP_name, CP_description, CP_ending_date, CP_type, CP_id_group);
        t_course_p.save();
        return t_course_p;
    }
    
    public static List<String> dates() {
        List<String> datesList  = new ArrayList<String>();
        for(T_course_p t_course_p: T_course_p.find.select("CP_ending_date").findList()) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String date = sdf.format(t_course_p.CP_ending_date);
              datesList.add(date);
            }
        return datesList;
    }

}

