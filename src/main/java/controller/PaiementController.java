package controller;

import dao.PaiementDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import model.Paiement;


import java.io.IOException;
import java.util.List;
@WebServlet("/PaiementController")
public class PaiementController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PaiementDAO paiementDAO = new PaiementDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Paiement> paiements = paiementDAO.findAll();
        request.setAttribute("listPaiement", paiements);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/paiementView.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Paiement paiement = new Paiement();
        paiementDAO.create(paiement);
        response.sendRedirect("/PaiementController");
    }
}