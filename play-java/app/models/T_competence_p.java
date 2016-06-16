package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlUpdate;

@Entity
public class T_competence_p extends Model {

    @Id
    public Integer CP_id;
    public String CP_name;
    public String CP_description;
    public Integer CP_type;
    
  
  
    public T_competence_p(String CP_name, String CP_description, Integer CP_type) {
        this.CP_name = CP_name;
        this.CP_description = CP_description;
        this.CP_type = CP_type;
    }

    public static Finder<Integer, T_competence_p> find = new Finder(Integer.class, T_competence_p.class);
    
     public static T_competence_p create(String CP_name, String CP_description, Integer CP_type) {
        T_competence_p t_competence_p = new T_competence_p(CP_name, CP_description, CP_type);
        t_competence_p.save();
        return t_competence_p;
    }
    
    public static int delete(Integer CP_id) {
        SqlUpdate skillDelete = Ebean.createSqlUpdate("DELETE FROM t_competence_p WHERE CP_id = :CP_id");
        skillDelete.setParameter("CP_id", CP_id);
        int deletedCount = skillDelete.execute(); 
        return deletedCount;
    }
    
    public static int deleteWithFamilies(Integer CP_type) {
        SqlUpdate skillDelete = Ebean.createSqlUpdate("DELETE FROM t_competence_p WHERE CP_type = :CP_type");
        skillDelete.setParameter("CP_type", CP_type);
        int deletedCount = skillDelete.execute(); 
        return deletedCount;
    }
    
    public static boolean update(Integer CP_id, String CP_name, String CP_description) {
        SqlUpdate skillUpdate = Ebean.createSqlUpdate("UPDATE t_competence_p SET CP_name = :CP_name, CP_description = :CP_description WHERE CP_id = :CP_id");
        skillUpdate.setParameter("CP_name", CP_name);
        skillUpdate.setParameter("CP_description", CP_description);
        skillUpdate.setParameter("CP_id", CP_id);
        int updatedCount = skillUpdate.execute(); 
        if(updatedCount != 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public static List<T_competence_p> searchByFamily(Integer CP_type) {
       return find.where().eq("CP_type", CP_type).findList();
    }

    
    public static List<Integer> searchSkills() {
        List<Integer> skills  = new ArrayList<Integer>();
        for(T_competence_p t_competence_p: T_competence_p.find.select("CP_id").findList()) {
                skills.add(t_competence_p.CP_id);
            }
        return skills;
    }
    
    public static T_competence_p searchSkillById(Integer id) {
        return find.select("CP_name").where().eq("CP_id", id).findUnique();
    }
    
    
    public static List<List<String>> searchLevelAllSkills() {
        List<Integer> familiesSkills = T_competence_t.searchFamiliesSkills();
        List<Integer> skills = T_competence_p.searchSkills();
        List<List<String>> levelAllSkills  = new ArrayList<List<String>>();
        for (Integer i = 0; i < familiesSkills.size(); i++) {
            List<String> levelAndIdFamSkills  = new ArrayList<String>();
            levelAndIdFamSkills.add("0");
            levelAndIdFamSkills.add("famCompetence" + familiesSkills.get(i));
            levelAndIdFamSkills.add("famSkillsLevel" + familiesSkills.get(i));
            levelAndIdFamSkills.add("" + familiesSkills.get(i));
            levelAllSkills.add(levelAndIdFamSkills);
            for (Integer j = 0; j < skills.size(); j++) {
                if (familiesSkills.get(i) == skills.get(i)) {
                    List<String> levelAndIdSkills  = new ArrayList<String>();
                    levelAndIdSkills.add("1");
                    levelAndIdFamSkills.add("competence" + skills.get(i));
                    levelAndIdSkills.add("skillLevel" + skills.get(i));
                    levelAndIdSkills.add("" + skills.get(i));
                    levelAllSkills.add(levelAndIdSkills);
                }
            }
        }
        return levelAllSkills;
    }
    
}
