package controller;

import dao.UtilisateurDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import model.Utilisateur;

import java.io.IOException;
import java.util.List;

@WebServlet("/UtilisateurController")
public class UtilisateurController extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	    private UtilisateurDAO utilisateurDAO;

	    public UtilisateurController() {
	        super();
	        utilisateurDAO = new UtilisateurDAO();
	    }

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String message = "";
	        if (request.getParameter("id") != null) {
	            int id = Integer.parseInt(request.getParameter("id"));  
	            if (utilisateurDAO.delete(id)) {
	                message = "Utilisateur supprimé avec succès.";
	            } else {
	                message = "Échec de la suppression de l'utilisateur.";
	            }
	        }
	        List<Utilisateur> utilisateurs = utilisateurDAO.findAll();
	        request.setAttribute("listUtilisateur", utilisateurs);
	        request.setAttribute("message", message);
	        RequestDispatcher rd = getServletContext().getRequestDispatcher("/ViewsAdmin/utilisateurView.jsp");
	        rd.forward(request, response);
	    }


	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String message = "";
	        String nom = request.getParameter("nom");
	        String prenom = request.getParameter("prenom");
	        String email = request.getParameter("email");
	        String motDePasse = request.getParameter("motDePasse");
	        Utilisateur utilisateur = new Utilisateur();
	        utilisateur.setNom(nom);
	        utilisateur.setPrenom(prenom);
	        utilisateur.setEmail(email);
	        utilisateur.setMotDePasse(motDePasse);
	        utilisateur.setRole("utilisateur"); 
	        if (utilisateurDAO.create(utilisateur)) {
	            message = "Utilisateur créé avec succès.";
	        } else {
	            message = "Échec de la création de l'utilisateur.";
	        }
	        List<Utilisateur> utilisateurs = utilisateurDAO.findAll();
	        request.setAttribute("listUtilisateur", utilisateurs);
	        request.setAttribute("message", message);
	        String name="yosr";
	        request.setAttribute("name", name);

	        RequestDispatcher rd = getServletContext().getRequestDispatcher("/ViewsAdmin/utilisateurView.jsp");
	        rd.forward(request, response);
	    }
	}
