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
<a href="/customer/historyOfOrders">History of own orders</a><br>
<a href="/customer/changeOrders">Change data of opened orders</a><br>
<a href="/customer/writeFeedbacks">Write feedback to completed order</a><br>
<a href="/customer/signOut">Sing out</a><br>

<h3><%=request.getAttribute("model")%></h3>

</body>
</html>

