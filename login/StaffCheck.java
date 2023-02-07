/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;

import da.StaffDA;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.StaffLoginModel;

public class StaffCheck {

    public String authenticateUser(StaffLoginModel stafflogin) {
        String id = stafflogin.getId();
        String password = stafflogin.getPassword();

        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String idDB = "";
        String passwordDB = "";
        String roleDB = "";

        try {
            con = StaffDA.createConnection();
            statement = con.createStatement();
            resultSet = statement.executeQuery("select id,password,role from staff");

            while (resultSet.next()) {
                idDB = resultSet.getString("id");
                passwordDB = resultSet.getString("password");
                roleDB = resultSet.getString("role");

                if (id.equals(idDB) && password.equals(passwordDB) && roleDB.equals("admin")) {
                    return "Admin_Role";
                } else if (id.equals(idDB) && password.equals(passwordDB) && roleDB.equals("staff")) {
                    return "Staff_Role";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Invalid user credentials";
    }
}
