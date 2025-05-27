<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Formulaire de Réservation</title>
    <link rel="stylesheet" href="Styles/styleReservation.css" />
</head>
<body>

<jsp:include page="navbar.jsp" />

<c:if test="${param.success == 'true'}">
    <div id="toast" style="background-color: #4CAF50; color: white; padding: 15px; position: fixed; top: 20px; right: 20px; z-index: 1000; border-radius: 5px;">
        ✅ Réservation effectuée avec succès !
    </div>
    <script>
        setTimeout(function () {
            document.getElementById("toast").style.display = "none";
        }, 3000);
    </script>
</c:if>

<h2>Formulaire de réservation</h2>

<form action="ReservationController" method="post">
    <input type="hidden" name="voyageId" value="${voyage.id}">

    <label>Utilisateur :</label>
    <input type="text" value="${utilisateur.nom}" disabled />
    <br/>

    <label>Voyage :</label>
    <input type="text" value="${voyage.trajet.depart.ville} → ${voyage.trajet.arrivee.ville}" disabled />
    <br/>

    <label>Date de réservation :</label>
    <input type="text" value="${dateReservation}" disabled />
    <br/>

    <label>Nombre de personnes :</label>
    <input type="number" id="nbPersonnes" name="nbPersonnes" min="1" required />
    <br/>
    
    <label>Prix total :</label>
    <input type="text" id="prixTotal" disabled />
    <br/>

    <input type="submit" value="Valider la réservation" />
</form>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        const prixUnitaire = parseFloat("${voyage.prixVoyage}");
        const inputNb = document.getElementById("nbPersonnes");
        const inputTotal = document.getElementById("prixTotal");

        // Calcul initial si une valeur est déjà présente
        if (inputNb.value) {
            updateTotal();
        }

        inputNb.addEventListener("input", updateTotal);

        function updateTotal() {
            const nb = parseInt(inputNb.value) || 0;
            const total = nb * prixUnitaire;
            inputTotal.value = isNaN(total) ? "" : total.toFixed(2) + " €";
        }
    });
</script>

</body>
</html>