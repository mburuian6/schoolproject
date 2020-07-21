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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import retail_movie_store_mgmt.Logic.HandleMainLogic;
import retail_movie_store_mgmt.Logic.SoftwareLogic;
import retail_movie_store_mgmt.product.Software;
import retail_movie_store_mgmt.ui.DialogPane;

/**
 * FXML Controller class
 *
 * @author Ian Mburu
 */
public class SoftwareInventoryController implements Initializable {

    /**
     * Initializes the controller class.
     * vars:  title,price,price on cd, limit,description
     */
    //Enter software
    @FXML TextField titleField;
    @FXML TextField priceField;
    @FXML TextField priceOpticalField;
    @FXML TextField limitField;
    @FXML TextArea descField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        viewSoftwareInventory();
        setUpAllSoftware();
    }    
    
    //INSERT NEW SOFTWARE
    public void enterSoftware(){
        String title = titleField.getText();
        String price = priceField.getText();
        String priceonOpticalDisk = priceOpticalField.getText();
        String limit  = limitField.getText();
        String desc = descField.getText();
        
        //verify input
        String[] arrInput = {price,priceonOpticalDisk,limit};
        String[] labels = {"Price","Price on optical disk","Installation Limit"};
        
        SoftwareLogic softwareLogic = BeansClass.softwareLogic();
        String whereFound = softwareLogic.verifyNumInput(arrInput,labels);
        if(whereFound!=null){
            //wrong input
            JOptionPane.showMessageDialog(null, whereFound+" field cannot contain alphabetic characters or negative number. It needs to be a valid amount");
        }else{
            if(title!= null || price!=null){
                //proceed to insert to db
                SoftwareLogic software = BeansClass.softwareLogic();
                software.insertSoftwareToDb(title, price, priceonOpticalDisk, limit, desc);

                //refresh data in app
                refresh();

                //clear fields
                titleField.setText("");
                priceField.setText("");
                priceOpticalField.setText("");
                limitField.setText("");
                descField.setText("");
            }
            else{
                displayError("Title and price cannot be empty");
            }
        }
    }
    
    //MODIFY SOFTWARE DETAILS
    @FXML ComboBox titleFieldModify;
    @FXML TextField priceFieldModify;
    @FXML TextField priceOpticalFieldModify;
    @FXML TextField limitFieldModify;
    @FXML TextArea descFieldModify;
    
    public void setUpAllSoftware(){
        SoftwareLogic softwareLogic = BeansClass.softwareLogic();
        ArrayList<Software> softwareList = softwareLogic.fetchAllSoftwareObjects();
        ArrayList<String>softwareNames = new ArrayList();
        
        //create a string version of the arrayList of softwares
        for(Software software: softwareList){
            softwareNames.add(software.getTitle());
        }
        
        ObservableList<String> products = FXCollections.observableArrayList(softwareNames);
        titleFieldModify.setItems(products);
        
        //
        titleFieldModify.setOnAction(new EventHandler<ActionEvent>(){
                public void handle(ActionEvent evt){                    
                    clearAllModifyTextFields();
                    fetchSoftwareDetails();
                };
        });
        
        
    }
    
    public void modifySoftware(){
        //populate         
        String title= titleFieldModify.getValue().toString();
        String price = priceFieldModify.getText();
        String priceOnOpticalDisk = priceOpticalFieldModify.getText();
        String installationLimit = limitFieldModify.getText();
        String desc = descFieldModify.getText();
        
        //verify input
        String[] arrInput = {price,priceOnOpticalDisk,installationLimit};
        String[] labels = {"Price","Price on optical disk","Installation Limit"};
        
        SoftwareLogic softwareLogic = BeansClass.softwareLogic();
        String whereFound = softwareLogic.verifyNumInput(arrInput,labels);
        if(whereFound!=null){
            //wrong input
            JOptionPane.showMessageDialog(null, whereFound+" field cannot contain alphabetic characters. It needs to be a valid number");
        }else{
            //proceed to update to db
            Software software = BeansClass.software(title);
            software = softwareLogic.appendProductDetails(software,price,priceOnOpticalDisk,installationLimit,desc);
            softwareLogic.updateSoftwareInDatabase(software); 
            
            //refresh data
            refresh();
        }
    }
    
    public void fetchSoftwareDetails(){
        String title= titleFieldModify.getValue().toString();
        if(title!= null){
            //fetch the actual data of the software obj a
            SoftwareLogic softwareLogic = BeansClass.softwareLogic();
            Software software = softwareLogic.fetchOneSoftware(title);

            //convert 'double' and 'int' properties to string
            HandleMainLogic instance = BeansClass.handleMainLogic();
            String strPrice = instance.convertDoubleToString(software.getPrice());
            String strPriceOptical = instance.convertDoubleToString(software.getPriceOnOpticalDisk());
            String strInstallationLimit = instance.convertIntToString(software.getInstallationLimit());

            //display
            priceFieldModify.setText(strPrice);
            priceOpticalFieldModify.setText(strPriceOptical);
            limitFieldModify.setText(strInstallationLimit);
            descFieldModify.setText(software.getDesc());
        }
        
        
        
    }
    
    public void refresh(){
        //refresh data from db into the relevant containers
        setUpAllSoftware();
        viewSoftwareInventory();
        
        clearAllModifyTextFields();
    }
    
    public void clearAllModifyTextFields(){
        priceFieldModify.setText("");
        priceOpticalFieldModify.setText("");
        limitFieldModify.setText("");
        descFieldModify.setText("");
    }
    
    public void deleteSoftware(){
        String title= titleFieldModify.getValue().toString();
        
        SoftwareLogic softwareLogic = BeansClass.softwareLogic();
        softwareLogic.deleteSoftware(title);
        
        //refresh data
        refresh();
    }
    
    
    
    //VIEW AVAILABLE SOFTWARES
    @FXML TableView softwareTable;
    @FXML TableColumn <Software,String> titleColumn;
    @FXML TableColumn <Software,Double> priceColumn;
    @FXML TableColumn <Software,Double> priceOpticalColumn;
    @FXML TableColumn <Software,Integer> installationLimitColumn;
    @FXML TableColumn <Software,String>descriptionColumn;
    public void viewSoftwareInventory(){
        //set placeholder
        softwareTable.setPlaceholder(new Label("No products to display"));
        
        //setCellFactory
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceOpticalColumn.setCellValueFactory(new PropertyValueFactory<>("price_optical_disk"));
        installationLimitColumn.setCellValueFactory(new PropertyValueFactory<>("installationLimit"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("desc"));

        //get table data
        SoftwareLogic softwareLogic = BeansClass.softwareLogic();
        ArrayList<Software> softwareList = softwareLogic.fetchAllSoftwareObjects();
        ObservableList<Software> allSoftwares = FXCollections.<Software>observableArrayList(softwareList);
        
        //set table data
        softwareTable.getItems().setAll(allSoftwares);
        
    }
        
    public int receiveConfirmationMethod(String Message){
        int response = JOptionPane.showConfirmDialog(null, Message);
        return response;
    }
    
    public void displayMessage(String message){
        DialogPane dialog = BeansClass.dialogPane();
        dialog.displayInfo(message);
    }
    
    public void displayError(String error){
        DialogPane dialog = BeansClass.dialogPane();
        dialog.displayError(error);
    }
    
    public void goBack(){
      titleField.getScene().getWindow().hide();
        
      HandleMainLogic instance = BeansClass.handleMainLogic();
      instance.callHome();
    }
}
