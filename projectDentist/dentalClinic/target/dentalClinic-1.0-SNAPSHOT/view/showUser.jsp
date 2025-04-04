<%-- 
    Document   : showUser
    Created on : 2/04/2025, 5:29:09 p. m.
    Author     : juand
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../layout/header.jsp" %>
        <title>Ver Usuarios</title>
    </head>
    <body id="page-top">
        <div id="wrapper">
            <%@include file="../layout/side_bar.jsp" %>
            <div id="content-wrapper" class="d-flex flex-column">
                <%@include file="../layout/nav.jsp" %>
                
                <%@include file="../layout/tableUser.jsp" %>
                
                <%@include file="../layout/footer.jsp" %>
            </div>
        </div>


        <%@include file="../layout/script.jsp" %> 

    </body>
</html>
