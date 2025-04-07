<%-- 
    Document   : CreaSchedule
    Created on : 6/04/2025, 10:44:21?p.?m.
    Author     : juand
--%>

<div class="container-fluid">
    
    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Crear un horario</h1>
    <p class="mb-4">Formulario para la creacion de un horario.</p>
    
    <form action="../ScheduleController"  method="POST" class="createSForm">
        
        <div class="mb-3">
            <label for="exampleInputAddress" class="form-label">Start Date</label>
            <input type="time" class="form-control" name="startTime" id="startTime" placeholder="Start Time">
        </div>
        <div class="mb-3">
            <label for="exampleInputAddress" class="form-label">End Date</label>
            <input type="time" class="form-control" name="endTime" id="endTime" placeholder="End Time">
        </div>
        
        <button type="submit" name="action" value="Create" class="btn btn-primary">Registrar</button>
        
    </form>
    
</div>
