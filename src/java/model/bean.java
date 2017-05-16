/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.RetrievalDAO;
import java.util.Properties;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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

@ManagedBean (name = "bean")
@SessionScoped
public class bean {
    String email;
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String checkEmail() {
        RetrievalDAO dao = new RetrievalDAO();
        String pass;
        
        pass = dao.checkLostPass(email);
        
        if (!pass.isEmpty()) // otherwise !pass.equals("") should work if this doesn't
            sendEmail(pass);
        
        return "PasswordSent.xhtml";
    }
    
    private void sendEmail(String pass) {
        String msg = "Your lost password is: " + pass;
        String to = email;
        String from = "sgreike@ilstu.edu";
        String host = "outlook.office365.com";
            
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.port", "587");
            
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("sgreike@ilstu.edu", "Semiramis1");
                // these are my actual ISU credentials, please don't steal my email, lol
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
                
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            message.setSubject("Lost Password Recovery");
            message.setContent(msg, "text/html");

            Transport.send(message);
            System.out.println("Sent message successfully!");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
