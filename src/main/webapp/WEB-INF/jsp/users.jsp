<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" --%>
<%--@ taglib prefix="fn" uri="http://restaurant_voting.ivan_kropachev.org/functions" --%>
<html>
<head>
    <title>Users</title>
    <link rel="stylesheet" href="../../css/style.css">
</head>
<body>
<section>
    <h3><a href="home">Home</a></h3>
    <hr/>
    <h2>Users</h2>

    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>E-mail</th>
            <th>Password</th>
            <th>Role</th>
            <th>Enabled</th>
            <th>Registered</th>
        </tr>
        </thead>
        <c:forEach items="${users}" var="user">
            <jsp:useBean id="user" scope="page" type="org.ivan_kropachev.restaurant_voting.model.User"/>
            <tr>
                <td>${user.id}</td>
                <td><c:out value="${user.name}"/></td>
                <td><a href="mailto:${user.email}">${user.email}</a></td>
                <td>${user.password}</td>
                <td>${user.roles}</td>
                <td><%=user.isEnabled()%></td>
                <td>${user.registered}</td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>