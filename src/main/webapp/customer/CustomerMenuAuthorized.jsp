<%@ page import="lv.etaxi.dto.CustomerDTO" %>
<%@ page import="lv.etaxi.MVC.MVCModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="staticRes/favicon.ico">

    <title>eTaxi (customer main menu)</title>

    <!-- Bootstrap core CSS -->
    <link href="staticRes/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="staticRes/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="staticRes/style.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>


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
            <a class="navbar-brand" href="/customer/signOut">Sing out</a>
        </div>
    </div>
</nav>


<% CustomerDTO customer = (CustomerDTO) session.getAttribute("customerDTO");
    if (customer != null) { %>
<div>
    <h2> Welcome, <%=customer.getName()%> to eTaxi system </h2>
    <h3> (your registered phone: <%=customer.getPhone()%>) </h3>
</div>
<%
    }
%>

<BR>

<div>

<h4> Please, select one of such options: </h4>

<BR>

<ul type="square" title="Customer menu:">
    <li><a href="/customer/customerEditProfile">Edit profile</a></li><BR>
    <li><a href="/customer/customerCreateNewOrder">Create new order</a></li><BR>
    <li><a href="/customer/customerHistoryOfOrders">All history of your orders</a></li><BR>
    <li><a href="/customer/customerEditDeleteOrders">Change data of opened orders</a></li><BR>
    <li><a href="/customer/customerWriteFeedbacksToOrders">Write feedback to completed orders</a></li>
</ul>

</div>

<BR>

<div>
    <%
        if (request.getAttribute("model") != null) {
            MVCModel model = (MVCModel) request.getAttribute("model");
    %>
    <h3>    <%=model.getMessage()%> </h3>
    <%
        }
    %>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="js/vendor/jquery.min.js"><\/script>')</script>
<script src="staticRes/js/bootstrap.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>

