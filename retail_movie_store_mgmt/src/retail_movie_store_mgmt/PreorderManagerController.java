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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import retail_movie_store_mgmt.Logic.HandlePreorderLogic;
import retail_movie_store_mgmt.commonUtil.DateTime;
import retail_movie_store_mgmt.preorders.Preorder;
import retail_movie_store_mgmt.ui.DialogPane;

/**
 * FXML Controller class
 *
 * @author Ian Mburu
 */
public class PreorderManagerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML AnchorPane anchorPane;
    
    @FXML ListView allUpcomingPreordersListView;
    @FXML ListView allPastPreordersListView;
    
    @FXML TextField idField;
    @FXML TextField customerNameField;
    @FXML DatePicker pickupDatePicker;
    @FXML TextArea peripheralField;
    @FXML TextArea moviesField;
    @FXML TextArea showsField;
    @FXML TextArea softwareField;
    @FXML CheckBox peripheralBox;
    @FXML ComboBox statusBox;
    
    String profile = User.loggedin.getUsername();
    LocalDate todayDate;
    Preorder preorder;
    String[] statusArr = {"pending","done"};
    ArrayList<Preorder> allEntriesList;
    String alarm = "!";
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //intial details
        DateTime dateTime = BeansClass.dateTime();
        LocalDate todayDate = dateTime.getTodayDate();
        this.todayDate = todayDate;
        
        
        setUpListView();
        
        
        ObservableList<String> statusObsList = FXCollections.observableArrayList(statusArr);
        statusBox.setItems(statusObsList);
        statusBox.setValue(statusArr[0]);
        
        //listeners
        //listview click events
        MultipleSelectionModel<String> beforeListViewModel = allUpcomingPreordersListView.getSelectionModel();// Get the list view selection model.
        beforeListViewModel.selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> changed,String oldVal, String newVal) {
                // Display the selection: newVal
                setItem(newVal);
            }
        });
        
        MultipleSelectionModel<String> afterListViewModel = allPastPreordersListView.getSelectionModel();// Get the list view selection model.
        afterListViewModel.selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> changed,String oldVal, String newVal) {
                // Display the selection: newVal
                setItem(newVal);
            }
        });
        
        peripheralBox.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                if(peripheralBox.isSelected()){
                    peripheralField.setEditable(true);
                }
                else{
                    peripheralField.setEditable(false);
                    peripheralField.setText("");
                }
            }
        });
        
    }    
    
    public ArrayList<Preorder> getData(){
        HandlePreorderLogic handlePreorderLogic = BeansClass.handlePreorderLogic();
        return handlePreorderLogic.getAllEntries();
    }
    
    public void setUpListView(){
        allEntriesList = getData();
        ArrayList<String> allEntriesNamesUpcoming = new ArrayList();
        ArrayList<String> allEntriesNamesDone = new ArrayList();
        HandlePreorderLogic handlePreorderLogic = BeansClass.handlePreorderLogic();
        
        for(Preorder preorder: allEntriesList){
            boolean checkStatus = handlePreorderLogic.checkStatusDone(preorder.getId());
            if(checkStatus){
                //done
                allEntriesNamesDone.add(preorder.getId());
            }
            else{
                //not done
                boolean checkTime = handlePreorderLogic.checkIfPickUpDateIsPassed(preorder);
                if(!checkTime){
                    allEntriesNamesUpcoming.add(preorder.getId());
                }
                else{
                    allEntriesNamesUpcoming.add(alarm+preorder.getId());
                }
            }
        }
        
        ObservableList allEntriesNamesUpcomingObsList = FXCollections.observableArrayList(allEntriesNamesUpcoming);
        ObservableList allEntriesNamesDoneObsList = FXCollections.observableArrayList(allEntriesNamesDone);
        
        allUpcomingPreordersListView.setItems(allEntriesNamesUpcomingObsList);
        allPastPreordersListView.setItems(allEntriesNamesDoneObsList);
    }
    
    
    public Preorder findOne(String id){
        
        //preprocess id
        if(id.contains(alarm)){
            id = id.substring(1);
        }
        
        //search
        for(Preorder preorder: allEntriesList){
            if(id.equals(preorder.getId())){
                return preorder;
            }
        }
        return null;
    }
    
    public void setItem(String id){
        HandlePreorderLogic handlePreorderLogic = BeansClass.handlePreorderLogic();
//        preorder = handlePreorderLogic.findOne(id);
        preorder = findOne(id);
        if(preorder == null){
            //pass
        }
        else{
            idField.setText(preorder.getId());
            customerNameField.setText(preorder.getCustomer_name());
            pickupDatePicker.setValue(preorder.getPickupDate());
            moviesField.setText(preorder.getMovies_list());
            showsField.setText(preorder.getShows_list());
            softwareField.setText(preorder.getSoftware_list());
            peripheralField.setText(preorder.getPeripheral());
            statusBox.setValue(preorder.getStatus());
        }
    }
    
    public void refresh(){
        setUpListView();
        clearAllFields();
        idField.requestFocus();
    }
    
    public void clearAllFields(){
        idField.setText("");
        customerNameField.setText("");
        pickupDatePicker.setValue(null);
        peripheralField.setText("");
        moviesField.setText("");
        showsField.setText("");
        softwareField.setText("");
        statusBox.setValue(statusArr[0]);
    }
    
    public void submitNewEntry(){
        String customerName = customerNameField.getText();
        LocalDate pickupDate = pickupDatePicker.getValue();
        String peripheral = peripheralField.getText();
        String movies = moviesField.getText();
        String shows =  showsField.getText();
        String software = softwareField.getText();
        String status = statusBox.getValue().toString();
        
        if(customerName== null || pickupDate==null){
            //null values
            displayError("'CustomerName' and 'pickupDate' cannot be empty");
        }
        else if(pickupDate.isBefore(todayDate)){
            displayError("'pickupDate' cannot be before today");
        }
        else{
            HandlePreorderLogic handlePreorderLogic = BeansClass.handlePreorderLogic();
            String id = handlePreorderLogic.generatePreorderId(customerName);
            
            preorder = BeansClass.preorder();
            preorder = handlePreorderLogic.appendPreorderDetails(preorder, id, customerName, todayDate, pickupDate, movies, shows, software,peripheral,status);
            handlePreorderLogic.insertPreorder(preorder);
            
            refresh();
        }
    }
    
    public void updateEntry(){
        String id = idField.getText();
        String customerName = customerNameField.getText();
        LocalDate pickupDate = pickupDatePicker.getValue();
        String peripheral = peripheralField.getText();
        String movies = moviesField.getText();
        String shows =  showsField.getText();
        String software = softwareField.getText();
        String status = statusBox.getValue().toString();
        
        
        if(customerName== null || pickupDate==null){
            //null values
            displayError("Please click an entry in any of the lists so as to edit them");
        }
        else{
            HandlePreorderLogic handlePreorderLogic = BeansClass.handlePreorderLogic();
            preorder = handlePreorderLogic.findOne(id);
            preorder = handlePreorderLogic.appendPreorderDetails(preorder, id, customerName, todayDate, pickupDate, peripheral, movies, shows, software, status);
            
            handlePreorderLogic.updatePreorder(preorder);
            refresh();
        }
    }
    
    public void deleteEntry(){
        String id = idField.getText();
        String customerName = customerNameField.getText();
        LocalDate pickupDate = pickupDatePicker.getValue();
        if(customerName== null || pickupDate==null){
            //null values
            displayError("Please click an entry in any of the lists so as to edit them");
        }
        else{
            HandlePreorderLogic handlePreorderLogic = BeansClass.handlePreorderLogic();
            preorder = handlePreorderLogic.findOne(id);
            handlePreorderLogic.deletePreorder(preorder);
            refresh();
        }
    }
    
    public void displayInfo(String message){
        DialogPane dialogPane = BeansClass.dialogPane();
        dialogPane.displayInfo(message);
    }
    
    public void displayError(String error){
        DialogPane dialogPane = BeansClass.dialogPane();
        dialogPane.displayError(error);
    }
    
    public void back(){
        anchorPane.getScene().getWindow().hide();
        
        HandleFxmlPages handleFxmlPages = BeansClass.handleFxmlPages();
        handleFxmlPages.Home();
        
    }
}
