/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.GalleryBean;

/**
 *
 * @author admin
 */
public interface GalleryDAO {
    
    public int createProfile(GalleryBean aProfile);
    public GalleryBean[] findAll();
    public GalleryBean findByName(String aName);   
    
}
