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
                resultat = "Succ�s de la cr�ation du client.";
            } else {
                resultat = "�chec de la cr�ation du client.";
            }
        } catch ( DAOException e ) {
            setErreur( "impr�vu", "Erreur impr�vue lors de la cr�ation." );
            resultat = "�chec de la cr�ation du client : une erreur impr�vue est survenue, merci de r�essayer dans quelques instants.";
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
                throw new FormValidationException( "Le login doit contenir au moins 2 caract�res." );
            }
        } else {
            throw new FormValidationException( "Merci d'entrer un login." );
        }
    }

    private void validationPassword(String password) throws FormValidationException {
        if (password != null) {
        	if (password.length() < 2 ) {
        		throw new FormValidationException( "Le password doit contenir au moins 2 caract�res." );
        	}
        } else {
        	throw new FormValidationException( "Merci d'entrer un password." );
        }
    }

    /*
     * Ajoute un message correspondant au champ sp�cifi� � la map des erreurs.
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    /*
     * M�thode utilitaire qui retourne null si un champ est vide, et son contenu
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
     * M�thode utilitaire qui a pour unique but d'analyser l'en-t�te
     * "content-disposition", et de v�rifier si le param�tre "filename" y est
     * pr�sent. Si oui, alors le champ trait� est de type File et la m�thode
     * retourne son nom, sinon il s'agit d'un champ de formulaire classique et
     * la m�thode retourne null.
     */
    private static String getNomFichier( Part part ) {
        /* Boucle sur chacun des param�tres de l'en-t�te "content-disposition". */
        for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
            /* Recherche de l'�ventuelle pr�sence du param�tre "filename". */
            if ( contentDisposition.trim().startsWith( "filename" ) ) {
                /*
                 * Si "filename" est pr�sent, alors renvoi de sa valeur,
                 * c'est-�-dire du nom de fichier sans guillemets.
                 */
                return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
            }
        }
        /* Et pour terminer, si rien n'a �t� trouv�... */
        return null;
    }

    /*
     * M�thode utilitaire qui a pour but d'�crire le fichier pass� en param�tre
     * sur le disque, dans le r�pertoire donn� et avec le nom donn�.
     */
    private void ecrireFichier( InputStream contenuFichier, String nomFichier, String chemin )
            throws FormValidationException {
        /* Pr�pare les flux. */
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            /* Ouvre les flux. */
            entree = new BufferedInputStream( contenuFichier, TAILLE_TAMPON );
            sortie = new BufferedOutputStream( new FileOutputStream( new File( chemin + nomFichier ) ),
                    TAILLE_TAMPON );

            /*
             * Lit le fichier re�u et �crit son contenu dans un fichier sur le
             * disque.
             */
            byte[] tampon = new byte[TAILLE_TAMPON];
            int longueur = 0;
            while ( ( longueur = entree.read( tampon ) ) > 0 ) {
                sortie.write( tampon, 0, longueur );
            }
        } catch ( Exception e ) {
            throw new FormValidationException( "Erreur lors de l'�criture du fichier sur le disque." );
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