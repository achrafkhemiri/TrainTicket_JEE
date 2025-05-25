<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
   
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/styleResultats.css">

</head>
<body>
<jsp:include page="navbar.jsp" />

<div class="page-container">
    <div class="search-summary">
    <h2><i class="fas fa-calendar-day"></i> Voyages disponibles le <span>${date}</span></h2>
    <p><i class="fas fa-map-signs"></i> Trajet : <strong>${depart}</strong> → <strong>${arrivee}</strong></p>

    <c:if test="${empty voyages}">
        <div class="message-empty">
            <i class="fas fa-exclamation-circle"></i> Aucun voyage trouvé pour cette date.
        </div>
    </c:if>
</div>

    <div class="cards-container">
        <c:forEach var="v" items="${voyages}">
            <div class="voyage-card">
                <h3><i class="fas fa-train"></i> Voyage #${v.id}</h3>
                <p><i class="fas fa-arrow-circle-right"></i> <strong>Départ :</strong> ${v.trajet.depart.ville} à ${v.heureDepart}</p>
                <p><i class="fas fa-arrow-circle-left"></i> <strong>Arrivée :</strong> ${v.trajet.arrivee.ville} à ${v.heureArrivee}</p>
                <p><i class="fas fa-ticket-alt"></i> <strong>Prix :</strong> ${v.prixVoyage} DT</p>

                <c:if test="${not empty v.trajet.garesDePassage}">
                    <div class="intermediate-section">
                        <p><i class="fas fa-route"></i> <strong>Gares intermédiaires :</strong></p>
                        <div class="intermediate-gares">
                            <c:forEach var="g" items="${v.trajet.garesDePassage}">
                                <div class="gare-chip">
                                    <i class="fas fa-map-pin"></i> ${g.ville} - ${g.nom}
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </c:if>

                <form action="ReservationServlet" method="post" class="reservation-btn">
                    <input type="hidden" name="voyageId" value="${v.id}">
                    <input type="submit" value="Réserver">
                </form>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>
 