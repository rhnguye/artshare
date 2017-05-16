package dao;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.UploadBean;

public class UploadDAOImpl implements UploadDAO {

    @Override
    public int createImage(UploadBean anUpload) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        int rowCount = 0;
        try {
            String myDB = "jdbc:derby://gfish2.it.ilstu.edu:1527/rhnguye_Fall2016_Project353;create=true";
//           String myDB = "jdbc:derby://localhost:1527/Project353";// connection string
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            java.io.InputStream fin = null;
            try {
                fin = anUpload.getTempFile().getInputStream();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(UploadDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(UploadDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            PreparedStatement ps = DBConn.prepareStatement(
                    "INSERT INTO Project353.ART VALUES ('No', ?, ?, 'Test', ?, ?, ?, ?)");

            ps.setString(1, anUpload.getName());
            ps.setInt(2, anUpload.getGrade());
            ps.setBinaryStream(3, fin);
            ps.setString(4, anUpload.getTag());
            ps.setInt(5, getMax() + 1);
            ps.setString(6, anUpload.getDisplayName());
            if (ps.execute() == true) {
                rowCount = 1;
            } else {
                rowCount = 0;
            }
            DBConn.commit();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return rowCount;
    }

    public ArrayList findAll() {

        String query = "SELECT * FROM Project353.ART";
        ArrayList anArtCollection = selectArtFromDB(query);
        return anArtCollection;

    }

    @Override
    public ArrayList findTags(String tag) {

        String query = "SELECT * FROM Project353.ART";
        ArrayList anArtCollection = selectArtFromDB(query);
        ArrayList aFinalArtCollection;
        aFinalArtCollection = new ArrayList();
        for (int i = 0; i < anArtCollection.size(); i++) {
            UploadBean temp = (UploadBean) anArtCollection.get(i);
            if (temp.getTag().toLowerCase().contains(tag.toLowerCase()) == true) {
                aFinalArtCollection.add(anArtCollection.get(i));
            }
        }
        return aFinalArtCollection;
    }

    public int getMax() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        
String myDB = "jdbc:derby://gfish2.it.ilstu.edu:1527/rhnguye_Fall2016_Project353;create=true";
//String myDB = "jdbc:derby://localhost:1527/Project353";
        int lastID = 0;

        try {
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS lastID FROM PROJECT353.ART");
            if (rs.next()) {
                lastID = rs.getInt("lastID");
            }
            rs.close();
            stmt.close();
            DBConn.close();
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }

        return lastID;
    }

    private ArrayList selectArtFromDB(String query) {
        ArrayList anArtBeanCollection = new ArrayList();
        Connection DBConn = null;
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
String myDB = "jdbc:derby://gfish2.it.ilstu.edu:1527/rhnguye_Fall2016_Project353;create=true";            
//String myDB = "jdbc:derby://localhost:1527/Project353";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String name, tag, displayName;
            int imageID;
            UploadBean anUploadBean;
            java.io.InputStream image;
            while (rs.next()) {
                name = rs.getString("USERNAME");
                imageID = rs.getInt("ID");
                image = rs.getBinaryStream("PIC");
                tag = rs.getString("Tag");
                displayName = rs.getString("DISPLAYNAME");

                anUploadBean = new UploadBean(name, imageID, image, tag, displayName);
                anArtBeanCollection.add(anUploadBean);
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
        return anArtBeanCollection;
    }

    public ArrayList findWinner(String winner) {

        String query = "SELECT * FROM Project353.ART WHERE WINNER = '" + winner + "'";
        ArrayList anArtCollection = selectArtFromDB(query);
        return anArtCollection;
    }
    
    public void setWinner(String disp) {

        String query = "UPDATE Project353.ART SET WINNER = 'Yes' WHERE DISPLAYNAME = '" + disp + "'";
        selectArtFromDB(query);
        //return anArtCollection;
    }

    public ArrayList findAllLocations() {

        String query = "SELECT * FROM Project353.ART";
        ArrayList anArtCollection = selectArtFromDB(query);
        return anArtCollection;

    }
}
