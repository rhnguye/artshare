/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.LoginBean;
import model.SignUpBean;

/**
 *
 * @author Ryan
 */
public interface DAO {

    /**
     *
     * @param aProfile
     * @return
     */
    public int createProfile(SignUpBean aProfile);

    public int editProfile(LoginBean l);

    public LoginBean login(String username, String password);

    public boolean validateLogin(String user, String pass);
}
