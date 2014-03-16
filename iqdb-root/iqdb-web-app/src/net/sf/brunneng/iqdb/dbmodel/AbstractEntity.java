/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sf.brunneng.iqdb.dbmodel;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author goodg_000
 */
@MappedSuperclass
public class AbstractEntity implements IEntity {
   
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)      
   private Long id;

   public Long getId() {
      return id;
   }
   public void setId(Long id) {
      this.id = id;
   }
   
   @Override
   public int hashCode() {
      int hash = 0;
      hash += (id != null ? id.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {             
      if (!(object instanceof AbstractEntity)) {
         return false;
      }
      
      if (!getClass().equals(object.getClass())) {
         return false;
      }
      
      AbstractEntity other = (AbstractEntity) object;
      if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return getClass().toString() + "[ id=" + id + " ]";
   }
}
