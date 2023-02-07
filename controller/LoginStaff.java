/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import login.StaffCheck;
import model.StaffLoginModel;

public class LoginStaff extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        StaffLoginModel stafflogin = new StaffLoginModel();

        stafflogin.setId(id);
        stafflogin.setPassword(password);

        StaffCheck check = new StaffCheck();

        try {
            String userValidate = check.authenticateUser(stafflogin);
            if (userValidate.equals("Admin_Role")) {
                System.out.println("Admin's Home");

                HttpSession session2 = request.getSession();
                session2.setAttribute("Admin", id);
                request.setAttribute("id", id);

                //request.getRequestDispatcher("/Staff/MaintainStaff.html").forward(request, response);
                response.sendRedirect("ViewStaff");

            } else if (userValidate.equals("Staff_Role")) {
                System.out.println("Staff's Home");

                HttpSession session2 = request.getSession();
                session2.setMaxInactiveInterval(10 * 60);
                session2.setAttribute("User", id);
                request.setAttribute("id", id);

                //request.getRequestDispatcher("/Product/product.html").forward(request, response);
                response.sendRedirect(request.getContextPath() +"/backEnd/HomePage.jsp");
                
            } else {
                System.out.println("Error message = " + userValidate);
                request.setAttribute("errMessage", userValidate);

                request.getRequestDispatcher("BackEndLogin.jsp").forward(request, response);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
