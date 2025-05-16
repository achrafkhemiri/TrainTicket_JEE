package controller;
 
import dao.ReservationDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import model.Reservation;


import java.io.IOException;
import java.util.List;
@WebServlet("/ReservationController")
public class ReservationController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ReservationDAO reservationDAO = new ReservationDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Reservation> reservations = reservationDAO.findAll();
        request.setAttribute("listReservation", reservations);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/reservationView.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Reservation reservation = new Reservation();
        reservationDAO.create(reservation);
        response.sendRedirect("/ReservationController");
    }
}
