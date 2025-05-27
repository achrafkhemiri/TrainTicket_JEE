<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Mes Réservations</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
            background: #f5f5f5;
        }
        h2 {
            margin-bottom: 20px;
        }
        .card-container {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
        }
        .card {
            background: white;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            padding: 20px;
            width: 300px;
            cursor: pointer;
            transition: transform 0.2s ease, box-shadow 0.2s ease;
            text-decoration: none;
            color: #333;
        }
        .card:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 20px rgba(0,0,0,0.15);
        }
        .card h3 {
            margin-top: 0;
            margin-bottom: 10px;
            font-size: 1.2em;
            color: #3498db;
        }
        .card p {
            margin: 6px 0;
        }
        .no-reservations {
            font-size: 1.1em;
            color: #666;
        }
    </style>
</head>
<body>
<jsp:include page="navbar.jsp" />
<h2>Mes Réservations</h2>

<c:if test="${empty mesReservations}">
    <p class="no-reservations">Vous n'avez aucune réservation.</p>
</c:if>

<c:if test="${not empty mesReservations}">
    <div class="card-container">
        <c:forEach var="r" items="${mesReservations}">
            <!-- Carte cliquable qui redirige vers la liste des billets de la réservation -->
            <a class="card" href="BilletController?action=list&reservationId=${r.id}">
                <h3>Réservation #${r.id}</h3>
                <p><strong>Voyage :</strong> ${r.voyage.trajet.depart.ville} → ${r.voyage.trajet.arrivee.ville}</p>
                <p><strong>Date de réservation :</strong> ${r.dateReservation}</p>
                <p><strong>Nombre de personnes :</strong> ${r.nbPersonnes}</p>
            </a>
        </c:forEach>
    </div>
</c:if>

</body>
</html>
