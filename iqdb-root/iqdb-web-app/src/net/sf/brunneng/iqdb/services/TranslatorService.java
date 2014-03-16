/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sf.brunneng.iqdb.services;

import javax.ejb.Stateless;
import net.sf.brunneng.jom.MergingContext;

/**
 *
 * @author goodg_000
 */
@Stateless
public class TranslatorService {
   private MergingContext mergingContext = new MergingContext();

   public MergingContext getMergingContext() {
      return mergingContext;
   }   
   
}
