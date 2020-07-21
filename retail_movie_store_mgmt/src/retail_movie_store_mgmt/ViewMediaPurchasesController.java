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
import retail_movie_store_mgmt.Logic.HandleMediaPurchaseLogic;
import retail_movie_store_mgmt.Logic.HandleProfileLogic;
import retail_movie_store_mgmt.commonUtil.DateTime;
import retail_movie_store_mgmt.purchases.media.MediaPurchase;
import retail_movie_store_mgmt.report.MediaPurchasesReport;
import retail_movie_store_mgmt.ui.DialogPane;

/**
 * FXML Controller class
 *
 * @author Ian Mburu
 */
public class ViewMediaPurchasesController implements Initializable {

    @FXML AnchorPane parentPane;
    
    @FXML TableView purchasesTable;
    @FXML TableColumn <MediaPurchase,String> idColumn;
    @FXML TableColumn <MediaPurchase,String> titleColumn;
    @FXML TableColumn <MediaPurchase,Double> priceColumn;
    @FXML TableColumn <MediaPurchase,Integer> quantityColumn;
    @FXML TableColumn <MediaPurchase,Double> totalColumn;
    @FXML TableColumn <MediaPurchase,LocalDate> dateColumn;
    
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
            HandleMediaPurchaseLogic handleMediaPurchaseLogic = BeansClass.handleMediaPurchaseLogic();
            ArrayList<MediaPurchase> thisDateEntries = handleMediaPurchaseLogic.getThisDateEntries(thisDate);
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
            HandleMediaPurchaseLogic handleMediaPurchaseLogic = BeansClass.handleMediaPurchaseLogic();
            setTable(handleMediaPurchaseLogic.getEntriesBetweenDates(startDate, todayDate));
        }
        else if(startDate == null && endDate != null){
            ArrayList<MediaPurchase> data = getData();
            if(data != null && !data.isEmpty()){
                MediaPurchase firstEntry = data.get(0);
                
                HandleMediaPurchaseLogic handleMediaPurchaseLogic = BeansClass.handleMediaPurchaseLogic();
                setTable(handleMediaPurchaseLogic.getEntriesBetweenDates(firstEntry.getDate(), todayDate));
            }
        }
        else if(startDate != null && endDate != null){
            HandleMediaPurchaseLogic handleMediaPurchaseLogic = BeansClass.handleMediaPurchaseLogic();
            setTable(handleMediaPurchaseLogic.getEntriesBetweenDates(startDate, endDate));
        }
        else{
            populateAll();
        }
    }
    
    public ArrayList<MediaPurchase> getData(){
        HandleMediaPurchaseLogic handleMediaPurchaseLogic = BeansClass.handleMediaPurchaseLogic();
        return handleMediaPurchaseLogic.getAllEntries();
    }
    
    public void setTable(ArrayList<MediaPurchase> data){
        
        //setCellFactory
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));

        //get table data
        ObservableList<MediaPurchase> allEntriesObs = FXCollections.observableArrayList(data);

        //set table data
        purchasesTable.getItems().setAll(allEntriesObs);
        
    }
    
    public void back(){
        parentPane.getScene().getWindow().hide();
        
        HandleFxmlPages handleFxmlPages = BeansClass.handleFxmlPages();
        handleFxmlPages.Home();
    }
    
    
    public void getMediaPurchasesReport(){
        HandleProfileLogic handleProfileLogic = BeansClass.handleProfileLogic();
        boolean privilege = handleProfileLogic.checkUserPrivilegeAdminManager(User.loggedin);
        if(privilege){
            Thread thread = new Thread(){
                public void run(){
                    MediaPurchasesReport mediaPurchasesReport = BeansClass.mediaPurchasesReport();
                    mediaPurchasesReport.produceReport();
                }
            };
            thread.start();
        }
        else{
            displayError("You are not allowed to access this functionality");
        }
    }
    
//    public void showReport(){
//        MediaPurchasesReport mediaPurchasesReport = BeansClass.mediaPurchasesReport();
//        mediaPurchasesReport.showReport();
//    }
//    public void selectionModel(){
//        TableView.TableViewSelectionModel <MediaPurchase> selectionModel = purchasesTable.getSelectionModel();
//        ObservableList<MediaPurchase> selectedItems = selectionModel.getSelectedItems();
//        selectedItems.addListener(new ListChangeListener<MediaPurchase>(){
//            public void onChanged(ListChangeListener.Change<? extends MediaPurchase> change){
//                ObservableList<? extends MediaPurchase> list = change.getList();
//                MediaPurchase mediaPurchase = list.get(0);
//                delField.setText(mediaPurchase.getId());
//            }
//        });
//    }
    
//    public void deletePurchase(){
//        String id = delField.getText();
//        MediaPurchase mediaPurchase = BeansClass.mediaPurchase();
//        mediaPurchase.setId(id);
//        
//        HandleMediaPurchaseLogic handleMediaPurchaseLogic = BeansClass.handleMediaPurchaseLogic();
//        boolean check = handleMediaPurchaseLogic.checkIfExists(mediaPurchase);
//        if(!check){
//            displayError("That entry does not exist");
//        }
//        else{
//            handleMediaPurchaseLogic.delete(mediaPurchase);
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
