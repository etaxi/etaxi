<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Taxi authorization</title>
</head>
<body>

<jsp:include page="/taxi/TaxiMenu.jsp" />

<div class="container">
<h1>Taxi authorization</h1>
    <form class="form-signin" action="/taxi/authorization" method="POST">
        <h2 class="form-signin-heading">Dear taxi driver, please sign in</h2>
        <label for="inputEmail" class="sr-only">Phone</label>
        <input type="text" name="login" id="inputEmail" class="form-control" placeholder="Phone (+371xxxxxxxx)" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> Remember me
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>
</div> <!-- /container -->
<b> ${message} </b>
</body>
</html>