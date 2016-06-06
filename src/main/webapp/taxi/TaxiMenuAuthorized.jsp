<%@ page import="lv.etaxi.dto.TaxiDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%--<head>

</head>--%>
<body>

<%--<form>
    <input onclick="showContent('/taxi/editprofile', 'contentBody')" type="button" value="Edit profile">
    <input onclick="showContent('/taxi/openorders', 'contentBody')" type="button" value="View all open orders">
    <input onclick="showContent('./takeorder.jsp', 'contentBody')" type="button" value="Take orders">
    <input onclick="showContent('/taxi/completeorder', 'contentBody')" type="button" value="Complete the order">
    <input onclick="showContent('/taxi/cancelorder', 'contentBody')" type="button" value="Cancel order">
    <input onclick="showContent('/taxi/history', 'contentBody')" type="button" value="View the history of your completed orders">
    <input onclick="showContent('/taxi/logoff', 'contentBody')" type="button" value="Logoff">
</form>--%>


<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand"> Main menu: </a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="/taxi/editprofile">Edit profile</a></li>
                <li class="active"><a href="/taxi/openorders">View all open orders</a></li>
                <li><a href="/taxi/history">View the history of your completed orders</a></li>
                <li><a href="/taxi/cancelorder">Cancel order</a></li>
                <li><a href="/taxi/completeorder">Complete the order</a></li>
                <li><a href="/taxi/logoff">Logoff</a></li>
            </ul>
        </div>
        <!--/.nav-collapse -->
    </div>
</nav>

<%--<a href="/taxi/editprofile">Edit profile</a><br>
<a href="/taxi/openorders">View all open orders</a><br>
<a href="/taxi/history">View the history of your completed orders</a><br>
<a href="/taxi/cancelorder">Cancel order</a><br>
<a href="/taxi/completeorder">Complete the order</a><br>
<a href="/taxi/logoff">Logoff</a><br>--%>
<div class="container">

    <%  TaxiDTO taxiDTO = (TaxiDTO) session.getAttribute("taxi"); %>
    <div><b>Hello, <%=taxiDTO.getName()%>   <%=taxiDTO.getCar()%>!</b></div><br>

</div>
<%-- <div id="message">< <b> ${message} </b> </div>>
--%>

</body>
</html>
