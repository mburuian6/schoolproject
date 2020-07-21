/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.report;

import BeansPackage.BeansClass;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.AutoText;
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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.view.JasperViewer;
import retail_movie_store_mgmt.commonUtil.DateTime;
import retail_movie_store_mgmt.database.Logs;
import retail_movie_store_mgmt.logs.Log;

/**
 *
 * @author Ian Mburu
 */
public class LogsReport {
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
        headerStyle.setBackgroundColor(Color.LIGHT_GRAY);
        headerStyle.setTextColor(Color.WHITE);
        headerStyle.setTransparency(Transparency.OPAQUE);
        
        Style importeStyle = new Style();
        importeStyle.setHorizontalAlign(HorizontalAlign.RIGHT);
        
        AbstractColumn columnLogNumber= ColumnBuilder.getNew().setColumnProperty("number",Integer.class.getName())
            .setTitle("Num").setWidth(20).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        AbstractColumn columnLogId= ColumnBuilder.getNew().setColumnProperty("logId",String.class.getName())
            .setTitle("Log Id").setWidth(85).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        AbstractColumn columnUsername= ColumnBuilder.getNew().setColumnProperty("username",String.class.getName())
            .setTitle("Username").setWidth(50).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        AbstractColumn columnTimeIn= ColumnBuilder.getNew().setColumnProperty("timeIn",LocalDateTime.class.getName())
            .setTitle("Time In").setWidth(50).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        AbstractColumn columnTimeOut= ColumnBuilder.getNew().setColumnProperty("timeOut",LocalDateTime.class.getName())
            .setTitle("Time Out").setWidth(50).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        drb.addColumn(columnLogNumber);
        drb.addColumn(columnLogId);
        drb.addColumn(columnUsername);
        drb.addColumn(columnTimeIn);
        drb.addColumn(columnTimeOut);
        
        drb.setTitleStyle(headerStyle).setTitle("RMS - Logs Report");
        drb.setUseFullPageWidth(true);
        
        DateTime dateTime = BeansClass.dateTime();
        LocalDate localDate =  dateTime.getTodayDate();
                
        String createdOn = "Created On:"+localDate.toString();
        drb.addAutoText(createdOn, AutoText.POSITION_HEADER, AutoText.ALIGNMENT_RIGHT, 570, importeStyle); //title
        drb.addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y, AutoText.POSITION_FOOTER, AutoText.ALIGNMENT_CENTER);//pages
        
        
    }
    
    public DynamicReport buildReport () throws Exception{
        setup();
        return drb.build();
    }
    
    public ArrayList<Log> getData(){
        ArrayList<Log> logsList = new ArrayList();
        Logs logs =BeansClass.logs();
        Iterable<Log> logIterable = logs.findAll();
        Iterator <Log> logIterator = logIterable.iterator();
        while(logIterator.hasNext()){
            Log next = logIterator.next();
            logsList.add(next);
        }
        
        
        logsList.sort(Comparator.comparing((Log o) -> o.getTimeIn()).reversed());
        
        
        
        return logsList;
    }
    
    
    public LocalDate convertToLocalDate(Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    
    public static void main(String[] args) {
        LogsReport test = new LogsReport();
        test.mainMethod();
        
        
    }
    
    public void mainMethod() {
        try {
            DynamicReport dr = buildReport();
            
            //set data
            JRDataSource ds = new JRBeanCollectionDataSource(getData());
            JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds);//dr, layoutManager, resultSet; affect pg layout
            JasperViewer.viewReport(jp,false);
            
//            ProgressIndicator pi = new ProgressIndicator();
//            pi.finishGeneration();
            
        } catch (Exception ex) {
//            DialogPane dialog = BeansClass.dialogPane();
//            dialog.displayError("Error. Could not generate report");
            ex.printStackTrace();
        }
    }
    
    
    
    
}
