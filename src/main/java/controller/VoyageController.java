package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TrainDAO;
import dao.TrajetDAO;
import dao.VoyageDAO;
import model.Train;
import model.Trajet;
import model.Voyage;

/**
 * Servlet implementation class VoyageController
 */
@WebServlet("/VoyageController")
public class VoyageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VoyageDAO voyageDAO = new VoyageDAO();
    private TrajetDAO trajetDAO = new TrajetDAO();
    private  TrainDAO trainDAO = new TrainDAO(); 
  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoyageController() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<Trajet> trajets = trajetDAO.findAll();   
        List<Voyage> voyages = voyageDAO.findAll();
        List<Train> trains = trainDAO.findAll();
	     

        req.setAttribute("trajets", trajets);
        req.setAttribute("listeVoyages", voyages);
        req.setAttribute("trains", trains);


        req.getRequestDispatcher("/ViewsAdmin/addVoyage.jsp").forward(req, res);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    int trajetId = Integer.parseInt(req.getParameter("trajetId"));  
	    
	    int trainId = Integer.parseInt(req.getParameter("trainId"));
	    
	    
	    LocalDate dateVoyage = LocalDate.parse(req.getParameter("dateVoyage"));
	    LocalTime heureDepart = LocalTime.parse(req.getParameter("heureDepart"));
	    LocalTime heureArrivee = LocalTime.parse(req.getParameter("heureArrivee"));
	    double prix = Double.parseDouble(req.getParameter("prixVoyage"));
	    int nbPlaces = Integer.parseInt(req.getParameter("nbPlacesDispo"));
        Train train = trainDAO.findById(trainId);


	    Trajet trajet = trajetDAO.findById(trajetId);
	    Voyage voyage = new Voyage(dateVoyage, heureDepart, heureArrivee, nbPlaces, prix, trajet,train);

	    voyageDAO.create(voyage);
	    res.sendRedirect("VoyageController");
	}


}
