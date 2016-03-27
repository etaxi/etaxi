<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP page</title>
</head>
<body>

<a href="/customer"> Main menu </a> <br>

<h1>Edit order by customer</h1>

<div class="container">
    <form class="form-signin" action="/customer/writeFeedback" method="POST">

        <h2 class="form-signin-heading">Please, ${customer} write feedback to order ID: ${orderId}:</h2>

        <h3 class="form-signin-heading">Date&Time of registration: ${date}</h3>
        <h3 class="form-signin-heading">From address: ${fromAddress}</h3>
        <h3 class="form-signin-heading">To address: ${toAddress}</h3>
        <h3 class="form-signin-heading">Date&Time of ride: ${orderedDateTime}</h3>

        <label for="feedback" class="sr-only">Feedback</label>
        <input type="text" name="feedback" id="feedback" class="form-control" placeholder="Feedback" required>

        <input type="hidden" name="orderId" value='${orderId}'/>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Send feedback</button>
    </form>
</div> <!-- /container -->

<b> ${message} </b>

</body>
</html>