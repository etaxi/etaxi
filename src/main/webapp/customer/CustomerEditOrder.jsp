<%@ page import="lv.etaxi.dto.CustomerDTO" %>
<%@ page import="lv.etaxi.dto.OrderDTO" %>
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
    <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" media="screen"
          href="http://tarruda.github.com/bootstrap-datetimepicker/assets/css/bootstrap-datetimepicker.min.css">

    <link href="staticRes/menustyle.css" rel="stylesheet" type="text/css">

    <title>eTaxi (new order)</title>

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
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="/customer/signOut">Sing out</a></li>
                </ul>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="/customer"> Main menu </a></li>
                </ul>
            </div>
        </div>
    </div>
</nav>

<BR>

<%
    MVCModel model = null;  OrderDTO order = null;
    CustomerDTO customer = (CustomerDTO) session.getAttribute("customerDTO");
    if (request.getAttribute("model") != null) {
        model = (MVCModel) request.getAttribute("model");
        order = (OrderDTO) model.getData();
    }
%>

<div class="container">

<form id="register" class="form-signin" action="" method="POST">

    <h3>Please, <%=customer.getName()%> change your order (order ID: <%=order.getOrderId()%>) </h3>

    <BR>

    <label for="fromAddress">Address from</label> <BR>
    <input input type="text" name="fromAddress" value="<%=order.getFromAdress()%>" id="fromAddress" class="form-control"
           placeholder="Ride from address" autofocus required>

    <label for="toAddress">Address to</label> <BR>
    <input type="text" name="toAddress" value="<%=order.getToAdress()%>" id="toAddress" class="form-control"
           placeholder="Ride to address" required>

    <div id="orderedDateTime" class="input-append date">
        <label for="orderedDateTimeInput">Date and time of ride:</label> <BR>
        <input type="text" value="<%=order.getOrderedDateTime()%>" name="orderedDateTime" id="orderedDateTimeInput"
               required>
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

    <input type="hidden" name="orderId" value="<%=order.getOrderId()%>"/>

    <label for="distance">Distance (km): </label> <BR>
    <input input type="text" value="<%= (order==null)? 0.00 : order.getDistance()%>" name="distance" id="distance" class="form-control" placeholder="Distance" readonly>

    <label for="price">Price of ride (EUR): </label> <BR>
    <input input type="text" value="<%= (order==null)? 0.00 : order.getPrice()%>" name="price" id="price" class="form-control" placeholder="Price" readonly>

    <input type='hidden' name='returnPage' value="/customer/CustomerEditOrder.jsp">

    <button class="btn btn-lg btn-primary btn-block" type="submit" value ="/customer/getDistanceForOrder" onclick="changeFormAction(this.value)">Get distance and price</button>

    <button class="btn btn-lg btn-primary btn-block" type="submit" value ="/customer/customerEditOrder" onclick="changeFormAction(this.value)">Submit new data</button>

    <script type="text/javascript">
        function changeFormAction(value) {
            document.getElementById("register").action = value;
        }
    </script>

    <BR>

    <h3><%= (model != null) ? model.getMessage() : "" %></h3>

</form>

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

