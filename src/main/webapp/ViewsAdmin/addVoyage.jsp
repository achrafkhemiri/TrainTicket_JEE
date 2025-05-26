<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Gestion des Voyages</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, sans-serif;
            background: #f4f7fb;
            margin: 0;
            padding: 0;
        }

        .main-content {
            margin-left: 220px;
            padding: 30px;
        }

        h2 {
            color: #2c3e50;
            font-size: 1.8em;
            border-left: 6px solid #3498db;
            padding-left: 12px;
            margin-bottom: 20px;
        }

        form {
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 3px 6px rgba(0,0,0,0.1);
            max-width: 1200px;
            margin-bottom: 40px;
        }

        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
        }

        input, select {
            width: 100%;
            padding: 8px 12px;
            margin-top: 5px;
            border: 1.5px solid #ccc;
            border-radius: 5px;
            font-size: 1em;
        }

        input[type="submit"] {
            margin-top: 20px;
            background-color: #3498db;
            color: white;
            border: none;
            padding: 10px 25px;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
            font-size: 1.1em;
        }

        input[type="submit"]:hover {
            background-color: #2980b9;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background: white;
            box-shadow: 0 2px 6px rgba(0,0,0,0.1);
        }

        th, td {
            padding: 14px 20px;
            border-bottom: 1px solid #eee;
            text-align: left;
        }

        th {
            background-color: #3498db;
            color: white;
        }

        tr:hover {
            background-color: #f0f8ff;
        }
    </style>
</head>
<body>

<jsp:include page="sidebar.jsp" />

<div class="main-content">

    <h2>Ajouter un nouveau Voyage</h2>

    <form action="${pageContext.request.contextPath}/VoyageController" method="post">
        <label for="trajet">Trajet :</label>
        <select name="trajetId" id="trajet" required>
            <c:forEach var="t" items="${trajets}">
                <option value="${t.id}">${t.depart.ville} → ${t.arrivee.ville}</option>
            </c:forEach>
        </select>
        <div id="gares-container" style="margin-top: 10px; font-size: 0.95em; color: #333;">
    <strong>Gares intermédiaires :</strong>
    <div id="gares-passage"></div>
</div> 

        <script>
    const trajetsMap = {
        <c:forEach var="t" items="${trajets}" varStatus="status">
            "${t.id}": [
                <c:forEach var="g" items="${t.garesDePassage}" varStatus="s">
                    "${g.ville} - ${g.nom}"<c:if test="${!s.last}">,</c:if>
                </c:forEach>
            ]<c:if test="${!status.last}">,</c:if>
        </c:forEach>
    };

    const selectTrajet = document.getElementById('trajet');
    const container = document.getElementById('gares-passage');

    function updateGares() {
        const trajetId = selectTrajet.value;
        const gares = trajetsMap[trajetId] || [];

        container.innerHTML = gares.length > 0
            ? gares.map(g => `<span style="display:inline-block; background:#eaf4fb; padding:6px 10px; margin:4px; border-radius:12px;">${g}</span>`).join('')
            : "<em>Aucune gare intermédiaire</em>";
    }

    selectTrajet.addEventListener('change', updateGares);

    // Initialise à l'ouverture
    window.addEventListener('DOMContentLoaded', updateGares);
</script>
        

        <label for="date">Date du voyage :</label>
        <input type="date" name="dateVoyage" required />

        <label for="heureDepart">Heure de départ :</label>
        <input type="time" name="heureDepart" required />

        <label for="heureArrivee">Heure d’arrivée :</label>
        <input type="time" name="heureArrivee" required />

        <label for="prix">Prix :</label>
     
<input type="number" name="prixVoyage" step="0.01" required />

        <label for="places">Nombre de places disponibles :</label>
        <input type="number" name="nbPlacesDispo" required />

 
        <input type="submit" value="Ajouter le Voyage" />
    </form>

    <h2>Liste des Voyages</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>Trajet</th>
            <th>Date</th>
            <th>Heure Départ</th>
            <th>Heure Arrivée</th>
            <th>Prix</th>
            <th>Places</th>
        </tr>
        <c:forEach var="v" items="${listeVoyages}">
            <tr>
                <td>${v.id}</td>
                <td>${v.trajet.depart.ville} → ${v.trajet.arrivee.ville}</td>
                <td>${v.dateVoyage}</td>
                <td>${v.heureDepart}</td>
                <td>${v.heureArrivee}</td>
                <td>${v.prixVoyage} DT</td>
                <td>${v.nbPlacesDispo}</td>
            </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>
