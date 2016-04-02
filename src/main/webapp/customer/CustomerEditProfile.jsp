<%@ page import="entity.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP page</title>
</head>
<body>

<a href="/customer"> Main customer menu </a> <br>

<div class="container">

    <h1>Customer data change</h1>

    <%  Customer customer = (Customer)session.getAttribute("customer");%>

    <form role="form" name = form1 action="/customer/customerEditProfile" method="POST">
        <div class="form-group">
            <label for="usr">Name, Surname:</label>
            <input type="text" name="name" value="<%=customer.getName()%>" placeholder= "Name" class="form-control" id="usr">
        </div>
        <div class="form-group">
            <label for="phone">Phone:</label>
            <input type="text" name="phone" value="<%=customer.getPhone()%>" placeholder= "you phone (+371xxxxxxx)" class="form-control" id="phone">
        </div>
        <div class="form-group">
            <label for="pwd">Password:</label>
            <input type="password" name="password" value="<%=customer.getPassword()%>" placeholder="Password" class="form-control" id="pwd">
        </div>
        <input type="submit" value="Save data" style='width:265px;'>

        <h3><%=request.getAttribute("message")%></h3>
    </form>

</div> <!-- /container -->


</body>
</html>