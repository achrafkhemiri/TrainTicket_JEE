<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Résultats de recherche</title>
    <link rel="stylesheet" href="../Styles/styleRecherche.css">
</head>
<body>
 
<div class="container">
    <h2>Trajets disponibles</h2>

    <c:if test="${empty trajets}">
        <div class="message">Aucun trajet trouvé pour votre recherche.</div>
    </c:if>

    <c:forEach var="trajet" items="${trajets}">
        <div class="trajet-card">
            <p><strong>Départ :</strong> ${trajet.villeDepart} à ${trajet.heureDepart}</p>
            <p><strong>Arrivée :</strong> ${trajet.villeArrivee} à ${trajet.heureArrivee}</p>
            <p><strong>Durée :</strong> ${trajet.duree}</p>
            <p><strong>Prix :</strong> ${trajet.prix} DT</p>
            <form action="reservation" method="get">
                <input type="hidden" name="trajetId" value="${trajet.id}">
                <input type="submit" value="Réserver">
            </form>
        </div>
    </c:forEach>
</div>

<jsp:include page="../Others/Footer.jsp"/>
</body>
</html>
