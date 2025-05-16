<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Gestion des Gares</title>
</head>
<body>
<h2>Ajouter une nouvelle gare</h2>

<form action="GareController" method="post">
    Nom: <input type="text" name="nom" required /><br/>
    Ville: <input type="text" name="ville" required /><br/>
    <input type="submit" value="Ajouter" />
</form>

<hr/>

<h2>Liste des gares</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Ville</th>
    </tr>
    <c:forEach var="gare" items="${listGare}">
        <tr>
            <td>${gare.id}</td>
            <td>${gare.nom}</td>
            <td>${gare.ville}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
