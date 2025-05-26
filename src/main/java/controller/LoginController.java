package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UtilisateurDAO;
import model.Utilisateur;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	  private static final long serialVersionUID = 1L;
	    private UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException { 
	        RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
	        rd.forward(req, resp);
	    }

	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {
	        String email = req.getParameter("email");
	        String mdp   = req.getParameter("motDePasse");

	        Utilisateur u = utilisateurDAO.findByEmailAndPassword(email, mdp);
	        if (u != null) {
	            HttpSession session = req.getSession(true);
	            session.setAttribute("utilisateur", u);
	            if ("admin".equalsIgnoreCase(u.getRole())) {
	                resp.sendRedirect(req.getContextPath() + "/ViewsAdmin/sidebar.jsp");
	            } else {
	                resp.sendRedirect(req.getContextPath() + "/ViewsClient/welcome.jsp");
	            }
	        } else {
	            req.setAttribute("message", "Email ou mot de passe incorrect.");
	            RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
	            rd.forward(req, resp);
	        }
	    }
}