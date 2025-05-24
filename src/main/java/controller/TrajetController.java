package controller;
 
import dao.GareDAO;
import dao.TrajetDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import model.Gare;
import model.Trajet;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@WebServlet("/TrajetController")
public class TrajetController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TrajetDAO trajetDAO = new TrajetDAO(); 
    private GareDAO gareDAO = new GareDAO();

     
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Gare> gares = gareDAO.findAll();
        List<Trajet> trajets = trajetDAO.findAll();
        request.setAttribute("gares", gares);
        request.setAttribute("listeTrajets", trajets);
        request.getRequestDispatcher("addTrajet.jsp").forward(request, response);
        }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 int departId = Integer.parseInt(request.getParameter("departId"));
         int arriveeId = Integer.parseInt(request.getParameter("arriveeId"));
         
         
         String[] passagesIds = request.getParameterValues("passages");

         Gare depart = gareDAO.findById(departId);
         Gare arrivee = gareDAO.findById(arriveeId);

         List<Gare> passages = new ArrayList<>();
         if (passagesIds != null) {
             for (String id : passagesIds) {
                 passages.add(gareDAO.findById(Integer.parseInt(id)));
             }
         }

         Trajet trajet = new Trajet(depart, arrivee, passages);
         trajetDAO.create(trajet);
 
         response.sendRedirect(request.getContextPath() + "/TrajetController");
    }
}
