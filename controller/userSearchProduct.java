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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import model.Productdetail;

/**
 *
 * @author ILLEGEAR
 */
@WebServlet(name = "userSearchProduct", urlPatterns = {"/userSearchProduct"})
public class userSearchProduct extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        
        String input = request.getParameter("searchValue");
        HttpSession session = request.getSession();
        List<Productdetail> productList = (List<Productdetail>) session.getAttribute("productList");
        List<Productdetail> searchList = new ArrayList<> ();
        int j=0;
        for(int i = 0; i< productList.size();i++){
            if(productList.get(i).getCode().contains(input)){
                searchList.add(j, productList.get(i));
                j++;
            }else if(productList.get(i).getPName().contains(input)){
                searchList.add(j, productList.get(i));
                j++;
            }
        }
        session.setAttribute("searchResult",searchList);
        response.sendRedirect("RetrieveProductDetail?checkSearch=1");
//        out.println(input);
//        for(int i=0;i<searchList.size();i++){
//        out.println(searchList.get(i).getCode());
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
