<%@ page import="lv.etaxi.MVC.MVCModel" %>
<%@ page import="lv.etaxi.dto.AdminDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<% AdminDTO adminDTO = (AdminDTO) session.getAttribute("adminDTO");
    if (adminDTO != null) { %>
<div><h3>Welcome, <%=adminDTO.getName()%>! </h3></div>
<%
    }
%>

<h3>Admin menu:</h3>

<a href="/admin/adminEditProfile">Edit profile</a><br>
<a href="/admin/signOut">Sing out</a><br>

<%
    if (request.getAttribute("model") != null) {
        MVCModel model = (MVCModel) request.getAttribute("model");
%>
<h3><%=model.getMessage()%></h3>
<%
    }
%>

</body>
</html>

