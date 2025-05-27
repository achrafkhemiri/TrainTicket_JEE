<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Liste des Billets</title>
    
    <link rel="stylesheet" href="Styles/styleBillet.css" />
    <script>
        // Fonction pour imprimer uniquement une carte spécifique
        function printCard(cardId) {
            var cardContent = document.getElementById(cardId).innerHTML;
            var printWindow = window.open('', '', 'height=600,width=800');
            printWindow.document.write('<html><head><title>Impression Billet</title>');
            // On peut ajouter ici le CSS pour un rendu correct
            printWindow.document.write('<style>');
            printWindow.document.write('body { font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif; padding: 20px; }');
            printWindow.document.write('.card { box-shadow: 0 3px 8px rgba(0,0,0,0.1); border-radius: 12px; padding: 20px 25px; width: 100%; box-sizing: border-box; }');
            printWindow.document.write('.card h3 { margin-top: 0; font-weight: 700; font-size: 1.2em; color: #34495e; }');
            printWindow.document.write('.card p { color: #555; font-size: 1em; }');
            printWindow.document.write('</style>');
            printWindow.document.write('</head><body>');
            printWindow.document.write(cardContent);
            printWindow.document.write('</body></html>');
            printWindow.document.close();
            printWindow.focus();
            printWindow.print();
            printWindow.close();
        }
    </script>
</head>
<body>

    <div class="container">
        <h2>Billets de la réservation</h2>

        <c:if test="${empty billets}">
            <p class="empty-msg">Aucun billet trouvé pour cette réservation.</p>
        </c:if>

        <c:if test="${not empty billets}">
            <div class="cards">
                <c:forEach var="b" items="${billets}">
                    <div class="card" id="card-${b.id}">
                        <h3>Billet #${b.id}</h3>
                       <%-- <p>Date d'émission : ${b.dateEmission}</p> --%>


                        <c:if test="${not empty b.reservation}">
                            <c:if test="${not empty b.reservation.utilisateur}">
                                <p><strong>Réservé par :</strong> ${b.reservation.utilisateur.prenom } ${b.reservation.utilisateur.nom} </p>
                            </c:if>

                            <c:if test="${not empty b.reservation.voyage}">
                                <p><strong>Voyage :</strong>
                                    <c:if test="${not empty b.reservation.voyage.trajet}">
                                        ${b.reservation.voyage.trajet.depart} → ${b.reservation.voyage.trajet.arrivee}
                                    </c:if>
                                    <c:if test="${empty b.reservation.voyage.trajet}">
                                        <span style="color:red;">Information trajet non disponible</span>
                                    </c:if>
                                </p>
                                <p><strong>Date de voyage :</strong> ${b.reservation.voyage.dateVoyage}</p>
                                <p><strong>Horaire de départ  :</strong> ${b.reservation.voyage.heureDepart}</p>
                                
                                 <p><strong>Horaire d'arrivée :</strong> ${b.reservation.voyage.heureArrivee}</p>
                                 
                                
                            </c:if>

                            <p><strong>Date de réservation :</strong> ${b.reservation.dateReservation}</p>
                        </c:if>

                        <button class="btn-print" onclick="printCard('card-${b.id}')">🖨️ Imprimer ce billet</button>
                    </div>
                </c:forEach>
            </div>
        </c:if>

        <p><a href="ReservationController?action=mesReservations" class="btn-return">← Retour à mes réservations</a></p>
    </div>

</body>
</html>
