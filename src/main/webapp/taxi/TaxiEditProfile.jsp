<%@ page import="lv.etaxi.entity.Taxi" %>
<%@ page import="lv.etaxi.dto.TaxiDTO" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>edit profile</title>
</head>
<body>

<div class="container">

<jsp:include page="/taxi/TaxiMenu.jsp" />

<h1>Edit profile</h1>

<%  TaxiDTO taxiDTO = (TaxiDTO)session.getAttribute("taxi");%>

<form role="form" name = form1 action="/taxi/editprofile" method="POST">
    <div class="form-group">
        <label for="name">Name, Surname:</label>
        <input type="text" name="name" value="<%=taxiDTO.getName()%>" placeholder= "Name" class="form-control" id="name" required>
    </div>
    <div class="form-group">
        <label for="car">Car:</label>
        <input type="text" name="car" value="<%=taxiDTO.getCar()%>" placeholder= "Car" class="form-control" id="car" required>
    </div>
    <div class="form-group">
        <label for="phone">Phone:</label>
        <input type="text" name="phone" value="<%=taxiDTO.getPhone()%>" placeholder= "you phone (+371xxxxxxx)" class="form-control" id="phone" required>
    </div>
    <div class="form-group">
        <label for="lgn">Login:</label>
        <input type="text" name="login" value="<%=taxiDTO.getLogin()%>" placeholder= "Login" class="form-control" id="lgn" required>
    </div>
    <div class="form-group">
        <label for="pwd">Password:</label>
        <input type="password" name="password" value="<%=taxiDTO.getPassword()%>" placeholder="Password" class="form-control" id="pwd" required>
    </div>
    <input type="submit" value="Save data" style='width:265px;'>

    <h3><%=request.getAttribute("message")%></h3>
</form>
</div>

</body>
</html>