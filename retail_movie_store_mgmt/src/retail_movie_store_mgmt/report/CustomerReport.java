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
import java.util.ArrayList;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.view.JasperViewer;
import retail_movie_store_mgmt.Customer.Customer;
import retail_movie_store_mgmt.Logic.HandleCustomerLogic;
import retail_movie_store_mgmt.commonUtil.DateTime;

/**
 *
 * @author Ian Mburu
 */
public class CustomerReport {
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
        
        AbstractColumn columnId= ColumnBuilder.getNew().setColumnProperty("id",String.class.getName())
            .setTitle("Id").setWidth(50).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        AbstractColumn columnUsername= ColumnBuilder.getNew().setColumnProperty("username",String.class.getName())
            .setTitle("Username").setWidth(40).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        AbstractColumn columnName= ColumnBuilder.getNew().setColumnProperty("customer_name",String.class.getName())
            .setTitle("Name").setWidth(40).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        AbstractColumn columnGender= ColumnBuilder.getNew().setColumnProperty("gender",String.class.getName())
            .setTitle("Gender").setWidth(30).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        AbstractColumn columnMovies= ColumnBuilder.getNew().setColumnProperty("movie_likes",String.class.getName())
            .setTitle("Movies").setWidth(50).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        AbstractColumn columnShows= ColumnBuilder.getNew().setColumnProperty("show_likes",String.class.getName())
            .setTitle("Shows").setWidth(50).setStyle(importeStyle)
            .setHeaderStyle(headerStyle).build();
        
        drb.addColumn(columnId);
        drb.addColumn(columnUsername);
        drb.addColumn(columnName);
        drb.addColumn(columnGender);
        drb.addColumn(columnMovies);
        drb.addColumn(columnShows);
        
        drb.setTitleStyle(headerStyle).setTitle("RMS - Customer Report");
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
    
    public ArrayList<Customer> getData(){
        HandleCustomerLogic logic = BeansClass.handleCustomerLogic();
        return logic.getAllCustomers();
    }
    
    public void mainMethod() {
        try {
            DynamicReport dr = buildReport();
            
            //set data
            JRDataSource ds = new JRBeanCollectionDataSource(getData());
            JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds);//dr, layoutManager, resultSet; affect pg layout
            JasperViewer.viewReport(jp,false);
        } catch (Exception ex) {
//            DialogPane dialog = BeansClass.dialogPane();
//            dialog.displayError("Error. Could not generate report");
            ex.printStackTrace();
        }
    }
}
