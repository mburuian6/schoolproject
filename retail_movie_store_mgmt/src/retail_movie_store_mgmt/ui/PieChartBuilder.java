/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javafx.scene.chart.PieChart;

/**
 *
 * @author Ian Mburu
 */
public class PieChartBuilder {
    
    public PieChart createPieChartStringDouble(HashMap map){
        PieChart pieChart = new PieChart();
        
        Set<Map.Entry<String, Double>> set = map.entrySet();
        for(Map.Entry<String, Double> me : set) {
           PieChart.Data slice = new PieChart.Data(me.getKey(),me.getValue());
           pieChart.getData().add(slice);
        }
        return pieChart;
    }
    
    public void createData(){
        
    }
}
