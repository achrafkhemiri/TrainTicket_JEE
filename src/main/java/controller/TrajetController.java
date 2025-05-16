package controller;
 
import dao.TrajetDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
 
import model.Trajet;


import java.io.IOException;
import java.util.List;
@WebServlet("/TrajetController")
public class TrajetController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TrajetDAO trajetDAO = new TrajetDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Trajet> trajets = trajetDAO.findAll();
        request.setAttribute("listTrajet", trajets);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/trajetView.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Trajet trajet = new Trajet();
        trajetDAO.create(trajet);
        response.sendRedirect("/TrajetController");
    }
}
