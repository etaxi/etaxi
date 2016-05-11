<%@ page import="lv.etaxi.dto.CustomeDTOr" %>
<%@ page import="lv.etaxi.dto.OrderDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP page</title>
</head>
<body>

<a href="/customer"> Main customer menu </a> <br>

<h1>Edit order by customer</h1>

<div class="container">
    <form class="form-signin" action="/customer/writeFeedbackToOrder" method="POST">

        <%  CustomerDTO customer = (CustomerDTO) session.getAttribute("customerDTO");
            OrderDTO order = (OrderDTO) request.getAttribute("model");
        %>

        <h2 class="form-signin-heading">Please, <%=customer.getName()%> write feedback to order ID: <%=order.getOrderId()%></h2>

        <h3 class="form-signin-heading">Date&Time of registration: <%=order.getDateTime()%> </h3>
        <h3 class="form-signin-heading">From address: <%=order.getFromAdress()%> </h3>
        <h3 class="form-signin-heading">To address: <%=order.getToAdress()%> </h3>
        <h3 class="form-signin-heading">Date&Time of ride: <%=order.getOrderedDateTime()%></h3>
        <h3 class="form-signin-heading">Distance (km): <%=order.getDistance()%></h3>
        <h3 class="form-signin-heading">Price of ride (EUR): <%=order.getPrice()%></h3>

        <label for="feedback" class="sr-only">Feedback</label>
        <input type="text" name="feedback" id="feedback" value="<%=order.getFeedback()%>" class="form-control" placeholder="Feedback" required>

        <input type="hidden" name="orderId" value="<%=order.getOrderId()%>"/>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Send feedback</button>
    </form>
</div> <!-- /container -->

<h3><%=request.getAttribute("message")%></h3>

</body>
</html>