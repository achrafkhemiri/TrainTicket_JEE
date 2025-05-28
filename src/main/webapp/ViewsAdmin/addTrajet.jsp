<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Gestion des Trajets</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
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
             max-width: 1200px;  margin-bottom: 40px;
  
        }

        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
        }

        select {
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

    <h2>Ajouter un nouveau Trajet</h2>

    <form action="${pageContext.request.contextPath}/TrajetController" method="post">
        <label for="depart">Gare de départ :</label>
        <select name="departId" id="depart" required>
            <c:forEach var="gare" items="${gares}">
                <option value="${gare.id}">${gare.ville} - ${gare.nom}</option>
            </c:forEach>
        </select>

        <label for="arrivee">Gare d’arrivée :</label>
        <select name="arriveeId" id="arrivee" required>
            <c:forEach var="gare" items="${gares}">
                <option value="${gare.id}">${gare.ville} - ${gare.nom}</option>
            </c:forEach>
        </select>

  <label for="passages">Gares de passage :</label>
<div style="display: flex; flex-wrap: wrap; gap: 12px; margin-top: 10px;">
    <c:forEach var="gare" items="${gares}">
        <label style="
            display: flex;
            align-items: center;
            gap: 6px;
            padding: 6px 10px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 0.9em;
            background-color: #fff;
            cursor: pointer;
            transition: background 0.2s, border-color 0.2s;
        ">
            <input type="checkbox" name="passages" value="${gare.id}" style="margin: 0;" />
            ${gare.ville} - ${gare.nom}
        </label>
    </c:forEach>
</div>

        <input type="submit" value="Ajouter le Trajet" />
    </form>

    <h2>Liste des Trajets</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>Départ</th>
            <th>Arrivée</th>
            <th>Gares de passage</th>
        </tr>
        <c:forEach var="trajet" items="${listeTrajets}">
            <tr>
                <td>${trajet.id}</td>
                <td>${trajet.depart.ville} - ${trajet.depart.nom}</td>
                <td>${trajet.arrivee.ville} - ${trajet.arrivee.nom}</td>
              <td>
    <div style="display: flex; flex-wrap: wrap; gap: 6px;">
        <c:forEach var="g" items="${trajet.garesDePassage}">
            <span style="
                background-color: #e1ecf4;
                color: #0366d6;
                padding: 6px 12px;
                border-radius: 20px;
                font-size: 0.9em;
                white-space: nowrap;
            ">
                ${g.ville} - ${g.nom}
            </span>
        </c:forEach>
    </div>
</td>

            </tr>
        </c:forEach>
    </table>

</div>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        const departSelect = document.getElementById('depart');
        const arriveeSelect = document.getElementById('arrivee');

        function updateArriveeOptions() {
            const selectedDepart = departSelect.value;

            Array.from(arriveeSelect.options).forEach(option => {
                option.disabled = option.value === selectedDepart;
            });

            // Auto-select another option if the current selection is disabled
            if (arriveeSelect.value === selectedDepart) {
                for (let option of arriveeSelect.options) {
                    if (!option.disabled) {
                        arriveeSelect.value = option.value;
                        break;
                    }
                }
            }
        }

        // Initial call on page load
        updateArriveeOptions();

        // Update on change
        departSelect.addEventListener('change', updateArriveeOptions);
    });
</script>


</body>
</html>
