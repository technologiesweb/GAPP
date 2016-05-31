package models;

import java.util.*;
import javax.persistence.*;
import com.avaje.ebean.Model;

@Entity
public class T_member_p extends Model {

    @Id
    public String MP_Login;
    public String MP_Password;
    
    public T_member_p(String MP_Login, String MP_Password) {
      this.MP_Login = MP_Login;
      this.MP_Password = MP_Password;
    }
    
    public static Finder<String, T_member_p> find = new Finder<String,T_member_p>(String.class, T_member_p.class);
    
    public static T_member_p authenticate(String MP_Login, String MP_Password) {
        return find.where().eq("MP_Login", MP_Login)
            .eq("MP_Password", MP_Password).findUnique();
    }
    
    
    public static T_member_p create(String MP_Login, String MP_Password) {
        T_member_p t_member_p = new T_member_p(MP_Login, MP_Password);
        t_member_p.save();
        return t_member_p;
    }

}

