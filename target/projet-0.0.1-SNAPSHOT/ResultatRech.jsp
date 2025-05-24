<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Résultats de recherche</title>
</head>
<body>
<h2>Voyages trouvés : ${date}</h2>
<p>De ${depart} à ${arrivee}</p>

<c:if test="${empty voyages}">
    <p>Aucun voyage disponible.</p>
</c:if>

<c:forEach var="v" items="${voyages}">
    <div style="margin-bottom: 20px;">
        <p>Départ : ${v.trajet.depart.ville} à ${v.heureDepart}</p>
        <p>Arrivée : ${v.trajet.arrivee.ville} à ${v.heureArrivee}</p>
        <p>Prix : ${v.prix} DT</p>

        <form action="ReservationServlet" method="post">
            <input type="hidden" name="voyageId" value="${v.id}">
            <input type="text" name="nomClient" placeholder="Votre nom" required>
            <input type="email" name="email" placeholder="Votre email" required>
            <input type="number" name="nbPlaces" value="1" min="1" required>
            <input type="submit" value="Réserver">
        </form>
    </div>
</c:forEach>

</body>
</html>
