<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP page</title>
</head>
<body>


<h1>Customer authorization</h1>
<div class="container">
    <form class="form-signin" action="/customer/customerAuthorization" method="POST">
        <h2 class="form-signin-heading">Dear customer, please sign in</h2>
        <label for="inputEmail" class="sr-only">Phone</label>
        <input type="text" name="login" id="inputEmail" class="form-control" placeholder="Phone (+371xxxxxxxx)" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>
</div> <!-- /container -->

<div class="container">
    <form class="form-signin" action="/customer/customerRegistration" method="GET">
        <button class="btn btn-lg btn-primary btn-block" type="submit">New customer registration</button>
    </form>
</div> <!-- /container -->

<h3><%=request.getAttribute("message")%></h3>

</body>
</html>