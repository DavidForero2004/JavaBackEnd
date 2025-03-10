<%-- 
    Document   : index
    Created on : 9/03/2025, 9:11:02 p. m.
    Author     : david
--%>

<%@page import="persistence.CategoryJpaController"%>
<%@page import="persistence.BookJpaController"%>
<%@page import="persistence.AuthorJpaController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <% 
            
            BookJpaController book = new BookJpaController();
            AuthorJpaController author = new AuthorJpaController(); 
            CategoryJpaController category = new CategoryJpaController();
        
        %>
    </body>
</html>
