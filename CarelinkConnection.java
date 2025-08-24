/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.mainmenuproject.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *skeleton code 
 * @author olwethu mtwazi
 */
public class CarelinkConnection {
        // MySQL Connection details
    private static final String dbURL = "jdbc:mysql://localhost:3306/carelink?useSSL=false&serverTimezone=UTC\";";
    private static final String username = "root"; // your MySQL username
    private static final String password = ""; // your MySQL password
    
    public static Connection connect() throws SQLException {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found. Make sure the driver is added to your classpath.", e);
        }

        // Create connection
        Connection conn = DriverManager.getConnection(dbURL, username, password);
        conn.setAutoCommit(true);
        return conn;
    }

    public static Connection derbyConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}