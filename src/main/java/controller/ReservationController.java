package controller;
 
import dao.ReservationDAO;
import dao.VoyageDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import model.Reservation;
import model.Voyage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
@WebServlet("/ReservationController")
public class ReservationController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ReservationDAO reservationDAO = new ReservationDAO();
    private VoyageDAO voyageDAO = new VoyageDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Reservation> reservations = reservationDAO.findAll();
        request.setAttribute("listReservation", reservations);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/reservationView.jsp");
        rd.forward(request, response);
    }

   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int voyageId = Integer.parseInt(request.getParameter("voyageId"));
        String nom = request.getParameter("nomClient");
        String email = request.getParameter("email");
        int nbPlaces = Integer.parseInt(request.getParameter("nbPlaces"));

        Voyage voyage = voyageDAO.findById(voyageId);

        Reservation res = new Reservation();
 /*       res.setNomClient(nom);
        res.setEmail(email);
        res.setVoyage(voyage);
        res.setNbPlaces(nbPlaces);
        res.setDateReservation(LocalDateTime.now());
*/
        // save via DAO (ReservationDAO.create(res))

        response.sendRedirect("confirmation.jsp");
    }
}
