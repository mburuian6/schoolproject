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
import java.time.LocalDate;
import net.sf.jasperreports.engine.design.JRDesignChart;

/**
 *
 * @author Ian Mburu
 */
public class PreorderSubReport {
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
        
        AbstractColumn columnId= ColumnBuilder.getNew().setColumnProperty("id",String.class.getName())
            .setTitle("Id").setWidth(50).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        AbstractColumn columnCustomerName= ColumnBuilder.getNew().setColumnProperty("customer_name",String.class.getName())
            .setTitle("Customer").setWidth(50).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        AbstractColumn columnOrderDate= ColumnBuilder.getNew().setColumnProperty("orderDate",LocalDate.class.getName())
            .setTitle("Order Date").setWidth(30).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        AbstractColumn columnPickDate= ColumnBuilder.getNew().setColumnProperty("pickupDate",LocalDate.class.getName())
            .setTitle("Pick-up Date").setWidth(30).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        AbstractColumn columnPeripheral= ColumnBuilder.getNew().setColumnProperty("peripheral",String.class.getName())
            .setTitle("Customer Disk").setWidth(30).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        AbstractColumn columnProfile= ColumnBuilder.getNew().setColumnProperty("profile",String.class.getName())
            .setTitle("Profile").setWidth(30).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        AbstractColumn columnMovies= ColumnBuilder.getNew().setColumnProperty("movies_list",String.class.getName())
            .setTitle("Movies").setWidth(45).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        AbstractColumn columnShows= ColumnBuilder.getNew().setColumnProperty("shows_list",String.class.getName())
            .setTitle("Shows").setWidth(45).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        AbstractColumn columnSoftwares= ColumnBuilder.getNew().setColumnProperty("software_list",String.class.getName())
            .setTitle("Software").setWidth(45).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        AbstractColumn columnStatus= ColumnBuilder.getNew().setColumnProperty("status",String.class.getName())
            .setTitle("Status").setWidth(45).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        
        drb.addColumn(columnId);
        drb.addColumn(columnCustomerName);
        drb.addColumn(columnOrderDate);
        drb.addColumn(columnPickDate);
        drb.addColumn(columnPeripheral);
        drb.addColumn(columnProfile);
        drb.addColumn(columnMovies);
        drb.addColumn(columnShows);
        drb.addColumn(columnSoftwares);
        drb.addColumn(columnStatus);
        
        drb.setTitle("RMS - Preorder SubReport");
        drb.setUseFullPageWidth(true);
        drb.setGrandTotalLegend("Total:").setGrandTotalLegendStyle(importeStyle);
        
        drb.addGlobalFooterVariable(columnCustomerName, DJCalculation.COUNT);
        
    }
    
    public DynamicReport buildReport () throws Exception{
        setup();
        return drb.build();
    }
}
