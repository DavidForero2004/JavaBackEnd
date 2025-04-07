<%@page import="model.Rol"%>
<%@page import="java.util.List"%>
<%@page import="controller.rolController"%>


<div class="container-fluid">
    
    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Crear un usuarios</h1>
    <p class="mb-4">Formulario para la creacion de un usuario.</p>
    
    
    <form action="../userController"  method="POST" class="createUForm">
        <div class="mb-3">
            <label for="exampleInputDni" class="form-label">Dni</label>
            <input type="number" class="form-control" name="dni" id="int" placeholder="Dni">
        </div>
        <div class="mb-3">
            <label for="exampleInputName" class="form-label">Name</label>
            <input type="text" class="form-control" name="name" id="name" placeholder="Name">
        </div>
        <div class="mb-3">
            <label for="exampleInputLastName" class="form-label">Last Name</label>
            <input type="text" class="form-control" name="lastName" id="lastName" placeholder="Last Name">
        </div>
        <div class="mb-3">
            <label for="exampleInputUserName" class="form-label">User name</label>
            <input type="text" class="form-control" name="userName" id="userName" placeholder="User name">
        </div>
        <div class="mb-3">
            <label for="exampleInputPhoneNumber" class="form-label">Phone number</label>
            <input type="number" class="form-control" name="phoneNumber" id="number" placeholder="Phone number">
        </div>
        <div class="mb-3">
            <label for="exampleInputAddress" class="form-label">Address</label>
            <input type="text" class="form-control" name="address" id="address" placeholder="Address">
        </div>
        <div class="mb-3">
            <label for="exampleInputAddress" class="form-label">Date Birth</label>
            <input type="date" class="form-control" name="dateBirth" id="dateBirth" placeholder="Date">
        </div>
        <div class="mb-3">
            <label for="exampleInputAddress" class="form-label">Rol</label>
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
        <div class="mb-3">
            <label for="exampleInputEmail" class="form-label">Email address</label>
            <input type="email" class="form-control" name="email" id="email" placeholder="Email">
            <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
        </div>
        <div class="mb-3">
            <label for="exampleInputPassword" class="form-label">Password</label>
            <input type="password" class="form-control" name="password" id="pwd" placeholder="Password">
        </div>      
        <button type="submit" name="action" value="Create" class="btn btn-primary">Registrar</button>
    </form>

</div>
