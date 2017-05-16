/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
import dao.RetrievalDAO;

/**
 *
 * @author Seth
 */
@ManagedBean (name="verifyBean")
@SessionScoped
public class VerifyBean {
    String result = "";
    String secNum = "";
    String userNum = "";

    public String getResult() {
        if (userNum.equals(""))
            return "";
        else if (userNum.equals(secNum)) {
            result = "<span style=\"color:green\"> Your email was verified! </span>";
            RetrievalDAO dao = new RetrievalDAO();
            dao.verifyEmail();
        }
        else
            result = "<span style=\"color:red\"> Incorrect secret number! </span>";
        
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
    public String getSecNum() {
        return secNum;
    }
    
    public void setSecNum(String secNum) {
        this.secNum = secNum;
    }
    
    public String getUserNum() {
        return userNum;
    }
    
    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }
    
    public String verifyEmail() {
        generateRandomNum();
        sendEmail();
        return "ConfirmVerification.xhtml";
    }
    
    public void generateRandomNum() {
        String rando = "";
        double num = (Math.random()*100);
        int cut = (int) num;
        
        rando += cut;
        secNum = rando;
    }
    
    private void sendEmail() {
        String msg = "Your secret value is: " + secNum;
        String to = "seth45321@gmail.com";
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
            message.setSubject("Email Verification from ArtShare");
            message.setContent(msg, "text/html");

            Transport.send(message);
            System.out.println("Sent message successfully!");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
