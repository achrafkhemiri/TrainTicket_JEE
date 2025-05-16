package controller;

import dao.GareDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import model.Gare;
import java.io.IOException;
import java.util.List;
@WebServlet("/GareController")

public class GareController extends HttpServlet {
	  private static final long serialVersionUID = 1L;
	    private GareDAO gareDAO = new GareDAO();

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        List<Gare> gares = gareDAO.findAll();
	        request.setAttribute("listGare", gares);
	        RequestDispatcher rd = getServletContext().getRequestDispatcher("/gareView.jsp"); // '/' au début
	        rd.forward(request, response);
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String nom = request.getParameter("nom");
	        String ville = request.getParameter("ville");

	        Gare gare = new Gare(nom, ville);
	        gareDAO.create(gare);
	        response.sendRedirect(request.getContextPath() + "/GareController");
	    }
}
