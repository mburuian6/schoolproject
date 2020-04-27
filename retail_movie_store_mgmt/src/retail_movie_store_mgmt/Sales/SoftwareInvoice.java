/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.Sales;

import BeansPackage.BeansClass;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.AutoText;
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
import java.util.ArrayList;
import java.util.Date;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.view.JasperViewer;
import retail_movie_store_mgmt.AddSoftwareSaleLogic;
import retail_movie_store_mgmt.ui.DialogPane;

/**
 *
 * @author Ian Mburu
 */
public class SoftwareInvoice {
    DynamicReportBuilder drb;
    JRDesignChart chart;
    public ArrayList<SoftwareSaleEntry> getData(String saleId){
        AddSoftwareSaleLogic addSoftwareSaleLogic = BeansClass.addSoftwareSaleLogic();
        return addSoftwareSaleLogic.findOneSale(saleId);
    }
    
    public Date getDate(String saleId){
        ArrayList<SoftwareSaleEntry> data = getData(saleId);
        if(data!=null){
            try{
            SoftwareSaleEntry entry = data.get(0);
            return entry.getDate();
            }
            catch(IndexOutOfBoundsException ne){
                DialogPane dialogPane = BeansClass.dialogPane();
                dialogPane.displayError("Empty Sale.Please enter one product to sell");
            }
        }
        return null;
    }
    
    public void setup(String saleId,String customerName){
        drb = new DynamicReportBuilder();
        
        //set styles
        Style headerStyle = new Style();
        headerStyle.setFont(Font.VERDANA_BIG_BOLD);
        headerStyle.setBorderBottom(Border.PEN_2_POINT());
        headerStyle.setHorizontalAlign(HorizontalAlign.LEFT);
        headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);
        headerStyle.setBackgroundColor(Color.DARK_GRAY);
        headerStyle.setTextColor(Color.WHITE);
        headerStyle.setTransparency(Transparency.OPAQUE);
        
        Style subTitleStyle = new Style();
        subTitleStyle.setHorizontalAlign(HorizontalAlign.RIGHT);
        subTitleStyle.setFont(Font.VERDANA_MEDIUM_BOLD);
        subTitleStyle.setBorderBottom(Border.PEN_2_POINT());
        subTitleStyle.setHorizontalAlign(HorizontalAlign.LEFT);
        subTitleStyle.setBackgroundColor(Color.DARK_GRAY);
        subTitleStyle.setTextColor(Color.WHITE);
        subTitleStyle.setTransparency(Transparency.OPAQUE);
        
        Style importeStyle = new Style();
        importeStyle.setHorizontalAlign(HorizontalAlign.RIGHT);
        
        AbstractColumn columnTitle= ColumnBuilder.getNew().setColumnProperty("title",String.class.getName())
            .setTitle("Title").setWidth(50).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        AbstractColumn columnPrice= ColumnBuilder.getNew().setColumnProperty("price",Double.class.getName())
            .setTitle("Price").setWidth(30).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        AbstractColumn columnQuantity= ColumnBuilder.getNew().setColumnProperty("quantity",Integer.class.getName())
            .setTitle("Quantity").setWidth(50).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        AbstractColumn columnSubTotal= ColumnBuilder.getNew().setColumnProperty("sub_total",Double.class.getName())
            .setTitle("Total").setWidth(50).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        AbstractColumn columnSubDiscount= ColumnBuilder.getNew().setColumnProperty("sub_discount",Double.class.getName())
            .setTitle("Discount").setWidth(50).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        AbstractColumn columnSubNetTotal= ColumnBuilder.getNew().setColumnProperty("sub_netTotal",Double.class.getName())
            .setTitle("Net Total").setWidth(50).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        
        drb.addColumn(columnTitle);
        drb.addColumn(columnPrice);
        drb.addColumn(columnQuantity);
        drb.addColumn(columnSubTotal);
        drb.addColumn(columnSubDiscount);
        drb.addColumn(columnSubNetTotal);
        
        drb.setTitleStyle(headerStyle).setTitle("RMS - Invoice"+ " : "+ getDate(saleId).toString() );
        drb.setGrandTotalLegend("Total");
        drb.setSubtitleStyle(subTitleStyle).setSubtitle("Customer: "+customerName+". Sale Id: "+ saleId );
        drb.addAutoText(AutoText.PATTERN_DATE_TIME_ONLY,AutoText.POSITION_HEADER,AutoText.ALIGNMENT_RIGHT);
        drb.addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y, AutoText.POSITION_FOOTER, AutoText.ALIGNMENT_CENTER);
        
        
        drb.addGlobalFooterVariable(columnQuantity, DJCalculation.SUM);
        drb.addGlobalFooterVariable(columnSubTotal, DJCalculation.SUM);
        drb.addGlobalFooterVariable(columnSubDiscount, DJCalculation.SUM);
        drb.addGlobalFooterVariable(columnSubNetTotal, DJCalculation.SUM);
        
        drb.setUseFullPageWidth(true);
        drb.setPrintBackgroundOnOddRows(true);
    }
    
    public DynamicReport buildReport (String saleId,String customerName) throws Exception{
        setup(saleId,customerName);
        return drb.build();
    }
    
    public void produceInvoice(String saleId,String customerName){
        try{
            DynamicReport dr = buildReport(saleId,customerName);
            
            JRDataSource ds = new JRBeanCollectionDataSource(getData(saleId));
            JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds);//dr, layoutManager, resultSet; affect pg layout
            JasperViewer.viewReport(jp,false);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
