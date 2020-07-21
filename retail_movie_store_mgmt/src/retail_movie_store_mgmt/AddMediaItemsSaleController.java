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
import retail_movie_store_mgmt.Sales.MediaAndCustomSaleEntry;
import retail_movie_store_mgmt.Sales.MediaInvoice;
import retail_movie_store_mgmt.ui.DialogPane;

/**
 * FXML Controller class
 *
 * @author Ian Mburu
 */ 
//refresh
public class AddMediaItemsSaleController implements Initializable {
    @FXML AnchorPane parentPane;
    
    @FXML TextField grossTotalField;
    
    @FXML TableView saleTable;
    @FXML TableColumn <MediaAndCustomSaleEntry,String> titleColumn;
    @FXML TableColumn <MediaAndCustomSaleEntry,Double> priceColumn;
    @FXML TableColumn <MediaAndCustomSaleEntry,Integer> numberColumn;
    @FXML TableColumn <MediaAndCustomSaleEntry,Double> subTotalColumn;
    @FXML TableColumn <MediaAndCustomSaleEntry,Double> discountColumn;
    @FXML TableColumn <MediaAndCustomSaleEntry,Double> subNetTotalColumn;
    
    static String saleId;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        saleId = AddMediaSaleLogic.saleIdC;
        refresh();
    }    
    
    public String getSaleId(){
        AddMediaSaleLogic addMediaSaleLogic = BeansClass.addMediaSaleLogic();
        return addMediaSaleLogic.getSaleId();
    }
    
    public void refresh(){
        
        AddMediaSaleLogic addMediaSaleLogic = BeansClass.addMediaSaleLogic();
        ArrayList<MediaAndCustomSaleEntry>individualSaleEntries = addMediaSaleLogic.findOneSale(saleId) ;
        
        //testing
        if(individualSaleEntries==null){
        }
        else if(individualSaleEntries.isEmpty()){
        }
        else{
            viewCurrentSale(individualSaleEntries);
            setGrossTotal(individualSaleEntries);
        }
        //
    }
    
    
    
    public void viewCurrentSale(ArrayList<MediaAndCustomSaleEntry>individualSaleEntries){
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
            ObservableList<MediaAndCustomSaleEntry> allEntriesObs = FXCollections.<MediaAndCustomSaleEntry>observableArrayList(individualSaleEntries);
        
            //set table data
            saleTable.getItems().setAll(allEntriesObs);
        }
        
    }
    
    //set gross totalCost
    public void setGrossTotal(ArrayList<MediaAndCustomSaleEntry>individualSaleEntries){
        if(!individualSaleEntries.isEmpty()){
            double grossTotal = 0;
            Iterator<MediaAndCustomSaleEntry> individualSaleEntriesIterator = individualSaleEntries.iterator();
        
            while(individualSaleEntriesIterator.hasNext()){
                MediaAndCustomSaleEntry entry = individualSaleEntriesIterator.next();
                double netCost = entry.getSub_netTotal();
                grossTotal = grossTotal+netCost;
            }
            //set total val
            HandleMainLogic instance = BeansClass.handleMainLogic();
            grossTotalField.setText(instance.convertDoubleToString(grossTotal));
        }
    }
    
    
    public void commit(){
        //call next
        //check the popup to know where to go next
        int [] pageSales = AddSalePopupPageController.pageCount;
        if(pageSales[0] == 1){
            //check whether to get invoice
            int invoice = getInvoice();
            if(invoice == 0){
                //get invoice
                String customerName = getCustomerName().trim();
                System.out.println("CustomerName: "+ customerName);
                if(customerName == null ){
                    displayError("Empty customerName");
                }
                else{
                    Thread thread = new Thread(new Runnable(){
                    @Override
                    public void run(){
                        MediaInvoice mediaInvoice = BeansClass.mediaInvoice();
                        mediaInvoice.produceInvoice(saleId,customerName);
                        }
                    });
                    thread.start();
                    back();
                }
            }
            
        }
        if(pageSales[0] == 2){
            //go to software
            softwareSalesPage();
        }
    }
    
    public void addSaleEntry(){
        hidePage();
        HandleFxmlPages instance = BeansClass.handleFxmlPages();
        instance.addMediaItemsSaleForm();
    }
    
    public void deleteEntry(){
        hidePage();
        HandleFxmlPages handleFxmlPages = BeansClass.handleFxmlPages();
        handleFxmlPages.deleteMediaSaleForm();
    }
    
    
    public void back(){
//        HandleFxmlPages handleFxmlPages =  BeansClass.handleFxmlPages();
//        handleFxmlPages.Home();
        hidePage();
    }
    
    public void softwareSalesPage(){
        HandleFxmlPages handleFxmlPages =  BeansClass.handleFxmlPages();
        handleFxmlPages.addSoftwareSale(SideMenuController.window);
        hidePage();
    }
    
    public void displayMessage(String Message){
        JOptionPane.showMessageDialog(null, Message);
    }
    
    public void displayError(String error){
        DialogPane dialogPane = BeansClass.dialogPane();
        dialogPane.displayError(parentPane.getScene().getWindow(),error);
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
    
    public void hidePage(){
        parentPane.getScene().getWindow().hide();
    }
    
    
}
