<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="lv.etaxi.dto.OrderDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>history</title>
</head>
<body>

<jsp:include page="/taxi/TaxiMenu.jsp" />

<h1>View the history of your completed orders</h1>

<div class="container">

    <table class="table">
        <tr>
            <td width="50"><b>Id</b></td>
            <td width="50"><b>Customer ID</b></td>
            <td width="100"><b>Date&Time</b></td>
            <td width="100"><b>Ordered Date&Time</b></td>
            <td width="100"><b>Status</b></td>
            <td width="200"><b>From address</b></td>
            <td width="200"><b>To address</b></td>
            <td width="50"><b>Taxi ID </b></td>
            <td width="50"><b>Distance </b></td>
            <td width="50"><b>Price </b></td>
            <td width="50"><b>Rate </b></td>
            <td width="200"><b>Feedback </b></td>
        </tr>

        <%
            if (request.getAttribute("model") != null) {
        %>

        <%
            List<OrderDTO> listOfOrdersDTO = (ArrayList<OrderDTO>)request.getAttribute("model");
            for (OrderDTO orderDTO : listOfOrdersDTO) {

        %>
            <tr>
                <td width="50"><%=orderDTO.getOrderId()%></td>
                <td width="50"><%=orderDTO.getCustomerId()%></td>
                <td width="100"><%=orderDTO.getOrderedDateTime()%></td>
                <td width="100"><%=orderDTO.getOrderedDateTime()%></td>
                <td width="100"><%=orderDTO.getOrderStatus()%></td>
                <td width="200"><%=orderDTO.getFromAdress()%></td>
                <td width="200"><%=orderDTO.getToAdress()%></td>
                <td width="50"><%=orderDTO.getTaxiId()%></td>
                <td width="50"><%=orderDTO.getDistance()%></td>
                <td width="50"><%=orderDTO.getPrice()%></td>
                <td width="50"><%=orderDTO.getRate()%></td>
                <td width="200"><%=orderDTO.getFeedback()%></td>
            </tr>
        <%}
        } else {

        %>
        <script language="Javascript">
            <!--
            alert ("Sorry, NO OPEN ORDERS FOUND in DB ! :(")
            //-->
        </script>

        <%}%>

    </table>
</div>

</body>
</html>
