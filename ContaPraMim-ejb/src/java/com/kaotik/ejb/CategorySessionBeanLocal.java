/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaotik.ejb;

import com.kaotik.entity.Category;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kaos12
 */
@Local
public interface CategorySessionBeanLocal {
    void create(Category category);
    void delete(final Category category);
    Category update(Category category);
    List<Category> findAll();
    Category find(int id);
}
