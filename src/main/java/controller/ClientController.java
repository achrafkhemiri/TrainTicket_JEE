package controller;

import dao.ClientDAO;
import dao.UtilisateurDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import model.Client;

import java.io.IOException;
import java.util.List;

@WebServlet("/ClientController")
public class ClientController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ClientDAO clientDAO = new ClientDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Client> clients = clientDAO.findAll();
        request.setAttribute("listClient", clients);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/clientView.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("motDePasse");
        Client client = new Client();
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setEmail(email);
        client.setMotDePasse(motDePasse);
        clientDAO.create(client);
        response.sendRedirect("/ClientController");
    }
}