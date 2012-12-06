/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaotik.charts.manager;

import com.google.common.collect.ImmutableList;
import com.kaotik.entity.Category;
import com.kaotik.entity.Expense;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author resilva
 */
public abstract class BaseChartManager implements ChartManager {
    private List<Expense> data;
    private List<Category> categories;
    
    public BaseChartManager(final List<Expense> data, final List<Category> categories){
        this.data = ImmutableList.copyOf(data);
        this.categories = ImmutableList.copyOf(categories);
    }
       
    public List<Expense> getData(){
        return data;
    }
    
    public List<Category> getCategories(){
        return categories;
    }   
}
