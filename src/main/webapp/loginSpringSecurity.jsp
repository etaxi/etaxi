
<!DOCTYPE html>
<html lang="en">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="staticRes/favicon.ico">

    <title>eTaxi (Spring Security authentication)</title>

    <!-- Bootstrap core CSS -->
    <link href="staticRes/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="staticRes/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="staticRes/style.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.jsp">eTaxi</a>
        </div>
        <!--/.nav-collapse -->
    </div>
</nav>


<div class="container">
<form class="form-signin" th:action="@{/login}" method="post">
    <h2 class="form-signin-heading">Please, login to eTAXI</h2>
    <label  for="inputLogin"> Login: </label> <BR>
    <input type="text" name="username" id="inputLogin" placeholder="Phone (+371xxxxxxxx)" required autofocus> <BR>
    <label for="inputPassword"> Password: </label> <BR>
    <input type="password" name="password" id = "inputPassword" placeholder="Password" required> <BR>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
</form>
</div>

<BR>
<BR>
<BR>

<div class="container">
    <form class="form-signin" action="/customer/customerRegistration" method="GET">
        <button class="btn btn-lg btn-primary btn-block" type="submit">New customer registration</button>
    </form>
</div>


<div class="container">
    <form class="form-signin" action="/taxi/registration" method="GET">
        <button class="btn btn-lg btn-primary btn-block" type="submit">New taxi driver registration</button>
    </form>
</div>

<div class="container">
    <form class="form-signin" action="/admin/adminRegistration" method="GET">
        <button class="btn btn-lg btn-primary btn-block" type="submit">New admin registration</button>
    </form>
</div>


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="js/vendor/jquery.min.js"><\/script>')</script>
<script src="staticRes/js/bootstrap.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
