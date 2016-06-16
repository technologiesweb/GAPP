package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlUpdate;

@Entity
public class T_mem_status_t extends Model {

    @Id
    public Integer MT_id;
    public Integer MT_id_status;
    public Integer MT_id_member;
  
    public T_mem_status_t(Integer MT_id_status, Integer MT_id_member) {
        this.MT_id_status = MT_id_status;
        this.MT_id_member = MT_id_member;
    }

    public static Finder<Integer, T_mem_status_t> find = new Finder(Integer.class, T_mem_status_t.class);
    
     public static T_mem_status_t create(Integer MT_id_status, Integer MT_id_member) {
        T_mem_status_t t_mem_status_t = new T_mem_status_t(MT_id_status, MT_id_member);
        t_mem_status_t.save();
        return t_mem_status_t;
    }
    
    public static Integer searchStatusByMember(Integer memberId) {
        T_mem_status_t t_mem_status_t = find.where().eq("MT_id_member", memberId).findUnique();
        return t_mem_status_t.MT_id_status;
    }
    
}

