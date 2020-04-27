/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt;

import BeansPackage.BeansClass;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import retail_movie_store_mgmt.Logic.HandleMainLogic;
import retail_movie_store_mgmt.Logic.HandleProfileLogic;
import retail_movie_store_mgmt.Sales.MediaAndCustomSaleEntry;
import retail_movie_store_mgmt.commonUtil.DateTime;
import retail_movie_store_mgmt.report.MediaSalesReport;

/**
 * FXML Controller class
 *
 * @author Ian Mburu
 */
public class ViewAllMediaSalesPageController implements Initializable {
    @FXML AnchorPane parentPane;
    
    @FXML TableView salesTable;
    @FXML TableColumn <MediaAndCustomSaleEntry,String> idColumn;
    @FXML TableColumn <MediaAndCustomSaleEntry,String> titleColumn;
    @FXML TableColumn <MediaAndCustomSaleEntry,Double> priceColumn;
    @FXML TableColumn <MediaAndCustomSaleEntry,Integer> quantityColumn;
    @FXML TableColumn <MediaAndCustomSaleEntry,Double> subTotalColumn;
    @FXML TableColumn <MediaAndCustomSaleEntry,Double> discountColumn;
    @FXML TableColumn <MediaAndCustomSaleEntry,Double> subNetTotalColumn;
    @FXML TableColumn <MediaAndCustomSaleEntry,String> profileColumn;
    @FXML TableColumn <MediaAndCustomSaleEntry,String> dateColumn;
    
    @FXML DatePicker dateThisPicker;
    @FXML DatePicker dateStartPicker;
    @FXML DatePicker dateEndPicker;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        populateAll();
        
        //Event Listeners
        //this date
        dateThisPicker.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                populateForThisDate();
            }
        });
        
        //in between dates
        dateStartPicker.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                populateInBetweenDates();
            }
        });
        
        dateEndPicker.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                populateInBetweenDates();
            }
        });
    }    
    
    public void populateAll(){
        clearAllFields();
        setTable(getData());
    }
    
    public void populateForThisDate(){
        LocalDate thisDate = dateThisPicker.getValue();
        dateStartPicker.setValue(null);
        dateEndPicker.setValue(null);
        
        if(thisDate == null){
            //pass
        }
        else{
            AddMediaSaleLogic addMediaSaleLogic = BeansClass.addMediaSaleLogic();
            ArrayList<MediaAndCustomSaleEntry> thisDatesEntries = addMediaSaleLogic.findThisDatesEntries(thisDate);
            setTable(thisDatesEntries);
        }
    }
    
    public void populateInBetweenDates(){
        LocalDate startDate = dateStartPicker.getValue();
        LocalDate endDate = dateEndPicker.getValue();
        dateThisPicker.setValue(null);
        
        DateTime dateTime = BeansClass.dateTime();
        LocalDate todayDate = dateTime.getTodayDate();
        
        if(startDate == null && endDate==null){
            //do nothing
        }
        else if(startDate != null && endDate == null){
            AddMediaSaleLogic addMediaSaleLogic = BeansClass.addMediaSaleLogic();
            setTable(addMediaSaleLogic.findBetweenDatesEntries(startDate, todayDate));
        }
        else if(startDate == null && endDate != null){
            ArrayList<MediaAndCustomSaleEntry> data = getData();
            if(data != null && !data.isEmpty()){
                MediaAndCustomSaleEntry firstEntry = data.get(0);
                HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
                LocalDate lclFirstEntryDate = handleMainLogic.convertToLocalDate(firstEntry.getDate());
                
                AddMediaSaleLogic addMediaSaleLogic = BeansClass.addMediaSaleLogic();
                setTable(addMediaSaleLogic.findBetweenDatesEntries(lclFirstEntryDate, todayDate));
            }
        }
        else if(startDate != null && endDate != null){
            AddMediaSaleLogic addMediaSaleLogic = BeansClass.addMediaSaleLogic();
            setTable(addMediaSaleLogic.findBetweenDatesEntries(startDate, endDate));
        }
        else{
            populateAll();
        }
    }
    
    public ArrayList<MediaAndCustomSaleEntry> getData(){
        AddMediaSaleLogic addMediaSaleLogic = BeansClass.addMediaSaleLogic();
        return addMediaSaleLogic.findAll() ;
    }
    
    public void setTable(ArrayList<MediaAndCustomSaleEntry>individualSaleEntries){
        System.out.println(individualSaleEntries);
        //setCellFactory
        idColumn.setCellValueFactory(new PropertyValueFactory<>("saleid"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        subTotalColumn.setCellValueFactory(new PropertyValueFactory<>("sub_total"));
        discountColumn.setCellValueFactory(new PropertyValueFactory<>("sub_discount"));
        subNetTotalColumn.setCellValueFactory(new PropertyValueFactory<>("sub_netTotal"));
        profileColumn.setCellValueFactory(new PropertyValueFactory<>("saleProfile"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        if(individualSaleEntries==null){
            //get table data
            ObservableList<MediaAndCustomSaleEntry> allEntriesObs = FXCollections.<MediaAndCustomSaleEntry>observableArrayList(individualSaleEntries);
        
            //set table data
            salesTable.getItems().setAll(allEntriesObs);
        }
        if(individualSaleEntries.isEmpty()){
            //get table data
            ObservableList<MediaAndCustomSaleEntry> allEntriesObs = FXCollections.<MediaAndCustomSaleEntry>observableArrayList(individualSaleEntries);
        
            //set table data
            salesTable.getItems().setAll(allEntriesObs);
        }
        else{
            //get table data
            ObservableList<MediaAndCustomSaleEntry> allEntriesObs = FXCollections.<MediaAndCustomSaleEntry>observableArrayList(individualSaleEntries);
        
            //set table data
            salesTable.getItems().setAll(allEntriesObs);
        }
    }
    
    public void back(){
        parentPane.getScene().getWindow().hide();
        
        HandleFxmlPages handleFxmlPages = BeansClass.handleFxmlPages();
        handleFxmlPages.Home();
    }
    
    
    public void getMediaSalesReport(){
        HandleProfileLogic handleProfileLogic = BeansClass.handleProfileLogic();
        boolean privilege = handleProfileLogic.checkUserPrivilegeAdminManager(User.loggedin);
        if(privilege){
            Thread thread = new Thread(){
                public void run(){
                    MediaSalesReport mediaSalesReport = BeansClass.mediaSalesReport();
                        mediaSalesReport.produceReport();                    
                }                
            };
            thread.start();
        }
        else{
            displayError("You are not allowed to access this functionality");
        }
        
    }
    
    public void clearAllFields(){
        dateThisPicker.setValue(null);
        dateStartPicker.setValue(null);
        dateEndPicker.setValue(null);
    }
    
    public void displayInfo(String message){
        //Window owner = parentPane.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        //alert.initOwner(owner);
        alert.show();
    }
    
    public void displayError(String message){
        //Window owner = parentPane.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        //alert.initOwner(owner);
        alert.show();
    }
}
