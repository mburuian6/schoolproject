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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import retail_movie_store_mgmt.Logic.HandleMainLogic;
import retail_movie_store_mgmt.Sales.SoftwareSaleEntry;

/**
 * FXML Controller class
 *
 * @author Ian Mburu
 */

//=>getSaleEntries,delete
public class DeleteSoftwareSaleFormController implements Initializable {
    @FXML AnchorPane parentPane;
        
    //editable
    @FXML ComboBox allEntriesComboBox;
    //uneditable
    @FXML TextField amountSoldField;
    @FXML TextField priceSoldField;
    //bottom
    //uneditable
    @FXML TextField discountedCostField;
    @FXML TextField totalCostField;
    @FXML TextField netCostField;
    
    int index;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setAllItemsInComboBox();
        allEntriesComboBox.setOnAction(new EventHandler<ActionEvent>(){
                public void handle(ActionEvent evt){
                    fetchProductDetails();
                }
        });
    }    
    
    public String getSaleId(){
        return AddSoftwareSaleLogic.saleIdC;
    }
    
    public ArrayList<SoftwareSaleEntry> getSaleEntries(){
        // get items from db
        AddSoftwareSaleLogic addSoftwareSaleLogic = BeansClass.addSoftwareSaleLogic();
        ArrayList<SoftwareSaleEntry>saleItems = addSoftwareSaleLogic.findOneSale(getSaleId());
        return saleItems;
    }
    
    public void setAllItemsInComboBox(){
        //reference the data
        ArrayList<SoftwareSaleEntry>individualSaleEntries = getSaleEntries();
        
        if(individualSaleEntries==null){
            //pass
        }
        else{
            //filter titles and put in combobox 
            ArrayList<String> titles = new ArrayList();
            if(!individualSaleEntries.isEmpty()){
                for(SoftwareSaleEntry entry: individualSaleEntries){
                    String title = entry.getTitle();
                    titles.add(title);
                }   
            }
            else{
            }
            ObservableList<String> entries = FXCollections.observableArrayList(titles);
            allEntriesComboBox.setItems(entries);
        }
        
    }
    
    public void fetchProductDetails(){
        String entry = allEntriesComboBox.getValue().toString();
        
        //get data from logic class
        ArrayList<SoftwareSaleEntry>individualSaleEntries = getSaleEntries() ;
        
        for(SoftwareSaleEntry saleEntry: individualSaleEntries){
            if(entry.equals(saleEntry.getTitle())){
                //show details
                HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
                priceSoldField.setText(handleMainLogic.convertDoubleToString(saleEntry.getPrice()));
                amountSoldField.setText(handleMainLogic.convertIntToString(saleEntry.getQuantity()));
                discountedCostField.setText(handleMainLogic.convertDoubleToString(saleEntry.getSub_discount()));
                totalCostField.setText(handleMainLogic.convertDoubleToString(saleEntry.getSub_total()));
                netCostField.setText(handleMainLogic.convertDoubleToString(saleEntry.getSub_netTotal()));
                index = individualSaleEntries.indexOf(saleEntry);
            }
        }
    }
    
    
    public void delete(){
        //delete selected product from sale
        AddSoftwareSaleLogic addSoftwareSaleLogic = BeansClass.addSoftwareSaleLogic();
        ArrayList<SoftwareSaleEntry>individualSaleEntries = getSaleEntries();
        
        SoftwareSaleEntry softwareSaleEntry = individualSaleEntries.remove(index);
        
        // update db
        addSoftwareSaleLogic.deleteEntry(softwareSaleEntry);
        
        //call the recipient class - invoked to read items again
        HandleFxmlPages handleFxmlPages = BeansClass.handleFxmlPages();
        handleFxmlPages.addSoftwareSale();
        hidePage();
    }
    
    public void cancel(){
        hidePage();
    }
    
    public void hidePage(){
        parentPane.getScene().getWindow().hide();
    }
}
