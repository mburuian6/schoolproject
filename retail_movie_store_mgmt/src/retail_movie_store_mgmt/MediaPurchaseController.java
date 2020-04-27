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
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import retail_movie_store_mgmt.Logic.HandleMediaPurchaseLogic;
import retail_movie_store_mgmt.purchases.media.MediaPurchase;
import retail_movie_store_mgmt.ui.DialogPane;

/**
 * FXML Controller class
 *
 * @author Ian Mburu
 */
public class MediaPurchaseController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML TableView todayPurchasesTable;
    @FXML TableColumn <MediaPurchase,String> idColumn;
    @FXML TableColumn <MediaPurchase,String> titleColumn;
    @FXML TableColumn <MediaPurchase,Double> totalColumn;
    
    @FXML ComboBox titlesBox;
    @FXML TextField quantityField;
    @FXML TextField priceField;
    
    @FXML TextField delField;
    @FXML ListView currentItemsView;
    
    ArrayList<String> ids = new ArrayList() ;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //set initial values
        setTodayEntriesTable();
        setAddTitles();
        ArrayList<String> ids = new ArrayList();
        this.ids = ids;
        
        //listeners
        //table
        selectionModel();
    }    
    
    public ArrayList<MediaPurchase> getAllData(){
        HandleMediaPurchaseLogic handleMediaPurchaseLogic = BeansClass.handleMediaPurchaseLogic();
        return handleMediaPurchaseLogic.getAllEntries();
    }
    
    public ArrayList<MediaPurchase> getAllTodaysData(){
        HandleMediaPurchaseLogic handleMediaPurchaseLogic = BeansClass.handleMediaPurchaseLogic();
        return handleMediaPurchaseLogic.getTodayEntries();
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
            ObservableList<MediaPurchase> allEntriesObs = FXCollections.observableArrayList(getAllTodaysData());
        
            //set table data
            todayPurchasesTable.getItems().setAll(allEntriesObs);
        }
    }
    
    public void setAddTitles(){
        HandleMediaPurchaseLogic handleMediaPurchaseLogic = BeansClass.handleMediaPurchaseLogic();
        ArrayList<String> allEntriesList = handleMediaPurchaseLogic.getAllMediaTitles();
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
        HandleMediaPurchaseLogic handleMediaPurchaseLogic = BeansClass.handleMediaPurchaseLogic();
        String whereFound = handleMediaPurchaseLogic.checkIntInput(params, labels);
        
        if(title==null||quantity==null||price==null){
            displayError("Please complete all the fields before proceeding");
        }
        else if(whereFound != null){
            displayError(whereFound+" field cannot contain alphabetic characters. It needs to be a valid positive number");
        }
        else{
            //proceed to insertion
            
            MediaPurchase mediaPurchase = BeansClass.mediaPurchase();
            String id = handleMediaPurchaseLogic.generatePurchaseId(title);
            mediaPurchase = handleMediaPurchaseLogic.appendPurchaseDetails(mediaPurchase, id, title, quantity, price);
            boolean insert = handleMediaPurchaseLogic.insertToDb(mediaPurchase);
            if(insert)ids.add(mediaPurchase.getId());
            
            refresh();
        }
    }
    
    public void selectionModel(){
        TableViewSelectionModel <MediaPurchase> selectionModel = todayPurchasesTable.getSelectionModel();
        ObservableList<MediaPurchase> selectedItems = selectionModel.getSelectedItems();
        selectedItems.addListener(new ListChangeListener<MediaPurchase>(){
            public void onChanged(Change<? extends MediaPurchase> change){
                ObservableList<? extends MediaPurchase> list = change.getList();
                MediaPurchase mediaPurchase = list.get(0);
                delField.setText(mediaPurchase.getId());
            }
        });
    }
    
    public void deletePurchase(){
        String id = delField.getText();
        MediaPurchase mediaPurchase = BeansClass.mediaPurchase();
        mediaPurchase.setId(id);
        
        HandleMediaPurchaseLogic handleMediaPurchaseLogic = BeansClass.handleMediaPurchaseLogic();
        boolean check = handleMediaPurchaseLogic.checkIfExists(mediaPurchase);
        if(!check){
            displayError("That entry does not exist");
        }
        else{
            boolean del = handleMediaPurchaseLogic.delete(mediaPurchase);
            if(del){
                boolean remove = ids.remove(id);
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
