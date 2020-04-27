/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt;

import BeansPackage.BeansClass;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import retail_movie_store_mgmt.Logic.HandleMainLogic;
import retail_movie_store_mgmt.Sales.SoftwareInvoice;
import retail_movie_store_mgmt.Sales.SoftwareSaleEntry;
import retail_movie_store_mgmt.ui.DialogPane;

/**
 * FXML Controller class
 *
 * @author Ian Mburu
 */
//=> refresh,initialize
public class AddSoftwareSaleController implements Initializable {
    @FXML AnchorPane parentPane;
    
    @FXML TextField grossTotalField;
    
    @FXML TableView saleTable;
    @FXML TableColumn <SoftwareSaleEntry,String> titleColumn;
    @FXML TableColumn <SoftwareSaleEntry,Double> priceColumn;
    @FXML TableColumn <SoftwareSaleEntry,Integer> numberColumn;
    @FXML TableColumn <SoftwareSaleEntry,Double> subTotalColumn;
    @FXML TableColumn <SoftwareSaleEntry,Double> discountColumn;
    @FXML TableColumn <SoftwareSaleEntry,Double> subNetTotalColumn;
    
    static String saleId;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        saleId = AddSoftwareSaleLogic.saleIdC;
        refresh();
    }    
    
    public String getSaleId(){
        AddSoftwareSaleLogic addSoftwareSaleLogic = BeansClass.addSoftwareSaleLogic();
        return addSoftwareSaleLogic.getSaleId();
    }
    
    public void refresh(){
        //get these items from db;
        AddSoftwareSaleLogic addSoftwareSaleLogic = BeansClass.addSoftwareSaleLogic();
        ArrayList<SoftwareSaleEntry>individualSaleEntries  = addSoftwareSaleLogic.findOneSale(saleId) ;
        
        
        if(individualSaleEntries==null){
        }
        else if(individualSaleEntries.isEmpty()){
        }
        else{
            viewCurrentSale(individualSaleEntries);
            setGrossTotal(individualSaleEntries);
        }
    }
    
    public void viewCurrentSale(ArrayList<SoftwareSaleEntry>individualSaleEntries){
        //setCellFactory
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        subTotalColumn.setCellValueFactory(new PropertyValueFactory<>("sub_total"));
        discountColumn.setCellValueFactory(new PropertyValueFactory<>("sub_discount"));
        subNetTotalColumn.setCellValueFactory(new PropertyValueFactory<>("sub_netTotal"));
        
        if(individualSaleEntries==null){
            //pass
        }
        if(individualSaleEntries.isEmpty()){
            //pass
        }
        else{
            //get table data
            ObservableList<SoftwareSaleEntry> allEntriesObs = FXCollections.<SoftwareSaleEntry>observableArrayList(individualSaleEntries);
        
            //set table data
            saleTable.getItems().setAll(allEntriesObs);
        }
    }
    
    //set gross totalCost
    public void setGrossTotal(ArrayList<SoftwareSaleEntry>individualSaleEntries){
        if(!individualSaleEntries.isEmpty()){
            double grossTotal = 0;
            Iterator<SoftwareSaleEntry> individualSaleEntriesIterator = individualSaleEntries.iterator();
        
            while(individualSaleEntriesIterator.hasNext()){
                SoftwareSaleEntry entry = individualSaleEntriesIterator.next();
                double netCost = entry.getSub_netTotal();
                grossTotal = grossTotal+netCost;
            }
            //set total val
            HandleMainLogic instance = BeansClass.handleMainLogic();
            grossTotalField.setText(instance.convertDoubleToString(grossTotal));
        }
    }
    
    public void addSaleEntry(){
        hidePage();
        HandleFxmlPages instance = BeansClass.handleFxmlPages();
        instance.addSoftwareSaleForm();
        
    }
    
    public void deleteEntry(){
        hidePage();
        HandleFxmlPages handleFxmlPages = BeansClass.handleFxmlPages();
        handleFxmlPages.deleteSoftwareSaleForm();
    }
    
    public void commit(){
        //call next
        int invoice = getInvoice();
            if(invoice == 0){
                //get invoice
                String customerName = getCustomerName();
                if(customerName == null ){
                    displayError("Empty customerName");
                }
                else{
                    Thread thread = new Thread(new Runnable(){
                        @Override
                        public void run(){
                            SoftwareInvoice softwareInvoice = BeansClass.softwareInvoice();
                            softwareInvoice.produceInvoice(saleId,customerName);
                        }
                    });
                    thread.start();
                    back();
                }
            }
            
        
    }
    
    public void back(){
//        HandleFxmlPages handleFxmlPages =  BeansClass.handleFxmlPages();
//        handleFxmlPages.Home();
        hidePage();
    }
    
    public void displayError(String error){
        DialogPane dialogPane = BeansClass.dialogPane();
        dialogPane.displayError(parentPane.getScene().getWindow(),error);
    }
    
    public void displayMessage(String Message){
        JOptionPane.showMessageDialog(null, Message);
    }
    
    public void hidePage(){
        parentPane.getScene().getWindow().hide();
    }
    
    public int getInvoice(){
        DialogPane dialog = BeansClass.dialogPane();
        int confirmation = dialog.getConfirmation("Do you want to get an invoice for this sale?");
        return confirmation;
    }
    
    public String getCustomerName(){
        DialogPane dialog = BeansClass.dialogPane();
        String customerName = dialog.getText("Please enter customer name");
        return customerName;
    }
    
}
