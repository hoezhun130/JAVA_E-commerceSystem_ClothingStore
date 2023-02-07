/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import model.Customer;

/**
 *
 * @author ILLEGEAR
 */
@WebServlet(name = "CustomerDetails", urlPatterns = {"/CustomerDetails"})
public class CustomerDetails extends HttpServlet {

    @PersistenceContext
    EntityManager em;
    @Resource
    UserTransaction utx;
    
    private String host = "jdbc:derby://localhost:1527/assignment";
    private String user = "nbuser";
    private String password = "nbuser";
    private Connection conn;
    private Customer customer;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            conn = DriverManager.getConnection(host, user, password);

            PreparedStatement stmt = conn.prepareStatement("Select * From Customer where ID ="+ Integer.parseInt(request.getParameter("id")));

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                customer = new Customer(rs.getInt("id") , rs.getString("fullname"), rs.getString("username"), rs.getString("password"), rs.getString("confirmpwd"), rs.getString("email"), rs.getString("phone"),rs.getDouble("walletbalance"),rs.getString("address"));
            }
            conn.close();
            
//            int id = Integer.parseInt(request.getParameter("id"));
//            Query query = em.createNamedQuery("Customer.findById").setParameter("id", id);
//            Customer customer = (Customer) query.getSingleResult();
//
            HttpSession session = request.getSession();
            session.setAttribute("customer", customer);

            
            response.sendRedirect("frontEnd/UserAcc.jsp");

        } catch (Exception ex) {
            String msg = ex.getMessage();
            ex.printStackTrace();
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
