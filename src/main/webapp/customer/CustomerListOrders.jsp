<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<a href="/customer"> Main menu </a> <br>

<h1>${message}</h1>

<div class="container">
    <form class="form-signin" action='${servletToCall}' method="POST">
        <label for="orderedDateTimeBegin" class="sr-only">Select oreders from:</label>
        <input type="datetime-local" value='${orderedDateTimeBegin}' name="orderedDateTimeBegin" id="orderedDateTimeBegin" required>
        <label for="orderedDateTimeEnd" class="sr-only"> to:</label>
        <input type="datetime-local" value='${orderedDateTimeEnd}' name="orderedDateTimeEnd" id="orderedDateTimeEnd" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Show order by period</button>
    </form>
</div> <!-- /container -->

<b> ${table} </b>

<h2>${messageAboutOperation}</h2>

</body>
</html>
