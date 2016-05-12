<%@ page import="lv.etaxi.dto.CustomerDTO" %>
<%@ page import="lv.etaxi.dto.OrderDTO" %>
<%@ page import="lv.etaxi.MVC.MVCModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>JSP page</title>
    <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" media="screen"
          href="http://tarruda.github.com/bootstrap-datetimepicker/assets/css/bootstrap-datetimepicker.min.css">
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

<a href="/customer"> Main menu </a>

<br>

<%
    MVCModel model = null;  OrderDTO order = null;
    CustomerDTO customer = (CustomerDTO) session.getAttribute("customerDTO");
    if (request.getAttribute("model") != null) {
        model = (MVCModel) request.getAttribute("model");
        order = (OrderDTO) model.getData();
    }
%>

<h2 class="form-signin-heading">Please, <%=customer.getName()%> change your order ID: <%=order.getOrderId()%></h2>

<form id="register" class="form-signin" action="" method="POST">

    <label for="fromAddress" class="sr-only">Address from</label>
    <input input type="text" name="fromAddress" value="<%=order.getFromAdress()%>" id="fromAddress" class="form-control"
           placeholder="Ride from address" autofocus required>

    <label for="toAddress" class="sr-only">Address to</label>
    <input type="text" name="toAddress" value="<%=order.getToAdress()%>" id="toAddress" class="form-control"
           placeholder="Ride to address" required>

    <div id="orderedDateTime" class="input-append date">
        <label for="orderedDateTimeInput">Date and time of ride:</label>
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

    <label for="distance" class="sr-only">Distance (km): </label>
    <input input type="text" value="<%= (order==null)? 0.00 : order.getDistance()%>" name="distance" id="distance" class="form-control" placeholder="Distance" readonly>

    <label for="price" class="sr-only">Price of ride (EUR): </label>
    <input input type="text" value="<%= (order==null)? 0.00 : order.getPrice()%>" name="price" id="price" class="form-control" placeholder="Price" readonly>

    <input type='hidden' name='returnPage' value="/customer/CustomerEditOrder.jsp">

    <button class="btn btn-lg btn-primary btn-block" type="submit" value ="/customer/getDistanceForOrder" onclick="changeFormAction(this.value)">Get distance and price</button>

    <button class="btn btn-lg btn-primary btn-block" type="submit" value ="/customer/customerEditOrder" onclick="changeFormAction(this.value)">Submit new data</button>

    <script type="text/javascript">
        function changeFormAction(value) {
            document.getElementById("register").action = value;
        }
    </script>

</form>

<h3><%= (model !=null) ? model.getMessage() : "" %></h3>

</body>
</html>