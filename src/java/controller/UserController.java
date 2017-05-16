/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.*;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import model.LoginBean;

@ManagedBean(name = "userController")
@SessionScoped
public class UserController {

    private boolean loggedin = false;
    private String email, fname, lname, password, uid;
    private LoginBean lb;
    private int count; //tries to log in

    /**
     * Creates a new instance of UserController
     */
    public UserController() {
        loggedin = false;
        lb = new LoginBean();
        DAOImpl dao = new DAOImpl();
        int count = 0;
    }

    /**
     * @return the loggedin
     */
    public boolean isLoggedin() {
        return loggedin;
    }

    /**
     * @param loggedin the loggedin to set
     */
    public void setLoggedin(boolean loggedin) {
        this.loggedin = loggedin;
    }

    /**
     * @return the lb
     */
    public LoginBean getLb() {
        return lb;
    }

    /**
     * @param lb the lb to set
     */
    public void setLb(LoginBean lb) {
        this.lb = lb;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * @param fname the fname to set
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * @return the lname
     */
    public String getLname() {
        return lname;
    }

    /**
     * @param lname the lname to set
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid the uid to set
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    public String login() {
//        DAOImpl dao=new DAOImpl();   
//        LoginBean temp=dao.login(lb.getUid(), lb.getPassword());
//        //email, fname, lname, password, uid;
//        if(temp!=null){
//            setEmail(temp.getEmail());
//            setFname(temp.getFname());
//            setLname(temp.getLname());
//            setUid(lb.getUid());
//            setPassword(lb.getPassword());
//            setLoggedin(true);
//            return "homepage.xhtml?faces-redirect=true";
//        }
//        return "error.xhtml";
        boolean valid = false;
        DAO aLoginDAO = new DAOImpl();

        valid = aLoginDAO.validateLogin(getUid(), getPassword());

        if (valid) {
            setLoggedin(true);
            return "homepage.xhtml?faces-redirect=true";
        } else {
            count++;
            if (count < 3) {
                return "LoginBad.xhtml";
            } else {
                return "LoginBadAttempts.xhtml";
            }
        }
    }

    public String logout() {
        setUid("");
        setPassword("");
        setEmail("");
        setFname("");
        setLname("");
        setLoggedin(false);

        return "homepage.xhtml?faces-redirect=true";
    }

    public String update() {
        boolean error = false;
        DAO sud = new DAOImpl();
        RetrievalDAO d = new RetrievalDAO();
        int rowcount = 0;
        if (!error) {
            rowcount = sud.editProfile(lb);
            if(lb.isIsSubbed())
            {
                d.subscribeMail(email);
            }
            else
                d.unsubscribeMail(email);
        }
        if (rowcount == 1) {
            return "homepage.xhtml?faces-redirect=true";
        }
        return "loginerror.xhtml";
    }

    public String checklogin(ComponentSystemEvent event) {
        String navi = null;

        if (!isLoggedin()) {

            FacesContext fc = FacesContext.getCurrentInstance();
            ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
            nav.performNavigation("mustLogin.xhtml?faces-redirect=true");
        }

        return navi;
    }
}
