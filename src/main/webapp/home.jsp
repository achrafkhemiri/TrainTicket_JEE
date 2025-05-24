<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Accueil - Réservation de Billets</title>
    <link rel="stylesheet" href="Styles/styleHome.css">
</head>
<body>
    <jsp:include page="Header.jsp"/>

    <div class="container">
        <h2>Rechercher un trajet</h2>

        <form action="recherche" method="get">
            <div>
                <label for="date">Date du voyage :</label>
                <input type="date" name="date" required>
            </div>

            <div>
                <label for="villeDepart">Ville de départ :</label>
                <input type="text" name="villeDepart" placeholder="Ex: Tunis" required>
            </div>

            <div>
                <label for="villeArrivee">Ville d’arrivée :</label>
                <input type="text" name="villeArrivee" placeholder="Ex: Sfax" required>
            </div>

            <input type="submit" value="Rechercher">
        </form>

       

    </div>

    <jsp:include page="Footer.jsp"/>
</body>
</html>
