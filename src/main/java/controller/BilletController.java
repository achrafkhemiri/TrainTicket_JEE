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
        List<Billet> billets = bielletDAO.findAll();
        request.setAttribute("listBillets", billets);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/billetView.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Billet billet = new Billet();
        bielletDAO.create(billet);
        response.sendRedirect("/BilletController");
    }
}