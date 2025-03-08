/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

$(document).ready(function(){
	// Activate tooltip
	$('[data-toggle="tooltip"]').tooltip();
	
	// Select/Deselect checkboxes
	var checkbox = $('table tbody input[type="checkbox"]');
	$("#selectAll").click(function(){
		if(this.checked){
			checkbox.each(function(){
				this.checked = true;                        
			});
		} else{
			checkbox.each(function(){
				this.checked = false;                        
			});
		} 
	});
	checkbox.click(function(){
		if(!this.checked){
			$("#selectAll").prop("checked", false);
		}
	});
});

    $(document).ready(function () {
    $(".editU").click(function () {
        var id = $(this).data("id");
        var dni = $(this).data("dni");
        var nombre = $(this).data("nombre");
        var apellido = $(this).data("apellido");
        var telefono = $(this).data("telefono");
        $("#editEmployeeModal input[name='id']").val(id);
        $("#editEmployeeModal input[name='dni']").val(dni);
        $("#editEmployeeModal input[name='nombre']").val(nombre);
        $("#editEmployeeModal input[name='apellido']").val(apellido);
        $("#editEmployeeModal input[name='telefono']").val(telefono);
    });
});