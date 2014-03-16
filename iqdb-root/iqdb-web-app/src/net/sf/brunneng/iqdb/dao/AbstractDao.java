/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sf.brunneng.iqdb.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.sf.brunneng.iqdb.dbmodel.IEntity;

/**
 *
 * @author goodg_000
 */
public abstract class AbstractDao<T extends IEntity> {
   
   @PersistenceContext(unitName = "iqdb-PU")
   private EntityManager em;
   
   private final Class<T> entityClass;

   public AbstractDao(Class<T> entityClass) {
      this.entityClass = entityClass;
   }

   protected EntityManager getEntityManager() {
      return em;
   }

   public void create(T entity) {
      getEntityManager().persist(entity);
   }

   public void update(T entity) {
      getEntityManager().merge(entity);
   }

   public void delete(T entity) {
      getEntityManager().remove(getEntityManager().merge(entity));      
   }

   public T find(Long id) {
      return getEntityManager().find(entityClass, id);
   }

   public List<T> findAll() {
      javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
      cq.select(cq.from(entityClass));
      return getEntityManager().createQuery(cq).getResultList();
   }   

   public int count() {
      javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
      javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
      cq.select(getEntityManager().getCriteriaBuilder().count(rt));
      javax.persistence.Query q = getEntityManager().createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
   }
   
}
