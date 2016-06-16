package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;

@Entity
public class T_note_p extends Model {

    @Id
    public int NP_id;
    public int NP_value;
    public String NP_name;
  
    public T_note_p(int NP_value, String NP_name) {
        this.NP_value = NP_value;
        this.NP_name = NP_name;
    }

    public static Finder<Long, T_note_p> find = new Finder(Long.class, T_note_p.class);

    public static T_note_p create(int NP_value, String NP_name) {
        T_note_p t_note_p = new T_note_p(NP_value, NP_name);
        t_note_p.save();
        return t_note_p;
    }

}
