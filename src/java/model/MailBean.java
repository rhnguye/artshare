/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import dao.*;

/**
 *
 * @author Seth
 */
@ManagedBean (name = "mailBean")
@SessionScoped
public class MailBean {
    boolean sub;
    String email;
    String subject;
    String body;

    public boolean getSub() {
        return sub;
    }

    public void setSub(boolean sub) {
        this.sub = sub;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    
//    public String assign() {
//        RetrievalDAO dao = new RetrievalDAO();
//        if (sub)
//            dao.subscribeMail(email);
//        else
//            dao.unsubscribeMail(email);
//        
//        return "done.xhtml";
//    }
    
    public String mailingList() {
        RetrievalDAO dao = new RetrievalDAO();
        dao.sendMailingList(subject, body);
        
        return "dashboard.xhtml?faces-redirect=true";
    }
    
}
