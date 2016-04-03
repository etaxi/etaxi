<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP page</title>
</head>
<body>

<div class="container">

    <h1>New customer registration</h1>

    <form role="form" name = form1 action="/customer/customerRegistration" method="POST">
        <div class="form-group">
            <label for="usr">Name, Surname:</label>
            <input type="text" name="name" placeholder= "Name" class="form-control" id="usr" required>
        </div>
        <div class="form-group">
            <label for="phone">Phone:</label>
            <input type="text" name="phone" placeholder= "you phone (+371xxxxxxx)" class="form-control" id="phone" required>
        </div>
        <div class="form-group">
            <label for="pwd">Password:</label>
            <input type="password" name="password" placeholder="Password" class="form-control" id="pwd" required>
        </div>
        <input type="submit" value="Register new customer" style='width:265px;'>
    </form>

</div> <!-- /container -->


<h3><%=request.getAttribute("message")%></h3>

</body>
</html>