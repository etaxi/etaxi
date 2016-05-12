<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP page</title>
</head>
<body>

<jsp:include page="/taxi/TaxiMenu.jsp" />

<h1>New taxi registration</h1>

<form role="form" name = form1 action="/taxi/registration" method="POST">
    <div class="form-group">
        <label for="usr">Name, Surname:</label>
        <input type="text" name="name" placeholder= "Name" class="form-control" id="usr">
    </div>
    <div class="form-group">
        <label for="car">Car:</label>
        <input type="text" name="car" placeholder= "Car" class="form-control" id="car">
    </div>
    <div class="form-group">
        <label for="phone">Phone:</label>
        <input type="text" name="phone" placeholder= "you phone (+371xxxxxxx)" class="form-control" id="phone">
    </div>
    <div class="form-group">
        <label for="lgn">Login:</label>
        <input type="text" name="login" placeholder= "login" class="form-control" id="lgn">
    </div>
    <div class="form-group">
        <label for="pwd">Password:</label>
        <input type="password" name="password" placeholder="Password" class="form-control" id="pwd">
    </div>
    <input type="submit" value="Register new taxi" style='width:265px;'>

</form>

<% String data = (String)request.getAttribute("model");
    if (data != null) {%>
<%=data +" "%>

<%}
%>

</body>
</html>