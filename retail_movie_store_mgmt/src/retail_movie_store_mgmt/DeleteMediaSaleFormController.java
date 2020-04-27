/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt;

import retail_movie_store_mgmt.Logic.HandleMainLogic;
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
import retail_movie_store_mgmt.Sales.MediaAndCustomSaleEntry;

/**
 * FXML Controller class
 *
 * @author Ian Mburu
 */
//getsaleentries,delete
public class DeleteMediaSaleFormController implements Initializable {
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
     * @param url
     * @param rb
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
        return AddMediaSaleLogic.saleIdC;
    }
    public ArrayList<MediaAndCustomSaleEntry> getSale(){
        // get items from db
        AddMediaSaleLogic addMediaSaleLogic = BeansClass.addMediaSaleLogic();
        ArrayList<MediaAndCustomSaleEntry>saleItems = addMediaSaleLogic.findOneSale(getSaleId());
        return saleItems;
    }
    
    public void setAllItemsInComboBox(){
        //reference the data
        ArrayList<MediaAndCustomSaleEntry>individualSaleEntries = getSale();
        
        if(individualSaleEntries==null){
            //pass
        }
        else{
            //filter titles and put in combobox 
            ArrayList<String> titles = new ArrayList();
            if(!individualSaleEntries.isEmpty()){
                for(MediaAndCustomSaleEntry entry: individualSaleEntries){
                    String title = entry.getTitle();
                    titles.add(title);
                }   
            }
            ObservableList<String> entries = FXCollections.observableArrayList(titles);
            allEntriesComboBox.setItems(entries);
        }
    }
    
    public void fetchProductDetails(){
        String entry = allEntriesComboBox.getValue().toString();
        
        //get data from logic class
        ArrayList<MediaAndCustomSaleEntry>individualSaleEntries =getSale() ;
        
        for(MediaAndCustomSaleEntry saleEntry: individualSaleEntries){
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
        AddMediaSaleLogic addMediaSaleLogic = BeansClass.addMediaSaleLogic();
        ArrayList<MediaAndCustomSaleEntry>individualSaleEntries = getSale();
        
        MediaAndCustomSaleEntry mediaAndCustomSaleEntry =individualSaleEntries.remove(index);
        
       
        //update db
         addMediaSaleLogic.deleteEntry(mediaAndCustomSaleEntry);
        
        //call the recipient class
        HandleFxmlPages handleFxmlPages = BeansClass.handleFxmlPages();
        handleFxmlPages.addMediaItemsSale();
        hidePage();
    }
    
    public void cancel(){
        hidePage();
    }
    
    public void hidePage(){
        parentPane.getScene().getWindow().hide();
    }
    
}
