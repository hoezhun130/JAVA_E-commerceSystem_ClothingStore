/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import da.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
import model.Productdetail;

/**
 *
 * @author MSI PC
 */
@WebServlet(name = "Loginform", urlPatterns = {"/Loginform"})
public class Loginform extends HttpServlet {

    @PersistenceContext
    EntityManager em, em2;
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
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet Loginform</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet Loginform at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }    
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
        
        HttpSession session = request.getSession();
        session.invalidate();
        
        response.sendRedirect("frontEnd/Login.jsp");
        
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

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        CustomerDAO custDAO = new CustomerDAO();

        try {
            Customer cust = custDAO.checkLogin(username, password);
            String page = "frontEnd/Login.jsp";

            if (cust != null) {
                
                Query query2 = em.createNamedQuery("Customer.findByUsername").setParameter("username", username);
                Customer customer = (Customer) query2.getSingleResult();
                HttpSession session = request.getSession();
                session.setAttribute("customer", customer);
                
                Query query3 = em2.createNamedQuery("Productdetail.findAll");
                List<Productdetail> productList = query3.getResultList();
                session.setAttribute("productList",productList);
                
                page = "frontEnd/index.jsp";
                
            } else {
                String message = "Invalid username or password. Please Try again later!";
                request.setAttribute("message", message);
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request, response);

        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
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
