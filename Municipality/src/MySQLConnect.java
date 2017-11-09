/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sareek
 */

import java.sql.*;
import javax.swing.*;
public class MySQLConnect {
    
     Connection conn =null;
    public static Connection ConnectDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/first_db","root","sarik");
           // JOptionPane.showMessageDialog(null,"connected to the database");
            return conn;
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
        return null;
        }
    }
    
}
