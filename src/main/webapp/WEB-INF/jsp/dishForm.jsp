<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Dish</title>
    <link rel="stylesheet" href="../../css/style.css">
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr>
    <h2>${param.action == 'create' ? 'Create dish' : 'Edit dish'}</h2>
    <jsp:useBean id="dish" type="org.ivan_kropachev.restaurant_voting.model.Dish" scope="request"/>
    <form method="post" action="dishes">
        <input type="hidden" name="id" value="${dish.id}">
        <dl>
            <dt>Name:</dt>
            <dd><input type="text" value="${dish.name}" name="name" required></dd>
        </dl>
        <dl>
            <dt>Restaurant:</dt>
            <dd><input type="number" value="${dish.restaurantId}" name="restaurantId" required></dd>
        </dl>
        <dl>
            <dt>Price:</dt>
            <dd><input type="number" value="${dish.price}" name="price" required></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>