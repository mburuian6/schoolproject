/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.report;

import ar.com.fdvs.dj.domain.chart.DJChart;
import ar.com.fdvs.dj.domain.chart.DJChartOptions;
import ar.com.fdvs.dj.domain.chart.builder.DJPieChartBuilder;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import ar.com.fdvs.dj.domain.entities.columns.PropertyColumn;
import java.awt.Color;

/**
 *
 * @author Ian Mburu
 */
public class PieChartBuild {
    
    public DJChart buildPieChart(AbstractColumn columnKey,AbstractColumn columnSerie,String title){
        DJChart djPieChart = new DJPieChartBuilder()
                .setCentered(true)
                .setBackColor(Color.LIGHT_GRAY)
                .setShowLegend(true)
                .setBackColor(Color.LIGHT_GRAY)
                .setShowLegend(true)
                .setPosition(DJChartOptions.POSITION_FOOTER)
                .setTitle(title)
                .setTitleColor(Color.DARK_GRAY)
                .setTitleFont(Font.ARIAL_BIG_BOLD)
                .setLegendColor(Color.darkGray)
                .setLegendPosition(DJChartOptions.EDGE_BOTTOM)
                .setTitlePosition(DJChartOptions.EDGE_TOP)
                .setLineStyle(DJChartOptions.LINE_STYLE_DOTTED)
                .setLineWidth(1)
                .setLineColor(Color.darkGray)
                .setPadding(5)
                //data set    
                .setKey((PropertyColumn)columnKey)
                .addSerie(columnSerie)
                //plot
                .setCircular(true)
                .build();
        return djPieChart;
    }
    
}
