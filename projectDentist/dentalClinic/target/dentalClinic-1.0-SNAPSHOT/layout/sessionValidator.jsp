<%@page import="model.User"%>
<%
    User currentUser = (User) request.getAttribute("currentUser");
    
    // Si no est� en el request, verificar la sesi�n
    if (currentUser == null) {
        currentUser = (User) session.getAttribute("userSession");
    }
    
    // Si no hay usuario autenticado, redirigir
    if (currentUser == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
    
    // Guardar en sesi�n para todas las p�ginas
    session.setAttribute("currentUser", currentUser);
    session.setAttribute("userRolType", currentUser.getRol().getId());
%>