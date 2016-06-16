package models;

import java.util.*;
import javax.persistence.*;
import com.avaje.ebean.Model;

@Entity
public class T_member_p extends Model {

    @Id
    public Integer MP_id;
    public String MP_login;
    public String MP_password;
    public String MP_name;
    public String MP_first_name;
    public String MP_mail;
    
    public T_member_p(String MP_login, String MP_password, String MP_name, String MP_first_name, String MP_mail) {
      this.MP_login = MP_login;
      this.MP_password = MP_password;
      this.MP_name = MP_name;
      this.MP_first_name = MP_first_name;
      this.MP_mail = MP_mail;
    }
    
    public static Finder<Integer, T_member_p> find = new Finder<Integer,T_member_p>(Integer.class, T_member_p.class);
    
    public static T_member_p authenticate(String MP_login, String MP_password) {
        return find.where().eq("MP_login", MP_login)
            .eq("MP_password", MP_password).findUnique();
    }
    
    
    public static T_member_p create(String MP_login, String MP_password) {
        T_member_p t_member_p = new T_member_p(MP_login, MP_password, null, null, null);
        t_member_p.save();
        return t_member_p;
    }
    
    public static List<String> searchNames() {
       List<String> names  = new ArrayList<String>();
        for(T_member_p t_member_p: T_member_p.find.select("MP_name").findList()) {
                names.add(t_member_p.MP_name);
            }
        return names;
    }
    
    public static List<String> searchFirstNames() {
       List<String> firstNames  = new ArrayList<String>();
            for(T_member_p t_member_p: T_member_p.find.select("MP_first_name").findList()) {
                firstNames.add(t_member_p.MP_first_name);
            }
        return firstNames;
    }
    
    public static List<String> search() {
        List<String> searchList  = new ArrayList<String>();
        List<String> names  = searchNames();
        List<String> firstNames  = searchFirstNames();
        List<String> groups  = T_group_p.searchGroups();
        for (Integer i = 0; i < names.size(); i++) {
            searchList.add(names.get(i) + " " + firstNames.get(i));
        }
        for (Integer i = 0; i < groups.size(); i++) {
            searchList.add(groups.get(i));
        }
        return searchList;
    }
    
    public static List<String> searchByName() {
        List<String> searchList  = new ArrayList<String>();
        List<String> names  = searchNames();
        List<String> firstNames  = searchFirstNames();
        for (Integer i = 0; i < names.size(); i++) {
            searchList.add(names.get(i) + " " + firstNames.get(i));
        }
        return searchList;
    }
    
    public static boolean verifySearch(String nameOrGroup) {
        List<String> searchList  = search();
        boolean success = false;
        for (Integer i = 0; i < searchList.size(); i++) {
            if (searchList.get(i).equals(nameOrGroup)) {
                success = true;
            }
        }
        return success;
    }
    
    public static boolean verifySearchByName(String name) {
        List<String> searchList  = searchByName();
        boolean success = false;
        for (Integer i = 0; i < searchList.size(); i++) {
            if (searchList.get(i).equals(name)) {
                success = true;
            }
        }
        return success;
    }
    
    public static String searchNameById(Integer id) {
        T_member_p t_member_p = find.where().eq("MP_id", id).findUnique();
        return t_member_p.MP_name + " " + t_member_p.MP_first_name;
    }

    public static String searchNameByLogin(String login) {
        T_member_p t_member_p = find.where().eq("MP_login", login).findUnique();
        return t_member_p.MP_name + " " + t_member_p.MP_first_name;
    }
    
    public static Integer searchIdByLogin(String login) {
        T_member_p t_member_p = find.where().eq("MP_login", login).findUnique();
        return t_member_p.MP_id;
    }
    
    
}

