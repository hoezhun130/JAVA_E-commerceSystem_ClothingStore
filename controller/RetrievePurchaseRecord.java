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
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import model.Customer;
import model.Purchasedrecord;

/**
 *
 * @author MSI PC
 */
@WebServlet(name = "RetrievePurchaseRecord", urlPatterns = {"/RetrievePurchaseRecord"})
public class RetrievePurchaseRecord extends HttpServlet {

    @PersistenceContext
    EntityManager em;
    @Resource
    UserTransaction utx;
    private String host = "jdbc:derby://localhost:1527/assignment";
    private String user = "nbuser";
    private String password = "nbuser";
    private Connection conn;
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
        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet RetrievePurchaseRecord</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet RetrievePurchaseRecord at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
        if(request.getParameter("page").equals("myPurchased")){
            List<Purchasedrecord> purchasedrecordList = new ArrayList<Purchasedrecord>();
            try{
             conn = DriverManager.getConnection(host, user, password);

            PreparedStatement stmt = conn.prepareStatement("Select * From PURCHASEDRECORD where CUSTOMERID ="+ Integer.parseInt(request.getParameter("id")));

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Purchasedrecord purchasedrecord = new Purchasedrecord(rs.getInt("RECORDID") , rs.getInt("CUSTOMERID"), rs.getDouble("TOTALPAYMENT"), rs.getString("PURCHASEDITEMS"), rs.getString("DELIVERYSTATUS"));
                purchasedrecordList.add(purchasedrecord);
            }
            conn.close();
            
            HttpSession session =request.getSession();
            session.setAttribute("recordList",purchasedrecordList);
            
//            RequestDispatcher rd = request.getRequestDispatcher("frontEnd/Packaging.jsp");
//            rd.forward (request, response);
            response.sendRedirect("frontEnd/Packaging.jsp");
            }catch(SQLException ex){
                String msg = ex.getMessage();
                ex.printStackTrace();
            } 

        }
        else if(request.getParameter("page").equals("orderHistory")){
            List<Purchasedrecord> purchasedrecordList = new ArrayList<Purchasedrecord>();
            try{
            conn = DriverManager.getConnection(host, user, password);

            PreparedStatement stmt = conn.prepareStatement("Select * From PURCHASEDRECORD where CUSTOMERID ="+ Integer.parseInt(request.getParameter("id")));

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Purchasedrecord purchasedrecord = new Purchasedrecord(rs.getInt("RECORDID") , rs.getInt("CUSTOMERID"), rs.getDouble("TOTALPAYMENT"), rs.getString("PURCHASEDITEMS"), rs.getString("DELIVERYSTATUS"));
                purchasedrecordList.add(purchasedrecord);
            }
            conn.close();
            
            HttpSession session =request.getSession();
            session.setAttribute("recordList",purchasedrecordList);
            
            response.sendRedirect("frontEnd/OrderHistory.jsp");

            }catch(SQLException ex){
                 String msg = ex.getMessage();
                ex.printStackTrace();
            }
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
