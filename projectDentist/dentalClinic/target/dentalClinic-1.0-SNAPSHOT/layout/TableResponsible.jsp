<%@page import="model.Responsible"%>
<%@page import="java.util.List"%>
<%@page import="controller.ResponsibleController"%>
<div class="container-fluid">

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Lista de responsables</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-striped" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Dni</th>
                            <th>Name</th>
                            <th>Last Name</th>
                            <th>Phone Number</th>                            
                            <th>Address</th>
                            <th>Date Birth</th>
                            <th>Email</th>
                            <th>Type Responsible</th>                          
                            <th>Action</th>
                        </tr>
                    </thead>

                    <tbody>
                        <%
                            ResponsibleController ControlR = new ResponsibleController();
                            List<Responsible> listResponsible = (List<Responsible>) session.getAttribute("listResponsible");

                            int cont = 1;
                            if (listResponsible != null && !listResponsible.isEmpty()) {
                                for (Responsible listR : listResponsible) {
                        %>
                        <tr>
                            <td><%=cont%></td>
                            <td><%=listR.getDni()%></td>
                            <td><%=listR.getName()%></td>
                            <td><%=listR.getLastName()%></td>
                            <td><%=listR.getPhoneNumber()%> </td>
                            <td><%=listR.getAddress()%></td>
                            <%
                                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                                sdf.setTimeZone(java.util.TimeZone.getTimeZone("America/Bogota")); // o tu zona local
                            %>
                            <td><%= (listR.getDateBirth() != null) ? sdf.format(listR.getDateBirth()) : ""%></td>
                            <td><%=listR.getEmail()%> </td>
                            <td><%=listR.getType()%></td>
                            <td>
                                <a href="#" class="editU" data-toggle="modal" data-target="#editEmployeeModal"
                                   data-id="<%=listR.getId()%>"
                                   data-dni="<%=listR.getDni()%>"
                                   data-name="<%=listR.getName()%>"
                                   data-lastname="<%=listR.getLastName()%>"
                                   data-phonenumber="<%=listR.getPhoneNumber()%>"
                                   data-address="<%=listR.getAddress()%>"
                                   data-datebirth="<%= (listR.getDateBirth() != null) ? new java.text.SimpleDateFormat("yyyy-MM-dd").format(listR.getDateBirth()) : ""%>"
                                   data-email="<%=listR.getEmail()%>"
                                   data-typeresponsible="<%=listR.getType()%>"
                                   <i class="material-icons" data-toggle="tooltip" title="Edit" style="color: greenyellow;">&#xE254;</i>
                                </a >
                                <a href="#deleteEmployeeModal" class="deleteU" data-toggle="modal" data-id="<%=listR.getId()%>">
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
                <form action="../ResponsibleController" method="POST">
                    <div class="modal-header">						
                        <h4 class="modal-title">Editar horario</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <div class="modal-body">					
                        <input type="hidden" id="editId" name="id_responsableEditar">
                        <div class="form-group">
                            <label>DNI</label>
                            <input type="text" class="form-control" id="editDni" name="dniEdit" required>
                        </div>
                        <div class="form-group">
                            <label>Name</label>
                            <input type="text" class="form-control" id="editName" name="nameEdit" required>
                        </div>
                        <div class="form-group">
                            <label>Last Name</label>
                            <input type="text" class="form-control" id="editPhoneNumber" name="lastNameEdit" required>
                        </div>	
                        <div class="form-group">
                            <label>Phone number</label>
                            <input type="text" class="form-control" id="editAddress" name="phoneNumberEdit" required>
                        </div>
                        <div class="form-group">
                            <label>Address</label>
                            <input type="text" class="form-control" id="editTelefono" name="addressEdit" required>
                        </div>
                        <div class="form-group">
                            <label>Date Birth</label>
                            <input type="date" class="form-control" id="editDateBirth" name="dateBirthEdit" required>
                        </div> 
                        <div class="form-group">
                            <label>Email</label>
                            <input type="text" class="form-control" id="editEmail" name="emailEdit" required>
                        </div>
                        <div class="form-group">
                            <label>Type Resposible</label>
                            <select id="edittypeResponsible" name="typeResponsibleEdit">
                                <option value="Madre">Madre</option>
                                <option value="Padre">Padre</option>
                                <option value="Herman@ Mayor">Herman@ mayor</option>
                            </select>                        
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-info" name="action" value="Edit" >Editar</button>
                    </div>
                </form>

            </div>
        </div>
    </div>
    <!-- Delete Modal HTML -->
    <div id="deleteEmployeeModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="../ResponsibleController" method="POST">
                    <div class="modal-header">						
                        <h4 class="modal-title">Eliminar Responsable</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <input type="hidden" id="editId" name="id_responsableEliminar">
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
