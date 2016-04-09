<%@ page import="lv.etaxi.entity.Taxi" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--
<form>
    <input onclick="showContent('./editprofile.jsp', 'contentBody')" type="button" value="Edit profile">
    <input onclick="showContent('./openorders.jsp', 'contentBody')" type="button" value="View all open orders">
    <input onclick="showContent('./takeorder.jsp', 'contentBody')" type="button" value="Take orders">
    <input onclick="showContent('./completeorder.jsp', 'contentBody')" type="button" value="Complete the order">
    <input onclick="showContent('./cancelorder.jsp', 'contentBody')" type="button" value="Cancel order">
    <input onclick="showContent('./history.jsp', 'contentBody')" type="button" value="View the history of your completed orders">
    <input onclick="showContent('./logoff', 'contentBody')" type="button" value="Logoff">

</form>
--%>
<a href="/taxi/editprofile">Edit profile</a><br>
<a href="/taxi/openorders">View all open orders</a><br>
<a href="/taxi/history">View the history of your completed orders</a><br>
<a href="/taxi/cancelorder">Cancel order</a><br>
<a href="/taxi/completeorder">Complete the order</a><br>
<a href="/taxi/logoff">Logoff</a><br>

    <%  Taxi taxi = (Taxi)session.getAttribute("taxi"); %>
    <div><b>Hello, <%=taxi.getName()%>   <%=taxi.getCar()%>!</b></div>
<br>

<%-- <div id="message">< <b> ${message} </b> </div>>
--%>

</body>
</html>
