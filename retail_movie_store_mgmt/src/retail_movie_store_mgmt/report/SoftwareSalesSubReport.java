/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.report;

import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import java.awt.Color;
import java.util.Date;
import net.sf.jasperreports.engine.design.JRDesignChart;

/**
 *
 * @author Ian Mburu
 */
public class SoftwareSalesSubReport {
    DynamicReportBuilder drb;
    JRDesignChart chart;
    
    public void setup(){
        drb = new DynamicReportBuilder();
        
        //set styles
        Style headerStyle = new Style();
        headerStyle.setFont(Font.VERDANA_MEDIUM_BOLD);
        headerStyle.setBorderBottom(Border.PEN_2_POINT());
        headerStyle.setHorizontalAlign(HorizontalAlign.RIGHT);
        headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);
        headerStyle.setBackgroundColor(Color.darkGray);
        headerStyle.setTextColor(Color.WHITE);
        headerStyle.setTransparency(Transparency.OPAQUE);
        
        Style importeStyle = new Style();
        importeStyle.setHorizontalAlign(HorizontalAlign.RIGHT);
        
        AbstractColumn columnId= ColumnBuilder.getNew().setColumnProperty("saleid",String.class.getName())
            .setTitle("Sale Id").setWidth(80).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        AbstractColumn columnTitle= ColumnBuilder.getNew().setColumnProperty("title",String.class.getName())
            .setTitle("Title").setWidth(40).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        AbstractColumn columnPrice= ColumnBuilder.getNew().setColumnProperty("price",Double.class.getName())
            .setTitle("Price").setWidth(25).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        AbstractColumn columnQuantity= ColumnBuilder.getNew().setColumnProperty("quantity",Integer.class.getName())
            .setTitle("Quantity").setWidth(40).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        AbstractColumn columnSubtotal= ColumnBuilder.getNew().setColumnProperty("sub_total",Double.class.getName())
            .setTitle("Sub-Total").setWidth(40).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        AbstractColumn columnSubDiscount= ColumnBuilder.getNew().setColumnProperty("sub_discount",Double.class.getName())
            .setTitle("Discount").setWidth(40).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        AbstractColumn columnSubNetTotal= ColumnBuilder.getNew().setColumnProperty("sub_netTotal",Double.class.getName())
            .setTitle("Net Total").setWidth(40).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        AbstractColumn columnProfile= ColumnBuilder.getNew().setColumnProperty("saleProfile",String.class.getName())
            .setTitle("Profile").setWidth(40).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        AbstractColumn columnDate= ColumnBuilder.getNew().setColumnProperty("date",Date.class.getName())
            .setTitle("Date").setWidth(55).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        //set the columns in the report builder
        drb.addColumn(columnId);
        drb.addColumn(columnTitle);
        drb.addColumn(columnPrice);
        drb.addColumn(columnQuantity);
        drb.addColumn(columnSubtotal);
        drb.addColumn(columnSubDiscount);
        drb.addColumn(columnSubNetTotal);
        drb.addColumn(columnProfile);
        drb.addColumn(columnDate);
        
        drb.setTitle("RMS - Software Sales SubReport");
        drb.setUseFullPageWidth(true);
        drb.setGrandTotalLegend("Total:").setGrandTotalLegendStyle(importeStyle);
        
        drb.addGlobalFooterVariable(columnTitle, DJCalculation.COUNT);
        drb.addGlobalFooterVariable(columnSubtotal, DJCalculation.SUM);
        drb.addGlobalFooterVariable(columnSubDiscount, DJCalculation.SUM);
        drb.addGlobalFooterVariable(columnSubNetTotal, DJCalculation.SUM);
        
        
    }
    
    public DynamicReport buildReport () throws Exception{
        setup();
        return drb.build();
    }
    
}
