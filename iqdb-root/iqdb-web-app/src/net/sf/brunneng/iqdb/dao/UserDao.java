/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sf.brunneng.iqdb.dao;

import javax.ejb.Stateless;
import net.sf.brunneng.iqdb.dbmodel.User;

/**
 *
 * @author goodg_000
 */
@Stateless
public class UserDao extends AbstractDao<User> {

   public UserDao() {
      super(User.class);
   }
   
}
