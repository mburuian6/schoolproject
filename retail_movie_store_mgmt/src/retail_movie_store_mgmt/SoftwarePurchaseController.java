/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt;

import BeansPackage.BeansClass;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import retail_movie_store_mgmt.Logic.HandleSoftwarePurchaseLogic;
import retail_movie_store_mgmt.purchases.software.SoftwarePurchase;
import retail_movie_store_mgmt.ui.DialogPane;

/**
 * FXML Controller class
 *
 * @author Ian Mburu
 */
public class SoftwarePurchaseController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML TableView todayPurchasesTable;
    @FXML TableColumn <SoftwarePurchase,String> idColumn;
    @FXML TableColumn <SoftwarePurchase,String> titleColumn;
    @FXML TableColumn <SoftwarePurchase,Double> totalColumn;
    
    @FXML ComboBox titlesBox;
    @FXML TextField quantityField;
    @FXML TextField priceField;
    
    @FXML TextField delField;
    @FXML ListView currentItemsView;
    
    ArrayList<String> ids = new ArrayList() ;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setTodayEntriesTable();
        setAddTitles();
        ArrayList<String> ids = new ArrayList();
        this.ids = ids;   
        
        selectionModel();
    }    
    
    public ArrayList<SoftwarePurchase> getAllData(){
        HandleSoftwarePurchaseLogic handleSoftwarePurchaseLogic = BeansClass.handleSoftwarePurchaseLogic();
        return handleSoftwarePurchaseLogic.getAllEntries();
    }
    
    public ArrayList<SoftwarePurchase> getAllTodaysData(){
        HandleSoftwarePurchaseLogic handleSoftwarePurchaseLogic = BeansClass.handleSoftwarePurchaseLogic();
        return handleSoftwarePurchaseLogic.getTodayEntries();
    }
    
    public void setTodayEntriesTable(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        
        if(getAllData()==null){
            //pass
        }
        if(getAllData().isEmpty()){
            //pass
        }
        else{
            //get table data
            ObservableList<SoftwarePurchase> allEntriesObs = FXCollections.observableArrayList(getAllTodaysData());
        
            //set table data
            todayPurchasesTable.getItems().setAll(allEntriesObs);
        }
    }
    
    public void setAddTitles(){
        HandleSoftwarePurchaseLogic handleSoftwarePurchaseLogic = BeansClass.handleSoftwarePurchaseLogic();
        ArrayList<String> allEntriesList = handleSoftwarePurchaseLogic.getAllSoftwareTitles();
        ObservableList<String> allEntriesObs = FXCollections.observableArrayList(allEntriesList);
        if(allEntriesList!= null){
            titlesBox.setItems(allEntriesObs);
        }
    }
    
    public void addPurchase(){
        String title = titlesBox.getValue().toString();
        String quantity = quantityField.getText();
        String price = priceField.getText();
        
        //validate input
        String[] params = {quantity,price};
        String [] labels = {"Quantity","Price"};
        HandleSoftwarePurchaseLogic handleSoftwarePurchaseLogic = BeansClass.handleSoftwarePurchaseLogic();
        String whereFound = handleSoftwarePurchaseLogic.checkIntInput(params, labels);
        
        if(whereFound != null){
            displayError(whereFound+" field cannot contain alphabetic characters. It needs to be a valid positive number");
        }
        else if(title==null||quantity==null||price==null){
            displayError("Please complete all the fields before proceeding");
        }
        else{
            //proceed to insertion
            
            SoftwarePurchase softwarePurchase = BeansClass.softwarePurchase();
            String id = handleSoftwarePurchaseLogic.generatePurchaseId(title);
            softwarePurchase = handleSoftwarePurchaseLogic.appendPurchaseDetails(softwarePurchase, id, title, quantity, price);
            String insert = handleSoftwarePurchaseLogic.insertToDb(softwarePurchase);
            
            if(insert.contains("item already exists")){
                displayError("Item already exists");
            }
            else if (insert.contains("Successful")){
                //displayInfo("Successful insertion");
                ids.add(softwarePurchase.getId());
            }
            
            refresh();
        }
    }
    
    public void selectionModel(){
        TableView.TableViewSelectionModel <SoftwarePurchase> selectionModel = todayPurchasesTable.getSelectionModel();
        ObservableList<SoftwarePurchase> selectedItems = selectionModel.getSelectedItems();
        selectedItems.addListener(new ListChangeListener<SoftwarePurchase>(){
            public void onChanged(ListChangeListener.Change<? extends SoftwarePurchase> change){
                ObservableList<? extends SoftwarePurchase> list = change.getList();
                SoftwarePurchase softwarePurchase = list.get(0);
                delField.setText(softwarePurchase.getId());
            }
        });
    }
    
    public void deletePurchase(){
        String id = delField.getText();
        SoftwarePurchase softwarePurchase = BeansClass.softwarePurchase();
        softwarePurchase.setId(id);
        
        HandleSoftwarePurchaseLogic handleSoftwarePurchaseLogic = BeansClass.handleSoftwarePurchaseLogic();
        boolean check = handleSoftwarePurchaseLogic.checkIfExists(softwarePurchase);
        if(!check){
            displayError("That entry does not exist");
        }
        else{
            boolean del = handleSoftwarePurchaseLogic.delete(softwarePurchase);
            
            if(del){
                ids.remove(id);
                //displayInfo("Successful Deletion!")
            }
            else{
                displayError("Error!Please contact support for help or check your connection");
            }
            refresh();
        }
    }
    
    public void setListView(){
        ObservableList<String> currentEntriesObs = FXCollections.observableArrayList(ids);
        currentItemsView.setItems(currentEntriesObs);
    }
    
    public void refresh(){
        setTodayEntriesTable();
        setListView();
        clearFields();
    }
    
    
    public void clearFields(){
        quantityField.setText("");
        priceField.setText("");
        delField.setText("");
    }
    
    public void displayInfo(String message){
        DialogPane dp = BeansClass.dialogPane();
        dp.displayInfo(message);
    }
    
    public void displayError(String message){
        DialogPane dp = BeansClass.dialogPane();
        dp.displayError(message);
    }
    
    public void back(){
        todayPurchasesTable.getScene().getWindow().hide();
        
        HandleFxmlPages handleFxmlPages = BeansClass.handleFxmlPages();
        handleFxmlPages.Home();        
    }
    
}
