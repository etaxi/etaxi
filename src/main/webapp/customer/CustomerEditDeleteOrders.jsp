<%@ page import="entity.Customer" %>
<%@ page import="entity.Order" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<a href="/customer"> Main customer menu </a> <br>

<%  Customer customer = (Customer)session.getAttribute("customer"); %>
<div><h3><%=customer.getName()%>, you can change orders by period:</h3></div>

<div class="container">
    <form class="form-signin" action='/customer/customerEditDeleteOrders' method="POST">
        <label for="orderedDateTimeBegin" class="sr-only">Select orders from:</label>
        <input type="datetime-local" value="2016-01-01 00:00:00" name="orderedDateTimeBegin" id="orderedDateTimeBegin" required>
        <label for="orderedDateTimeEnd" class="sr-only"> to:</label>
        <input type="datetime-local" value="2016-01-01 00:00:00" name="orderedDateTimeEnd" id="orderedDateTimeEnd" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Show order by period</button>
    </form>
</div> <!-- /container -->


<div align="left">

    <table align="left" border="1" width="950">

        <%
            if (request.getAttribute("model") != null) {
        %>

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
            <td width="200"><b>Delete order</b></td>
            <td width="200"><b>Edit order</b></td>

        </tr>

        <%
            List<Order> listOfOrders = (ArrayList<Order>) request.getAttribute("model");
            for (Order order : listOfOrders) {

        %>
        <tr>
            <td width="50"><%=order.getOrderId()%>
            </td>
            <td width="50"><%=order.getCustomerId()%>
            </td>
            <td width="100"><%=order.getDateTime()%>
            </td>
            <td width="100"><%=order.getOrderedDateTime()%>
            </td>
            <td width="100"><%=order.getOrderStatus()%>
            </td>
            <td width="200"><%=order.getFromAdress()%>
            </td>
            <td width="200"><%=order.getToAdress()%>
            </td>
            <td width="50"><%=order.getTaxiId()%>
            </td>
            <td width="50"><%=order.getDistance()%>
            </td>
            <td width="50"><%=order.getPrice()%>
            </td>
            <td width="50"><%=order.getRate()%>
            </td>
            <td width="200"><%=order.getFeedback()%>
            </td>
            <td>
                <form action='/customer/customerDeleteOrder' method='post'>
                <input type='hidden' name='orderId' value="<%=order.getOrderId()%>">
                <input type='submit' value='Delete order'/></form>
            </td>
            <td>
                <form action='/customer/customerEditOrder' method='get'>
                    <input type='hidden' name='orderId' value="<%=order.getOrderId()%>">
                    <input type='submit' value='Edit order'/></form>
            </td>
        </tr>

        <%
         }}
        %>

    </table>
</div>

<h3><%=request.getAttribute("message")%></h3>

</body>
</html>
