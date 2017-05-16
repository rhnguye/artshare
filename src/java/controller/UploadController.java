/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UploadDAOImpl;
import dao.UploadDAO;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;
import model.UploadBean;

/**
 *
 * @author admin
 */
@ManagedBean
public class UploadController {

    private List<String> images;

    private List<UploadBean> tempArray;

    public void validateFile(FacesContext ctx,
            UIComponent comp,
            Object value) {
        List<FacesMessage> msgs = new ArrayList<FacesMessage>();
        Part file = (Part) value;
        if (file == null) {
            msgs.add(new FacesMessage("no file"));
        } else {
            if (file.getSize() > 25000000) {
                msgs.add(new FacesMessage("file too big"));
            }
            /*if (!"image/png".equals(file.getContentType())) {
             msgs.add(new FacesMessage("not an image file"));
             }*/
        }
        if (!msgs.isEmpty()) {
            throw new ValidatorException(msgs);
        }
    }
    private UploadBean theModel;
    private String tag;

    /**
     * Creates a new instance of ProfileController
     */
    public UploadController() {
        theModel = new UploadBean();
    }

    public UploadBean getTheModel() {
        return theModel;
    }

    public void setTheModel(UploadBean theModel) {
        this.theModel = theModel;
    }

    public String createImage() throws IOException {
        UploadDAO aProfileDAO = new UploadDAOImpl();
        //I:\\NetBeansApps\\Imagedisplayportion\\ArtShare1.0\\web\\resources\\images
        Path file = Files.createTempFile(Paths.get("I:\\NetBeansApps\\ArtShare1.0\\web\\resources\\images"), "somefilename-", ".jpg");
        try (InputStream input = theModel.getTempFile().getInputStream()) {
            Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);
        }
        theModel.setDisplayName(file.getFileName().toString());

        aProfileDAO.createImage(theModel);
        return "homepage.xhtml";
    }

    @PostConstruct
    public void init() {
        images = new ArrayList<String>();
        UploadDAOImpl imageGet = new UploadDAOImpl();
        tempArray = new ArrayList<UploadBean>();
        tempArray = imageGet.findAllLocations();
        for (int i = 0; i < tempArray.size(); i++) {
            UploadBean temp = tempArray.get(i);
            System.out.println(temp.getDisplayName());
            images.add(temp.getDisplayName());
        }

    }

    public void getWinner() {
        images = new ArrayList<String>();
        UploadDAOImpl imageGet = new UploadDAOImpl();
        tempArray = new ArrayList<UploadBean>();
        tempArray = imageGet.findWinner("Yes");
        for (int i = 0; i < tempArray.size(); i++) {
            UploadBean temp = tempArray.get(i);
            System.out.println(temp.getDisplayName());
            images.add(temp.getDisplayName());
        }

    }

    public void getByTag() {
        images = new ArrayList<String>();
        UploadDAOImpl imageGet = new UploadDAOImpl();
        tempArray = new ArrayList<UploadBean>();
        tempArray = imageGet.findTags(tag);
        for (int i = 0; i < tempArray.size(); i++) {
            UploadBean temp = tempArray.get(i);
            System.out.println(temp.getDisplayName());
            images.add(temp.getDisplayName());
        }
    }

    public void getLoser() {
        images = new ArrayList<String>();
        UploadDAOImpl imageGet = new UploadDAOImpl();
        tempArray = new ArrayList<UploadBean>();
        tempArray = imageGet.findWinner("No");
        for (int i = 0; i < tempArray.size(); i++) {
            UploadBean temp = tempArray.get(i);
            System.out.println(temp.getDisplayName());
            images.add(temp.getDisplayName());
        }

    }

    public List<String> getImages() {
        return images;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
    
    public void makeWinner(String s)
    {
        images = new ArrayList<String>();
        UploadDAOImpl imageGet = new UploadDAOImpl();
        tempArray = new ArrayList<UploadBean>();
        imageGet.setWinner(s);
    }
}