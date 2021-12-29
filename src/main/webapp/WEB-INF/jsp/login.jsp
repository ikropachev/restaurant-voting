<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Restaurant Voting (by Ivan Kropachev)</title>
</head>
<body>
<h3>Project "Restaurant Voting" (by Ivan Kropachev)</h3>
<hr>
Application without frontend.
<br>Authorization for any user will redirect to index with examples of all REST-requests for both from
command-line.</br>
<br>user@gmail.com:user - sign in like user.</br>
<br>admin@gmail.com:admin - sign in like admin.</br>
<hr>
<div class="container">
    <form class="form-inline my-2" id="login_form" action="spring_security_check" method="post">
        Username:
        <input class="form-control mr-1" type="text" placeholder="Email" name="username">
        Password:
        <input class="form-control mr-1" type="password" placeholder="Password" name="password">
        <button class="btn btn-success" type="submit">
            <span class="fa fa-sign-in"></span> Submit
        </button>
    </form>
</div>
<br>Or open API:
<div class="container">
    <div class="lead"></div>
    <button onclick="window.open('swagger-ui.html')" id="swaggerButton" class="btn btn-success">Swagger REST Api
        Documentation
    </button>
</div>
<hr>
List of using technologies:
<div class="jumbotron py-0">
    <div class="container">
        <div class="lead py-4">
            <a href="http://projects.spring.io/spring-security/">Spring Security</a>,
            <br><a href="https://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html">Spring
            MVC</a>,
            <br><a href="http://spring.io/blog/2014/05/07/preview-spring-security-test-method-security">Spring Security
            Test</a>,
            <br><a href="http://hibernate.org/orm/">Hibernate ORM</a>,
            <br><a href="http://hibernate.org/validator/">Hibernate Validator</a>,
            <br><a href="http://www.slf4j.org/">SLF4J</a>,
            <br><a href="https://github.com/FasterXML/jackson">Json Jackson</a>,
            <br><a href="http://ru.wikipedia.org/wiki/JSP">JSP</a>,
            <br><a href="http://en.wikipedia.org/wiki/JavaServer_Pages_Standard_Tag_Library">JSTL</a>,
            <br><a href="http://tomcat.apache.org/">Apache Tomcat</a>,
            <br><a href="http://ehcache.org">EHCACHE</a>,
            <br><a href="http://www.postgresql.org/">PostgreSQL</a>,
            <br><a href="http://hsqldb.org/">HSQLDB</a>,
            <br><a href="https://junit.org/junit5/">JUnit 5</a>,
            <br><a href="https://swagger.io/specification/v2/">Swagger v2</a>
        </div>
    </div>
</div>
<div class="container lead">
</div>
<script type="text/javascript">
    function login(username, password) {
        $('input[name="username"]').val(username);
        $('input[name="password"]').val(password);
        $("#login_form").submit();
    }
</script>
</body>
</html>