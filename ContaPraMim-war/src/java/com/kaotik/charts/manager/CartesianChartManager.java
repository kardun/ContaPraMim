/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaotik.charts.manager;

import com.kaotik.entity.Category;
import com.kaotik.entity.Expense;
import java.util.List;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author kaos12
 */
public abstract class CartesianChartManager extends BaseChartManager{
    
    public CartesianChartManager(final List<Expense> data, final List<Category> categories){
         super(data, categories);
    }
    
    /*
    @Override
    public PieChartModel getAsPieChart(){
        PieChartModel p = new PieChartModel();
        Map<String,Number> dataMap = p.getData();
        for(Category ct: categories){   
            if(!dataMap.containsKey(ct.getName())){
                for(Expense e: data){
                    if(e.getCategory().equals(ct)){
                        BigDecimal value = e.getValue();
                        BigDecimal c = (BigDecimal)dataMap.get(ct.getName());
                        BigDecimal current = (c== null ? BigDecimal.ZERO :c);
                        dataMap.put(ct.getName(), value.add(current));
                    }
                }
            }
        }
        p.setData(dataMap); 
        return p;
    }
    * */
    
    protected CartesianChartModel getCartesianModel(){
       CartesianChartModel c = new CartesianChartModel();
        for(Category  ct: getCategories()){
            ChartSeries series = new ChartSeries(); 
            series.setLabel(ct.getName());
            for(Expense e: getData()){
                if(e.getCategory().equals(ct)){
                    series.set(e.getCreatedAt(), Double.valueOf(e.getValue().toString()));
                }
            }
            if(!series.getData().isEmpty()){
                c.addSeries(series);
            }
        }
        return c; 
    }
}
