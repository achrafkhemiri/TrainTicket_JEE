<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Réservation - Options</title>
    <link rel="stylesheet" href="Styles/styleRes.css">
</head>
<body>
<jsp:include page="Header.jsp"/>

<div class="container">
    <h2>Réserver votre trajet</h2>

    <div class="trajet-card">
        <p><strong>Départ :</strong> ${trajet.villeDepart} à ${trajet.heureDepart}</p>
        <p><strong>Arrivée :</strong> ${trajet.villeArrivee} à ${trajet.heureArrivee}</p>
        <p><strong>Prix :</strong> ${trajet.prix} DT</p>
    </div>

    <form action="confirmerReservation" method="post">
        <input type="hidden" name="trajetId" value="${trajet.id}" />

        <label for="classe">Classe :</label>
        <select name="classe" required>
            <option value="">-- Choisir --</option>
            <option value="1ere">1ère classe</option>
            <option value="2eme">2ème classe</option>
            <option value="eco">Économique</option>
        </select>

        <label>Préférences :</label>
        <div class="checkboxes">
            <label><input type="checkbox" name="options" value="fenetre"> Côté fenêtre</label>
            <label><input type="checkbox" name="options" value="famille"> Espace famille</label>
            <label><input type="checkbox" name="options" value="nonfumeur"> Wagon non-fumeur</label>
        </div>

        <input type="submit" value="Confirmer la réservation">
    </form>
</div>

<jsp:include page="Footer.jsp"/>
</body>
</html>
