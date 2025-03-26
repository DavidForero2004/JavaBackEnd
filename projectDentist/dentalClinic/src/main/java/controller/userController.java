/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import persistence.UserJpaController;

/**
 *
 * @author david
 */
@WebServlet(name = "userController", urlPatterns = {"/userController"})
public class userController extends HttpServlet {

    String Message = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        findUser(email, password, response, request);

    }

    protected void findUser(String email, String password, HttpServletResponse response, HttpServletRequest request) throws IOException {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DentistPu");
        UserJpaController userJPA = new UserJpaController(emf);

        User user = userJPA.findUserByEmail(email);

        if (user != null) {

            String pass = user.getPassword();
            boolean isTrue = userLogin(email, password, user.getEmail(), pass);

            if (isTrue == true) {
                //crear una sesion
                HttpSession session = request.getSession(true);
                session.setAttribute("userSession", user);

                response.sendRedirect("view/index.jsp");
            } else {

                response.sendRedirect("login.jsp");
            }
        } else {

            response.sendRedirect("login.jsp");
        }
    }

    protected boolean userLogin(String email, String password, String emailD, String passwordD) {
        if (email.equals(emailD) && password.equals(passwordD)) {
            return true;
        } else {
            return false;
        }
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("login.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
