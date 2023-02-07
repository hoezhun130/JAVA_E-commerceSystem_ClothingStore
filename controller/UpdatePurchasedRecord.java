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
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import model.Cart;
import model.Productdetail;
import model.Purchasedrecord;

/**
 *
 * @author ILLEGEAR
 */
@WebServlet(name = "UpdatePurchasedRecord", urlPatterns = {"/UpdatePurchasedRecord"})
public class UpdatePurchasedRecord extends HttpServlet {

    private String host = "jdbc:derby://localhost:1527/assignment";
    private String user = "nbuser";
    private String password = "nbuser";
    private Connection conn1;
    private Connection conn2;
    @PersistenceContext
    EntityManager em;
    @Resource
    UserTransaction utx;

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
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet RetrieveDetails</title>");
        out.println("</head>");
        out.println("<body>");
        try {
            HttpSession session = request.getSession();
            Purchasedrecord purchasedrecord = (Purchasedrecord) session.getAttribute("purchasedRecord");
            double walletBalance = Double.parseDouble(request.getParameter("walletBalance"));
            double totalPay = purchasedrecord.getTotalpayment();
            double balance = walletBalance-totalPay;
            
            List<Cart> cartList = (List<Cart>) session.getAttribute("cartList");

            if (walletBalance >= totalPay) {
                utx.begin();
                em.persist(purchasedrecord);
                utx.commit();
                out.println("passsed1");

                conn1 = DriverManager.getConnection(host, user, password);

                PreparedStatement stmt1 = conn1.prepareStatement("DELETE FROM CART WHERE CUSTOMERID = ?");
                stmt1.setDouble(1, purchasedrecord.getCustomerid());
                stmt1.executeUpdate();
                conn1.close();

                conn2 = DriverManager.getConnection(host, user, password);

                PreparedStatement stmt2 = conn2.prepareStatement("UPDATE customer SET WALLETBALANCE = ? WHERE ID = ?");
                stmt2.setDouble(1, balance);
                stmt2.setInt(2, purchasedrecord.getCustomerid());
                stmt2.executeUpdate();
                conn2.close();

                out.println("<script>");
                out.println("alert('Payment Made Successfully. Thank You.');");
                out.println("location.href='frontEnd/index.jsp'");
                out.println("</script>");

            } else {

                out.println("<script>");
                out.println("alert('Wallet Balnce insufficient. Please Top Up first.');");
                out.println("location.href='CustomerDetails?id=" + purchasedrecord.getCustomerid() + "'");
                out.println("</script>");

            }

        } catch (Exception ex) {
            String msg = ex.getMessage();
            ex.printStackTrace();
            out.print("failed");
        }
        out.println("</body>");
        out.println("</html>");

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
