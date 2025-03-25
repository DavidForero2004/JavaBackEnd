<%-- 
    Document   : login
    Created on : 8/03/2025, 9:52:32 p. m.
    Author     : david
--%>

<%@page import="persistence.RolJpaController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="layout/header.jsp" %>
        <link rel="stylesheet" href="styles/css/login.css"/>
        <title>JSP Page</title>

    </head>
    <body>
        <% RolJpaController jpaC = new RolJpaController(); %>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="wrapper">
                        <div class="form" id="form1">
                            <div class="title"><h1>Login</h1></div>
                            <form action="#" class="loginForm">
                                <div class="profileImage">
                                   <i class="fa-solid fa-tooth"></i>

                                </div>
                                <div class="form-group">
                                    <i class="fa fa-envelope form-icon"></i>
                                    <input type="email" class="form-control" id="email" placeholder="Email">
                                </div>
                                <div class="form-group">
                                    <i class="fa fa-lock form-icon"></i>
                                    <input type="password" class="form-control" id="pwd" placeholder="Password">
                                </div>
                                <button type="submit" class="btn btn-default">login</button>
                            </form>
                        </div>
                        <div class="form form-horizontal" id="form2">
                            <div class="title"><h1>Sing Up</h1></div>
                            <form action="#" class="signupForm">
                                <div class="profileImage">
                                   <i class="fa-solid fa-tooth"></i>
                                </div>
                                <div class="form-group">
                                    <i class="fa fa-envelope form-icon"></i>
                                    <input type="email" class="form-control" id="email" placeholder="Email">
                                </div>
                                <div class="form-group">
                                    <i class="fa fa-lock form-icon"></i>
                                    <input type="password" class="form-control" id="pwd" placeholder="Password">
                                </div>
                                <div class="form-group">
                                    <i class="fa fa-lock form-icon"></i>
                                    <input type="password" class="form-control" id="repwd" placeholder="Retype-Password">
                                </div>
                                <button type="submit" class="btn btn-default">Sign up</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="layout/script.jsp" %> 
        <script src="styles/js/login.js"></script>
    </body>
</html>
