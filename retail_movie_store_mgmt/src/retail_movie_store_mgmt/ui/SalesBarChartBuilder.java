/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.ui;

import BeansPackage.BeansClass;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import retail_movie_store_mgmt.Logic.HandleMainLogic;
import retail_movie_store_mgmt.Sales.MediaAndCustomSaleEntry;
import retail_movie_store_mgmt.Sales.SoftwareSaleEntry;
import retail_movie_store_mgmt.database.nosql.MediaSalesHandle;
import retail_movie_store_mgmt.database.nosql.SoftwareSalesHandle;

/**
 *
 * @author Ian Mburu
 */
public class SalesBarChartBuilder {
    final static String jan = "jan";
    final static String feb = "feb";
    final static String mar = "mar";
    final static String apr = "apr";
    final static String may = "may";
    final static String jun = "jun";
    final static String jul = "jul";
    final static String aug = "aug";
    final static String sep = "sep";
    final static String oct = "oct";
    final static String nov = "nov";
    final static String dec = "dec";
    
    static HashMap<String,Double> mediaMonthlyData = new HashMap(12);
    static HashMap<String,Double> softwareMonthlyData = new HashMap(12);
    ArrayList<String> monthsList = new ArrayList();
    
    public void setMonths(){
        monthsList.clear();
        
        monthsList.add(jan);
        monthsList.add(feb);
        monthsList.add(mar);
        monthsList.add(apr);
        monthsList.add(may);
        monthsList.add(jun);
        monthsList.add(jul);
        monthsList.add(aug);
        monthsList.add(sep);
        monthsList.add(oct);
        monthsList.add(nov);
        monthsList.add(dec);        
    }
    
    public void setNewMediaValue(String month,double subTotal){
        if (mediaMonthlyData.containsKey(month)) {
            double newVal = mediaMonthlyData.get(month) + subTotal;
            mediaMonthlyData.replace(month, newVal);
        } else {
            mediaMonthlyData.put(month, subTotal);
        }
    }
    
    public void setNewSoftwareValue(String month,double subTotal){
        if (softwareMonthlyData.containsKey(month)) {
            double newVal = softwareMonthlyData.get(month) + subTotal;
            softwareMonthlyData.replace(month, newVal);
        } else {
            softwareMonthlyData.put(month, subTotal);
        }
    }
    
    public ObservableList<XYChart.Series> createBarChart(){
        setMonths();
        getMediaData();
        getSoftwareData();
        
//        final CategoryAxis xAxis = new CategoryAxis();
//        final NumberAxis yAxis = new NumberAxis();
//        BarChart<String,Number> bc = 
//            new BarChart<>(xAxis,yAxis);
        
//        bc.setTitle("Sales BarChart");
//        xAxis.setLabel("Month");
//        yAxis.setLabel("Amount");
                
        XYChart.Series series1 = new XYChart.Series();
        System.out.println("Setting name 'Media'");
        series1.setName("Media");
        System.out.println("Opening monthsList: "+ monthsList);
        for(String month: monthsList){
            System.out.println("Evaluating month: "+ month);
            if(mediaMonthlyData.get(month) != null){
                System.out.println("Month: "+ month + "; number: "+mediaMonthlyData.get(month));
                series1.getData().add(new XYChart.Data(month, mediaMonthlyData.get(month)));
            }else{
                System.out.println("Null Error.Month: "+ month + "; number: "+0);
                series1.getData().add(new XYChart.Data(month,0));
            }
        }
        
        XYChart.Series series2 = new XYChart.Series();
        System.out.println("Setting name 'Software'");
        series2.setName("Software");
        System.out.println("Opening monthsList: "+ monthsList);
        for(String month: monthsList){
            System.out.println("Evaluating month: "+ month);
            if(softwareMonthlyData.get(month) != null){
                System.out.println("Month: "+ month + "; number: "+softwareMonthlyData.get(month));
                series2.getData().add(new XYChart.Data(month, softwareMonthlyData.get(month)));
            }else{
                System.out.println("Null Error.Month: "+ month + "; number: "+0);
                series2.getData().add(new XYChart.Data(month,0));
            }
        }
        
        ObservableList<XYChart.Series> seriesList = FXCollections.observableArrayList(series1,series2);
        return seriesList;
                
//        bc.getData().add(series1);
//        bc.getData().add(series2);
//        return bc;
    }
    
    public void getMediaData(){
        
        //get all the data for the year
        MediaSalesHandle dataHandle = BeansClass.mediaSalesHandle();
        ArrayList<MediaAndCustomSaleEntry> allEntries = dataHandle.findAll();
        
        HandleMainLogic logic = BeansClass.handleMainLogic();
        
        for(MediaAndCustomSaleEntry entry: allEntries){
            LocalDate localDate = logic.convertToLocalDate(entry.getDate());
            //that year
            if(localDate.getYear() == LocalDate.now().getYear()){
                switch (localDate.getMonthValue()) {
                    case 1:
                        setNewMediaValue("jan",entry.getSub_netTotal());
                        break;
                    case 2:
                        setNewMediaValue("feb",entry.getSub_netTotal());
                        break;
                    case 3:
                        setNewMediaValue("mar",entry.getSub_netTotal());
                        break;
                    case 4:
                        setNewMediaValue("apr",entry.getSub_netTotal());
                        break;
                    case 5:
                        setNewMediaValue("may",entry.getSub_netTotal());
                        break;
                    case 6:
                        setNewMediaValue("jun",entry.getSub_netTotal());
                        break;
                    case 7:
                        setNewMediaValue("jul",entry.getSub_netTotal());
                        break;
                    case 8:
                        setNewMediaValue("aug",entry.getSub_netTotal());
                        break;
                    case 9:
                        setNewMediaValue("sep",entry.getSub_netTotal());
                        break;
                    case 10:
                        setNewMediaValue("oct",entry.getSub_netTotal());
                        break;
                    case 11:
                        setNewMediaValue("nov",entry.getSub_netTotal());
                        break;
                    case 12:
                        setNewMediaValue("dec",entry.getSub_netTotal());
                        break;
                    default:
                        break;
                }
            }
        }
    }
    
    public void getSoftwareData(){
        
        //get all the data for the year
        SoftwareSalesHandle dataHandle = BeansClass.softwareSalesHandle();
        ArrayList<SoftwareSaleEntry> allEntries = dataHandle.findAll();
        
        HandleMainLogic logic = BeansClass.handleMainLogic();
        
        for(SoftwareSaleEntry entry: allEntries){
            LocalDate localDate = logic.convertToLocalDate(entry.getDate());
            //that year
            if(localDate.getYear() == LocalDate.now().getYear()){
                switch (localDate.getMonthValue()) {
                    case 1:
                        setNewSoftwareValue("jan",entry.getSub_netTotal());
                        break;
                    case 2:
                        setNewSoftwareValue("feb",entry.getSub_netTotal());
                        break;
                    case 3:
                        setNewSoftwareValue("mar",entry.getSub_netTotal());
                        break;
                    case 4:
                        setNewSoftwareValue("apr",entry.getSub_netTotal());
                        break;
                    case 5:
                        setNewSoftwareValue("may",entry.getSub_netTotal());
                        break;
                    case 6:
                        setNewSoftwareValue("jun",entry.getSub_netTotal());
                        break;
                    case 7:
                        setNewSoftwareValue("jul",entry.getSub_netTotal());
                        break;
                    case 8:
                        setNewSoftwareValue("aug",entry.getSub_netTotal());
                        break;
                    case 9:
                        setNewSoftwareValue("sep",entry.getSub_netTotal());
                        break;
                    case 10:
                        setNewSoftwareValue("oct",entry.getSub_netTotal());
                        break;
                    case 11:
                        setNewSoftwareValue("nov",entry.getSub_netTotal());
                        break;
                    case 12:
                        setNewSoftwareValue("dec",entry.getSub_netTotal());
                        break;
                    default:
                        break;
                }
            }
        }
    }
    
    
//    @Override
//    public void start(Stage stage){        
//        
//        SalesBarChartBuilder obj = new SalesBarChartBuilder();
//        final BarChart<String,Number> bc = obj.createBarChart();
//        Scene scene  = new Scene(bc,800,600);
//        stage.setScene(scene);
//        stage.show();
//    }
//    
//    public static void main(String[] args) {
//        launch(args);
//    }
}
