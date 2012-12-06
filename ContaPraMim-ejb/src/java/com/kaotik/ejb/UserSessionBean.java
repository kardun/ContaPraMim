/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaotik.ejb;

import com.kaotik.entity.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kaos12
 */
//@Stateless(name="UserSessionBean", mappedName="KaotikEJB#UserSessionBean")
@Stateless
public class UserSessionBean implements UserSessionBeanLocal {
    @PersistenceContext(unitName="ContaPraMim-ejbPU")
    private EntityManager em;

    @Override
    public void create(User user) {
        em.persist(user);
    }

    @Override
    public void delete(User user) {
        em.remove(em.merge(user));
    }

    @Override
    public User update(User user) {
       return em.merge(user);
    }

    @Override
    public List<User> findAll() {
        return em.createNamedQuery("User.findAll").getResultList();
    }

    @Override
    public User find(int id) {
        Query q = em.createNamedQuery("User.findById");
        q.setParameter("id", id);
        List<User> result = q.getResultList();
        if(!result.isEmpty()){
            return result.get(0);
        }
        return null;
    }
}
