package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GareDAO;
import dao.VoyageDAO;
import model.Gare;
import model.Voyage;

/**
 * Servlet implementation class RechVoyage
 */
@WebServlet("/RechVoyage")
public class RechVoyage extends HttpServlet {
	  private GareDAO gareDAO = new GareDAO();
	    private VoyageDAO voyageDAO = new VoyageDAO();

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	 String villeDepart = request.getParameter("villeDepart");
	         String villeArrivee = request.getParameter("villeArrivee");
	         LocalDate dateVoyage = LocalDate.parse(request.getParameter("date"));

	         // Récupérer les gares par ville
	         Gare depart = gareDAO.findByVille(villeDepart);
	         Gare arrivee = gareDAO.findByVille(villeArrivee);

	         if (depart == null || arrivee == null) {
	             request.setAttribute("erreur", "Gares non trouvées pour les villes spécifiées.");
	             request.getRequestDispatcher("/WEB-INF/views/resultatsVoyage.jsp").forward(request, response);
	             return;
	         }

	         // Chercher les voyages
	         List<Voyage> voyages = voyageDAO.findByDateAndTrajet(dateVoyage, depart, arrivee);

	         request.setAttribute("voyages", voyages);
	         request.setAttribute("date", dateVoyage);
	         request.setAttribute("depart", villeDepart);
	         request.setAttribute("arrivee", villeArrivee);

	        request.getRequestDispatcher("ResultatRech.jsp").forward(request, response);
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        int departId = Integer.parseInt(request.getParameter("departId"));
	        int arriveeId = Integer.parseInt(request.getParameter("arriveeId"));
	        LocalDate date = LocalDate.parse(request.getParameter("dateVoyage"));

	        Gare depart = gareDAO.findById(departId);
	        Gare arrivee = gareDAO.findById(arriveeId);

	        List<Voyage> voyages = voyageDAO.findByDateAndTrajet(date, depart, arrivee);

	        request.setAttribute("voyages", voyages);
	        request.setAttribute("date", date);
	        request.setAttribute("depart", depart.getVille());
	        request.setAttribute("arrivee", arrivee.getVille());

	        request.getRequestDispatcher("ResultatRech.jsp").forward(request, response);
	    }

}
