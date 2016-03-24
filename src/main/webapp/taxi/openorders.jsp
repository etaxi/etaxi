<%@ page import="entity.Order" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Open orders</title>
</head>
<body>
<h1>View all open orders</h1>

<form name="orderId" action="/taxi/takeorder" method="post">
        Order ID: <input type="text" name="orderId" /> <br/>
        <input type="submit" value="Take order" />
</form>

<b> ${table} </b>

</body>
</html>