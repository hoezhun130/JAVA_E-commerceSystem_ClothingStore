/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import model.Cart;
import model.Productdetail;

/**
 *
 * @author ILLEGEAR
 */
@WebServlet(name = "CartDetail", urlPatterns = {"/CartDetail"})
public class CartDetail extends HttpServlet {

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
        Query query = em.createNamedQuery("Cart.findByCustomerid").setParameter("customerid",Integer.parseInt(request.getParameter("id")));
        List<Cart> cartList = query.getResultList();
        
        HttpSession session =request.getSession();
        session.setAttribute("cartList",cartList);
        
        RequestDispatcher rd = request.getRequestDispatcher("frontEnd/cart.jsp");
        rd.forward (request, response);

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
        try {

            HttpSession session = request.getSession();
            Productdetail product = (Productdetail) session.getAttribute("ATCproduct");

            Cart cartItem = new Cart(product.getCode(), product.getPName(),
                    request.getParameter("color"), Integer.parseInt(request.getParameter("qty")),
                    product.getPrice(), product.getImg(),Integer.parseInt(request.getParameter("id")));

            utx.begin();
            em.persist(cartItem);
            utx.commit();

            response.sendRedirect("CartDetail?id="+request.getParameter("id"));

        } catch (Exception ex) {
            String msg = ex.getMessage();
            ex.printStackTrace();
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
