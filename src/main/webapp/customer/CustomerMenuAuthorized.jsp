<%@ page import="entity.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%  Customer customer = (Customer)session.getAttribute("customer"); %>
<div><h3>Welcome, <%=customer.getName()%> (phone: <%=customer.getPhone()%>)</h3></div>

<h3>Customer menu:</h3>

<a href="/customer/customerEditProfile">Edit profile</a><br>
<a href="/customer/customerCreateNewOrder">Create new order</a><br>
<a href="/customer/customerHistoryOfOrders">History of your orders</a><br>
<a href="/customer/customerEditDeleteOrders">Change data of opened orders</a><br>
<a href="/customer/customerWriteFeedbacksToOrders">Write feedback to completed orders</a><br>
<a href="/customer/signOut">Sing out</a><br>

<h3><%=request.getAttribute("model")%></h3>

</body>
</html>

