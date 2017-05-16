/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.util.ArrayList;
import model.UploadBean;

/**
 *
 * @author admin
 */
public interface UploadDAO {
    
    public int createImage(UploadBean anUpload);
    public ArrayList findAll();
    public ArrayList findTags(String tag);
    
}
