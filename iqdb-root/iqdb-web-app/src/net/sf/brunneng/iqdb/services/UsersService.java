/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sf.brunneng.iqdb.services;

import net.sf.brunneng.iqdb.model.UserModel;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;
import net.sf.brunneng.iqdb.dao.UserDao;
import net.sf.brunneng.iqdb.dbmodel.User;

/**
 *
 * @author goodg_000
 */
@Stateless
@Transactional
public class UsersService extends AbstractBusinessService {
   
   @EJB
   private UserDao userDao;
   
   public void createNewUser(UserModel userModel) {
      User user = translator.getMergingContext().map(userModel, User.class);      
      userDao.create(user);
   }
   
   public void updateUser(UserModel userModel) {
      User user = translator.getMergingContext().map(userModel, User.class);      
      userDao.update(user);
   }
   
}
