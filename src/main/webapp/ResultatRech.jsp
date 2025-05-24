<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Résultats de recherche</title>
    <link rel="stylesheet" href="Styles/styleResultats.css">
</head>
<body>

<h2>Voyages trouvés : ${date}</h2>
<p class="header-summary">De ${depart} à ${arrivee}</p>

<c:if test="${empty voyages}">
    <div class="message-empty">
        Aucun voyage disponible.
    </div>
</c:if>
<div class="cards-container">
    <c:forEach var="v" items="${voyages}">
        <div class="voyage-card"> 
<p><i class="fas fa-location-arrow"></i><strong>Départ :</strong> ${v.trajet.depart.ville} à ${v.heureDepart}</p>
<p><i class="fas fa-map-marker-alt"></i><strong>Arrivée :</strong> ${v.trajet.arrivee.ville} à ${v.heureArrivee}</p>
<p><i class="fas fa-money-bill"></i><strong>Prix :</strong> ${v.prixVoyage} DT</p>

            <c:if test="${not empty v.trajet.garesDePassage}">
                <p><strong>Gares intermédiaires :</strong></p>
                <ul>
                    <c:forEach var="g" items="${v.trajet.garesDePassage}">
                        <li>${g.ville} - ${g.nom}</li>
                    </c:forEach>
                </ul>
            </c:if>

            <div class="reservation-btn">
                <form action="ReservationServlet" method="post">
                    <input type="hidden" name="voyageId" value="${v.id}">
                    <input type="submit" value="Réserver">
                </form>
            </div>
        </div>
    </c:forEach>
</div>


</body>
</html>
