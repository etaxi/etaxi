<%@ page import="lv.etaxi.entity.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <%
        Customer customer = (Customer)session.getAttribute("customer");
        if (customer != null) {  %>
            <div id="menu"> <jsp:include page="/customer/CustomerMenuAuthorized.jsp" /> </div>
        <%}
        else {%>
            <div id="menu"> <jsp:include page="/customer/CustomerAuthorization.jsp" /> </div>
        <%}
    %>

</body>
</html>
