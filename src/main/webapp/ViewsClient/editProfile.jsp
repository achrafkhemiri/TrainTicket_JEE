<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    model.Utilisateur user = (model.Utilisateur) session.getAttribute("utilisateur");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Modifier le Profil</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, sans-serif;
            background-color: #f5f7fa;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 600px;
            margin: 50px auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.08);
        }

        h2 {
            text-align: center;
            color: #2c3e50;
            margin-bottom: 30px;
        }

        label {
            font-weight: 600;
            display: block;
            margin-top: 15px;
        }

        input[type="text"],
        input[type="email"],
        input[type="password"] {
            width: 100%;
            padding: 10px 14px;
            border-radius: 6px;
            border: 1.5px solid #ccc;
            font-size: 1rem;
            margin-top: 5px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #3498db;
            color: white;
            font-weight: bold;
            border: none;
            padding: 12px 20px;
            font-size: 1.1rem;
            margin-top: 25px;
            border-radius: 6px;
            cursor: pointer;
            width: 100%;
        }

        input[type="submit"]:hover {
            background-color: #2980b9;
        }

        .back-link {
            text-align: center;
            margin-top: 20px;
        }

        .back-link a {
            text-decoration: none;
            color: #3498db;
            font-weight: 500;
        }

        .back-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<jsp:include page="navbar.jsp"/>

<div class="container">
    <h2><i class="fas fa-user-edit"></i> Modifier mon profil</h2>

    <form action="${pageContext.request.contextPath}/EditProfile" method="post">
     <label for="email">Email</label>
        <input type="email" name="email" value="${utilisateur.email}" disabled>
    
    
        <label for="nom">Nom</label>
        <input type="text" name="nom" value="${utilisateur.nom}" required>

        <label for="prenom">Prénom</label>
        <input type="text" name="prenom" value="${utilisateur.prenom}" required>

       
        <label for="motDePasse">Mot de passe</label>
        <input type="password" name="motDePasse" value="${utilisateur.motDePasse}" required>

        <input type="submit" value="Enregistrer les modifications">
    </form>
 
</div>

</body>
</html>
