package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import dao.VoyageDAO;
import model.Voyage;

/**
 * Servlet implementation class ListeVoyage
 */
@WebServlet("/ListeVoyage")
public class ListeVoyage extends HttpServlet {
	  private VoyageDAO voyageDAO = new VoyageDAO();

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        List<Voyage> voyages = voyageDAO.findAll();  
	        request.setAttribute("voyages", voyages);
 
	        request.getRequestDispatcher("/ViewsClient/listeVoyages.jsp").forward(request, response);
	    }

}
