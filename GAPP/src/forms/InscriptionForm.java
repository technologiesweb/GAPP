package forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import beans.T_member_p;
import dao.DAOException;
import dao.T_member_pDao;

public final class InscriptionForm {
    private static final String CHAMP_LOGIN      = "login";
    private static final String CHAMP_PASSWORD   = "password";
    private static final String CHAMP_CONF       = "confirmation";

    private static final String ALGO_CHIFFREMENT = "SHA-256";

    private String              resultat;
    private Map<String, String> erreurs          = new HashMap<String, String>();
    private T_member_pDao      t_member_pDao;

    public InscriptionForm(T_member_pDao t_member_pDao) {
        this.t_member_pDao = t_member_pDao;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public T_member_p inscrireUtilisateur( HttpServletRequest request ) {
        String login = getValeurChamp( request, CHAMP_LOGIN );
        String password = getValeurChamp(request, CHAMP_PASSWORD);
        String confirmation = getValeurChamp( request, CHAMP_CONF );

        T_member_p t_member_p = new T_member_p();
        try {
            traiterEmail(login, t_member_p);
            traiterMotsDePasse(password, confirmation, t_member_p);

            if ( erreurs.isEmpty() ) {
            	t_member_pDao.creer(t_member_p);
                resultat = "Succès de l'inscription.";
            } else {
                resultat = "Échec de l'inscription.";
            }
        } catch ( DAOException e ) {
            resultat = "Échec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
        }

        return t_member_p;
    }

    /*
     * Appel �  la validation de l'adresse email reçue et initialisation de la
     * propriété email du bean
     */
    private void traiterEmail(String login, T_member_p t_member_p) {
        try {
            validationLogin(login);
        } catch (FormValidationException e) {
            setErreur(CHAMP_LOGIN, e.getMessage());
        }
        t_member_p.setMP_Login(login);
    }

    /*
     * Appel �  la validation des mots de passe reçus, chiffrement du mot de
     * passe et initialisation de la propriété motDePasse du bean
     */
    private void traiterMotsDePasse(String password, String confirmation, T_member_p t_member_p ) {
        try {
            validationPassword(password, confirmation);
        } catch ( FormValidationException e ) {
            setErreur(CHAMP_PASSWORD, e.getMessage() );
            setErreur( CHAMP_CONF, null );
        }

        /*
         * Utilisation de la bibliothèque Jasypt pour chiffrer le mot de passe
         * efficacement.
         * 
         * L'algorithme SHA-256 est ici utilisé, avec par défaut un salage
         * aléatoire et un grand nombre d'itérations de la fonction de hashage.
         * 
         * La String retournée est de longueur 56 et contient le hash en Base64.
         */
        ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
        passwordEncryptor.setAlgorithm( ALGO_CHIFFREMENT );
        passwordEncryptor.setPlainDigest( false );
        String motDePasseChiffre = passwordEncryptor.encryptPassword(password);

        t_member_p.setMP_Password(motDePasseChiffre);
    }

    /* Validation de l'adresse email */
    private void validationLogin(String login) throws FormValidationException {
        if (login != null) {
            if (!login.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
                throw new FormValidationException( "Merci de saisir une adresse mail valide." );
            } else if (t_member_pDao.trouver(login) != null ) {
                throw new FormValidationException( "Cette adresse email est déj�  utilisée, merci d'en choisir une autre." );
            }
        } else {
            throw new FormValidationException( "Merci de saisir une adresse mail." );
        }
    }

    /* Validation des mots de passe */
    private void validationPassword(String password, String confirmation) throws FormValidationException {
        if (password != null && confirmation != null ) {
            if (!password.equals( confirmation)) {
                throw new FormValidationException( "Les mots de passe entrés sont différents, merci de les saisir �  nouveau." );
            } else if (password.length() < 3 ) {
                throw new FormValidationException( "Les mots de passe doivent contenir au moins 3 caractères." );
            }
        } else {
            throw new FormValidationException( "Merci de saisir et confirmer votre mot de passe." );
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié �  la map des erreurs.
     */
    private void setErreur(String champ, String message) {
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
}