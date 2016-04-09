<%@ page import="lv.etaxi.entity.Order" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<jsp:include page="/taxi/TaxiMenu.jsp" />

<h1>View the history of your completed orders</h1>

<div align="center">

    <table align="center" border="1" width="950">
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
            List<Order> listOfOrders = (ArrayList<Order>)request.getAttribute("model");
            for (Order order : listOfOrders) {

        %>
            <tr>
                <td width="50"><%=order.getOrderId()%></td>
                <td width="50"><%=order.getCustomerId()%></td>
                <td width="100"><%=order.getDateTime()%></td>
                <td width="100"><%=order.getOrderedDateTime()%></td>
                <td width="100"><%=order.getOrderStatus()%></td>
                <td width="200"><%=order.getFromAdress()%></td>
                <td width="200"><%=order.getToAdress()%></td>
                <td width="50"><%=order.getTaxiId()%></td>
                <td width="50"><%=order.getDistance()%></td>
                <td width="50"><%=order.getPrice()%></td>
                <td width="50"><%=order.getRate()%></td>
                <td width="200"><%=order.getFeedback()%></td>
            </tr>
        <%}
            if (listOfOrders.size()==0) {
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
