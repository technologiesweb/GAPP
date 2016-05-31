package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;

@Entity
public class T_competence_p extends Model {

    @Id
    public int CP_id;
    public String CP_name;
    public int CP_parent_id;
    public String CP_description;
    public int CP_coeff;
  
    public T_competence_p(int CP_id, String CP_name, int CP_parent_id, String CP_description, int CP_coeff) {
        this.CP_id = CP_id;
        this.CP_name = CP_name;
        this.CP_parent_id = CP_parent_id;
        this.CP_description = CP_description;
        this.CP_coeff = CP_coeff;
    }

    public static Finder<Long, T_competence_p> find = new Finder(Long.class, T_competence_p.class);

    public static T_competence_p create(int CP_id, String CP_name, int CP_parent_id, String CP_description, int CP_coeff) {
        T_competence_p t_competence_p = new T_competence_p(CP_id, CP_name, CP_parent_id, CP_description, CP_coeff);
        t_competence_p.save();
        return t_competence_p;
    }

}
