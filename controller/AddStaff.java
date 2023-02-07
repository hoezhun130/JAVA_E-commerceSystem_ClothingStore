/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import model.Staff;
import model.StaffService;

/**
 *
 * @author User
 */
public class AddStaff extends HttpServlet {

    @PersistenceContext
    EntityManager em;
    @Resource
    UserTransaction utx;

    String connectionUrl = "jdbc:derby://localhost:1527/assignment";
    String userid = "nbuser";
    String password = "nbuser";

    Connection connection;
    Statement statement;
    ResultSet resultSet;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {

            connection = DriverManager.getConnection(connectionUrl, userid, password);
            statement = connection.createStatement();
            String sql = "SELECT * FROM STAFF ORDER BY ID DESC";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                String fname = resultSet.getString("ID");
            }

            String input = resultSet.getString("ID");
            String lastThreeDigits = "";
            lastThreeDigits = input.substring(input.length() - 3);

            int staffID = Integer.parseInt(lastThreeDigits);
            staffID = staffID + 1;
            String staffid = "";

            if (staffID >= 0 && staffID < 10) {
                staffid = "ST00";
            } else if (staffID >= 10 && staffID < 100) {
                staffid = "ST0";
            } else if (staffID >= 100) {
                staffid = "ST";
            }

            String id = staffid + staffID;
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            int age = Integer.parseInt(request.getParameter("age"));
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            String role = "staff";

            //---------------
            int register = (Integer.parseInt(request.getParameter("register")));
            //---------------

            Staff staff = new Staff(id, name, phone, age, email, password, role);
            StaffService staffservice = new StaffService(em);

            utx.begin();
            boolean success = staffservice.addStaff(staff);
            utx.commit();

            HttpSession session = request.getSession();
            session.setAttribute("id", id);
            session.setAttribute("name", name);
            session.setAttribute("phone", phone);
            session.setAttribute("age", age);
            session.setAttribute("email", email);
            session.setAttribute("password", password);
            session.setAttribute("role", role);
            session.setAttribute("success", success);
            session.setAttribute("register", register);

            response.sendRedirect("backEnd/Staff/Done/ConfirmAdd.jsp");

        } catch (Exception ex) {
            //Logger.getLogger(AddStaff.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Something went wrong.");
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
        processRequest(request, response);
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
        processRequest(request, response);
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
