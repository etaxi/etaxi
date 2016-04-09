<%@ page import="lv.etaxi.entity.Customer" %>
<%@ page import="java.sql.Timestamp" %>
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

<a href="/customer"> Main customer menu </a>

<br>

<h1>New order creation</h1>

<form class="form-signin" action="/customer/customerCreateNewOrder" method="POST">

    <% Customer customer = (Customer) session.getAttribute("customer"); %>
    <div><h3>Please, <%=customer.getName()%> enter new order information: </h3></div>

    <label for="fromAddress" class="sr-only">Address from</label>
    <input input type="text" name="fromAddress" id="fromAddress" class="form-control" placeholder="Ride from address" required autofocus>

    <label for="toAddress" class="sr-only">Address to</label>
    <input type="text" name="toAddress" id="toAddress" class="form-control" placeholder="Ride to address" required>

    <div id="orderedDateTime" class="input-append date">
        <label for="orderedDateTimeInput">Date and time of ride:</label>
        <input type="text" value="<%= new Timestamp(new java.util.Date().getTime())%>" name="orderedDateTime" id="orderedDateTimeInput" required>
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

    <button class="btn btn-lg btn-primary btn-block" type="submit">Create new order</button>
</form>

<h3><%=request.getAttribute("message")%></h3>

</body>
</html>