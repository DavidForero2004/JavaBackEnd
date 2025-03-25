<%-- 
    Document   : validateSession
    Created on : 24/03/2025, 12:41:39 p. m.
    Author     : juand
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% 
    HttpSession misession = request.getSession();
    String email = (String) request.getSession().getAttribute("email");
    
    if (email == null){
        response.sendRedirect("login.jsp");
    }
%>