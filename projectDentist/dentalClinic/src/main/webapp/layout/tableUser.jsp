<%-- 
    Document   : tableUser
    Created on : 2/04/2025, 5:38:08 p. m.
    Author     : juand
--%>

<%@page import="model.Rol"%>
<%@page import="controller.rolController"%>
<%@page import="model.User"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Ver usuarios</h1>
    <p class="mb-4">Lista de los usuarios que estan creados.</p>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Lista de usuarios</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-striped" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Dni</th>
                            <th>User Name</th>
                            <th>Name</th>
                            <th>Last Name</th>
                            <th>Phone number</th>
                            <th>address</th>
                            <th>email</th>
                            <th>Date Birth</th>
                            <th>Rol</th>
                            <th>Action</th>
                        </tr>
                    </thead>

                    <tbody>
                        <%
                            List<User> listUser = (List<User>) session.getAttribute("listUser");

                            int cont = 1;
                            if (listUser != null && !listUser.isEmpty()) {
                                for (User listU : listUser) {
                        %>
                        <tr>
                            <td><%=cont%></td>
                            <td><%=listU.getDni()%></td>
                            <td><%=listU.getUserName()%></td>
                            <td><%=listU.getName()%> </td>
                            <td><%=listU.getLastName()%></td>
                            <td><%=listU.getPhoneNumber()%></td>
                            <td><%=listU.getAddress()%></td>
                            <td><%=listU.getEmail()%></td>
                            <td><%=listU.getDateBirth()%></td>
                            <td><%=listU.getRol().getName()%> </td>
                            <td>
                                <a href="#" class="editU" data-toggle="modal" data-target="#editEmployeeModal"
                                   data-id="<%=listU.getId()%>"
                                   data-dni="<%=listU.getDni()%>"
                                   data-username="<%=listU.getUserName()%>"
                                   data-name="<%=listU.getName()%>"
                                   data-lastname="<%=listU.getLastName()%>"
                                   data-phonenumber="<%=listU.getPhoneNumber()%>"
                                   data-address="<%=listU.getAddress()%>"
                                   data-email="<%=listU.getEmail()%>"
                                   data-datebirth="<%= (listU.getDateBirth() != null) ? new java.text.SimpleDateFormat("yyyy-MM-dd").format(listU.getDateBirth()) : ""%>"
                                   data-rol="<%=listU.getRol().getId()%>"
                                   <i class="material-icons" data-toggle="tooltip" title="Edit" style="color: greenyellow;">&#xE254;</i>
                                </a >
                                <a href="#deleteEmployeeModal" class="deleteU" data-toggle="modal" data-id="<%=listU.getId()%>">
                                    <i class="material-icons" data-toggle="tooltip" title="Delete" style="color: red;">&#xE872;</i>
                                </a>

                            </td>
                        </tr>
                        <%
                                    cont++;
                                }
                            } else {

                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <!-- Edit Modal HTML -->
    <div id="editEmployeeModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="../userController" method="POST">
                    <div class="modal-header">						
                        <h4 class="modal-title">Editar Usuario</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <div class="modal-body">					
                        <input type="hidden" id="editId" name="id_usuarioEditar">
                        <div class="form-group">
                            <label>DNI</label>
                            <input type="text" class="form-control" id="editDni" name="dniEdit" required>
                        </div>
                        <div class="form-group">
                            <label>UserName</label>
                            <input type="text" class="form-control" id="editUsername" name="usernameedit" required>
                        </div>
                        <div class="form-group">
                            <label>Name</label>
                            <input type="text" class="form-control" id="editName" name="nameEdit" required>
                        </div>
                        <div class="form-group">
                            <label>lastName</label>
                            <input type="text" class="form-control" id="editPhoneNumber" name="lastNameEdit" required>
                        </div>	
                        <div class="form-group">
                            <label>phoneNumber</label>
                            <input type="text" class="form-control" id="editAddress" name="phoneNumberEdit" required>
                        </div>
                        <div class="form-group">
                            <label>address</label>
                            <input type="text" class="form-control" id="editTelefono" name="addressEdit" required>
                        </div>
                        <div class="form-group">
                            <label>email</label>
                            <input type="text" class="form-control" id="editEmail" name="emailEdit" required>
                        </div>
                        <div class="form-group">
                            <label>dateBirth</label>
                            <input type="date" class="form-control" id="editDateBirth" name="dateBirthEdit" required>
                        </div>
                        <div class="form-group">
                            <label>Rol</label>
                            <select name="idrol">
                                <% rolController rolControl = new rolController();
                                    List<Rol> listRol = rolControl.listRol();

                                    if (listRol != null) {
                                        for (Rol rol : listRol) {


                                %>
                                <option value="<%=rol.getId()%>"><%=rol.getName()%> </option>
                                <% }
                                    }%>
                            </select>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-info" name="action" value="edit" >Editar</button>
                    </div>
                </form>

            </div>
        </div>
    </div>
    <!-- Delete Modal HTML -->
    <div id="deleteEmployeeModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="../userController" method="POST">
                    <div class="modal-header">						
                        <h4 class="modal-title">Eliminar Usuario</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <input type="hidden" id="editId" name="id_usuarioEliminar">
                    <div class="modal-body">					
                        <p>¿Esta seguro de eliminar este dato?</p>
                        <p class="text-warning"><small>!Esta acción no se puede deshacer¡.</small></p>
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                        <input type="submit" class="btn btn-danger" name="action" value="Delete">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
