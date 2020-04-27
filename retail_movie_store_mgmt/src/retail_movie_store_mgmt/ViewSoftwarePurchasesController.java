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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import retail_movie_store_mgmt.Logic.HandleProfileLogic;
import retail_movie_store_mgmt.Logic.HandleSoftwarePurchaseLogic;
import retail_movie_store_mgmt.commonUtil.DateTime;
import retail_movie_store_mgmt.purchases.software.SoftwarePurchase;
import retail_movie_store_mgmt.report.SoftwarePurchasesReport;
import retail_movie_store_mgmt.ui.DialogPane;

/**
 * FXML Controller class
 *
 * @author Ian Mburu
 */
public class ViewSoftwarePurchasesController implements Initializable {

    @FXML AnchorPane parentPane;
    
    @FXML TableView purchasesTable;
    @FXML TableColumn <SoftwarePurchase,String> idColumn;
    @FXML TableColumn <SoftwarePurchase,String> titleColumn;
    @FXML TableColumn <SoftwarePurchase,Double> priceColumn;
    @FXML TableColumn <SoftwarePurchase,Integer> quantityColumn;
    @FXML TableColumn <SoftwarePurchase,Double> totalColumn;
    @FXML TableColumn <SoftwarePurchase,LocalDate> dateColumn;
    
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
        //selectionModel();
        
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
//        selectionModel();
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
            HandleSoftwarePurchaseLogic handleSoftwarePurchaseLogic = BeansClass.handleSoftwarePurchaseLogic();
            ArrayList<SoftwarePurchase> thisDateEntries = handleSoftwarePurchaseLogic.getThisDateEntries(thisDate);
            setTable(thisDateEntries);
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
            HandleSoftwarePurchaseLogic handleSoftwarePurchaseLogic = BeansClass.handleSoftwarePurchaseLogic();
            setTable(handleSoftwarePurchaseLogic.getEntriesBetweenDates(startDate, todayDate));
        }
        else if(startDate == null && endDate != null){
            ArrayList<SoftwarePurchase> data = getData();
            if(data != null && !data.isEmpty()){
                SoftwarePurchase firstEntry = data.get(0);
                
                HandleSoftwarePurchaseLogic handleSoftwarePurchaseLogic = BeansClass.handleSoftwarePurchaseLogic();
                setTable(handleSoftwarePurchaseLogic.getEntriesBetweenDates(firstEntry.getDate(), todayDate));
            }
        }
        else if(startDate != null && endDate != null){
            HandleSoftwarePurchaseLogic handleSoftwarePurchaseLogic = BeansClass.handleSoftwarePurchaseLogic();
            setTable(handleSoftwarePurchaseLogic.getEntriesBetweenDates(startDate, endDate));
        }
        else{
            populateAll();
        }
    }
    
    public ArrayList<SoftwarePurchase> getData(){
        HandleSoftwarePurchaseLogic handleSoftwarePurchaseLogic = BeansClass.handleSoftwarePurchaseLogic();
        return handleSoftwarePurchaseLogic.getAllEntries();
    }
    
    public void setTable(ArrayList<SoftwarePurchase> data){
        //setCellFactory
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));

        //get table data
        ObservableList<SoftwarePurchase> allEntriesObs = FXCollections.observableArrayList(data);

        //set table data
        purchasesTable.getItems().setAll(allEntriesObs);
        
    }
    
    public void back(){
        parentPane.getScene().getWindow().hide();
        
        HandleFxmlPages handleFxmlPages = BeansClass.handleFxmlPages();
        handleFxmlPages.Home();
    }
    
    
    public void getSoftwarePurchasesReport(){
        HandleProfileLogic handleProfileLogic = BeansClass.handleProfileLogic();
        boolean privilege = handleProfileLogic.checkUserPrivilegeAdminManager(User.loggedin);
        if(privilege){
            Thread thread = new Thread(){
                public void run(){
                    SoftwarePurchasesReport softwarePurchasesReport = BeansClass.softwarePurchasesReport();
                    softwarePurchasesReport.produceReport();
                }
            };
            thread.start();
        }
        else{
            displayError("You are not allowed to access this functionality");
        }
    }
    
//    public void selectionModel(){
//        TableView.TableViewSelectionModel <SoftwarePurchase> selectionModel = purchasesTable.getSelectionModel();
//        ObservableList<SoftwarePurchase> selectedItems = selectionModel.getSelectedItems();
//        selectedItems.addListener(new ListChangeListener<SoftwarePurchase>(){
//            public void onChanged(ListChangeListener.Change<? extends SoftwarePurchase> change){
//                ObservableList<? extends SoftwarePurchase> list = change.getList();
//                SoftwarePurchase softwarePurchase = list.get(0);
//                delField.setText(softwarePurchase.getId());
//            }
//        });
//    }
    
//    public void deletePurchase(){
//        String id = delField.getText();
//        SoftwarePurchase softwarePurchase = BeansClass.softwarePurchase();
//        softwarePurchase.setId(id);
//        
//        HandleSoftwarePurchaseLogic handleSoftwarePurchaseLogic = BeansClass.handleSoftwarePurchaseLogic();
//        boolean check = handleSoftwarePurchaseLogic.checkIfExists(softwarePurchase);
//        if(!check){
//            displayError("That entry does not exist");
//        }
//        else{
//            handleSoftwarePurchaseLogic.delete(softwarePurchase);
//            delField.clear();
//            refresh();
//        }
//    }
    
//    public void refresh(){
//        setTable(getData());
//    }
    
    public void clearAllFields(){
        dateThisPicker.setValue(null);
        dateStartPicker.setValue(null);
        dateEndPicker.setValue(null);
    }
    
    public void displayInfo(String message){
        DialogPane dialogPane = BeansClass.dialogPane();
        dialogPane.displayInfo(message);
    }
    
    public void displayError(String message){
        DialogPane dialogPane = BeansClass.dialogPane();
        dialogPane.displayError(message);
    }
    
}
