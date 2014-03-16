/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sf.brunneng.iqdb.dbmodel;

import java.io.Serializable;

/**
 *
 * @author goodg_000
 */
public interface IEntity extends Serializable {
   
   Long getId();
   void setId(Long id);
}
