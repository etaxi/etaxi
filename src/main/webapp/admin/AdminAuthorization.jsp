<%--
  Created by IntelliJ IDEA.
  User: G
  Date: 29.03.2016
  Time: 13:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP page</title>
</head>
<body>

<h1>Admin authorization</h1>
<div class="container">
    <form class="form-signin" action="/admin/adminAuthorization" method="POST">
        <h2 class="form-signin-heading">Dear admin, please sign in</h2>
        <label for="inputLogin" class="sr-only">Login</label>
        <input type="text" name="login" id="inputLogin" class="form-control" placeholder="Login" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>
</div> <!-- /container -->

<div class="container">
    <form class="form-signin" action="/admin/adminRegistration" method="GET">
        <button class="btn btn-lg btn-primary btn-block" type="submit">New admin registration</button>
    </form>
</div> <!-- /container -->

<a href="index.html">eTaxi portal</a><br>

<h3><%=request.getAttribute("message")%></h3>

</body>
</html>