/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.LoginBean;
import model.SignUpBean;

/**
 *
 * @author Ryan
 */

public class DAOImpl implements DAO {

    @Override
    public int createProfile(SignUpBean sub) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");

        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        int rowCount = 0;
        try {
            //String myDB = "jdbc:derby://localhost:1527/Project353";
            String myDB = "jdbc:derby://gfish2.it.ilstu.edu:1527/rhnguye_Fall2016_Project353;create=true";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            
            String insertString;
            Statement stmt = DBConn.createStatement();
            insertString = "INSERT INTO Project353.Users SET VALUES ('"
                    + sub.getUid()
                    + "','" + sub.getFname()
                    + "','" + sub.getLname()
                    + "','" + sub.getEmail()
                    + "','" + sub.getPass()
                    + "','" + sub.isIsPlus() 
                    + "','" + false+ "')";

            rowCount = stmt.executeUpdate(insertString);
            System.out.println("insert string =" + insertString);
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
        
        return rowCount;
    }
    
    public int editProfile(LoginBean sub) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");

        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        int rowCount = 0;
        try {
            //String myDB = "jdbc:derby://localhost:1527/Project353";
            String myDB = "jdbc:derby://gfish2.it.ilstu.edu:1527/rhnguye_Fall2016_Project353;create=true";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            
            String insertString;
            Statement stmt = DBConn.createStatement();
            insertString = "UPDATE Project353.Users VALUES ('"
                    + sub.getUid()
                    + "','" + sub.getFname()
                    + "','" + sub.getLname()
                    + "','" + sub.getEmail()
                    + "','" + sub.getPassword()
                    + "','" + sub.isIsPlus()
                    + "','" + false
                    + "','" + sub.isIsSubbed()+ "')";

            rowCount = stmt.executeUpdate(insertString);
            System.out.println("insert string =" + insertString);
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
        
        return rowCount;
    }

    
    @Override
    public boolean validateLogin(String user, String pass) {
        boolean valid = false;
        Connection DBConn = null;
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        
        try {
            //String myDB = "jdbc:derby://localhost:1527/Project353";
            String myDB = "jdbc:derby://gfish2.it.ilstu.edu:1527/rhnguye_Fall2016_Project353;create=true";
            DBConn = DriverManager.getConnection(myDB, "itkstu", "student");

            String query = "SELECT * FROM Project353.Users";
            
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String dbUser, dbPass;
            while (rs.next()) {
                dbUser = rs.getString("uid");
                dbPass = rs.getString("password");
                if (dbUser.equals(user) && dbPass.equals(pass))
                    valid = true;
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return valid;
    }

    @Override
    public LoginBean login(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
