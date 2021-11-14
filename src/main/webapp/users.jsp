<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" --%>
<%--@ taglib prefix="fn" uri="http://restaurant_voting.ivan_kropachev.org/functions" --%>
<html>
<head>
    <title>Users</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr/>
    <h2>Users</h2>

    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Id</th>
            <th>Named</th>
            <th>E-mail</th>
            <th>Password</th>
            <th>Privileged</th>
            <th>Date and time of last vote</th>
            <th>Voted restaurant</th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td>${user.password}</td>
                <td>${user.privileged}</td>
                <td>${user.voteDateTime}</td>
                <td>${user.restaurantId}</td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>