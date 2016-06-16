package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlUpdate;

@Entity
public class T_competence_l extends Model {

    @Id
    public Integer CL_id;
    public Integer CL_id_student;
    public Integer CL_id_competence;
    public Integer CL_bool_competence;
    public Integer CL_id_tutor;
    public Integer CL_level;
    public String CL_comment;

  
    public T_competence_l(Integer CL_id_student, Integer CL_id_competence, Integer CL_bool_competence, Integer CL_id_tutor, Integer CL_level, String CL_comment) {
        this.CL_id_student = CL_id_student;
        this.CL_id_competence = CL_id_competence;
        this.CL_bool_competence = CL_bool_competence;
        this.CL_id_tutor = CL_id_tutor;
        this.CL_level = CL_level;
        this.CL_comment = CL_comment;
    }

    public static Finder<Integer, T_competence_l> find = new Finder(Integer.class, T_competence_l.class);
    
     public static T_competence_l create(Integer CL_id_student, Integer CL_id_competence, Integer CL_bool_competence, Integer CL_id_tutor, Integer CL_level, String CL_comment) {
        T_competence_l t_competence_l = new T_competence_l(CL_id_student, CL_id_competence, CL_bool_competence, CL_id_tutor, CL_level, CL_comment);
        t_competence_l.save();
        return t_competence_l;
    }
    
    public static boolean updateLevel(Integer CL_id, Integer CL_level) {
        SqlUpdate skillLevelUpdate = Ebean.createSqlUpdate("UPDATE t_competence_l SET CL_level = :CL_level WHERE CL_id = :CL_id");
        skillLevelUpdate.setParameter("CL_level", CL_level);
        skillLevelUpdate.setParameter("CL_id", CL_id);
        int updatedLevelCount = skillLevelUpdate.execute(); 
        if(updatedLevelCount != 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean updateComment(Integer CL_id, String CL_comment) {
        SqlUpdate skillCommentUpdate = Ebean.createSqlUpdate("UPDATE t_competence_l SET CL_comment = :CL_comment WHERE CL_id = :CL_id");
        skillCommentUpdate.setParameter("CL_comment", CL_comment);
        skillCommentUpdate.setParameter("CL_id", CL_id);
        int updatedCommentCount = skillCommentUpdate.execute(); 
        if(updatedCommentCount != 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public static Integer searchLevelForAStudentAndASkill(Integer studentId, Integer skillId, Integer skillBoolean, Integer tutorId) {
         T_competence_l t_competence_l = find.where().eq("CL_id_student", studentId).eq("CL_id_competence", skillId).eq("CL_bool_competence", skillBoolean).eq("CL_id_tutor", tutorId).findUnique();
        return t_competence_l.CL_level;
    }
    
    public static Integer computeMark(Integer studentId) {
        List<Integer> skillsMark  = new ArrayList<Integer>();
        List<Integer> skillsFamiliesMark  = new ArrayList<Integer>();
        Integer meanSkillMark = 0;
        Integer mark = 0;
        
        for(T_competence_t t_competence_t: T_competence_t.find.select("CT_id").findList()) {
            for(T_competence_p t_competence_p: T_competence_p.find.select("CP_type").findList()){
                if(t_competence_t.CT_id == t_competence_p.CP_type) {
                    for(T_competence_l t_competence_l: T_competence_l.find.all()) {
                        if(t_competence_l.CL_bool_competence == 1 && t_competence_l.CL_id_competence == t_competence_p.CP_id && t_competence_l.CL_id_student == studentId) {
                            skillsMark.add(t_competence_l.CL_level);
                        }
                    }
                }
            }
            
            if (skillsMark.size() != 0) { // Si une famille a des compétences
                for(Integer i = 0; i < skillsMark.size(); i++) {
                    meanSkillMark += skillsMark.get(i);
                }
                meanSkillMark = meanSkillMark / skillsMark.size();
                skillsFamiliesMark.add(meanSkillMark);
                meanSkillMark = 0;
                skillsMark.clear();
            } else { // Si elle n'en a pas
                for(T_competence_l t_competence_l: T_competence_l.find.all()) {
                    if(t_competence_l.CL_bool_competence == 0 && t_competence_l.CL_id_competence == t_competence_t.CT_id && t_competence_l.CL_id_student == studentId) {
                            skillsFamiliesMark.add(t_competence_l.CL_level);
                    }
                }
            }
        }
        
        if (skillsFamiliesMark.size() != 0) {
            for(Integer i = 0; i < skillsFamiliesMark.size(); i++) {
                mark += skillsFamiliesMark.get(i);
            }
            mark = 4 * mark / skillsFamiliesMark.size();
            return mark;
        } else {
            return 0;
        }
    }
    
    
    
}

