<%@ page import="lv.etaxi.dto.TaxiDTO" %>
<%@ page import="lv.etaxi.dto.OrderDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    TaxiDTO taxiDTO = (TaxiDTO) session.getAttribute("taxi");
    if (taxiDTO != null) {  %>
        <div id="menu"> <jsp:include page="/taxi/TaxiMenuAuthorized.jsp" /> </div>>
        <div><b>Hello, <%=taxiDTO.getName()%>   <%=taxiDTO.getCar()%>!</b></div>
        <div><a href="/taxi/logoff">Exit</a></div>
    <%}
    else {%>
        <div id="menu"> <jsp:include page="/taxi/TaxiMenuNotAuthorized.jsp" /> </div>
    <%}%>


<% OrderDTO orderDTO = (OrderDTO)session.getAttribute("order");
    if (orderDTO != null) {
        String orderText = "FROM: " + orderDTO.getFromAdress() + " TO: " + orderDTO.getToAdress();
%>
<%="Actual order : " + orderText +" "%>

<%}
%>

</body>
</html>
