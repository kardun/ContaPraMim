/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaotik.charts.manager;

import com.kaotik.entity.Category;
import com.kaotik.entity.Expense;
import java.util.List;
import org.primefaces.model.chart.ChartModel;

/**
 *
 * @author resilva
 */
public class AnnualCartesianChartManager extends CartesianChartManager {
    
    public AnnualCartesianChartManager(final List<Expense> data, final List<Category> categories){
         super(data, categories);
    }
    
    @Override
    public ChartModel getModel() {
       return getCartesianModel();
    }
}
