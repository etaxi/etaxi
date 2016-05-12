<%@ page import="lv.etaxi.dto.CustomerDTO" %>
<%@ page import="lv.etaxi.dto.OrderDTO" %>
<%@ page import="lv.etaxi.MVC.MVCModel" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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


<a href="/customer"> Main customer menu </a> <br>

<%  CustomerDTO customer = (CustomerDTO) session.getAttribute("customerDTO"); %>
<div><h3><%=customer.getName()%>, you can write feedbacks to completed orders by period:</h3></div>

<form class="form-signin" action='/customer/customerWriteFeedbacksToOrders' method="POST">

    <div id="orderedDateTimeBegin" class="input-append date">
        <label for="orderedDateTimeBeginInput"> Period from: </label>
        <input type="text"
               value="<%= new Timestamp(new java.util.Date(System.currentTimeMillis() - 2628000000l).getTime())%>"
               name="orderedDateTimeBegin" id="orderedDateTimeBeginInput"
               required>
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
        <label for="orderedDateTimeEndInput"> to: </label>
        <input type="text"
               value="<%= new Timestamp(new java.util.Date(System.currentTimeMillis() + 2628000000l).getTime())%>"
               name="orderedDateTimeEnd" id="orderedDateTimeEndInput"
               required>
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


<div align="left">

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
            <td width="200"><b>Write feedback</b></td>

        </tr>

        <%
            MVCModel model = (MVCModel) request.getAttribute("model");
            List<OrderDTO> listOfOrders = (List<OrderDTO>) model.getData();
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
                <form action='/customer/writeFeedbackToOrder' method='get'>
                <input type='hidden' name='orderId' value="<%=order.getOrderId()%>">
                <input type='submit' value='Write feedback'/></form>
            </td>

        </tr>

        <%
         }}}
        %>

    </table>
</div>

<%
    if (request.getAttribute("model") != null) {
        MVCModel model = (MVCModel) request.getAttribute("model");
%>
<h3><%=model.getMessage()%></h3>
<%
    }
%>

</body>
</html>
