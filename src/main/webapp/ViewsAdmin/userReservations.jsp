<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Réservations de l'utilisateur</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />

    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #f4f7fb;
            margin: 0;
            padding: 0;
            display: flex;
        }

        .sidebar {
            width: 220px;
            background-color: #1f2d3d;
            color: #ecf0f1;
            padding: 30px 20px;
            position: fixed;
            top: 0;
            left: 0;
            height: 100vh;
            box-shadow: 2px 0 6px rgba(0,0,0,0.2);
            display: flex;
            flex-direction: column;
            z-index: 1000;
        }

        .sidebar-header {
            font-size: 1.5rem;
            font-weight: 600;
            color: #3498db;
            margin-bottom: 40px;
            text-align: center;
            letter-spacing: 0.5px;
        }

        .sidebar-menu {
            list-style: none;
            padding: 0;
            margin: 0;
            flex-grow: 1;
        }

        .sidebar-menu li {
            margin-bottom: 18px;
        }

        .sidebar-menu li a {
            display: flex;
            align-items: center;
            gap: 12px;
            color: #bdc3c7;
            text-decoration: none;
            padding: 10px 14px;
            border-radius: 6px;
            font-weight: 500;
            transition: background-color 0.3s ease, color 0.3s ease;
        }

        .sidebar-menu li a:hover,
        .sidebar-menu li a.active {
            background-color: #294866;
            color: #ecf0f1;
        }

        .sidebar-menu li a i {
            min-width: 20px;
            text-align: center;
        }

        .sidebar-menu li a span {
            font-size: 1em;
        }

        .main-content {
            margin-left: 220px;
            padding: 30px;
            flex-grow: 1;
            max-width: 1000px;
        }

        h2 {
            color: #2c3e50;
            font-size: 1.8em;
            border-left: 6px solid #3498db;
            padding-left: 12px;
            margin-bottom: 20px;
            font-weight: 700;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background: white;
            box-shadow: 0 2px 6px rgba(0,0,0,0.1);
            border-radius: 8px;
            overflow: hidden;
            margin-top: 20px;
        }

        th, td {
            padding: 14px 20px;
            border-bottom: 1px solid #eee;
            text-align: left;
            font-size: 1em;
        }

        th {
            background-color: #3498db;
            color: white;
        }

        tr:hover {
            background-color: #f0f8ff;
        }

        /* Responsive */
        @media (max-width: 768px) {
            body {
                flex-direction: column;
            }
            .sidebar {
                width: 100%;
                height: auto;
                position: relative;
                box-shadow: none;
            }
            .main-content {
                margin-left: 0;
                padding: 20px;
            }
        }
    </style>
</head>
<body>

<jsp:include page="sidebar.jsp" />

<div class="main-content">
    <h2>Réservations de ${utilisateur.nom} ${utilisateur.prenom}</h2>

    <table>
        <tr>
            <th>ID</th>
            <th>Date</th>
            <th>Voyage</th>
            <th>Nb Personnes</th>
            <th>Prix Total</th>
        </tr>
        <c:forEach var="res" items="${reservations}">
            <tr>
                <td>${res.id}</td>
                <td>${res.dateReservation}</td>
                <td>
                    <c:choose>
                        <c:when test="${not empty res.voyage}">
                            ${res.voyage.trajet.depart} → ${res.voyage.trajet.arrivee.nom}
                        </c:when>
                        <c:otherwise>
                            <em>Aucun voyage</em>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>${res.nbPersonnes}</td>
                <td>${res.prixTotal}</td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
