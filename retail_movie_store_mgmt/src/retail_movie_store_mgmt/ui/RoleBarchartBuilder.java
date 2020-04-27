/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.ui;

import BeansPackage.BeansClass;
import java.util.HashMap;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import retail_movie_store_mgmt.HomeLogic;

/**
 *
 * @author Ian Mburu
 */
public class RoleBarchartBuilder {
    
    public HashMap getData(){
        HomeLogic instance = BeansClass.homeLogic();
        return instance.getUserRoleMap();
    }
        
    public BarChart<String, Number> produceChart(){
        final CategoryAxis itemAxis = new CategoryAxis();
        final NumberAxis numberAxis = new NumberAxis();
        final BarChart<String,Number> chart = 
                new BarChart<String,Number>(itemAxis,numberAxis);
        
        chart.setTitle("Users");
        itemAxis.setLabel("Role");       
        numberAxis.setLabel("Number");
        
        HashMap<String, Double> data = getData();
        
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Employees");
        series1.getData().add(new XYChart.Data("Employees", data.get("employee")));
        chart.getData().add(series1);
        
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Managers");
        series2.getData().add(new XYChart.Data("Managers", data.get("manager")));
        chart.getData().add(series2);
        
        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Admin");
        series3.getData().add(new XYChart.Data("Admin", data.get("admin")));
        chart.getData().add(series3);
//        chart.getData().addAll(elements);
        
        return chart;
    }
}
