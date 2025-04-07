/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Responsible;
import persistence.ResponsibleJpaController;
import persistence.exceptions.NonexistentEntityException;

/**
 *
 * @author juand
 */
@WebServlet(name = "ResponsibleController", urlPatterns = {"/ResponsibleController"})
public class ResponsibleController extends HttpServlet {

    ResponsibleJpaController responsibleJPA = new ResponsibleJpaController();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Responsible> listResponsible = listResponsible();
        HttpSession misession = request.getSession();
        misession.setAttribute("listResponsible", listResponsible);

        response.sendRedirect("view/ShowResponsible.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        List<Responsible> listResponsible = responsibleJPA.findResponsibleEntities();

        switch (action) {
            case "Create":

                try {
                    String dni = request.getParameter("dni");
                    String name = request.getParameter("name");
                    String lastName = request.getParameter("lastName");
                    String phoneNumber = request.getParameter("phoneNumber");
                    String address = request.getParameter("address");
                    String email = request.getParameter("email");
                    String dateBirthStr = request.getParameter("dateBirth");
                    String typeResponsible = request.getParameter("typeResponsible");

                    // Validar que la fecha de nacimiento sea anterior a hoy
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date dateBirth = formatter.parse(dateBirthStr);
                    Date today = new Date();

                    if (dateBirth.after(today)) {
                        response.sendRedirect("view/CreateResponsible.jsp?error=La fecha de nacimiento debe ser anterior a hoy.");
                        return;
                    }

                    Responsible responsible = new Responsible();
                    responsible.setDni(dni);
                    responsible.setName(name);
                    responsible.setLastName(lastName);
                    responsible.setPhoneNumber(phoneNumber);
                    responsible.setAddress(address);
                    responsible.setEmail(email);
                    responsible.setDateBirth(dateBirth);
                    responsible.setType(typeResponsible);
                    createSchedule(responsible);

                    listResponsible = responsibleJPA.findResponsibleEntities();
                    HttpSession session = request.getSession();
                    session.setAttribute("listResponsible", listResponsible);

                    response.sendRedirect("view/ShowResponsible.jsp?success=Responsable registrado exitosamente.");

                } catch (Exception e) {
                }
                break;

            case "Edit":
                try {
                    int id = Integer.parseInt(request.getParameter("id_responsableEditar"));
                    Responsible responsible = getResponsible(id);

                    if (responsible == null) {
                        response.sendRedirect("view/index.jsp?error=Responsable no encontrado.");
                        return;
                    }

                    String dni = request.getParameter("dniEdit");
                    String name = request.getParameter("nameEdit");
                    String lastName = request.getParameter("lastNameEdit");
                    String phoneNumber = request.getParameter("phoneNumberEdit");
                    String address = request.getParameter("addressEdit");
                    String email = request.getParameter("emailEdit");
                    String dateBirthStr = request.getParameter("dateBirthEdit");
                    String typeResponsible = request.getParameter("typeResponsibleEdit");

                    // Validar que la fecha de nacimiento sea anterior a hoy
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date dateBirth = formatter.parse(dateBirthStr);
                    Date today = new Date();

                    if (dateBirth.after(today)) {
                        response.sendRedirect("view/ShowSchedule.jsp?error=La fecha de nacimiento debe ser anterior a hoy.");
                        return;
                    }

                    responsible.setId(id);
                    responsible.setDni(dni);
                    responsible.setName(name);
                    responsible.setLastName(lastName);
                    responsible.setPhoneNumber(phoneNumber);
                    responsible.setAddress(address);
                    responsible.setEmail(email);
                    responsible.setDateBirth(dateBirth);
                    responsible.setType(typeResponsible);

                    editResponsible(responsible);

                    listResponsible = responsibleJPA.findResponsibleEntities();
                    HttpSession session = request.getSession();
                    session.setAttribute("listResponsible", listResponsible);
                    response.sendRedirect("view/ShowResponsible.jsp?success=Responsable editado exitosamente.");

                } catch (NumberFormatException e) {
                    System.out.println(e);
                    response.sendRedirect("view/ShowResponsible.jsp?error=Error en los datos del responsable.");
                } catch (Exception ex) {
                    System.out.println(ex);
                    response.sendRedirect("view/ShowResponsible.jsp?error=No se pudo editar el responsable.");
                }

                break;

            case "Delete":

                int id = Integer.parseInt(request.getParameter("id_responsableEliminar"));

                deleteResposible(id);

                listResponsible = responsibleJPA.findResponsibleEntities();
                HttpSession session = request.getSession();
                session.setAttribute("listResponsible", listResponsible);
                response.sendRedirect("view/ShowResponsible.jsp?success=Responsable eliminado exitosamente.");

                break;
            default:
                throw new AssertionError();
        }

    }

    public List<Responsible> listResponsible() {
        return responsibleJPA.findResponsibleEntities();
    }

    protected void createSchedule(Responsible responsible) {
        responsibleJPA.create(responsible);
    }

    public Responsible getResponsible(int id_editar) {
        return responsibleJPA.findResponsible(id_editar);
    }

    public void editResponsible(Responsible responsible) {
        try {
            responsibleJPA.edit(responsible);
        } catch (Exception ex) {
            Logger.getLogger(ScheduleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteResposible(int id_eliminar) {
        try {
            responsibleJPA.destroy(id_eliminar);
        } catch (NonexistentEntityException ex) {

            Logger.getLogger(ScheduleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
