/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sf.brunneng.iqdb.web;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.inject.Named;
import net.sf.brunneng.iqdb.model.UserModel;
import net.sf.brunneng.iqdb.services.UsersService;

/**
 *
 * @author goodg_000
 */

@Named
@RequestScoped
public class UserManagementBean {
   
   @EJB
   private UsersService userService;
   
   @Model
   private UserModel userModel = new UserModel();
   
   public String createNewUser() {
      userService.createNewUser(userModel);
      
      return null;
   }

    public UsersService getUserService() {
        return userService;
    }

    public void setUserService(UsersService userService) {
        this.userService = userService;
    }
}
