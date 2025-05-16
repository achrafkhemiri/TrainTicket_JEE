<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Gestion des Gares</title>
    <style>
        /* Reset léger */
        body, h2, table, form {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        body {
            background-color: #f5f7fa;
            color: #333;
            padding: 30px;
            line-height: 1.6;
        }

        h2 {
            margin-bottom: 15px;
            color: #2c3e50;
            font-weight: 700;
            font-size: 1.8em;
            border-left: 6px solid #3498db;
            padding-left: 12px;
        }

        form {
            background: white;
            padding: 20px 25px;
            border-radius: 8px;
            box-shadow: 0 3px 6px rgba(0,0,0,0.1);
            max-width: 400px;
            margin-bottom: 30px;
        }

        form label {
            display: block;
            margin-bottom: 6px;
            font-weight: 600;
            color: #34495e;
        }

        form input[type="text"] {
            width: 100%;
            padding: 8px 12px;
            margin-bottom: 15px;
            border: 1.5px solid #ccc;
            border-radius: 5px;
            font-size: 1em;
            transition: border-color 0.3s ease;
        }

        form input[type="text"]:focus {
            border-color: #3498db;
            outline: none;
            box-shadow: 0 0 5px rgba(52,152,219,0.5);
        }

        form input[type="submit"] {
            background-color: #3498db;
            color: white;
            border: none;
            padding: 10px 25px;
            border-radius: 5px;
            cursor: pointer;
            font-weight: 700;
            font-size: 1.1em;
            transition: background-color 0.3s ease;
        }

        form input[type="submit"]:hover {
            background-color: #2980b9;
        }

        hr {
            margin: 40px 0;
            border: none;
            border-top: 1px solid #ddd;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background: white;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 3px 6px rgba(0,0,0,0.1);
        }

        table th, table td {
            padding: 14px 20px;
            text-align: left;
            border-bottom: 1px solid #eee;
            font-size: 1em;
        }

        table th {
            background-color: #3498db;
            color: white;
            font-weight: 700;
        }

        table tr:hover {
            background-color: #f0f8ff;
        }

        /* Responsive */
        @media (max-width: 480px) {
            form, table {
                width: 100%;
                font-size: 0.9em;
            }

            form input[type="submit"] {
                width: 100%;
                padding: 12px;
            }
        }
    </style>
</head>
<body>
<h2>Ajouter une nouvelle gare</h2>

<form action="${pageContext.request.contextPath}/GareController" method="post">
    <label for="nom">Nom :</label>
    <input type="text" id="nom" name="nom" required />

    <label for="ville">Ville :</label>
    <input type="text" id="ville" name="ville" required />

    <input type="submit" value="Ajouter" />
</form>

<hr/>

<h2>Liste des gares</h2>
<table border="1" cellpadding="5" cellspacing="0">
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
