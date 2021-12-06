<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" --%>
<%--@ taglib prefix="fn" uri="http://restaurant_voting.ivan_kropachev.org/functions" --%>
<html>
<head>
    <title>Votes</title>
    <link rel="stylesheet" href="../../css/style.css">
</head>
<body>
<section>
    <h3><a href="home">Home</a></h3>
    <hr/>
    <h2>Votes</h2>

    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Id</th>
            <th>User</th>
            <th>Date and time</th>
            <th>Restaurant</th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.votes}" var="vote">
            <tr>
                <td>${vote.id}</td>
                <td>${vote.userId}</td>
                <td>${vote.dateTime}</td>
                <td>${vote.restaurantId}</td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
