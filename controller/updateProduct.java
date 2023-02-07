/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import model.Productdetail;
import model.productService;

/**
 *
 * @author HoeZhun
 */
public class updateProduct extends HttpServlet {
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
try {
            productService productService = new productService(em);
            String code = request.getParameter("code");
            String pName = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            double rating = Double.parseDouble(request.getParameter("rating"));
            String color = request.getParameter("color");
            String sleevelength = request.getParameter("sleeve");
            String material = request.getParameter("material");
            String season = request.getParameter("season");
            String neckline = request.getParameter("neckline");
            Integer stock = Integer.parseInt(request.getParameter("stock"));
            String shipsfrom = request.getParameter("ship");
            String des = request.getParameter("des");
            String img = request.getParameter("image");

            Productdetail product = new Productdetail(code, pName, price, rating, color, sleevelength, material, season, neckline, stock, shipsfrom, des, img);

            utx.begin();
            boolean success = productService.updateProduct(product);
            utx.commit();
            HttpSession session = request.getSession();
            session.setAttribute("success", success);
            response.sendRedirect("backEnd/UpdateConfirm.jsp");
        } catch (Exception ex) {
            Logger.getLogger(addProduct.class.getName()).log(Level.SEVERE, null, ex);
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
