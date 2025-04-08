/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Dentist;
import model.Rol;
import model.Schedule;
import model.User;
import org.mindrot.jbcrypt.BCrypt;
import persistence.DentistJpaController;
import persistence.UserJpaController;

/**
 *
 * @author david
 */
@WebServlet(name = "DentistController", urlPatterns = {"/DentistController"})
public class DentistController extends HttpServlet {

    UserJpaController userJPA = new UserJpaController();
    DentistJpaController dentistJPA = new DentistJpaController();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        String dni = request.getParameter("dni");
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String userName = request.getParameter("userName");
        String dateBirthStr = request.getParameter("dateBirth");
        String email = request.getParameter("email");
        String specialty = request.getParameter("specialty");
        int id_schedule = Integer.parseInt(request.getParameter("Schedule"));

        switch (action) {
            case "create":
                //validar que el id no sea == 0    
                int id_user = createUser(dni, name, lastName, phoneNumber, address, dateBirthStr, email, userName, dni, 3);

                System.out.println(id_user);
                
                
                boolean isCorrect = createDentist(specialty, id_schedule, id_user);
                
                
                if (isCorrect) {
                    System.out.println("InsertCorrect");
                }
                break;
            default:
                throw new AssertionError();
        }

    }

    protected int createUser(String dni, String name, String lastName, String phoneNumber, String address, String dateBirth, String email, String userName, String password, int Rol) {

        /*
            1: Paciente, 
            2: Responsable, 
            3: dentista, 
            4: secretaria, 
            5: admin
         */
        //validar que no devuelva Invalid (Falta)
        String passwordHash = hashPassword(password);
        //validar que no devuelva null (Falta)
        Date birthDate = convertDate(dateBirth);

        Rol rol = new Rol();
        rol.setId(Rol);

        try {
            /*10 datos para crear un usuario*/
            User user = new User();
            user.setDni(dni);
            user.setName(name);
            user.setLastName(lastName);
            user.setPhoneNumber(phoneNumber);
            user.setAddress(address);
            user.setUserName(userName);
            user.setDateBirth(birthDate);
            user.setEmail(email);
            user.setPassword(passwordHash);
            user.setRol(rol);

            return userJPA.createAndReturnId(user);
        } catch (Error err) {
            System.out.println(err);
            return 0;
        }
    }

    protected boolean createDentist(String specialty, int id_schedule, int id_user) {

        Dentist dentist = new Dentist();

        Schedule schedule = new Schedule();
        User user = new User();

        try {
            schedule.setId(id_schedule);
            user.setId(id_user);

            dentist.setSpecialty(specialty);
            dentist.setSchedule(schedule);
            dentist.setUser(user);

            dentistJPA.create(dentist);
            return true;
            
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }

    }

    protected String hashPassword(String password) {
        try {
            //Encrypt
            String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
            return passwordHash;
        } catch (Error err) {
            System.out.println(err);
            return "Invalid";
        }
    }

    protected Date convertDate(String date) {
        try {
            // Validar que la fecha de nacimiento sea anterior a hoy
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date dateBirth = formatter.parse(date);
            return dateBirth;
        } catch (ParseException ex) {
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
