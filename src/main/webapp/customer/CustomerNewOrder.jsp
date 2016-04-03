<%@ page import="entity.Customer" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP page</title>
</head>
<body>

<a href="/customer"> Main customer menu </a> <br>

<h1>New order creation</h1>

<div class="container">
    <form class="form-signin" action="/customer/customerCreateNewOrder" method="POST">

        <%  Customer customer = (Customer)session.getAttribute("customer"); %>
        <div><h3>Please, <%=customer.getName()%> enter new order information: </h3></div>

        <label for="fromAddress" class="sr-only">Address from</label>
        <input input type="text" name="fromAddress" id="fromAddress" class="form-control" placeholder="Ride from address" required autofocus>
        <label for="toAddress" class="sr-only">Address to</label>
        <input type="text" name="toAddress" id="toAddress" class="form-control" placeholder="Ride to address" required>
        <label for="orderedDateTime" class="sr-only">Date and time of ride:</label>
        <input type="datetime-local" value="<%= new Timestamp(new java.util.Date().getTime())%>" name="orderedDateTime" id="orderedDateTime" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Create new order</button>
    </form>
</div> <!-- /container -->

<h3><%=request.getAttribute("message")%></h3>

</body>
</html>