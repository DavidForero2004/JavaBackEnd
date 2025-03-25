/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.user;
import persistence.userJpaController;

/**
 *
 * @author juand
 */
@WebServlet(name = "svUser", urlPatterns = {"/svUser"})
public class svUser extends HttpServlet {

    userJpaController userJpa = new userJpaController();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().removeAttribute("email");
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+"//login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
      
        boolean validacion = false;
        validacion = comprobarAcceso(email, password);

        if (validacion) {
            HttpSession misession = request.getSession(true);
            misession.setAttribute("email", email);
            response.sendRedirect("view/index.jsp");
            //System.out.println("Si entraste");
        } else {
            //System.out.println("No entraste");
            response.sendRedirect("login.jsp?error=true");
        }

    }

    public boolean comprobarAcceso(String email, String password) {
        List<user> listUser = new ArrayList<user>();
        listUser = userJpa.finduserEntities();
        for (user usu : listUser) {
            //System.out.println("Comparando con -> Email BD: [" + usu.getEmail() + "] | Password BD: [" + usu.getPassword() + "]");
            //System.out.println("Ingresado -> Email: [" + email + "] | Password: [" + password + "]");

            if (usu.getEmail().equals(email)) {
                //System.out.println("Email coincide!");
                if (usu.getPassword().equals(password)) {
                    //System.out.println("Password coincide! Si hago el comprobar");
                    return true;
                } else {
                    //System.out.println("Password NO coincide!");
                }
            } else {
               // System.out.println("Email NO coincide!");
            }
        }
        //System.out.println("NO hago el comprobar");
        return false;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
