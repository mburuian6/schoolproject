
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.report;

import BeansPackage.BeansClass;
import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import java.awt.Color;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import retail_movie_store_mgmt.Sales.SoftwareSaleEntry;
import retail_movie_store_mgmt.database.nosql.SoftwareSalesHandle;
import retail_movie_store_mgmt.ui.DialogPane;

/**
 *
 * @author Ian Mburu
 */
public class SoftwareSalesReport {
    
    Set<String> keys;
    public LinkedHashMap getData(){
        SoftwareSalesHandle softwareSalesHandle = BeansClass.softwareSalesHandle();
        LinkedHashMap <String,ArrayList> dateItems = softwareSalesHandle.getAllItemsPerDate();
        keys = dateItems.keySet();
        return dateItems;
    }
    
    public int getNumberOfSubreports(){
        return getData().size();
    }
    
    public Object[] getKeySet(){
        getData();
        Object[] toArray =  keys.toArray(); //********* the cast!!!!!!!!!!!!!!
        return toArray;
    }
    
    public DynamicReport buildReport(){
        //set styles
        Style headerStyle = new Style();
        headerStyle.setFont(Font.VERDANA_MEDIUM_BOLD);
        headerStyle.setBorderBottom(Border.PEN_2_POINT());
        headerStyle.setHorizontalAlign(HorizontalAlign.RIGHT);
        headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);
        headerStyle.setBackgroundColor(Color.darkGray);
        headerStyle.setTextColor(Color.WHITE);
        headerStyle.setTransparency(Transparency.OPAQUE);
        
        Style titleStyle = new Style();
        titleStyle.setFont(new Font(13,Font._FONT_VERDANA,true));
        
        DynamicReportBuilder drb = new DynamicReportBuilder();
        Integer margin = 20;
        drb.setTitleStyle(titleStyle)
        .setTitle("RMS - Software Sales Report")
        .setUseFullPageWidth(true)
        .setWhenNoDataAllSectionNoDetail()
        .addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y,AutoText.POSITION_FOOTER,AutoText.ALIGNMENT_CENTER);
        
        Object[] keySetArr = getKeySet();
        for(int i=0; i<getNumberOfSubreports();i++){
            SoftwareSalesSubReport softwareSalesSubReport = BeansClass.softwareSalesSubReport();
            DynamicReport subReport;
            try {
                subReport = softwareSalesSubReport.buildReport(); 
                subReport.setTitle(subReport.getTitle()+ ". Report for : "+ keySetArr[i].toString());
                drb.addConcatenatedReport(subReport,new ClassicLayoutManager(),keySetArr[i].toString(),
                    DJConstants.DATA_SOURCE_ORIGIN_PARAMETER,DJConstants.DATA_SOURCE_TYPE_COLLECTION,false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        LocalDate localDate =  LocalDate.now();
        String createdOn = "Created On:"+localDate.toString();
        drb.addAutoText(createdOn, AutoText.POSITION_HEADER, AutoText.ALIGNMENT_RIGHT, 100, headerStyle); //title
        
        
        drb.addField("totals", HashMap.class);
        
        DynamicReport dr = drb.build();
        return dr;
        
    }
    
    public void produceReport(){
        try{
            DynamicReport dr = buildReport();
            JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(),getData());//dr, layoutManager, resultSet
            JasperViewer.viewReport(jp,false);
        }
        catch(JRException e){
            e.printStackTrace();
            DialogPane dialogPane = BeansClass.dialogPane();
            dialogPane.displayError("Error. Could not generate report; Error message: "+ e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        SoftwareSalesReport test = new SoftwareSalesReport();
        test.produceReport();
        
//        test.getSummaryPerMonth();
    }
    
    public LocalDate convertToLocalDate(Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    
    public HashMap getSummaryPerMonth(){
        Calendar cal = new GregorianCalendar();
        int year = cal.get(Calendar.YEAR);
        String strYear = Integer.toString(year);
        HashMap <String,ArrayList> dateItems  = getData();
        HashMap<String,Double>totals = new HashMap();
        System.out.println("Actual processing:");
        Set set = dateItems.keySet();
        
        for (Object keyObj: set){
            String dateStr = keyObj.toString();
            //check year 
            if(dateStr.contains(strYear)){
                ArrayList<SoftwareSaleEntry> entries= dateItems.get(dateStr);
                double totalForThisDate = 0;
                for(SoftwareSaleEntry entry: entries){
                    totalForThisDate = totalForThisDate+ entry.getSub_netTotal();
                }
                totals.put(dateStr, totalForThisDate);
            }
        }
        
        System.out.println("Totals: "+ totals);
        return totals;
    }
    
    public void getSummaryPerYear(){
        
    }
    
}
