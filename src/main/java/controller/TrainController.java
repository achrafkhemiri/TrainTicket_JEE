package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TrainDAO;
import model.Train;

@WebServlet("/addTrain")
public class TrainController extends HttpServlet {

    private TrainDAO trainDAO;

    @Override
    public void init() throws ServletException {
        trainDAO = new TrainDAO();  }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get form parameters
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String departureTime = request.getParameter("departureTime");

        // Create new Train object
        Train train = new Train(name, type, departureTime);

        // Save to DB using DAO
        boolean created = trainDAO.create(train);

        if (created) {
            response.sendRedirect("success.jsp");  
        } else {
            request.setAttribute("error", "Failed to add train.");
            request.getRequestDispatcher("addTrain.jsp").forward(request, response);
        }
    }

    // Optional: Handle GET to show the form
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("addTrain.jsp").forward(request, response);
    }
}