<%--
  Created by IntelliJ IDEA.
  User: G
  Date: 29.03.2016
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div class="container">

    <h1>New admin registration</h1>

    <form role="form" name = form1 action="/admin/adminRegistration" method="POST">
        <div class="form-group">
            <label for="usr">Name, Surname:</label>
            <input type="text" name="name" placeholder= "Name" class="form-control" id="usr" required>
        </div>
        <div class="form-group">
            <label for="lgn">Login:</label>
            <input type="text" name="login" placeholder= "login" class="form-control" id="lgn" required>
        </div>
        <div class="form-group">
            <label for="pwd">Password:</label>
            <input type="password" name="password" placeholder="Password" class="form-control" id="pwd" required>
        </div>
        <input type="submit" value="Register new admin" style='width:265px;'>
    </form>

</div> <!-- /container -->


<h3><%=request.getAttribute("message")%></h3>

</body>
</html>
