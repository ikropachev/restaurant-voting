<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" --%>
<%--@ taglib prefix="fn" uri="http://restaurant_voting.ivan_kropachev.org/functions" --%>
<html>
<head>
    <title>Menus</title>
    <link rel="stylesheet" href="../../css/style.css">
</head>
<body>
<section>
    <h3><a href="home">Home</a></h3>
    <hr/>
    <h2>Menus</h2>
    <hr/>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Restaurant Id</th>
            <th>Name</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.restaurants}" var="restaurant">
            <tr>
                <td>${restaurant.id}</td>
                <td>${restaurant.name}</td>
            </tr>
            <tr>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
