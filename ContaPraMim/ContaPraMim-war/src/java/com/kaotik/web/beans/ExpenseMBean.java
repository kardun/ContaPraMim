/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaotik.web.beans;

import com.kaotik.charts.manager.AnnualCartesianChartManager;
import com.kaotik.charts.manager.CartesianChartManager;
import com.kaotik.ejb.CategorySessionBeanLocal;
import com.kaotik.ejb.ExpenseSessionBeanLocal;
import com.kaotik.ejb.UserSessionBeanLocal;
import com.kaotik.entity.Category;
import com.kaotik.entity.Expense;
import com.kaotik.entity.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author resilva
 */
@ManagedBean(name="expenseMBean")
@SessionScoped
public class ExpenseMBean implements Serializable {
    @EJB
    private ExpenseSessionBeanLocal expenseService;
    @EJB
    private CategorySessionBeanLocal categoryService;
    @EJB
    private UserSessionBeanLocal userService;
    
    private Expense expense = new Expense();
    private Integer selectedCategory;
    private CartesianChartManager chartManager;
    private User user;
    
    /**
     * Creates a new instance of ExpenseMBean
     */
    public ExpenseMBean() {
    }
     
    @PostConstruct
    public void initialize(){
       chartManager = new CartesianChartManager();
       user = userService.find(1);
       loadChart();
    }
    
    public Expense getExpense(){
        return expense;
    }
    
    public void setSelectedCategory(Integer newCategory){
        selectedCategory = newCategory;
    }
    
    public Integer getSelectedCategory(){
        return selectedCategory;
    }
    
    public void setExpense(Expense expense){
        this.expense = expense;
    }
       
    public List<Category> getCategories(){
        return categoryService.findAll();
    }
       
    public PieChartModel getPieChartModel(){
        return chartManager.getAsPieChart();
    }
    
    public List<SelectItem> getCategoriesForSelect(){
        List<SelectItem> list = new ArrayList<SelectItem>();
        for(Category c: getCategories()){
            SelectItem si = new SelectItem(c.getId(), c.getName());
            list.add(si);
        }
        return list;
    }
    
    public CartesianChartModel getAnnualCartesianModel(){
        final List<Expense> expenses = expenseService.findAllWithinCurrentYear(user)
        AnnualCartesianChartManager manager = new AnnualCartesianChartManager(, null)
    }
     
    public CartesianChartModel getMonthlyCartesianModel(){
        return chartManager.getMonthlyCartesianModel();
    }
    
    public void newExpense(ActionEvent event){
        if(selectedCategory == null || selectedCategory == 0){
             FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_FATAL, "O campo Categoria é obrigatorio",null));
        }
        expense.setUser(user);
        expense.setCategory(categoryService.find(selectedCategory));
        expenseService.create(expense);
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Conta Criada com Sucesso!",null));
      setExpense(new Expense());
      loadChart();
    }
    
    private void loadChart(){
        List<Expense> allExpenses = expenseService.findByUser(user);
        chartManager.setData(allExpenses);
        chartManager.setCategories(getCategories());
    }
}
