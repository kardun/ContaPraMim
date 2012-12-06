/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaotik.ejb;

import com.kaotik.entity.Category;
import com.kaotik.entity.Expense;
import com.kaotik.entity.User;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kaos12
 */
@Local
public interface ExpenseSessionBeanLocal { 
    void create(final Expense expense);
    void delete(final Expense expense);
    List<Expense> findAll();
    List<Expense> findByUser(final User u);
    List<Expense> findByCategory(final Category c);
    List<Expense> findAllByDateRange(final Date lowerDate, final Date greaterDate);
    List<Expense> findAllByUserAndDateRange(final User u, final Date lowerDate, final Date greaterDate);
    List<Expense> findAllWithinYear(final User u, int year);
    List<Expense> findAllWithinMonth(final User u, int month, int year);
    Expense find(int id);
}
