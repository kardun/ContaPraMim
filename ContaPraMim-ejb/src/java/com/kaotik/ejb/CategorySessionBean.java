/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaotik.ejb;

import com.kaotik.entity.Category;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kaos12
 */
//@Stateless(name="CategorySessionBean", mappedName="KaotikEJB#CategorySessionBean")
@Stateless
public class CategorySessionBean implements CategorySessionBeanLocal {
    @PersistenceContext(unitName="ContaPraMim-ejbPU")
    private EntityManager em;

    @Override
    public void create(final Category category) {
        em.persist(category);
    }

    @Override
    public void delete(final Category category) {
        em.remove(em.merge(category));
    }

    @Override
    public Category update(Category category) {
        return em.merge(category);
    }

    @Override
    public List<Category> findAll() {
        return em.createNamedQuery("Category.findAll").getResultList();
    }

    @Override
    public Category find(int id) {
        Query q = em.createNamedQuery("Category.findById");
        q.setParameter("id", id);
        List<Category> result = q.getResultList();
        if(!result.isEmpty()){
            return result.get(0);
        }
        return null;
    }
}