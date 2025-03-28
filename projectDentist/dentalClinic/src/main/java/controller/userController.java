/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Rol;
import model.User;
import org.mindrot.jbcrypt.BCrypt;
import persistence.UserJpaController;

@WebServlet(name = "userController", urlPatterns = {"/userController"})
public class userController extends HttpServlet {

    String Message = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    UserJpaController userJPA = new UserJpaController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordRpd = request.getParameter("repwd");

        switch (action) {
            case "signup":
                try {
                    String dni = request.getParameter("dni");
                    String name = request.getParameter("name");
                    String lastName = request.getParameter("lastName");
                    String phoneNumber = request.getParameter("phoneNumber");
                    String address = request.getParameter("address");
                    String userName = request.getParameter("userName");
                    String dateBirthStr = request.getParameter("dateBirth");                

                    // Validar que ningún campo esté vacío
                    if (dni == null || name == null || lastName == null || phoneNumber == null
                            || address == null || userName == null || dateBirthStr == null
                            || email == null || password == null || passwordRpd == null
                            || dni.isEmpty() || name.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty()
                            || address.isEmpty() || userName.isEmpty() || dateBirthStr.isEmpty()
                            || email.isEmpty() || password.isEmpty() || passwordRpd.isEmpty()) {

                        response.sendRedirect("login.jsp?error=Todos los campos son obligatorios.");
                        return;
                    }

                    // Validar que las contraseñas coincidan
                    if (!password.equals(passwordRpd)) {
                        response.sendRedirect("login.jsp?error=Las contraseñas no coinciden.");
                        return;
                    }

                    // Validar que la fecha de nacimiento sea anterior a hoy
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date dateBirth = formatter.parse(dateBirthStr);
                    Date today = new Date();

                    if (dateBirth.after(today)) {
                        response.sendRedirect("login.jsp?error=La fecha de nacimiento debe ser anterior a hoy.");
                        return;
                    }

                    // Si pasa todas las validaciones, encriptamos la contraseña y creamos el usuario
                    password = hashPassword(password, passwordRpd); // Asegúrate de que este método encripte bien la contraseña

                    Rol rol = new Rol();
                    rol.setId(1);

                    User user = new User();
                    user.setDni(dni);
                    user.setName(name);
                    user.setLastName(lastName);
                    user.setPhoneNumber(phoneNumber);
                    user.setAddress(address);
                    user.setUserName(userName);
                    user.setDateBirth(dateBirth);
                    user.setEmail(email);
                    user.setPassword(password);
                    user.setRol(rol);

                    createUser(user); // Método para guardar el usuario en la BD

                    response.sendRedirect("login.jsp?success=Usuario registrado exitosamente.");
                } catch (ParseException e) {
                    response.sendRedirect("login.jsp?error=Formato de fecha incorrecto.");
                } catch (Exception ex) {
                    response.sendRedirect("login.jsp?error=Error interno del servidor.");
                }
                break;

            case "login":
                boolean validacion = false;
                List<User> listUser = userJPA.findUserEntities();
                User loggedInUser = null;

                for (User usu : listUser) {
                    if (usu.getEmail().equals(email) && BCrypt.checkpw(password, usu.getPassword())) {
                        loggedInUser = usu;
                        validacion = true;
                        break;
                    }
                }

                if (validacion && loggedInUser != null) {
                    HttpSession misession = request.getSession(true);
                    misession.setAttribute("userSession", loggedInUser); // Guardar el usuario completo
                    response.sendRedirect("view/index.jsp");
                    System.out.println("Si entraste");
                } else {
                    response.sendRedirect("login.jsp?error=true");
                }
                break;
            default:
                throw new AssertionError();
        }
    }

    public boolean comprobarAcceso(String email, String password) {
        List<User> listUser = new ArrayList<User>();
        listUser = userJPA.findUserEntities();

        for (User usu : listUser) {

            // Compara la contraseña ingresada con el hash almacenado
            boolean isPasswordValid = BCrypt.checkpw(password, usu.getPassword());
            System.out.println(isPasswordValid);

            if (usu.getEmail().equals(email)) {
                if (isPasswordValid) {
                    return true;
                } else {
                    Message = "User or Password Incorrect";
                }
            } else {
                Message = "User or Password Incorrect";
            }
        }
        return false;
    }

    protected void createUser(User user) {
        userJPA.create(user);
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("login.jsp");
    }

    public String hashPassword(String password, String validatePassowrd) {
        //Encrypt
        String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
        return passwordHash;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
