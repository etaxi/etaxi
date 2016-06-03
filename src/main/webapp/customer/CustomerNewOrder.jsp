<%@ page import="lv.etaxi.dto.CustomerDTO" %>
<%@ page import="lv.etaxi.dto.OrderDTO" %>
<%@ page import="lv.etaxi.MVC.MVCModel" %>
<%@ page import="java.sql.Timestamp" %>
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


<script type="text/javascript"
        src="http://cdnjs.cloudflare.com/ajax/libs/jquery/1.8.3/jquery.min.js">
</script>
<script type="text/javascript"
        src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/js/bootstrap.min.js">
</script>
<script type="text/javascript"
        src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.min.js">
</script>
<script type="text/javascript"
        src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.pt-BR.js">
</script>

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
            <a class="navbar-brand" href="/customer"> Main menu </a>
        </div>
    </div>
</nav>

<%
    MVCModel model = null;  OrderDTO order = null;
    if (request.getAttribute("model") != null) {
        model = (MVCModel) request.getAttribute("model");
        order = (OrderDTO) model.getData();
    }
%>

<BR>

<div class="container">

<h2>New customer registration</h2>

<form id="register" class="form-signin" action="" method="POST">

    <% CustomerDTO customer = (CustomerDTO) session.getAttribute("customerDTO"); %>
    <div><h3>Please, <%=customer.getName()%> enter new order information: </h3></div>

    <label for="fromAddress" class="sr-only">Address from</label>
    <input input type="text" name="fromAddress" id="fromAddress" value="<%=(order==null)? "" : order.getFromAdress()%>" class="form-control" placeholder="Ride from address" required autofocus>

    <label for="toAddress" class="sr-only">Address to</label>
    <input type="text" name="toAddress" id="toAddress" value="<%=(order==null)? "" : order.getToAdress()%>" class="form-control" placeholder="Ride to address" required>

    <div id="orderedDateTime" class="input-append date">
        <label for="orderedDateTimeInput">Date and time of ride:</label>
        <input type="text" value="<%= (order==null)? new Timestamp(new java.util.Date().getTime()) : order.getOrderedDateTime()%>" name="orderedDateTime" id="orderedDateTimeInput" required>
            <span class="add-on">
                <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
            </span>
    </div>
    <script type="text/javascript">
        $('#orderedDateTime').datetimepicker({
            format: 'yyyy-MM-dd hh:mm:ss',
            language: 'pt-BR'
        });
    </script>

    <label for="distance" class="sr-only">Distance (km): </label>
    <input input type="text" value="<%= (order==null)? 0.00 : order.getDistance()%>" name="distance" id="distance" class="form-control" placeholder="Distance" readonly>

    <label for="price" class="sr-only">Price of ride (EUR): </label>
    <input input type="text" value="<%= (order==null)? 0.00 : order.getPrice()%>" name="price" id="price" class="form-control" placeholder="Price" readonly>

    <input type='hidden' name='returnPage' value="/customer/CustomerNewOrder.jsp">

    <button class="btn btn-lg btn-primary btn-block" type="submit" value ="/customer/getDistanceForOrder" onclick="changeFormAction(this.value)">Get distance and price</button>

    <button class="btn btn-lg btn-primary btn-block" type="submit" value ="/customer/customerCreateNewOrder" onclick="changeFormAction(this.value)">Create new order</button>

    <script type="text/javascript">
        function changeFormAction(value) {
           document.getElementById("register").action = value;
        }
    </script>

</form>

</div> <!-- /container -->

<BR>

<h3><%= (model != null) ? model.getMessage() : "" %></h3>


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
