/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import com.sun.javafx.css.SizeUnits;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Customer;

/**
 *
 * @author MSI PC
 */
public class CustomerDAO {

    private String dburl="jdbc:derby://localhost:1527/assignment"; 
    private String user = "nbuser";
    private String password = "nbuser";

    public Customer checkLogin(String username, String pass) throws SQLException, ClassNotFoundException {
        
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection connection = DriverManager.getConnection(dburl, user, password);
        String sql = "SELECT * FROM CUSTOMER WHERE username = ? and password = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, username);
        stmt.setString(2, pass);
        
        ResultSet rs = stmt.executeQuery();
        
        Customer cust = null;
        
        if (rs.next()) {
            cust = new Customer();
            cust.setFullname(rs.getString("fullname"));
            cust.setUsername(username);
//            cust.setEmail(rs.getString("email"));
//            cust.setPhone(rs.getString("phone"));
        }
        
        connection.close();
        
        return cust;
    }
    
    public Customer checkUsername(String username) throws SQLException, ClassNotFoundException {
        
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection connection = DriverManager.getConnection(dburl, user, password);
        String sql = "SELECT * FROM CUSTOMER WHERE username = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, username);
        
        ResultSet rs = stmt.executeQuery();
        
        Customer cust = null;
        
        if (rs.next()) {
            cust = new Customer(rs.getString("fullname"), rs.getString("username"), rs.getString("password"), rs.getString("confirmPwd"), rs.getString("email"), rs.getString("phone"), rs.getString("address"));
            
        }
        
        connection.close();
        
        return cust;
    }
    
    public void updatePass(String pass1, String cofirmpwd, String username) throws SQLException, ClassNotFoundException {
        
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection connection = DriverManager.getConnection(dburl, user, password);
        String sql = "UPDATE CUSTOMER SET password = ?, confirmPwd = ? where username = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, pass1);
        stmt.setString(2, cofirmpwd);
        stmt.setString(3, username);
        stmt.executeUpdate();
    }
}
