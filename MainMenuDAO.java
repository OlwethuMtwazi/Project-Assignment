/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.mainmenu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import za.ac.cput.mainmenuproject.connection.CarelinkConnection;

/**
 * deals with what queries to run
 *
 * @author olwethu mtwazi
 */
public class MainMenuDAO {

    private Connection con;
    private Statement stmt;
    private PreparedStatement pstmt;

    public MainMenuDAO() {
        try {
            this.con = CarelinkConnection.derbyConnection();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:" + e.getMessage());
        }
    }

    public void CloseConnections() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException exception) {
            // optional: log error
        }
    }

    public String getUserName(int userId) {
        String name = "";
        String sql = "SELECT name FROM users WHERE user_id = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs != null && rs.next()) {
                name = rs.getString("name");
            }
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }
        return name;
    }

    public String getUserRole(int userId) {
        String role = "";
        String sql = "SELECT role FROM users WHERE user_id = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs != null && rs.next()) {
                role = rs.getString("role");
            }
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }
        return role;
    }// end of getUserRole

    public String getNextSession(int userId) {
        String nextSession = "You have no upcoming sessions.";
        String sql = "SELECT date, time FROM sessions WHERE user_id = ? AND status='Confirmed' ORDER BY date ASC, time ASC LIMIT 1";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs != null && rs.next()) {
                nextSession = "Your next session: " + rs.getDate("date") + " at " + rs.getTime("time");
            }
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }
        return nextSession;
    }//end of getNextSession

    public void closeConnections() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
