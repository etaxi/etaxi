<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
   <input onclick="showContent('./authorization.jsp', 'contentBody')" type="button" value="Authorization">
   <input onclick="showContent('./registration.jsp', 'contentBody')" type="button" value="Registration">
--%>
<div class="container">


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
                <a class="navbar-brand"> Main menu: </a>
            </div>
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="/taxi/authorization">Authorization</a></li>
                    <li><a href="/taxi/registration">Registration</a></li>
                </ul>
            </div>
            <!--/.nav-collapse -->
        </div>
    </nav>

<%--   <a href="/taxi/authorization">Authorization</a>    <br>
   <a href="/taxi/registration">Registration</a>     <br>--%>


        <b> ${message} </b>

</div>
