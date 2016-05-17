package forms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import beans.T_member_p;
import dao.T_member_pDao;
import dao.DAOException;

import eu.medsea.mimeutil.MimeUtil;

public final class CreationT_member_pForm {
    private static final String CHAMP_LOGIN       = "login";
    private static final String CHAMP_PASSWORD    = "password";

    private static final int    TAILLE_TAMPON   = 10240;                        // 10ko

    private String              resultat;
    private Map<String, String> erreurs         = new HashMap<String, String>();
    private T_member_pDao       t_member_pDao;

    public CreationT_member_pForm(T_member_pDao t_member_pDao) {
        this.t_member_pDao = t_member_pDao;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public T_member_p creerT_member_p(HttpServletRequest request, String chemin) {
        String login = getValeurChamp(request, CHAMP_LOGIN);
        String password = getValeurChamp(request, CHAMP_PASSWORD);

        T_member_p t_member_p = new T_member_p();

        traiterLogin(login, t_member_p);
        traiterPassword(password, t_member_p);

        try {
            if ( erreurs.isEmpty() ) {
            	t_member_pDao.creer(t_member_p);
                resultat = "Succès de la création du client.";
            } else {
                resultat = "Échec de la création du client.";
            }
        } catch ( DAOException e ) {
            setErreur( "imprévu", "Erreur imprévue lors de la création." );
            resultat = "Échec de la création du client : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
        }

        return t_member_p;
    }

    private void traiterLogin(String login, T_member_p t_member_p) {
        try {
            validationLogin(login);
        } catch ( FormValidationException e ) {
            setErreur(CHAMP_LOGIN, e.getMessage());
        }
        t_member_p.setMP_Login(login);
    }

    private void traiterPassword(String password, T_member_p t_member_p) {
        try {
            validationPassword(password);
        } catch (FormValidationException e) {
            setErreur(CHAMP_PASSWORD, e.getMessage());
        }
        t_member_p.setMP_Password(password);
    }

    private void validationLogin(String login) throws FormValidationException {
        if (login != null ) {
            if (login.length() < 2) {
                throw new FormValidationException( "Le login doit contenir au moins 2 caractères." );
            }
        } else {
            throw new FormValidationException( "Merci d'entrer un login." );
        }
    }

    private void validationPassword(String password) throws FormValidationException {
        if (password != null) {
        	if (password.length() < 2 ) {
        		throw new FormValidationException( "Le password doit contenir au moins 2 caractères." );
        	}
        } else {
        	throw new FormValidationException( "Merci d'entrer un password." );
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }

    /*
     * Méthode utilitaire qui a pour unique but d'analyser l'en-tête
     * "content-disposition", et de vérifier si le paramètre "filename" y est
     * présent. Si oui, alors le champ traité est de type File et la méthode
     * retourne son nom, sinon il s'agit d'un champ de formulaire classique et
     * la méthode retourne null.
     */
    private static String getNomFichier( Part part ) {
        /* Boucle sur chacun des paramètres de l'en-tête "content-disposition". */
        for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
            /* Recherche de l'éventuelle présence du paramètre "filename". */
            if ( contentDisposition.trim().startsWith( "filename" ) ) {
                /*
                 * Si "filename" est présent, alors renvoi de sa valeur,
                 * c'est-à-dire du nom de fichier sans guillemets.
                 */
                return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
            }
        }
        /* Et pour terminer, si rien n'a été trouvé... */
        return null;
    }

    /*
     * Méthode utilitaire qui a pour but d'écrire le fichier passé en paramètre
     * sur le disque, dans le répertoire donné et avec le nom donné.
     */
    private void ecrireFichier( InputStream contenuFichier, String nomFichier, String chemin )
            throws FormValidationException {
        /* Prépare les flux. */
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            /* Ouvre les flux. */
            entree = new BufferedInputStream( contenuFichier, TAILLE_TAMPON );
            sortie = new BufferedOutputStream( new FileOutputStream( new File( chemin + nomFichier ) ),
                    TAILLE_TAMPON );

            /*
             * Lit le fichier reçu et écrit son contenu dans un fichier sur le
             * disque.
             */
            byte[] tampon = new byte[TAILLE_TAMPON];
            int longueur = 0;
            while ( ( longueur = entree.read( tampon ) ) > 0 ) {
                sortie.write( tampon, 0, longueur );
            }
        } catch ( Exception e ) {
            throw new FormValidationException( "Erreur lors de l'écriture du fichier sur le disque." );
        } finally {
            try {
                sortie.close();
            } catch ( IOException ignore ) {
            }
            try {
                entree.close();
            } catch ( IOException ignore ) {
            }
        }
    }
}