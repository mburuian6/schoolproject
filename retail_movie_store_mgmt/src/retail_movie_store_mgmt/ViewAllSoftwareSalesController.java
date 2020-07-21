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
import java.util.Date;
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
import retail_movie_store_mgmt.Sales.SoftwareSaleEntry;
import retail_movie_store_mgmt.commonUtil.DateTime;
import retail_movie_store_mgmt.report.SoftwareSalesReport;

/**
 * FXML Controller class
 *
 * @author Ian Mburu
 */
public class ViewAllSoftwareSalesController implements Initializable {
    
    @FXML AnchorPane parentPane;
    
    @FXML TableView salesTable;
    @FXML TableColumn <SoftwareSaleEntry,String> idColumn;
    @FXML TableColumn <SoftwareSaleEntry,String> titleColumn;
    @FXML TableColumn <SoftwareSaleEntry,Double> priceColumn;
    @FXML TableColumn <SoftwareSaleEntry,Integer> quantityColumn;
    @FXML TableColumn <SoftwareSaleEntry,Double> subTotalColumn;
    @FXML TableColumn <SoftwareSaleEntry,Double> discountColumn;
    @FXML TableColumn <SoftwareSaleEntry,Double> subNetTotalColumn;
    @FXML TableColumn <SoftwareSaleEntry,String> profileColumn;
    @FXML TableColumn <SoftwareSaleEntry,Date> dateColumn;
    
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
            AddSoftwareSaleLogic addSoftwareSaleLogic = BeansClass.addSoftwareSaleLogic();
            ArrayList<SoftwareSaleEntry> thisDatesEntries = addSoftwareSaleLogic.findThisDatesEntries(thisDate);
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
            AddSoftwareSaleLogic addSoftwareSaleLogic = BeansClass.addSoftwareSaleLogic();
            setTable(addSoftwareSaleLogic.findBetweenDatesEntries(startDate, todayDate));
        }
        else if(startDate == null && endDate != null){
            ArrayList<SoftwareSaleEntry> data = getData();
            if(data != null && !data.isEmpty()){
                SoftwareSaleEntry firstEntry = data.get(0);
                HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
                LocalDate lclFirstEntryDate = handleMainLogic.convertToLocalDate(firstEntry.getDate());
                
                AddSoftwareSaleLogic addSoftwareSaleLogic = BeansClass.addSoftwareSaleLogic();
                setTable(addSoftwareSaleLogic.findBetweenDatesEntries(lclFirstEntryDate, todayDate));
            }
        }
        else if(startDate != null && endDate != null){
            AddSoftwareSaleLogic addSoftwareSaleLogic = BeansClass.addSoftwareSaleLogic();
            setTable(addSoftwareSaleLogic.findBetweenDatesEntries(startDate, endDate));
        }
        else{
            populateAll();
        }
    }
    
    public ArrayList<SoftwareSaleEntry> getData(){
        AddSoftwareSaleLogic addSoftwareSaleLogic = BeansClass.addSoftwareSaleLogic();
        return addSoftwareSaleLogic.findAll() ;
    }
    
    public void setTable(ArrayList<SoftwareSaleEntry>individualSaleEntries){
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
            ObservableList<SoftwareSaleEntry> allEntriesObs = FXCollections.<SoftwareSaleEntry>observableArrayList(individualSaleEntries);
            //set table data
            salesTable.getItems().setAll(allEntriesObs);
        }
        if(individualSaleEntries.isEmpty()){
            //get table data
            ObservableList<SoftwareSaleEntry> allEntriesObs = FXCollections.<SoftwareSaleEntry>observableArrayList(individualSaleEntries);
            //set table data
            salesTable.getItems().setAll(allEntriesObs);
        }
        else{
            //get table data
            ObservableList<SoftwareSaleEntry> allEntriesObs = FXCollections.<SoftwareSaleEntry>observableArrayList(individualSaleEntries);
            //set table data
            salesTable.getItems().setAll(allEntriesObs);
        }
    }
    
    
    
    public void getSoftwareSalesReport(){
        HandleProfileLogic handleProfileLogic = BeansClass.handleProfileLogic();
        boolean privilege = handleProfileLogic.checkUserPrivilegeAdminManager(User.loggedin);
        if(privilege){
            Thread thread = new Thread(){
                public void run(){
                    SoftwareSalesReport softwareSalesReport = BeansClass.softwareSalesReport();
                    softwareSalesReport.produceReport();
                    
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
    
    public void back(){
        parentPane.getScene().getWindow().hide();
        
        HandleFxmlPages handleFxmlPages = BeansClass.handleFxmlPages();
        handleFxmlPages.Home();
    }
}
