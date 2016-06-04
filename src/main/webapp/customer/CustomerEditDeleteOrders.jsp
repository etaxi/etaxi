<%@ page import="lv.etaxi.dto.CustomerDTO" %>
<%@ page import="lv.etaxi.dto.OrderDTO" %>
<%@ page import="lv.etaxi.MVC.MVCModel" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
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

    <title>eTaxi</title>

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

<%  CustomerDTO customer = (CustomerDTO) session.getAttribute("customerDTO"); %>

<div align="left">

    <form class="form-signin" action='/customer/customerEditDeleteOrders' method="POST">

        <h3><%=customer.getName()%>, you can change orders by period:</h3>

        <div id="orderedDateTimeBegin" class="input-append date">
            <label for="orderedDateTimeBeginInput"> Period from: </label> <BR>
            <input type="text"
                   value="<%= new Timestamp(new java.util.Date(System.currentTimeMillis() - 2628000000l).getTime())%>"
                   name="orderedDateTimeBegin" id="orderedDateTimeBeginInput" required>
            <span class="add-on">
                <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
            </span>
        </div>
        <script type="text/javascript">
            $('#orderedDateTimeBegin').datetimepicker({
                format: 'yyyy-MM-dd hh:mm:ss',
                language: 'pt-BR'
            });
        </script>

        <div id="orderedDateTimeEnd" class="input-append date">
            <label for="orderedDateTimeEndInput"> Period to: </label> <BR>
            <input type="text"
                   value="<%= new Timestamp(new java.util.Date(System.currentTimeMillis() + 2628000000l).getTime())%>"
                   name="orderedDateTimeEnd" id="orderedDateTimeEndInput" required>
            <span class="add-on">
                <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
            </span>
        </div>
        <script type="text/javascript">
            $('#orderedDateTimeEnd').datetimepicker({
                format: 'yyyy-MM-dd hh:mm:ss',
                language: 'pt-BR'
            });
        </script>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Show orders by period</button>
    </form>


    <table align="left" border="1" width="950">

        <%
            if (request.getAttribute("model") != null) {
        %>

        <tr>
            <td width="50"><b>Id</b></td>
            <td width="50"><b>Customer ID</b></td>
            <td width="100"><b>Ordered Date&Time</b></td>
            <td width="100"><b>Status</b></td>
            <td width="200"><b>From address</b></td>
            <td width="200"><b>To address</b></td>
            <td width="50"><b>Taxi ID </b></td>
            <td width="50"><b>Distance </b></td>
            <td width="50"><b>Price </b></td>
            <td width="50"><b>Rate </b></td>
            <td width="200"><b>Feedback </b></td>
            <td width="200"><b>Delete order</b></td>
            <td width="200"><b>Edit order</b></td>
        </tr>

        <%
            MVCModel model = (MVCModel) request.getAttribute("model");
            List<OrderDTO> listOfOrders = (ArrayList<OrderDTO>) model.getData();
            if (listOfOrders != null) {
                for (OrderDTO order : listOfOrders) {
        %>

        <tr>
            <td width="50"><%=order.getOrderId()%>
            </td>
            <td width="50"><%=order.getCustomerId()%>
            </td>
            <td width="100"><%=order.getOrderedDateTime()%>
            </td>
            <td width="100"><%=order.getOrderStatus()%>
            </td>
            <td width="200"><%=order.getFromAdress()%>
            </td>
            <td width="200"><%=order.getToAdress()%>
            </td>
            <td width="50"><%=order.getTaxiId()%>
            </td>
            <td width="50"><%=order.getDistance()%>
            </td>
            <td width="50"><%=order.getPrice()%>
            </td>
            <td width="50"><%=order.getRate()%>
            </td>
            <td width="200"><%=order.getFeedback()%>
            </td>
            <td>
                <form action='/customer/customerDeleteOrder' method='post'>
                    <input type='hidden' name='orderId' value="<%=order.getOrderId()%>">
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Delete order</button>
                </form>
            </td>
            <td>
                <form action='/customer/customerEditOrder' method='get'>
                    <input type='hidden' name='orderId' value="<%=order.getOrderId()%>">
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Edit order</button>
                </form>
            </td>
        </tr>

        <%
         }}}
        %>

    </table>

    <BR>
    <BR>

    <form>
        <%  if (request.getAttribute("model") != null) {
            MVCModel model = (MVCModel) request.getAttribute("model");
        %>
            <h4> <%=model.getMessage()%> </h4>
        <%
            }
        %>
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