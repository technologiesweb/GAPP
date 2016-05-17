package dao;

import java.util.List;
import beans.T_member_p;

public interface T_member_pDao {
    void creer(T_member_p t_member_p) throws DAOException;
    
    T_member_p trouverLoginAndPassword(String MP_Login, String MP_Password) throws DAOException;

    List<T_member_p> lister() throws DAOException;

    void supprimer(T_member_p t_member_p) throws DAOException;
}
