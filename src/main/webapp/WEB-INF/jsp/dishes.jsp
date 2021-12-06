<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Dishes</title>
    <link rel="stylesheet" href="../../css/style.css">
</head>
<body>
<section>
    <h3><a href="home">Home</a></h3>
    <hr/>
    <h2>Dishes</h2>
    <hr/>
    <a href="dishes?action=create">Add Dish</a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Restaurant</th>
            <th>Price</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.dishes}" var="dish">
            <tr>
                <td>${dish.id}</td>
                <td>${dish.name}</td>
                <td>${dish.restaurantId}</td>
                <td>${dish.price}</td>
                <td><a href="dishes/update?id=${dish.id}">Update</a></td>
                <td><a href="dishes/delete/${dish.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
