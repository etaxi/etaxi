<%@ page import="entity.Admin" %><%--
  Created by IntelliJ IDEA.
  User: G
  Date: 07.04.2016
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP Page</title>
</head>
<body>

<a href="/admin"> Main admin menu </a> <br>

<div class="container">

    <h1>Admin data change</h1>

    <%  Admin admin = (Admin) session.getAttribute("admin");%>

    <form role="form" name = form1 action="/admin/adminEditProfile" method="POST">
        <div class="form-group">
            <label for="usr">Name, Surname:</label>
            <input type="text" name="name" value="<%=admin.getName()%>" placeholder= "Name" class="form-control" id="usr" required>
        </div>
        <div class="form-group">
            <label for="lgn">Login:</label>
            <input type="text" name="login" value="<%=admin.getLogin()%>" placeholder= "login" class="form-control" id="lgn" required>
        </div>
        <div class="form-group">
            <label for="pwd">Password:</label>
            <input type="password" name="password" value="<%=admin.getPassword()%>" placeholder="Password" class="form-control" id="pwd" required>
        </div>
        <input type="submit" value="Save data" style='width:265px;'>

        <h3><%=request.getAttribute("message")%></h3>
    </form>

</div> <!-- /container -->

</body>
</html>
