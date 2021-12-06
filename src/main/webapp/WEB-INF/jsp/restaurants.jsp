<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" --%>
<%--@ taglib prefix="fn" uri="http://restaurant_voting.ivan_kropachev.org/functions" --%>
<html>
<head>
    <title>Restaurants</title>
    <link rel="stylesheet" href="../../css/style.css">
</head>
<body>
<section>
    <h3><a href="home">Home</a></h3>
    <hr/>
    <h2>Restaurants</h2>
    <hr/>
    <a href="restaurants?action=create">Add Restaurant</a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.restaurants}" var="restaurant">
            <tr>
                <td>${restaurant.id}</td>
                <td>${restaurant.name}</td>
                <td><a href="restaurants/update&id=${restaurant.id}">Update</a></td>
                <td><a href="restaurants/delete&id=${restaurant.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
