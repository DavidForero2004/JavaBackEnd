<%@page import="model.User"%>
<%
    User currentUser = (User) request.getAttribute("currentUser");
    
    // Si no está en el request, verificar la sesión
    if (currentUser == null) {
        currentUser = (User) session.getAttribute("userSession");
    }
    
    // Si no hay usuario autenticado, redirigir
    if (currentUser == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
    
    // Guardar en sesión para todas las páginas
    session.setAttribute("currentUser", currentUser);
    session.setAttribute("userRolType", currentUser.getRol().getId());
%>