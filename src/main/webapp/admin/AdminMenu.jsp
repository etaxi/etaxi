<%@ page import="lv.etaxi.dto.AdminDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    AdminDTO adminDTO = (AdminDTO) session.getAttribute("adminDTO");
    if (adminDTO != null) {  %>
<div id="menu"> <jsp:include page="/admin/AdminMenuAuthorized.jsp" /> </div>
<%}
else {%>
<div id="menu"> <jsp:include page="/index.jsp" /> </div>
<%}
%>

</body>
</html>
