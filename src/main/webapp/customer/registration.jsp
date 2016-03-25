<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP page</title>
</head>
<body>


<div class="container">

    <h1>New customer registration</h1>

    <form role="form" name = form1 action="/customer/registration" method="POST">
        <div class="form-group">
            <label for="usr">Name, Surname:</label>
            <input type="text" name="name" placeholder= "Name" class="form-control" id="usr">
        </div>
        <div class="form-group">
            <label for="phone">Phone:</label>
            <input type="text" name="phone" placeholder= "you phone (+371xxxxxxx)" class="form-control" id="phone">
        </div>
        <div class="form-group">
            <label for="pwd">Password:</label>
            <input type="password" name="password" placeholder="Password" class="form-control" id="pwd">
        </div>
        <input type="submit" value="Register new customer" style='width:265px;'>
        <p> Registration process: <b> ${message} </b> </p>
    </form>

</div> <!-- /container -->


</body>
</html>