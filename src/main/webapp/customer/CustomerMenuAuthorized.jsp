<%@ page import="lv.etaxi.dto.CustomerDTO" %>
<%@ page import="lv.etaxi.MVC.MVCModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" media="screen"
          href="http://tarruda.github.com/bootstrap-datetimepicker/assets/css/bootstrap-datetimepicker.min.css">

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="staticRes/favicon.ico">

    <title>eTaxi - customer</title>

    <!-- Bootstrap core CSS -->
    <link href="staticRes/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="css/ie10-viewport-bug-workaround.css" rel="stylesheet">

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

<% CustomerDTO customer = (CustomerDTO) session.getAttribute("customerDTO");
    if (customer != null) { %>
<div><h3>Welcome, <%=customer.getName()%> (phone: <%=customer.getPhone()%>)</h3></div>
<%
    }
%>

<h3> Customer menu: </h3>

<BR>

<a href="/customer/customerEditProfile">Edit profile</a><br>
<a href="/customer/customerCreateNewOrder">Create new order</a><br>
<a href="/customer/customerHistoryOfOrders">History of your orders</a><br>
<a href="/customer/customerEditDeleteOrders">Change data of opened orders</a><br>
<a href="/customer/customerWriteFeedbacksToOrders">Write feedback to completed orders</a><br><br>
<a href="/customer/signOut">Sing out</a><br>

<BR>

<ul class="nav navbar-nav">
    <li><a href="#">Home</a></li>
    <li class="dropdown"><a href="" data-toggle="dropdown"> Customer menu options: <span class="caret"></span></a>
        <ul class="dropdown-menu">
        <li><a href="/customer/customerEditProfile">Edit profile</a><br></li>
        <li><a href="/customer/customerCreateNewOrder">Create new order</a><br></li>
        <li><a href="/customer/customerHistoryOfOrders">History of your orders</a><br></li>
        <li><a href="/customer/customerEditDeleteOrders">Change data of opened orders</a><br></li>
        <li><a href="/customer/customerWriteFeedbacksToOrders">Write feedback to completed orders</a><br></li>
        </ul>
    </li>
    <li class="active"><a href="/customer/signOut">Sing out</a></li>
</ul>

<BR>


<%
    if (request.getAttribute("model") != null) {
        MVCModel model = (MVCModel) request.getAttribute("model");
%>
<h3><%=model.getMessage()%></h3>
<%
    }
%>


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

