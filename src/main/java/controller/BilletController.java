package controller;

import dao.BilletDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import model.Billet;
import java.io.IOException;
import java.util.List;

@WebServlet("/BilletController")
public class BilletController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BilletDAO bielletDAO = new BilletDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("list".equals(action)) {
            String reservationIdParam = request.getParameter("reservationId");
            if (reservationIdParam != null) {
                int reservationId = Integer.parseInt(reservationIdParam);
                List<Billet> billets = bielletDAO.findByReservationIdWithDetails(reservationId);
                request.setAttribute("billets", billets);

                RequestDispatcher rd = request.getRequestDispatcher("ViewsClient/listeBillets.jsp");
                rd.forward(request, response);
            } else {
                response.sendRedirect("ReservationController?action=mesReservations");
            }
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Billet billet = new Billet();
        bielletDAO.create(billet);
        response.sendRedirect("/BilletController");
    }
}