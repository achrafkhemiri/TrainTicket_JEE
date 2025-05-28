<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Gestion des Trains</title>
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
            max-width: 400px;
            margin-bottom: 40px;
        }

        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
        }

        input[type="text"], input[type="time"] {
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

    <h2>Ajouter un nouveau Train</h2>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/addTrain" method="post">
        <label for="name">Nom du train :</label>
        <input type="text" id="name" name="name" required />

        <label for="type">Type :</label>
        <input type="text" id="type" name="type" required />

      <%-- Champ heure de départ désactivé temporairement
<label for="departureTime">Heure de départ :</label>
<input type="time" id="departureTime" name="departureTime" required />
--%>
      

        <input type="submit" value="Ajouter" />
    </form>

    <h2>Liste des Trains</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Type</th>
           <%-- <th>Heure de départ</th>--%>
        </tr>
        <c:forEach var="train" items="${listTrain}">
            <tr>
                <td>${train.id}</td>
                <td>${train.name}</td>
                <td>${train.type}</td>
                <%-- <td>${train.departureTime}</td> --%>

            </tr>
        </c:forEach>
    </table>

</div>

</body>
</html>
