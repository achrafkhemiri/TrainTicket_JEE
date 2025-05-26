package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UtilisateurDAO;
import model.Utilisateur;

/**
 * Servlet implementation class EditProfile
 */
@WebServlet("/EditProfile")
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 private UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
 

	        utilisateur.setNom(request.getParameter("nom"));
	        utilisateur.setPrenom(request.getParameter("prenom"));
	        utilisateur.setEmail(request.getParameter("email"));
	        utilisateur.setMotDePasse(request.getParameter("motDePasse")); 

	        utilisateurDAO.update(utilisateur);
 
	        request.getSession().setAttribute("utilisateur", utilisateur);

	        response.sendRedirect("ViewsClient/welcome.jsp");
	    }

}
