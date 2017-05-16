/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import model.GalleryBean;

/**
 *
 * @author admin
 */
public class GalleryDAOImpl implements GalleryDAO {

    @Override
    public int createProfile(GalleryBean aProfile) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        int rowCount = 0;
        try {
           // String myDB = "jdbc:derby://localhost:1527/PROJECT353";// connection string
           String myDB = "jdbc:derby://gfish2.it.ilstu.edu:1527/rhnguye_Fall2016_Project353;create=true";

            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            String temp = "";

            String insertString;
            Statement stmt = DBConn.createStatement();
            insertString = "INSERT INTO PROJECT353.SHOPPINGCART VALUES ('"
                    + aProfile.getPicture1()
                    + "','" + aProfile.getPicture2()
                    + "','" + aProfile.getPicture3()
                    + "','" + aProfile.getPicture4()
                    + "','" + aProfile.getPicture5()
                    + "','" + aProfile.getTotalPrice()
                    + "','" + aProfile.getRoyalties()
                    + "')";

            rowCount = stmt.executeUpdate(insertString);
            System.out.println("insert string =" + insertString);
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return rowCount;
    }
    
    @Override
    public GalleryBean[] findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public GalleryBean findByName(String aName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
