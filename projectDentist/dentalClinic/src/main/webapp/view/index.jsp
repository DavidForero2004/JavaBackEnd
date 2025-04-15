<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../layout/sessionValidator.jsp" %>
        <c:set var="currentUser" value="${sessionScope.currentUser}" scope="request" />
        <c:set var="userRolType" value="${sessionScope.userRolType}" scope="request" />
        
        <%@include file="../layout/header.jsp" %>
        <title>Dashboard</title>
    </head>
    <body id="page-top">
        <div id="wrapper">
            <%@include file="../layout/side_bar.jsp" %>
            <div id="content-wrapper" class="d-flex flex-column">
                <%@include file="../layout/nav.jsp" %>
                <div class="container-fluid">
                    <!-- Contenido principal de tu página aquí -->
                </div>
                <%@include file="../layout/footer.jsp" %>
            </div>
        </div>
        <%@include file="../layout/script.jsp" %> 
    </body>
</html>