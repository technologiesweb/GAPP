package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlUpdate;


@Entity
public class T_competence_t extends Model {

    @Id
    public Integer CT_id;
    public String CT_name;
    public Integer CT_coeff;
    public String CT_description;

    
    public T_competence_t(String CT_name, Integer CT_coeff, String CT_description) {
        this.CT_name = CT_name;
        this.CT_coeff = CT_coeff;
        this.CT_description = CT_description;
    }
    
     public static Finder<Integer, T_competence_t> find = new Finder(Integer.class, T_competence_t.class);

    
    public static T_competence_t create(String CT_name, Integer CT_coeff, String CT_description) {
        T_competence_t t_competence_t = new T_competence_t(CT_name, CT_coeff, CT_description);
        t_competence_t.save();
        return t_competence_t;

    }
    
    
    public static String searchNameById(Integer CT_id) {
       return find.where().eq("CT_id", CT_id).findUnique().CT_name;
    }
    

    public static int delete(Integer CT_id) {
        SqlUpdate familySkillsDelete = Ebean.createSqlUpdate("DELETE FROM t_competence_t WHERE CT_id = :CT_id");
        familySkillsDelete.setParameter("CT_id", CT_id);
        int deletedCount = familySkillsDelete.execute(); 
        return deletedCount;
    }
    
    public static boolean update(Integer CT_id, String CT_name, Integer CT_coeff, String CT_description) {
        SqlUpdate familySkillsUpdate = Ebean.createSqlUpdate("UPDATE t_competence_t SET CT_name = :CT_name, CT_coeff = :CT_coeff, CT_description = :CT_description WHERE CT_id = :CT_id");
        familySkillsUpdate.setParameter("CT_name", CT_name);
        familySkillsUpdate.setParameter("CT_coeff", CT_coeff);
        familySkillsUpdate.setParameter("CT_description", CT_description);
        familySkillsUpdate.setParameter("CT_id", CT_id);
        int updatedCount = familySkillsUpdate.execute(); 
        if(updatedCount != 0) {
            return true;
        } else {
            return false;
        }
    }

        public static List<Integer> searchFamiliesSkills() {
            List<Integer> familiesSkills  = new ArrayList<Integer>();
            for(T_competence_t t_competence_t: T_competence_t.find.select("CT_id").findList()) {
                familiesSkills.add(t_competence_t.CT_id);
            }
            return familiesSkills;
    }
    
    public static List<Boolean> skillsInFamily() {
        List<Boolean> skillsInFamilyList  = new ArrayList<Boolean>();
        boolean skillsInFamilyBoolean = false;
        for(T_competence_t t_competence_t: T_competence_t.find.select("CT_id").findList()){
            for(T_competence_p t_competence_p: T_competence_p.find.select("CP_type").findList()){
                if(t_competence_t.CT_id == t_competence_p.CP_type) {
                    skillsInFamilyBoolean = true;
                }
            }
            if (skillsInFamilyBoolean) {
                skillsInFamilyList.add(true);
                skillsInFamilyBoolean = false;
            } else {
                skillsInFamilyList.add(false);
            }
        }
        return skillsInFamilyList;
    }


}

