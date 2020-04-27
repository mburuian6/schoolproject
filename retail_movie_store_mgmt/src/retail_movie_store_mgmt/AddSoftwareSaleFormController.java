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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import retail_movie_store_mgmt.Logic.HandleMainLogic;
import retail_movie_store_mgmt.Logic.SoftwareLogic;
import retail_movie_store_mgmt.Sales.SoftwareSaleEntry;
import retail_movie_store_mgmt.commonUtil.Calculator;
import retail_movie_store_mgmt.product.Software;
import retail_movie_store_mgmt.ui.DialogPane;

/**
 * FXML Controller class
 *
 * @author Ian Mburu
 */

//=> setsaledata
public class AddSoftwareSaleFormController implements Initializable {
    @FXML AnchorPane parentPane;
    @FXML Label numberLabel;
    @FXML Label discountLabel;
    
    //editable
    @FXML ComboBox allSoftwareComboBox;
    @FXML TextField amountSoldField;
    @FXML CheckBox choiceBox;
    //uneditable
    @FXML TextField installationLimitField;
    @FXML TextField priceSoldField;
    @FXML TextArea descriptionSoldField;
    //bottom
    //editable
    @FXML TextField discountedCostField;
    //uneditable
    @FXML TextField totalCostField;
    @FXML TextField netCostField;
    
    Software software;
    double totalCostAssign;
    double discountAssign;
    double netCostAssign;
    ArrayList<SoftwareSaleEntry> individualSaleEntries = new ArrayList(); 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setAllItemsInComboBox();
        
        //listen for any changes and attach details
        allSoftwareComboBox.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent evt){
                fetchProductDetails();
                calculate();
            }
        });
        
        amountSoldField.textProperty().addListener(new ChangeListener<String>(){
            public void changed(final ObservableValue<? extends String> ov, final String oldVal,final String newVal){
                //calculate
                calculate();
            }
        });
        
        discountedCostField.textProperty().addListener(new ChangeListener<String>(){
            public void changed(final ObservableValue<? extends String> ov, final String oldVal,final String newVal){
                
                //calculate
                calculate();
            }
        });
        
        //listen for any changes and attach details
        //listen for choicebox to set price
        choiceBox.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent evt){
                if(choiceBox.isSelected()){
                    HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
                    double price = checkChoiceBoxForPrice(software);
                    String strPrice = handleMainLogic.convertDoubleToString(price);
                    priceSoldField.setText(strPrice);

                    calculate();
                }
                else{
                    HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
                    double price = checkChoiceBoxForPrice(software);
                    String strPrice = handleMainLogic.convertDoubleToString(price);
                    priceSoldField.setText(strPrice);
                    calculate();
                }
            }
        });
            
        //listen for any changes and attach details
        amountSoldField.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent evt){
                calculate();
            }
        });
        
        discountedCostField.setOnAction(new EventHandler<ActionEvent>(){
                public void handle(ActionEvent evt){
                    calculate();
                }
        });
        
        amountSoldField.textProperty().addListener(new ChangeListener<String>(){
            public void changed(final ObservableValue<? extends String> ov, final String oldVal,final String newVal){
                //calculate
                calculate();
            }
        });
        
        discountedCostField.textProperty().addListener(new ChangeListener<String>(){
            public void changed(final ObservableValue<? extends String> ov, final String oldVal,final String newVal){
                
                //calculate
                calculate();
            }
        });
    }    
    
    public void calculate(){
        //adjust total and net cost
                    boolean valid = validateInputForTotalCost();
                    HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
                    
                    if(valid){
                        String strNumberOfItems = amountSoldField.getText();
                        String strPriceOfProduct = priceSoldField.getText();
                        
                        
                        double priceOfProduct = handleMainLogic.convertStrToDouble(strPriceOfProduct);
                        int numberOfItems = handleMainLogic.convertStrToInt(strNumberOfItems);
                        
                        double totalCost = calculateTotalCost(priceOfProduct,numberOfItems);
                        totalCostAssign = totalCost;
                        
                        boolean valid2 = validateInputForNetCost();
                        if(valid2){
                            //valid discount input
                            String strDiscount = discountedCostField.getText();
                            double discount = handleMainLogic.convertStrToDouble(strDiscount);
                            discountAssign = discount;
                            
                            if(totalCostAssign>discount){
                                //normal
                                double netCost = calculateNetCost(totalCostAssign,discount);
                                netCostAssign = netCost;
                                //display results
                                totalCostField.setText(handleMainLogic.convertDoubleToString(totalCost));
                                netCostField.setText(handleMainLogic.convertDoubleToString(netCost));
                            }
                            else{
                                //abnormal
                                netCostAssign= totalCost;
                                totalCostField.setText(handleMainLogic.convertDoubleToString(totalCost));
                                netCostField.setText(handleMainLogic.convertDoubleToString(netCostAssign));
                            }
                        }
                        else{
                            //invalid discount input
                            totalCostField.setText(handleMainLogic.convertDoubleToString(totalCost));
                            netCostField.setText(handleMainLogic.convertDoubleToString(totalCost));
                        }
                    }
                    else{
                        //invalid number input
                        totalCostField.setText("0");
                        netCostField.setText("0");
                    }
    }
    
    public void setAllItemsInComboBox(){
        SoftwareLogic instance = BeansClass.softwareLogic();
        ArrayList<Software> softwareList = instance.fetchAllSoftwareObjects();
        ArrayList<String> softwareTitles = new ArrayList();
        
        for(Software software: softwareList){
            String title = software.getTitle();
            softwareTitles.add(title);
        }
            
        ObservableList<String> products = FXCollections.observableArrayList(softwareTitles);
        allSoftwareComboBox.setItems(products);
    }
    
    public void fetchProductDetails(){
        SoftwareLogic instance = BeansClass.softwareLogic();
        String title = allSoftwareComboBox.getValue().toString();
        
        Software software = instance.fetchOneSoftware(title);
        this.software = software;
        
        HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
        int limit = software.getInstallationLimit();
        String description = software.getDescription();
        double price = checkChoiceBoxForPrice(software);
        String strPrice = handleMainLogic.convertDoubleToString(price);
        
        //show the details
        installationLimitField.setText(handleMainLogic.convertIntToString(limit));
        priceSoldField.setText(strPrice);
        descriptionSoldField.setText(description);
        
    }
    
    public double checkChoiceBoxForPrice(Software software){
        if(choiceBox.isSelected()){
            double productPrice = software.getPrice_optical_disk();
            return productPrice;
        }
        else{
            double productPrice = software.getPrice();
            return productPrice;
        }
    }
    
    public double calculateTotalCost(double price,int numberOfItems){
        Calculator calculator = BeansClass.calculator();
        double totalCost = calculator.calculateTotalCost(price, numberOfItems);
        return totalCost;
    }
    
    public double calculateNetCost(double totalCost,double discount){
        Calculator calculator = BeansClass.calculator();
        double netCost = calculator.calculateNetCost(totalCost, discount);
        return netCost;
    }
    
    public boolean validateInputForTotalCost(){
        String strNumberOfItems = amountSoldField.getText();
        
        String []params = {strNumberOfItems};
        String [] labels = {numberLabel.getText()};
        AddSoftwareSaleLogic addSoftwareSaleLogic = BeansClass.addSoftwareSaleLogic();
        String response = addSoftwareSaleLogic.verifyNumInput(params, labels);
        
        if(response != null){
            return false;
        }
        else{
            return true;
        }
    }
    
    public boolean validateInputForNetCost(){
        String strDiscount = discountedCostField.getText();
        
        String []params = {strDiscount};
        String [] labels = {discountLabel.getText()};
        AddMediaSaleLogic addMediaSaleLogic = BeansClass.addMediaSaleLogic();
        String response = addMediaSaleLogic.verifyNumInput(params, labels);
        
        if(response != null){
            return false;
        }
        else{
            return true;
        }
    }
    
    public void submit(){
        //make sure it is not null
        if(allSoftwareComboBox.getValue() == null){
            displayError("The drop down menu for 'Product' cannot be null");
            return;
        }
        else if(amountSoldField.getText() == null){
            displayError("The amount of items sold cannot be null");
            return;
        }
            
        //get input
        String productTitle =  allSoftwareComboBox.getValue().toString();
        String strNumberOfItems = amountSoldField.getText();
        String strPriceOfProduct = priceSoldField.getText();
        
        String strSubTotal = totalCostField.getText();
        String strSubDiscount = discountedCostField.getText();
        String strSubNetTotal = netCostField.getText();
        
        //validate
        String []params = {strNumberOfItems};
        String [] labels = {numberLabel.getText()};
        AddSoftwareSaleLogic addSoftwareSaleLogic = BeansClass.addSoftwareSaleLogic();
        String response = addSoftwareSaleLogic.verifyNumInput(params, labels);
        
        if(response!=null){
            displayError(response+" field cannot be empty.Please enter a valid number and try again");
        }
        else{
            //create entry object
            SoftwareSaleEntry softwareSaleEntry = BeansClass.softwareSaleEntry();
            addSoftwareSaleLogic.appendDetailsToEntry(
                softwareSaleEntry, 
                productTitle, 
                strPriceOfProduct, 
                strNumberOfItems, 
                strSubTotal,
                strSubDiscount,
                strSubNetTotal
                );
            
            
            //add to sale entries arrList -> sale object
            individualSaleEntries.add(softwareSaleEntry);
            
            //enter entries in table in table
            saveSaleData(individualSaleEntries); 
            hidePage();
        }
        
    }
    
    //display to table in 'parent' page
    public void saveSaleData(ArrayList<SoftwareSaleEntry>individualSaleEntries){
        //take the data to the db
        AddSoftwareSaleLogic addSoftwareSaleLogic = BeansClass.addSoftwareSaleLogic();
        addSoftwareSaleLogic.insert(individualSaleEntries);
        
        callParentClass();
    }
        
    public void callParentClass(){
        HandleFxmlPages handleFxmlPages = BeansClass.handleFxmlPages();
        handleFxmlPages.addSoftwareSale();
        hidePage();
    }
    
    public void cancel(){
        hidePage();
    }
    
    public void displayMessage(String message){
        DialogPane dialog = BeansClass.dialogPane();
        dialog.displayInfo(message);
    }
    
    public void displayError(String error){
        DialogPane dialog = BeansClass.dialogPane();
        dialog.displayInfo(error);
    }
    
    public void hidePage(){
        parentPane.getScene().getWindow().hide();
    }
    
}
