/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import dao.*;

/**
 *
 * @author Ryan
 */

@ManagedBean
@RequestScoped
public class LoginBean {
    private String email, fname, lname, password, uid;
    //private int status; //0 free user, 1 paid user, 2 administrator
    private boolean isAdmin; //administrator 
    private boolean isPlus; //paid user
    private boolean isSubbed; //mailing list
  
    public LoginBean() {
    }

    public String getEmail() {
        return email;
    }

 
    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

 
    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

  
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isIsPlus() {
        return isPlus;
    }

    public void setIsPlus(boolean isPlus) {
        this.isPlus = isPlus;
    }

    public boolean isIsSubbed() {
        return isSubbed;
    }

    public void setIsSubbed(boolean isSubbed) {
        this.isSubbed = isSubbed;
    }
    
    
}
