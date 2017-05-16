/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.servlet.http.Part;

/**
 *
 * @author admin
 */
public class UploadBean {

    // These correspond to the form elements
    private String file;
    private String tag;
    private Part tempFile;
    private int grade;
    private String name;
    private java.io.InputStream image;
    private int imageID;
    private String displayName;

    public UploadBean(String file, int imageID, java.io.InputStream image, String tag, String displayName) {
        this.file = file;
        this.imageID = imageID;
        this.image = image;
        this.tag = tag;
        this.displayName = displayName;
    }

    public UploadBean() {
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public java.io.InputStream getImage() {
        return image;
    }

    public void setImage(java.io.InputStream image) {
        this.image = image;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Part getTempFile() {
        return tempFile;
    }

    public void setTempFile(Part tempFile) {
        this.tempFile = tempFile;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
