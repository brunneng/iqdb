package net.sf.brunneng.iqdb.model;

import javax.enterprise.inject.Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author goodg_000
 */
@Model
public class UserModel extends AbstractModel {
   
   private String name;
   private String email;
   private String password;
   private String role;

   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }

   public String getEmail() {
      return email;
   }
   public void setEmail(String email) {
      this.email = email;
   }

   public String getPassword() {
      return password;
   }
   public void setPassword(String password) {
      this.password = password;
   }

   public String getRole() {
      return role;
   }
   public void setRole(String role) {
      this.role = role;
   }
   
   
}
