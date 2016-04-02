<%@ page import="entity.Customer" %>
<%@ page import="entity.Order" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP page</title>
</head>
<body>

<a href="/customer"> Main menu </a> <br>

<%  Customer customer = (Customer)session.getAttribute("customer");
    Order order = (Order)request.getAttribute("model");
%>

<h2 class="form-signin-heading">Please, <%=customer.getName()%> change your order ID: <%=order.getOrderId()%></h2>

<div class="container">
    <form class="form-signin" action="/customer/customerEditOrder" method="POST">
        <label for="fromAddress" class="sr-only">Address from</label>
        <input input type="text" name="fromAddress" value="<%=order.getFromAdress()%>" id="fromAddress" class="form-control" placeholder="Ride from address" autofocus>
        <label for="toAddress" class="sr-only">Address to</label>
        <input type="text" name="toAddress" value="<%=order.getToAdress()%>" id="toAddress" class="form-control" placeholder="Ride to address">
        <label for="orderedDateTime" class="sr-only">Date and time of ride:</label>
        <input type="datetime-local" value="<%=order.getOrderedDateTime()%>" name="orderedDateTime" id="orderedDateTime" required>
        <input type="hidden" name="orderId" value="<%=order.getOrderId()%>"/>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit new data</button>
    </form>
</div> <!-- /container -->

<b> ${message} </b>

</body>
</html>