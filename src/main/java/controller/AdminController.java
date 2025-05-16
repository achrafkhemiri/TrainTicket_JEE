package controller;

import dao.AdminDAO; 
import model.Admin;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/AdministrateurController")
public class AdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AdminDAO adminDAO = new AdminDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Admin> admins = adminDAO.findAllAdmins();
        request.setAttribute("listAdmin", admins);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/adminView.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("motDePasse");
        Admin admin = new Admin();
        admin.setNom(nom);
        admin.setPrenom(prenom);
        admin.setEmail(email);
        admin.setMotDePasse(motDePasse);
        adminDAO.create(admin);
        response.sendRedirect("/AdministrateurController");
    }
}