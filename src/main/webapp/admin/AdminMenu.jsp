<%@ page import="lv.etaxi.entity.Admin" %><%--
  Created by IntelliJ IDEA.
  User: G
  Date: 29.03.2016
  Time: 13:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Admin admin = (Admin) session.getAttribute("admin");
    if (admin != null) {  %>
<div id="menu"> <jsp:include page="/admin/AdminMenuAuthorized.jsp" /> </div>
<%}
else {%>
<div id="menu"> <jsp:include page="/admin/AdminAuthorization.jsp" /> </div>
<%}
%>
</body>
</html>
