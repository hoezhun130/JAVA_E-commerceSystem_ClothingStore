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
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import model.Customer;

/**
 *
 * @author ILLEGEAR
 */
@WebServlet(name = "TopUp", urlPatterns = {"/TopUp"})
public class TopUp extends HttpServlet {

    private String host = "jdbc:derby://localhost:1527/assignment";
    private String user = "nbuser";
    private String password = "nbuser";
    private Connection conn;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id"));
        double walletBalance = Double.parseDouble(request.getParameter("walletBalance"));
        double topUp = Double.parseDouble(request.getParameter("topUp"));
        double total = walletBalance + topUp;
        try {
            conn = DriverManager.getConnection(host, user, password);

            PreparedStatement stmt = conn.prepareStatement("UPDATE customer SET WALLETBALANCE = ? WHERE ID = ?");
            stmt.setDouble(1, total);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            conn.close();
            
            
              /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RetrieveDetails</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<script>");
            out.println("alert('Top Up Successfully.');");
            out.println("location.href='CustomerDetails?id="+id+"'");
            out.println("</script>");
            out.println("</body>");
            out.println("</html>");
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
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
