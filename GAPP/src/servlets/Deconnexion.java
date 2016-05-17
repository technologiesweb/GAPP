package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Deconnexion")
public class Deconnexion extends HttpServlet {
	public static final String ATT_SESSION_USER = "sessionT_member_p";
    public static final String URL_REDIRECTION = "/Gapp/connexion.html";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    	
        /* Récupération et destruction de la session en cours */
        HttpSession session = request.getSession();
        System.out.println("Deconnexion");
        System.out.println("session.getAttribute(ATT_SESSION_USER) : " + session.getAttribute(ATT_SESSION_USER));
        session.removeAttribute(ATT_SESSION_USER);
        System.out.println("session.getAttribute(ATT_SESSION_USER) : " + session.getAttribute(ATT_SESSION_USER));
        session.invalidate();

        response.sendRedirect(URL_REDIRECTION );
    }
    
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    }
}