<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Formulaire de Réservation</title>
    <link rel="stylesheet" href="Styles/styleReservation.css" />
</head>
<body>

<style>
body {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background: #f4f7fb;
    margin: 0;
    padding: 40px;
    display: flex;
    justify-content: center;
    align-items: flex-start;
    min-height: 100vh;
    color: #2c3e50;
}

/* Conteneur principal du formulaire */
.form-container {
    background: #fff;
    padding: 15px;
    border-radius: 12px;
    box-shadow: 0 4px 10px rgba(0,0,0,0.1);
    max-width: 400px;
    width: 50%;
    /*box-sizing: border-box;*/
    margin:  auto; /* centre horizontalement */
    max-height: auto;
}

/* Titre */
.form-container h5 {
    color: #2c3e50;
    font-size: 1.4em;
    border-left: 4px solid #3498db;
    padding-left: 10px;
    margin-bottom: 20px;
    font-weight: 700;
}

/* Formulaire */
form {
    display: flex;
    flex-direction: column;
}

/* Étiquettes */
label {
    margin-top: 16px;
    font-weight: 600;
    color: #34495e;
    font-size: 0.95em;
}

/* Champs de saisie */
input[type="text"],
input[type="number"] {
    width: 100%;
    padding: 8px 10px;
    margin-top: 6px;
    border: 1.5px solid #ccc;
    border-radius: 5px;
    font-size: 0.95em;
    transition: border-color 0.3s ease, box-shadow 0.3s ease;
    box-sizing: border-box;
}

input[type="text"]:disabled {
    background-color: #eaeef3;
    color: #7f8c8d;
    cursor: not-allowed;
}

input[type="text"]:focus,
input[type="number"]:focus {
    border-color: #3498db;
    outline: none;
    box-shadow: 0 0 6px rgba(52, 152, 219, 0.4);
}

/* Bouton de validation */
input[type="submit"] {
    margin-top: 25px;
    background-color: #3498db;
    color: white;
    border: none;
    padding: 10px 25px;
    border-radius: 5px;
    cursor: pointer;
    font-weight: 700;
    font-size: 1em;
    transition: background-color 0.3s ease;
    width: 100%;
    box-sizing: border-box;
}

input[type="submit"]:hover {
    background-color: #2980b9;
}

/* Toast de succès */
#toast {
    background-color: #4CAF50;
    color: white;
    padding: 14px 22px;
    position: fixed;
    top: 20px;
    right: 20px;
    z-index: 1000;
    border-radius: 6px;
    box-shadow: 0 3px 6px rgba(0,0,0,0.2);
    font-weight: 600;
    display: flex;
    align-items: center;
    gap: 8px;
    font-size: 0.95em;
}

/* Responsive */
@media (max-width: 480px) {
    body {
        padding: 20px 10px;
        flex-direction: column;
        align-items: center;
    }

    .form-container {
        padding: 18px 20px;
        max-width: 100%;
    }

    .form-container h5 {
        font-size: 1.3em;
        padding-left: 8px;
    }
}
</style>

<c:if test="${param.success == 'true'}">
    <div id="toast">✅ Réservation effectuée avec succès !</div>
    <script>
        setTimeout(() => {
            document.getElementById("toast").style.display = "none";
        }, 3000);
    </script>
</c:if>



<!-- CONTAINER DU FORMULAIRE -->
<div class="form-container">
    <h5>Formulaire de réservation</h5>

    <form action="ReservationController" method="post">
        <input type="hidden" name="voyageId" value="${voyage.id}">

        <label>Utilisateur :</label>
        <input type="text" value="${utilisateur.nom}" disabled />

        <label>Voyage :</label>
        <input type="text" value="${voyage.trajet.depart.ville} → ${voyage.trajet.arrivee.ville}" disabled />

        <label>Date de réservation :</label>
        <input type="text" value="${dateReservation}" disabled />

        <label>Nombre de personnes :</label>
        <input type="number" id="nbPersonnes" name="nbPersonnes" min="1" required />

        <label>Prix total :</label>
        <input type="text" id="prixTotal" disabled />

        <input type="submit" value="Valider la réservation" />
    </form>
</div>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        const prixUnitaire = parseFloat("${voyage.prixVoyage}");
        const inputNb = document.getElementById("nbPersonnes");
        const inputTotal = document.getElementById("prixTotal");

        function updateTotal() {
            const nb = parseInt(inputNb.value) || 0;
            const total = nb * prixUnitaire;
            inputTotal.value = isNaN(total) ? "" : total.toFixed(2) + " Dt";
        }

        inputNb.addEventListener("input", updateTotal);
        if (inputNb.value) updateTotal();
    });
</script>

</body>
</html>
