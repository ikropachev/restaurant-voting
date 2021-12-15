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
Application without frontend. Authorization for any user will show examples of all REST-requests for both.</br>
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
<hr>
List of using technologies:
<div class="jumbotron py-0">
    <div class="container">
        <div class="lead py-4"> <br>
            <a href="http://projects.spring.io/spring-security/">Spring Security</a>,
            <a href="https://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html">Spring MVC</a>,
            <a href="http://projects.spring.io/spring-data-jpa/">Spring Data JPA</a>,
            <a href="http://spring.io/blog/2014/05/07/preview-spring-security-test-method-security">Spring Security
                Test</a>,
            <a href="http://hibernate.org/orm/">Hibernate ORM</a>,
            <a href="http://hibernate.org/validator/">Hibernate Validator</a>,
            <a href="http://www.slf4j.org/">SLF4J</a>,
            <a href="https://github.com/FasterXML/jackson">Json Jackson</a>,
            <a href="http://ru.wikipedia.org/wiki/JSP">JSP</a>,
            <a href="http://en.wikipedia.org/wiki/JavaServer_Pages_Standard_Tag_Library">JSTL</a>,
            <a href="http://tomcat.apache.org/">Apache Tomcat</a>,
            <a href="http://www.webjars.org/">WebJars</a>,
            <a href="http://datatables.net/">DataTables</a>,
            <a href="http://ehcache.org">EHCACHE</a>,
            <a href="http://www.postgresql.org/">PostgreSQL</a>,
            <a href="http://hsqldb.org/">HSQLDB</a>,
            <a href="https://junit.org/junit5/">JUnit 5</a>,
            <a href="http://hamcrest.org/JavaHamcrest/">Hamcrest</a>,
            <a href="https://assertj.github.io/doc/">AssertJ</a>,
            <a href="http://jquery.com/">jQuery</a>,
            <a href="https://plugins.jquery.com/">jQuery plugins</a>,
            <a href="http://getbootstrap.com/">Bootstrap</a>.
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