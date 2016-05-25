<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <title>Spring Security authentication </title>
</head>
<body>
<%--<div th:if="${param.error}">--%>
    <%--Invalid username and password.--%>
<%--</div>--%>
<%--<div th:if="${param.logout}">--%>
    <%--You have been logged out.--%>
<%--</div>--%>
<h1> Please, login to ETAXI </h1>
<h3> SPRING SECURITY authentication </h3>

<BR>

<form th:action="@{/login}" method="post">
    <div><label> Login:    <input type="text" name="username"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <div><input type="submit" value="Sign In"/></div>
</form>

<BR>

<div class="container">
    <form class="form-signin" action="/customer/customerRegistration" method="GET">
        <button class="btn btn-lg btn-primary btn-block" type="submit">New customer registration</button>
    </form>
</div>

</body>
</html>
