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
import javax.swing.JOptionPane;
import retail_movie_store_mgmt.Logic.HandleMainLogic;
import retail_movie_store_mgmt.Logic.HandleProductLogic;
import retail_movie_store_mgmt.Sales.MediaAndCustomSaleEntry;
import retail_movie_store_mgmt.commonUtil.Calculator;
import retail_movie_store_mgmt.product.Product;

/**
 * FXML Controller class
 *
 * @author Ian Mburu
 */
//=> saveSaledata
public class AddMediaSaleFormController implements Initializable {
    @FXML AnchorPane parentPane;
    @FXML Label numberLabel;
    @FXML Label discountLabel;
    
    //editable
    @FXML ComboBox allProductsComboBox;
    @FXML TextField amountSoldField;
    @FXML CheckBox choiceBox;
    //uneditable
    @FXML TextField brandSoldField;
    @FXML TextField priceSoldField;
    @FXML TextArea descriptionSoldField;
    //bottom
    //editable
    @FXML TextField discountedCostField;
    //uneditable
    @FXML TextField totalCostField;
    @FXML TextField netCostField;
    
    Product product;
    double totalCostAssign;
    double discountAssign;
    double netCostAssign;
    ArrayList<MediaAndCustomSaleEntry> individualSaleEntries = new ArrayList(); 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setAllItemsInComboBox();
        
        //listen for any changes and attach details
            allProductsComboBox.setOnAction(new EventHandler<ActionEvent>(){
                public void handle(ActionEvent evt){
                    fetchProductDetails();
                }
            });
            
            //listen for any changes and attach details
            //listen for choicebox to set price
            choiceBox.setOnAction(new EventHandler<ActionEvent>(){
                public void handle(ActionEvent evt){
                    if(choiceBox.isSelected()){
                        HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
                        double price = checkChoiceBoxForPrice(product);
                        String strPrice = handleMainLogic.convertDoubleToString(price);
                        priceSoldField.setText(strPrice);
                        
                        calculate();
                    }
                    else{
                        HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
                        double price = checkChoiceBoxForPrice(product);
                        String strPrice = handleMainLogic.convertDoubleToString(price);
                        priceSoldField.setText(strPrice);
                        calculate();
                    }
                }
            });
            
            //listen for any changes and attach details
            amountSoldField.setOnAction(new EventHandler<ActionEvent>(){
                public void handle(ActionEvent evt){
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
                            
                            String strDiscount = discountedCostField.getText();
                            double discount = handleMainLogic.convertStrToDouble(strDiscount);
                            discountAssign = discount;
                            
                            double netCost = calculateNetCost(totalCost,discount);
                            netCostAssign = netCost;
                            //display results
                            totalCostField.setText(handleMainLogic.convertDoubleToString(totalCost));
                            netCostField.setText(handleMainLogic.convertDoubleToString(netCost));
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
        HandleProductLogic instance = BeansClass.handleProductLogic();
        ArrayList<String> productList = instance.fetchAllProducts();
        
        ObservableList<String> products = FXCollections.observableArrayList(productList);
        allProductsComboBox.setItems(products);
    }
    
    public void fetchProductDetails(){
        HandleProductLogic instance = BeansClass.handleProductLogic();
        String title = allProductsComboBox.getValue().toString();
        
        Product product = instance.fetchOneProduct(title);
        this.product = product;
        
        HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
        String brand = product.getBrand();
        String description = product.getDescription();
        double price = checkChoiceBoxForPrice(product);
        String strPrice = handleMainLogic.convertDoubleToString(price);
        
        //show the details
        brandSoldField.setText(brand);
        priceSoldField.setText(strPrice);
        descriptionSoldField.setText(description);
        
    }
    
    public double checkChoiceBoxForPrice(Product product){
        if(choiceBox.isSelected()){
            double productPrice = product.getPrice_optical_disk();
            return productPrice;
        }
        else{
            double productPrice = product.getPrice();
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
        AddMediaSaleLogic addMediaSaleLogic = BeansClass.addMediaSaleLogic();
        String response = addMediaSaleLogic.verifyNumInput(params, labels);
        
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
        if(allProductsComboBox.getValue() == null){
            displayMessage("The drop down menu for 'Product' cannot be null");
            return;
        }
        else if(amountSoldField.getText() == null){
            displayMessage("The amount of items sold cannot be null");
            return;
        }
            
        //get input
        String productTitle =  allProductsComboBox.getValue().toString();
        String strNumberOfItems = amountSoldField.getText();
        String strPriceOfProduct = priceSoldField.getText();
        
        String strSubTotal = totalCostField.getText();
        String strSubDiscount = discountedCostField.getText();
        String strSubNetTotal = netCostField.getText();
        
        //validate
        String []params = {strNumberOfItems};
        String [] labels = {numberLabel.getText()};
        AddMediaSaleLogic addMediaSaleLogic = BeansClass.addMediaSaleLogic();
        String response = addMediaSaleLogic.verifyNumInput(params, labels);
        
        if(response!=null){
            AddMediaSaleFormController addMediaSaleFormController = BeansClass.addMediaSaleFormController();
            addMediaSaleFormController.displayMessage(response+" field cannot be empty.Please enter a valid number and try again");
            return;
        }
        else{
            //create entry object
            MediaAndCustomSaleEntry mediaAndCustomSaleEntry = BeansClass.mediaAndCustomSaleEntry();
            addMediaSaleLogic.appendDetailsToEntry(
                    mediaAndCustomSaleEntry, 
                    productTitle, 
                    strPriceOfProduct, 
                    strNumberOfItems, 
                    strSubTotal,
                    strSubDiscount,
                    strSubNetTotal
                    );
            
            
            //add to sale entries arrList -> sale object
            individualSaleEntries.add(mediaAndCustomSaleEntry);
            
            //enter entries in table in table
            saveSaleData(individualSaleEntries);
        }
        
    }
    
    //display to table in 'parent' page
    public void saveSaleData(ArrayList<MediaAndCustomSaleEntry>individualSaleEntries){
        //send items to db via logic
        AddMediaSaleLogic addMediaSaleLogic = BeansClass.addMediaSaleLogic();
        addMediaSaleLogic.insert(individualSaleEntries);
        
        callParentClass();
    }
        
    public void callParentClass(){
        HandleFxmlPages handleFxmlPages = BeansClass.handleFxmlPages();
        handleFxmlPages.addMediaItemsSale();
        hidePage();
    }
    public void cancel(){
        hidePage();
    }
    
    public void displayMessage(String Message){
        JOptionPane.showMessageDialog(null, Message);
    }
    
    public void hidePage(){
        parentPane.getScene().getWindow().hide();
    }
    
    
}
