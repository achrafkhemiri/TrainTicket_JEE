<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Bienvenue - BilletExpress</title>
    <link rel="stylesheet" href="../Styles/styleWelcome.css">
</head>
<body>

<jsp:include page="navbar.jsp" />

<div class="welcome-container">
    <div class="welcome-text">
        <h1>Bienvenue sur <span>BilletExpress</span></h1>
        <p>
            Trouvez et réservez facilement vos billets de train dans toute la Tunisie.
           
        </p>
        <a href="${pageContext.request.contextPath}/ViewsClient/home.jsp" class="btn-start">
            <i class="fas fa-search-location"></i> Commencer la recherche
        </a>
    </div>

    <div class="welcome-image">
        <img src="../assets/train-station.png" alt="Train illustration">
    </div>
</div>

</body>
</html>
