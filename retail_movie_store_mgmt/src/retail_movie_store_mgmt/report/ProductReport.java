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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import retail_movie_store_mgmt.database.ProductHandle;
import retail_movie_store_mgmt.database.SoftwareHandle;
import retail_movie_store_mgmt.product.Product;
import retail_movie_store_mgmt.product.Software;
import retail_movie_store_mgmt.ui.DialogPane;

/**
 *
 * @author Ian Mburu
 */
public class ProductReport {

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
        
        
        DynamicReportBuilder drb = new DynamicReportBuilder();
        drb.setTitleStyle(headerStyle)
        .setTitle("RMS Product Report")
        .setUseFullPageWidth(true)
        .setWhenNoDataAllSectionNoDetail()
        .addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y,AutoText.POSITION_FOOTER,AutoText.ALIGNMENT_CENTER);
        
        MediaProductSubReport mediaProductSubReport = BeansClass.mediaProductSubReport();
        SoftwareProductSubReport softwareProductSubReport = BeansClass.softwareProductSubReport();
        try {
            DynamicReport subReport1 = mediaProductSubReport.buildReport();
            drb.addConcatenatedReport(subReport1,new ClassicLayoutManager(),"media",
                    DJConstants.DATA_SOURCE_ORIGIN_PARAMETER,DJConstants.DATA_SOURCE_TYPE_COLLECTION,false);
            
            DynamicReport subReport2 = softwareProductSubReport.buildReport();
            drb.addConcatenatedReport(subReport2,new ClassicLayoutManager(),"software",
                    DJConstants.DATA_SOURCE_ORIGIN_PARAMETER,DJConstants.DATA_SOURCE_TYPE_COLLECTION,false);
                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        LocalDate localDate =  LocalDate.now();
        String createdOn = "Created On:"+localDate.toString();
        drb.addAutoText(createdOn, AutoText.POSITION_HEADER, AutoText.ALIGNMENT_RIGHT, 100, headerStyle); //title
        
        DynamicReport dr = drb.build();
        return dr;
    }
    
    public void produceReport() {
        try{
            DynamicReport dr = buildReport();
            JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(),getData());
            JasperViewer.viewReport(jp,false);
        }
        catch(Exception e){
            e.printStackTrace();
            DialogPane dialogPane = BeansClass.dialogPane();
            dialogPane.displayError("Error. Could not generate report. Error message: "+ e.getMessage());
        }
    }
    
    public int getNumberOfSubreports(){
        return getData().size();
    }
    
    public LinkedHashMap getData(){
        LinkedHashMap <String, ArrayList> products = new LinkedHashMap(2);
        
        //media
        ArrayList<Product> mediaList = new ArrayList();
        ProductHandle productHandle =BeansClass.productHandle();
        Iterable<Product> productIterable = productHandle.findAll();
        Iterator <Product> productIterator = productIterable.iterator();
        while(productIterator.hasNext()){
            mediaList.add(productIterator.next());
        }
        
        products.put("media", mediaList);
        
        //software
        ArrayList<Product> softwareList = new ArrayList();
        SoftwareHandle softwareHandle =BeansClass.softwareHandle();
        Iterable<Software> softwareIterable = softwareHandle.findAll();
        Iterator <Software> softwareIterator = softwareIterable.iterator();
        while(softwareIterator.hasNext()){
            softwareList.add(softwareIterator.next());
        }
        
        products.put("software", softwareList);
        
        return products;
    }
    
    public static void main(String[] args) {
        ProductReport test = new ProductReport();
        test.produceReport();
    }
    
}
