package controller;

import dao.GalleryDAOImpl;
import java.util.Properties;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.GalleryBean;
import org.primefaces.event.RateEvent;
import dao.GalleryDAO;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
@ManagedBean
@SessionScoped
public class GalleryController {

    // This corresponds to the response to be sent back to the client
    private String response;
    private GalleryBean theModel;
    private int count = 0;
    private int royalties = 0;
    public ArrayList<String> cart = new ArrayList<String>();
    public String pic1;

    public GalleryController() {
        theModel = new GalleryBean();
    }

    public GalleryBean getTheModel() {
        return theModel;
    }

    public void setTheModel(GalleryBean theModel) {
        this.theModel = theModel;
    }


    public void addCart() {
        cart.add(theModel.getPicture1());
        System.out.println(theModel.getPicture1());
        System.out.println(cart.get(0));
    }
    public void pic2() {
        theModel.setPicture2("06312");
        count++;
    }

    public void pic3() {
        theModel.setPicture3("06313");
        count++;
    }

    public void pic4() {
        theModel.setPicture4("06314");
        count++;
    }

    public void pic5() {
        theModel.setPicture5("06315");
        count++;
    }

    public void fix() {
        theModel = null;
    }

    public String getResponse() {
        String resultStr = "";
        resultStr += "Your purchase(s): ";
        for(int i=0; i<cart.size(); i++) {
            resultStr += "\n" + cart.get(i);
        }
        resultStr += "<br/>The total was: $" + cart.size()*20 + "</br>";
        resultStr += "Your receipt will be emailed to you.";

        response = resultStr;
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void sendEmailBuyer() {
        String msg = response + "<br/>";
        msg += "Your painting will be sent to: " + theModel.getStreet()
                + "\n" + theModel.getCity() + ", " + theModel.getState() + "\n "
                + theModel.getZipCode() + "";

        String emailBuyer = theModel.getEmail();
        String to = emailBuyer;
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
            message.setSubject("Email Receipt");
            message.setContent(msg, "text/html");

            Transport.send(message);
            System.out.println("Sent message successfully!");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
public void sendEmailRoyalties() {
        String msg = "Royalties Need to be paid for:";
        for(int i=0; i<cart.size(); i++) {
             msg += "Image: " + cart.get(i) +"For: " + 20*.3 + "\n";
        }
        String emailSurvey = "it353artshare@gmail.com";
        String to = emailSurvey;
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
            message.setSubject("Email Receipt");
            message.setContent(msg, "text/html");

            Transport.send(message);
            System.out.println("Sent message successfully!");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
}
    
    
    public void sendEmailSurvey() {
        String msg = "Anonymous feedback from a user: " + "<br/>";
        msg += "Were you able to find what you were looking for? (yes no)" + theModel.getQuestion1();
        msg += "How was your shopping experience? (star rating)" + theModel.getRating();
        msg += "Would you recommend this site to a friend? (yes no)" + theModel.getQuestion2();
        msg += "Additional comments" + theModel.getTextArea();

        String emailSurvey = "it353artshare@gmail.com";
        String to = emailSurvey;
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
            message.setSubject("Email Receipt");
            message.setContent(msg, "text/html");

            Transport.send(message);
            System.out.println("Sent message successfully!");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public String createProfile() {
        //theModel.setTotalPrice(Integer.toString(count * 20));
        theModel.setRoyalties(Double.toString((cart.size()*20) * .3));

            return "confirmation.xhtml?faces-redirect=true"; 

    }

    public String createSurveyProfile() {
        GalleryDAO aProfileDAO = new GalleryDAOImpl();
        int status = aProfileDAO.createProfile(theModel);
        if (status == 1) {
            return "survey2.xhtml?faces-redirect=true"; // navigate to "response.xhtml"
        } else {
            return "error.xhtml";
        }
    }

    public String getConfirmation() {
        String resultStr = "";
        resultStr += "Your purchase(s): ";
        for(int i=0; i<cart.size(); i++) {
            resultStr += "\n" + cart.get(i);
        }

        resultStr += "<br/>Total: $" + cart.size()*20 + "</br>";

        response = resultStr;
        return response;
    }
    
    public void clear() {
        if (theModel.getPicture1() != null) {
            theModel.setPicture1(null);
        }
        if (theModel.getPicture2() != null) {
            theModel.setPicture2(null);
        }
        if (theModel.getPicture3() != null) {
            theModel.setPicture3(null);
        }
        if (theModel.getPicture4() != null) {
            theModel.setPicture4(null);
        }
        if (theModel.getPicture5() != null) {
            theModel.setPicture5(null);
        }

         count=0;
    }

    private Integer rating3;

    public void onrate(RateEvent rateEvent) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rate Event", "You rated:" + ((Integer) rateEvent.getRating()).intValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void oncancel() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancel Event", "Rate Reset");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public Integer getRating3() {
        return rating3;
    }

    public void setRating3(Integer rating3) {
        this.rating3 = rating3;
    }

    public String getRatingResponse() {
        String resultStr = "";
        //resultStr += "Hello, You graded " + theModel.getPictureID() + "as  " + theModel.getRating() + "stars. " +"<br/>";
        resultStr += "Hello, You graded picture" + " as  " + theModel.getRating() + " stars. " + "<br/>";

        response = resultStr;
        return response;
    }

    public void setRatingResponse(String response) {
        this.response = response;
    }

    public String createRating() {
        GalleryDAO aProfileDAO = new GalleryDAOImpl();    // Creating a new object each time.
        int status = aProfileDAO.createProfile(theModel); // Doing anything with the object after this?
        if (status == 1) {
            return "response.xhtml?faces-redirect=true"; // navigate to "response.xhtml"
        } else {
            return "response.xhtml";
        }
    }

}
