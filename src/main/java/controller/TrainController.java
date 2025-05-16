package controller;

import java.io.IOException;
import java.util.List;

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
        trainDAO = new TrainDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
        request.setAttribute("listTrain", trainDAO.findAll());
 
        request.getRequestDispatcher("addTrain.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String departureTime = request.getParameter("departureTime");

        Train train = new Train(name, type, departureTime);

        boolean created = trainDAO.create(train);

        
        List<Train> trains = trainDAO.findAll();
        request.setAttribute("listTrain", trains);
        request.getRequestDispatcher("addTrain.jsp").forward(request, response);
    }
}
