<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form>
<%--
   <input onclick="showContent('./authorization.jsp', 'contentBody')" type="button" value="Authorization">
   <input onclick="showContent('./registration.jsp', 'contentBody')" type="button" value="Registration">
--%>

   <a href="/taxi/TaxiAuthorization.jsp">Authorization</a>
    <br>
   <a href="/taxi/TaxiRegistration.jsp">Registration</a>
    <br>
    <b> ${message} </b>
</form>
