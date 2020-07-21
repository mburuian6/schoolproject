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
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import retail_movie_store_mgmt.Logic.HandlePreorderLogic;
import retail_movie_store_mgmt.preorders.Preorder;
import retail_movie_store_mgmt.report.PreorderReport;

/**
 * FXML Controller class
 *
 * @author Ian Mburu
 */
public class ViewPreordersController implements Initializable {

    /**
     * Initializes the controller class.
     */
        //view
    @FXML TableView allPreordersTable;
    @FXML TableColumn idColumn;
    @FXML TableColumn customerNameColumn;
    @FXML TableColumn orderDateColumn;
    @FXML TableColumn pickupDateColumn;
    @FXML TableColumn peripheralColumn;
    @FXML TableColumn moviesListColumn;
    @FXML TableColumn showsListColumn;
    @FXML TableColumn softwareListColumn;
    @FXML TableColumn profileColumn;
    @FXML TableColumn statusColumn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        setUpTableViewInitial();
    }    
    
    public ArrayList<Preorder> getData(){
        HandlePreorderLogic handlePreorderLogic = BeansClass.handlePreorderLogic();
        return handlePreorderLogic.getAllEntries();
    }
    
    public void setUpTableViewInitial(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        pickupDateColumn.setCellValueFactory(new PropertyValueFactory<>("pickupDate"));
        peripheralColumn.setCellValueFactory(new PropertyValueFactory<>("peripheral"));
        moviesListColumn.setCellValueFactory(new PropertyValueFactory<>("movies_list"));
        showsListColumn.setCellValueFactory(new PropertyValueFactory<>("shows_list"));
        softwareListColumn.setCellValueFactory(new PropertyValueFactory<>("software_list"));
        profileColumn.setCellValueFactory(new PropertyValueFactory<>("profile"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        ArrayList<Preorder> allEntriesList = getData();
        if(allEntriesList==null || allEntriesList.isEmpty()){
            //pass
        }
        else{
            //get table data
            ObservableList<Preorder> allEntriesObs = FXCollections.observableArrayList(allEntriesList);
        
            //set table data
            allPreordersTable.getItems().setAll(allEntriesObs);
        }
    }
    
    public void generateReport(){
        PreorderReport preorderReport = BeansClass.preorderReport();
        preorderReport.produceReport();
    }
    
    public void back(){
        allPreordersTable.getScene().getWindow().hide();
        HandleFxmlPages handleFxmlPages = BeansClass.handleFxmlPages();
        handleFxmlPages.Home();        
    }
    
}
