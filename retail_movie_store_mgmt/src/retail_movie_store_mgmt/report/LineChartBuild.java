/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.report;

import ar.com.fdvs.dj.domain.chart.DJChart;
import ar.com.fdvs.dj.domain.chart.DJChartOptions;
import ar.com.fdvs.dj.domain.chart.NumberExpression;
import ar.com.fdvs.dj.domain.chart.builder.DJLineChartBuilder;
import ar.com.fdvs.dj.domain.chart.plot.DJAxisFormat;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import ar.com.fdvs.dj.domain.entities.columns.PropertyColumn;
import java.awt.Color;

/**
 *
 * @author Ian Mburu
 */
public class LineChartBuild {
    public DJChart buildDualLineChart(
            AbstractColumn columnKey,
            AbstractColumn columnSerie1,
            AbstractColumn columnSerie2,
            String title
    ){
        DJAxisFormat xAxisFormat = new DJAxisFormat(columnKey.getTitle());
        xAxisFormat.setLabelFont(Font.ARIAL_SMALL);
        xAxisFormat.setLabelColor(Color.DARK_GRAY);
        xAxisFormat.setTickLabelFont(Font.ARIAL_SMALL);
        xAxisFormat.setTickLabelColor(Color.DARK_GRAY);
        xAxisFormat.setTickLabelMask("");
        xAxisFormat.setLineColor(Color.DARK_GRAY);
        
        DJAxisFormat yAxisFormat = new DJAxisFormat(columnSerie1.getTitle());
        yAxisFormat.setLabelFont(Font.ARIAL_SMALL);
        yAxisFormat.setLabelColor(Color.DARK_GRAY);
        yAxisFormat.setTickLabelFont(Font.ARIAL_SMALL);
        yAxisFormat.setTickLabelColor(Color.DARK_GRAY);
        yAxisFormat.setTickLabelMask("#,##0.0");
        yAxisFormat.setLineColor(Color.DARK_GRAY);
        yAxisFormat.setRangeMaxValueExpression(new NumberExpression(10));
        
        
        DJChart djChart = new DJLineChartBuilder()
                .setCentered(true)
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
                .setCategory((PropertyColumn)columnKey)
                .addSerie(columnSerie1)
                .addSerie(columnSerie2) 
                //plot
                .setShowShapes(true)
                .setShowLines(true)
                .setCategoryAxisFormat(xAxisFormat)
                .build();
        return djChart;
    }
    
    public DJChart buildLineChart(
            AbstractColumn columnKey,
            AbstractColumn columnSerie1,
            String title
    ){
        DJAxisFormat xAxisFormat = new DJAxisFormat(columnKey.getTitle());
        xAxisFormat.setLabelFont(Font.ARIAL_SMALL);
        xAxisFormat.setLabelColor(Color.DARK_GRAY);
        xAxisFormat.setTickLabelFont(Font.ARIAL_SMALL);
        xAxisFormat.setTickLabelColor(Color.DARK_GRAY);
        xAxisFormat.setTickLabelMask("");
        xAxisFormat.setLineColor(Color.DARK_GRAY);
        
        DJAxisFormat yAxisFormat = new DJAxisFormat(columnSerie1.getTitle());
        yAxisFormat.setLabelFont(Font.ARIAL_SMALL);
        yAxisFormat.setLabelColor(Color.DARK_GRAY);
        yAxisFormat.setTickLabelFont(Font.ARIAL_SMALL);
        yAxisFormat.setTickLabelColor(Color.DARK_GRAY);
        yAxisFormat.setTickLabelMask("#,##0.0");
        yAxisFormat.setLineColor(Color.DARK_GRAY);
        yAxisFormat.setRangeMaxValueExpression(new NumberExpression(10));
        
        
        DJChart djChart = new DJLineChartBuilder()
                .setCentered(true)
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
                .setCategory((PropertyColumn)columnKey)
                .addSerie(columnSerie1)
                //plot
                .setShowShapes(true)
                .setShowLines(true)
                .setCategoryAxisFormat(xAxisFormat)
                .build();
        return djChart;
    }
}
