/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaotik.ejb;

import com.kaotik.entity.Category;
import com.kaotik.entity.Expense;
import com.kaotik.entity.User;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author kaos12
 */
//@Stateless(name="ExpenseSessionBean", mappedName="KaotikEJB#ExpenseSessionBean")
@Stateless
public class ExpenseSessionBean implements ExpenseSessionBeanLocal {
    @PersistenceContext(unitName="ContaPraMim-ejbPU")
    private EntityManager em;
    
    @Override
    public void create(final Expense expense) {
        em.persist(expense);
    }

    @Override
    public void delete(final Expense expense) {
        em.remove(em.merge(expense));
    }
   
    @Override
    public List<Expense> findAll() {
        return em.createNamedQuery("Expense.findAll").getResultList();
    }

    @Override
    public List<Expense> findByUser(final User u) {
        return em.createNamedQuery("Expense.findAllByUser")
                 .setParameter("userId", u.getId())
                 .getResultList();
    }

    @Override
    public List<Expense> findByCategory(final Category c) {
        return em.createNamedQuery("Expense.findAllByCategory")
                 .setParameter("categoryId", c.getId())
                 .getResultList();
    }

    @Override
    public Expense find(int id) {
        List<Expense> result = em.createNamedQuery("Expense.findById")
                .setParameter("id", id)
                .getResultList();
        return (result.isEmpty()) ? null : result.get(0);
    }

    @Override
    public List<Expense> findAllByDateRange(final Date lowerDate, final Date greaterDate) {
        return em.createNamedQuery("Expense.findAllByDateRange")
                 .setParameter("lowerDate", lowerDate)
                 .setParameter("greaterDate", greaterDate)
                 .getResultList();
    }

    @Override
    public List<Expense> findAllByUserAndDateRange(final User u, final Date lowerDate, final Date greaterDate) {
        return em.createNamedQuery("Expense.findAllByUserAndDateRange")
                 .setParameter("userId", u.getId())
                 .setParameter("lowerDate", lowerDate)
                 .setParameter("greaterDate", greaterDate)
                 .getResultList();
    }

    @Override
    public List<Expense> findAllWithinYear(User u, int year) {
        Calendar firstDay = Calendar.getInstance();
        Calendar lastDay  = Calendar.getInstance();
        firstDay.set(year, 1, 1, 0, 0, 0);
        lastDay.set(year, 12, 31, 23, 59, 59);
        return findAllByUserAndDateRange(u, firstDay.getTime(), lastDay.getTime());
    }

    @Override
    public List<Expense> findAllWithinMonth(User u, int month, int year) {
        Calendar firstDay = Calendar.getInstance();
        Calendar lastDay  = Calendar.getInstance();
        firstDay.set(year, month, 1, 0, 0, 0);
        int maxDays = firstDay.getActualMaximum(Calendar.DAY_OF_MONTH);
        lastDay.set(year, month, maxDays, 23, 59, 59);
        return findAllByUserAndDateRange(u, firstDay.getTime(), lastDay.getTime());
    }
}