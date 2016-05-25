<%@ page import="lv.etaxi.entity.Customer" %>
<%@ page import="lv.etaxi.dto.CustomerDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <%
        CustomerDTO customer = (CustomerDTO) session.getAttribute("customerDTO");
        if (customer != null) {  %>
            <div id="menu"> <jsp:include page="/customer/CustomerMenuAuthorized.jsp" /> </div>
        <%}
        else {%>
            <div id="menu"> <jsp:include page="/index.jsp" /> </div>
        <%}
    %>

</body>
</html>
