package dao;

import java.util.List;
import beans.T_courses_t;

public interface T_courses_tDao {
    void creer(T_courses_t t_courses_t) throws DAOException;

    T_courses_t trouver(Long CT_id) throws DAOException;
    
    List<T_courses_t> lister() throws DAOException;

    void supprimer(T_courses_t t_courses_t) throws DAOException;
}
