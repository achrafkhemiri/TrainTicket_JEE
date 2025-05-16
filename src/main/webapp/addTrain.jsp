<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Train</title>
</head>
<body>
<h2>Add New Train</h2>
 
<form action="addTrain" method="post">
    Name: <input type="text" name="name" required><br/>
    Type: <input type="text" name="type" required><br/>
    Departure Time: <input type="text" name="departureTime" placeholder="HH:mm" required><br/>
    <input type="submit" value="Create Train">
</form>

</body>
</html>
