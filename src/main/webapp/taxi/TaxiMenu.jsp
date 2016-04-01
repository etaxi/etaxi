<%@ page import="entity.Taxi" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    Taxi taxi = (Taxi)session.getAttribute("taxi");
    if (taxi != null) {  %>
        <div id="menu"> <jsp:include page="/taxi/TaxiMenuAuthorized.jsp" /> </div>>
        <div><b>Hello, <%=taxi.getName()%>   <%=taxi.getCar()%>!</b></div>
        <div><a href="/taxi/logoff">Exit</a></div>
    <%}
    else {%>
        <div id="menu"> <jsp:include page="/taxi/TaxiMenuNotAuthorized.jsp" /> </div>
    <%}%>



</body>
</html>
