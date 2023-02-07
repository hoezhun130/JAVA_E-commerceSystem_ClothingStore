/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package da;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StaffDA {

    public static Connection createConnection() {
        Connection con = null;
        String url = "jdbc:derby://localhost:1527/assignment";
        String username = "nbuser";
        String password = "nbuser";

        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Post establishing a DB connection - " + con);
        } catch (SQLException e) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            e.printStackTrace();
        }

        return con;
    }
}
