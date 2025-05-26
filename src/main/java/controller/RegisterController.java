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
 * Servlet implementation class RegisterController
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	  private final UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String nom        = request.getParameter("nom");
	        String prenom     = request.getParameter("prenom");
	        String email      = request.getParameter("email");
	        String motDePasse = request.getParameter("motDePasse");
 
	        if (utilisateurDAO.existsByEmail(email)) {
	            request.setAttribute("message", "Cet email est déjà utilisé !");
	            request.getRequestDispatcher("register.jsp").forward(request, response);
	            return;
	        }
 
	        Utilisateur u = new Utilisateur(nom, prenom, email, motDePasse, "utilisateur");
	        utilisateurDAO.create(u);
 
	        response.sendRedirect("login.jsp");
	    }}