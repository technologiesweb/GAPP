package dao;

import static dao.DAOUtilitaire.fermeturesSilencieuses;
import static dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.T_member_p;

public class T_member_pDaoImpl implements T_member_pDao {

    private static final String SQL_SELECT           = "SELECT MP_Login, MP_Password FROM t_member_p ORDER BY MP_Login";
    private static final String SQL_SELECT_PAR_LOGIN = "SELECT MP_Login, MP_Password FROM t_member_p WHERE MP_Login = ? AND MP_Password = ?";
    private static final String SQL_INSERT           = "INSERT INTO t_member_p (MP_Login, MP_Password) VALUES (?, ?)";
    private static final String SQL_DELETE_PAR_LOGIN = "DELETE FROM t_member_p WHERE MP_Login = ?";

    private DAOFactory daoFactory;

    T_member_pDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /* Impl�mentation de la m�thode d�finie dans l'interface T_member_pDao */
    public T_member_p trouverLoginAndPassword(String MP_Login, String MP_Password) throws DAOException {
        return trouver(SQL_SELECT_PAR_LOGIN, MP_Login, MP_Password);
    }
    
    /* Impl�mentation de la m�thode d�finie dans l'interface T_member_pDao */
    @Override
    public void creer(T_member_p t_member_p) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true,t_member_p.getMP_Login(), t_member_p.getMP_Password());
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "�chec de la cr�ation du client, aucune ligne ajout�e dans la table." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
    }

    /* Impl�mentation de la m�thode d�finie dans l'interface T_member_pDao */
    @Override
    public List<T_member_p> lister() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<T_member_p> t_members_p = new ArrayList<T_member_p>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement( SQL_SELECT );
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
            	t_members_p.add( map( resultSet ) );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connection );
        }

        return t_members_p;
    }

    /* Impl�mentation de la m�thode d�finie dans l'interface ClientDao */
    @Override
    public void supprimer(T_member_p t_member_p) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_DELETE_PAR_LOGIN, true, t_member_p.getMP_Login() );
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "�chec de la suppression du client, aucune ligne supprim�e de la table." );
            } else {
            	t_member_p.setMP_Login( null );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }
    }

    /*
     * M�thode g�n�rique utilis�e pour retourner un client depuis la base de
     * donn�es, correspondant � la requ�te SQL donn�e prenant en param�tres les
     * objets pass�s en argument.
     */
    private T_member_p trouver( String sql, Object... objets ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        T_member_p t_member_p = null;

        try {
            /* R�cup�ration d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            /*
             * Pr�paration de la requ�te avec les objets pass�s en arguments
             * (ici, uniquement un id) et ex�cution.
             */
            preparedStatement = initialisationRequetePreparee( connexion, sql, false, objets );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de donn�es retourn�e dans le ResultSet */
            if ( resultSet.next() ) {
                t_member_p = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return t_member_p;
    }

    /*
     * Simple méthode utilitaire permettant de faire la correspondance (le
     * mapping) entre une ligne issue de la table des utilisateurs (un
     * ResultSet) et un bean Utilisateur.
     */
    private static T_member_p map( ResultSet resultSet ) throws SQLException {
    	T_member_p t_member_p = new T_member_p();
    	t_member_p.setMP_Login(resultSet.getString("MP_Login"));
    	t_member_p.setMP_Password(resultSet.getString("MP_Password"));
        return t_member_p;
    }
}