/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.RequestDispatcher;
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
 * @author MSI PC
 */
@WebServlet(name = "Signupform", urlPatterns = {"/Signupform"})
public class Signupform extends HttpServlet {
    @PersistenceContext EntityManager em;
    @Resource UserTransaction utx;

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
//            out.println("<title>Servlet Signupform</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet Signupform at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
          try {
              String fullname = request.getParameter("fullname");
              String username = request.getParameter("username");
              String email = request.getParameter("email");
              String phone = request.getParameter("phone");
              String address = request.getParameter("address");
              String password = request.getParameter("password");
              String confirmpwd = request.getParameter("confirmPwd");
              
              Customer cust = new Customer(fullname, username, password, confirmpwd, email, phone, address);
              utx.begin();
              em.persist(cust);
              utx.commit();
              
              String message = "Your Account has been succesfully created.";
              request.setAttribute("message", message);
              String page = "frontEnd/Signup.jsp";
              RequestDispatcher dispatcher = request.getRequestDispatcher(page);
              dispatcher.forward(request, response);
          }
          catch (Exception ex) {
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
