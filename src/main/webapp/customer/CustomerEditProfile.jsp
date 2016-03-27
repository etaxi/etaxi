<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP page</title>
</head>
<body>

<a href="/customer"> Main menu </a> <br>

<div class="container">

    <h1>Customer data change</h1>

    <form role="form" name = form1 action="/customer/editProfileCustomer" method="POST">
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
        <input type="submit" value="Save data" style='width:265px;'>
        <p> <b> ${message} </b> </p>
    </form>

</div> <!-- /container -->


</body>
</html>