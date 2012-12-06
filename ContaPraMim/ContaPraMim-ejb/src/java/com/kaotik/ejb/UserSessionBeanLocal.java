/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaotik.ejb;

import com.kaotik.entity.User;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kaos12
 */
@Local
public interface UserSessionBeanLocal {
    void create(User user);
    void delete(final User user);
    User update(User user);
    List<User> findAll();
    User find(int id); 
}
