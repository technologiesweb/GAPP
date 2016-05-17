package dao;

import static dao.DAOUtilitaire.fermeturesSilencieuses;
import static dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.T_courses_t;

public class T_courses_tDaoImpl implements T_courses_tDao {

    private static final String SQL_SELECT        = "SELECT CT_id, CT_name, CT_description FROM t_courses_t ORDER BY CT_id";
    private static final String SQL_SELECT_PAR_ID = "SELECT CT_id, CT_name, CT_description FROM t_courses_t WHERE CT_id = ?";
    private static final String SQL_INSERT        = "INSERT INTO t_courses_t (CT_id, CT_name, CT_description) VALUES (?, ?, ?)";
    private static final String SQL_DELETE_PAR_ID = "DELETE FROM t_courses_t WHERE CT_id = ?";

    private DAOFactory          daoFactory;

    T_courses_tDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /* Implémentation de la méthode définie dans l'interface CommandeDao */
    @Override
    public T_courses_t trouver(Long CT_id) throws DAOException {
        return trouver( SQL_SELECT_PAR_ID, CT_id);
    }

    /* Implémentation de la méthode définie dans l'interface CommandeDao */
    @Override
    public void creer(T_courses_t t_courses_t) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true,
            		t_courses_t.getCT_id(), t_courses_t.getCT_name(), t_courses_t.getCT_description());
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création de la commande, aucune ligne ajoutée dans la table." );
            }
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
            	t_courses_t.setCT_id( valeursAutoGenerees.getLong(1));
            } else {
                throw new DAOException( "Échec de la création de la commande en base, aucun ID auto-généré retourné." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
    }

    /* Implémentation de la méthode définie dans l'interface ClientDao */
    @Override
    public List<T_courses_t> lister() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<T_courses_t> t_coursess_t = new ArrayList<T_courses_t>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement( SQL_SELECT );
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
            	t_coursess_t.add(map( resultSet ));
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connection );
        }

        return t_coursess_t;
    }

    /* Implémentation de la méthode définie dans l'interface CommandeDao */
    @Override
    public void supprimer( T_courses_t t_courses_t) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE_PAR_ID, true, t_courses_t.getCT_id() );
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la suppression de la commande, aucune ligne supprimée de la table." );
            } else {
            	t_courses_t.setCT_id( null );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }
    }

    /*
     * Méthode générique utilisée pour retourner une commande depuis la base de
     * données, correspondant à la requête SQL donnée prenant en paramètres les
     * objets passés en argument.
     */
    private T_courses_t trouver( String sql, Object... objets ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        T_courses_t t_courses_t = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            /*
             * Préparation de la requête avec les objets passés en arguments
             * (ici, uniquement un id) et exécution.
             */
            preparedStatement = initialisationRequetePreparee( connexion, sql, false, objets );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données retournée dans le ResultSet */
            if ( resultSet.next() ) {
            	t_courses_t = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return t_courses_t;
    }

    /*
     * Simple méthode utilitaire permettant de faire la correspondance (le
     * mapping) entre une ligne issue de la table des commandes (un ResultSet)
     * et un bean Commande.
     */
    private T_courses_t map( ResultSet resultSet ) throws SQLException {
    	T_courses_t t_courses_t = new T_courses_t();
    	t_courses_t.setCT_id( resultSet.getLong("CT_id"));
    	t_courses_t.setCT_name( resultSet.getString("CT_name"));
    	t_courses_t.setCT_description(resultSet.getString("CT_description"));
        return t_courses_t;
    }

}