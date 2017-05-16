/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.*;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Seth
 */
public class RetrievalDAO {

    public String checkLostPass(String email) {
        String pass = "";
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

            String query = "SELECT password FROM Project353.Users WHERE email = ?";

            PreparedStatement pstmt = DBConn.prepareStatement(query);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                pass = rs.getString("password");
            }
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return pass;
    }

    public void verifyEmail() {
        String email = "'seth45321@gmail.com'";
        Connection DBConn = null;

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        try {
            String myDB = "jdbc:derby://localhost:1527/Project353";
            DBConn = DriverManager.getConnection(myDB, "itkstu", "student");

            String query = "UPDATE Project353.Accounts SET verified=\"y\" WHERE email=\"?\"";

            PreparedStatement pstmt = DBConn.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL update");
            e.printStackTrace();
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void subscribeMail(String email) {
        Connection DBConn = null;

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        try {
            String myDB = "jdbc:derby://localhost:1527/Project353";
            DBConn = DriverManager.getConnection(myDB, "itkstu", "student");

            String query = "UPDATE Project353.Users SET isSubbed = 'y' WHERE email = ?";

            PreparedStatement pstmt = DBConn.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.executeQuery();
            pstmt.close();
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL update");
            e.printStackTrace();
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void unsubscribeMail(String email) {
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

            String query = "UPDATE Project353.Users SET isSubbed = 'n' WHERE email = ?";

            PreparedStatement pstmt = DBConn.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.executeQuery();
            pstmt.close();
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL update");
            e.printStackTrace();
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private void sendMassEmail(String email, String subject, String body) {
        String msg = body;
        String to = email;
        String from = "it353artshare@gmail.com";
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("it353artshare@gmail.com", "it353Pass");
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            message.setSubject(subject);
            message.setContent(msg, "text/html");

            Transport.send(message);
            System.out.println("Sent message successfully!");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public void sendMailingList(String subject, String body) {
        String email;

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

            String query = "SELECT * FROM Project353.Users WHERE mailingList='y'";

            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                email = rs.getString("email");
                sendMassEmail(email, subject, body);
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

    }
}
