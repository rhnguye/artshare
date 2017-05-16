/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DAO;
import dao.DAOImpl;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import model.SignUpBean;

@ManagedBean(name = "signUpController")
@SessionScoped

public class SignUpController {
    private SignUpBean model;
    private String message;
    public SignUpController(){
        model=new SignUpBean();
        message="";
    }
    /**
     * @return the model
     */
    public SignUpBean getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(SignUpBean model) {
        this.model = model;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        if(message.equals(""))
            message="Username already exists<br>";
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
   
    public String createProfile(){
        boolean error=false;
        message="";
        DAO sud=new DAOImpl();
        int rowcount=0;
        if(!error)
           rowcount=sud.createProfile(model);
        model=new SignUpBean();
        if(rowcount==1){
            return "homepage.xhtml?faces-redirect=true";
        }
        return "loginerror.xhtml";
    }
  
}
