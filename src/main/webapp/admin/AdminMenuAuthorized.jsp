<%@ page import="lv.etaxi.entity.Admin" %><%--
  Created by IntelliJ IDEA.
  User: G
  Date: 06.04.2016
  Time: 13:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%  Admin admin = (Admin) session.getAttribute("admin"); %>
<div><h3>Welcome, <%=admin.getName()%> (login: <%=admin.getLogin()%>)</h3></div>

<h3>Admin menu:</h3>

<a href="/admin/adminEditProfile">Edit profile</a><br>
<a href="/admin/adminHistoryOfOrders">History of all orders</a><br>


<a href="/admin/signOut">Sing out</a><br>

<h3><%=request.getAttribute("message")%></h3>

</body>
</html>
