/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UploadDAO;
import dao.UploadDAOImpl;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.UploadBean;

/**
 *
 * This is a class that simply returns a list of profile bean objects to the
 * caller for display purposes. The profiles are retrieved from the DB in the
 * method getProfileBeans().
 *
 * @author Lim
 */
@ManagedBean
@RequestScoped
public class TableControllerWithRealDBProfile {

    private List<UploadBean> profiles;

    /**
     * Creates a new instance of TableBeanWithDummyProfile
     */
    public TableControllerWithRealDBProfile() {
    }

    public List<UploadBean> getProfileBeans() {
        // get the profiles by interacting with ProfileDAO.
        UploadDAO anUploadDAO = new UploadDAOImpl();    // Creating a new object each time.
        this.profiles = anUploadDAO.findAll();            // Doing anything with the object after this?
//        this.profiles = aProfileDAO.findByName("Billy Lim");            // Doing anything with the object after this?
        return profiles;
    }

    public void setProfileBeans(List<UploadBean> profiles) {
        this.profiles = profiles;
    }
}
