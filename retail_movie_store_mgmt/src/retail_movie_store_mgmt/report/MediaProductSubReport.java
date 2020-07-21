/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.report;

import BeansPackage.BeansClass;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.chart.DJChart;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import java.awt.Color;
import java.time.LocalDate;
import net.sf.jasperreports.engine.design.JRDesignChart;
import retail_movie_store_mgmt.commonUtil.DateTime;

/**
 *
 * @author Ian Mburu
 */
public class MediaProductSubReport {
    DynamicReportBuilder drb;
    JRDesignChart chart;
    
    public void setup(){
        drb = new DynamicReportBuilder();
        
        //set styles
        Style headerStyle = new Style();
        headerStyle.setFont(Font.VERDANA_BIG_BOLD);
        headerStyle.setBorderBottom(Border.PEN_2_POINT());
        headerStyle.setHorizontalAlign(HorizontalAlign.RIGHT);
        headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);
        headerStyle.setBackgroundColor(Color.DARK_GRAY);
        headerStyle.setTextColor(Color.WHITE);
        headerStyle.setTransparency(Transparency.OPAQUE);
        
        Style importeStyle = new Style();
        importeStyle.setHorizontalAlign(HorizontalAlign.RIGHT);
        
        AbstractColumn columnTitle= ColumnBuilder.getNew().setColumnProperty("title",String.class.getName())
            .setTitle("Title").setWidth(50).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        AbstractColumn columnbrand= ColumnBuilder.getNew().setColumnProperty("brand",String.class.getName())
            .setTitle("Brand").setWidth(50).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        AbstractColumn columnPrice= ColumnBuilder.getNew().setColumnProperty("price",Double.class.getName())
            .setTitle("Price").setWidth(50).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        AbstractColumn columnPriceOptical= ColumnBuilder.getNew().setColumnProperty("price_optical_disk",Double.class.getName())
            .setTitle("Price on Optical Disk").setWidth(50).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        AbstractColumn columnDescription= ColumnBuilder.getNew().setColumnProperty("description",String.class.getName())
            .setTitle("Description").setWidth(100).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        
        drb.addColumn(columnTitle);
        drb.addColumn(columnbrand);
        drb.addColumn(columnPrice);
        drb.addColumn(columnPriceOptical);
        drb.addColumn(columnDescription);
        
        
        
        drb.setTitleStyle(headerStyle).setTitle("RMS - Media $ Customs Product Report");
        drb.setUseFullPageWidth(true);
        drb.setPrintBackgroundOnOddRows(true);
        drb.setGrandTotalLegend("Total:").setGrandTotalLegendStyle(importeStyle);
        drb.addGlobalFooterVariable(columnDescription, DJCalculation.COUNT);
        
        DateTime dateTime = BeansClass.dateTime();
        LocalDate localDate =  dateTime.getTodayDate();
        
        BarChartBuild instance = BeansClass.barChartBuild();
        DJChart barChart = instance.buildDualBarChart(columnTitle, columnPrice, columnPriceOptical, "Media Bar-Chart");
        drb.addChart(barChart);
        
//        PieChartBuild pieChartB = BeansClass.pieChartBuild();
//        DJChart pieChart = pieChartB.buildPieChart(columnTitle, columnPrice, "Media Pie-Chart Based on Price");
//        drb.addChart(pieChart);
                
        String createdOn = "Created On:"+localDate.toString();
        drb.addAutoText(createdOn, AutoText.POSITION_HEADER, AutoText.ALIGNMENT_RIGHT, 570, importeStyle); //title
//        drb.addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y, AutoText.POSITION_FOOTER, AutoText.ALIGNMENT_CENTER);//pages
        
        
    }
    
    public DynamicReport buildReport () throws Exception{
        setup();
        return drb.build();
    }
    /*********************************************************/
//    public ArrayList<Product> getData(){
//        ArrayList<Product> productList = new ArrayList();
//        ProductHandle productHandle =BeansClass.productHandle();
//        Iterable<Product> productIterable = productHandle.findAll();
//        Iterator <Product> productIterator = productIterable.iterator();
//        while(productIterator.hasNext()){
//            productList.add(productIterator.next());
//        }
//        return productList;
//    }
    
//    public void mainMethod() {
//        try {
//            DynamicReport dr = buildReport();
//            
//            //set data
//            JRDataSource ds = new JRBeanCollectionDataSource(getData());
//            JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds);//dr, layoutManager, resultSet; affect pg layout
//            JasperViewer.viewReport(jp,false);
//            
//        } catch (Exception ex) {
//            HomeController homeController = BeansClass.homeController();
//            homeController.displayError("Error. Could not generate report");
//        }
//    }
//    
//    public static void main(String[] args) {
//        MediaProductSubReport pr = new MediaProductSubReport();
//        pr.mainMethod();
//    }
}
