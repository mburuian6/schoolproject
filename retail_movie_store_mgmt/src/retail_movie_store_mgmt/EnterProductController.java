/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt;

import retail_movie_store_mgmt.Logic.HandleProductLogic;
import retail_movie_store_mgmt.Logic.HandleMainLogic;
import BeansPackage.BeansClass;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import retail_movie_store_mgmt.product.Mashup;
import retail_movie_store_mgmt.product.Mix;
import retail_movie_store_mgmt.product.Movie;
import retail_movie_store_mgmt.product.MusicAudio;
import retail_movie_store_mgmt.product.MusicVideo;
import retail_movie_store_mgmt.product.Podcast;
import retail_movie_store_mgmt.product.Product;
import retail_movie_store_mgmt.product.TvSeries;

/**
 * FXML Controller class
 *
 * @author Ian Mburu
 */
public class EnterProductController implements Initializable {
    //@FXML TabPane tabPane;
    //Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
    Product product;
    
    //Enter category
    @FXML CheckBox movieBox;
    @FXML CheckBox tvSeriesBox;
    @FXML CheckBox musicAudioBox;
    @FXML CheckBox musicVideoBox;
    @FXML CheckBox softwareBox;
    @FXML CheckBox videoMixBox;
    @FXML CheckBox podcastBox;
    @FXML CheckBox videomashupBox;
    
    //Edit Category
    @FXML ComboBox showProducts;
    @FXML TextField price_shField;
    @FXML TextField price_ctsField;
    @FXML TextField price_optical_shField;
    @FXML TextField price_optical_ctsField;
    @FXML TextField brandField;
    @FXML TextArea otherDetailsField;
    @FXML Label feedback;
    
    //add custom category
    @FXML TextField otherProductTitleField;
    @FXML TextField otherProductPriceShField;
    @FXML TextField otherProductPriceCtsField;
    @FXML CheckBox otherProductOpticalBox;
    @FXML TextField otherProductOpticalPriceShField;
    @FXML TextField otherProductOpticalPriceCtsField;
    
    //del custom category
    @FXML ComboBox displayCustomCategory;
    @FXML TextField titleCustomCategory;
    @FXML TextField priceCustomCategory;
    @FXML TextField priceOpticalCustomCategory;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //edit category details tab show selected products
        HandleProductLogic instance = BeansClass.handleProductLogic();
        ArrayList<String> productList = instance.fetchAllProducts();
        
        ObservableList<String> products = FXCollections.observableArrayList(productList);
        showProducts.setItems(products);
        
        //set initial val
        if(!products.isEmpty()){
            Iterator <String> prodIterator = products.iterator();
            while(prodIterator.hasNext()){
                showProducts.setValue(prodIterator.next());
            }
        
            //listen for any changes (invalid method)
//            showProducts.setOnAction(new EventHandler<ActionEvent>(){
//                public void handle(ActionEvent evt){
//                    String productInAction = showProducts.getValue().toString();
//                    if(productInAction.equals("Software")){
//                        feedback.setText("Software is highly specialized and aspects such as Price will have to be specified individually in the"
//                                + " inventory function.");
//
//                        enableTextFields(false);
//                    }
//                    else{
//                        enableTextFields(true);
//                    }
//                }
//            });
            
            //add custom tab
            //listen for changes on checkbox
            otherProductOpticalBox.setOnAction(new EventHandler<ActionEvent>(){
                public void handle(ActionEvent evt){
                    if(otherProductOpticalBox.isSelected()){
                        otherProductOpticalPriceShField.setEditable(true);
                        otherProductOpticalPriceCtsField.setEditable(true);
                    }
                    else{
                        otherProductOpticalPriceShField.setEditable(false);
                        otherProductOpticalPriceCtsField.setEditable(false);
                    }
                }
            });
        }
        
        //delete customs tab
        //put items on combobox
        ArrayList<String> customProducts = instance.fetchCustomProducts();
        ObservableList<String> customProductsObservable = FXCollections.observableArrayList(customProducts);
        displayCustomCategory.setItems(customProductsObservable);
        displayCustomCategory.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent evt){
                go();
            }
        });
        //view products tab
        viewProducts(); //populate view table
    }    
    
    public void emptyAllTextFields(){
        price_shField.setText("");
        price_ctsField.setText("");
        price_optical_shField.setText("");
        price_optical_ctsField.setText("");
        brandField.setText("");
        otherDetailsField.setText("");
        otherProductTitleField.setText("");
        otherProductPriceShField.setText("");
        otherProductOpticalPriceShField.setText("");
        titleCustomCategory.setText("");
        priceCustomCategory.setText("");
        priceOpticalCustomCategory.setText("");        
    }
    
    public void enableTextFields(boolean set){
        price_shField.setEditable(set);
        price_ctsField.setEditable(set);
        price_optical_shField.setEditable(set);
        price_optical_ctsField.setEditable(set);
        brandField.setEditable(set);
        if(set==true){
            feedback.setText("");
        }
    }
    
 
    //ENTER CATEGORY
    public void categoryTab(){
        Product product;
        if(movieBox.isSelected()){
            product = new Movie(movieBox.getText());
            insertToDb(product);
        }else{
            product = new Movie(movieBox.getText());
            deleteFromDb(product);
        }
        
        if(tvSeriesBox.isSelected()){
            product = new TvSeries(tvSeriesBox.getText());            
            insertToDb(product);
        }else{
            product = new TvSeries(tvSeriesBox.getText()); 
            deleteFromDb(product);
        }
        
        if(musicAudioBox.isSelected()){
            product = new MusicAudio(musicAudioBox.getText());
            insertToDb(product);
        }else{
            product = new MusicAudio(musicAudioBox.getText());
            deleteFromDb(product);
        }
        
        
        if(musicVideoBox.isSelected()){
            product = new MusicVideo(musicVideoBox.getText());
            insertToDb(product);
        }else{
            product = new MusicVideo(musicVideoBox.getText());
            deleteFromDb(product);
        }
        
        if(videoMixBox.isSelected()){
            product = new Mix(videoMixBox.getText());
            insertToDb(product);
        }else{
            product = new Mix(videoMixBox.getText());
            deleteFromDb(product);
        }
        
        if(podcastBox.isSelected()){
            product = new Podcast(podcastBox.getText());
            insertToDb(product);
        }else{
            product = new Podcast(podcastBox.getText());
            deleteFromDb(product);
        }
        
        if(videomashupBox.isSelected()){
            product = new Mashup(videomashupBox.getText());
            insertToDb(product);
        }else{
            product = new Mashup(videomashupBox.getText());
            deleteFromDb(product);
        }
        
        refresh();
         //repopulate view table 
    }
    
    public void insertToDb(Product product){
        HandleProductLogic handleProductLogic = BeansClass.handleProductLogic();
        handleProductLogic.insertProductIntoDatabaseEmpty(product);        
    }
    
    public void deleteFromDb(Product product){
        HandleProductLogic handleProductLogic = BeansClass.handleProductLogic();
        handleProductLogic.deleteProductFromDatabase(product);
    }
    
    //EDIT CATEGORY
    public void refresh(){
        //repopulate 'display' table
        viewProducts();
        
        HandleProductLogic instance = BeansClass.handleProductLogic();
        //repopulate 'edit' combo box
        ArrayList<String> productList = instance.fetchAllProducts();
        ObservableList<String> products = FXCollections.observableArrayList(productList);
        if(!products.isEmpty() && products!=null){
            showProducts.setItems(products);
        }
        
        //repopulate 'delete' combobox
        ArrayList<String> customProducts = instance.fetchCustomProducts();
        ObservableList<String> customProductsObservable = FXCollections.observableArrayList(customProducts);
        if(!customProductsObservable.isEmpty() && customProductsObservable!= null){
            displayCustomCategory.setItems(customProductsObservable);
        }
        
        
        emptyAllTextFields();
//        if(!products.isEmpty()){
//            Iterator <String> prodIterator = products.iterator();
//            while(prodIterator.hasNext()){
//                showProducts.setValue(prodIterator.next());
//            }
//        }
//        
    }
    
    public void editCategoryDetails(){
        //get all input
        String price_sh = price_shField.getText();
        String price_cts = price_ctsField.getText();
        String price_optical_sh = price_optical_shField.getText();
        String price_optical_cts = price_optical_ctsField.getText();
        String brand = brandField.getText();
        String otherDetails = otherDetailsField.getText();
        
        String[] arrInput = {price_sh,price_cts,price_optical_sh,price_optical_cts};
        String [] labels = {"Price(Ksh)","Price(cts)","Price on Optical Disk(Ksh)","Price on Optical Disk(cts)"};
        String whereFound = checkIntInput(arrInput,labels);
        if(whereFound!=null){
            //wrong input
            //JOptionPane.showMessageDialog(null, whereFound+" field cannot contain alphabetic characters. It needs to be a valid number");
            displayError(whereFound+" field cannot contain alphabetic characters. It needs to be a valid number");
        }
        else{
            String title = showProducts.getValue().toString();
            
            HandleProductLogic handleProductLogic = BeansClass.handleProductLogic();
            Product product = BeansClass.product(title);
            
            product = handleProductLogic.appendProductDetails(product,brand,price_sh,price_cts,price_optical_sh,price_optical_cts,otherDetails);
            handleProductLogic.updateProductInDatabase(product);
            
            refresh();
            
            //set all fields null
            price_shField.setText("");
            price_ctsField.setText("");
            price_optical_shField.setText("");
            price_optical_ctsField.setText("");
            brandField.setText("");
            otherDetailsField.setText("");
        }
        
    }
    
    //checks all data one by one and returns where 'dirty' input is found in place of/together with int
    public String checkIntInput(String[]params,String[]labels){        
        for(int i=0;i<params.length;i++){
            String input = params[i];
            boolean found = checkForAlphasInInt(input);
            if(found){
                return labels[i];
            }
        }
        return null;
    }
    
    public boolean checkForAlphasInInt(String input){
        String lowercase = "[a-z]";
        String uppercase = "[A-Z]";
        String specialChars = "-";
        Pattern patt1 = Pattern.compile(lowercase);
        Pattern patt2 = Pattern.compile(uppercase);
        Pattern patt3 = Pattern.compile(specialChars);
        
        Matcher matt1 = patt1.matcher(input);
        Matcher matt2 = patt2.matcher(input);
        Matcher matt3 = patt3.matcher(input);
                
        if(matt1.find()){
            return true;
        }
        else if(matt2.find()){
            return true;
        }
        else if(matt3.find()){
            return true;
        }
        return false;
    }
    
    //ADD CUSTOM CATEGORY
    public void addCustomCategory(){
        String otherProductTitle=  otherProductTitleField.getText();
        String otherProductPriceSh= otherProductPriceShField.getText();
        String otherProductPriceCts = otherProductPriceCtsField.getText();
        String otherProductOpticalPriceSh = "0";
        String otherProductOpticalPriceCts = "0";
        if(otherProductOpticalBox.isSelected()){
            otherProductOpticalPriceSh =otherProductOpticalPriceShField.getText();
            otherProductOpticalPriceCts = otherProductOpticalPriceCtsField.getText();
        }
        
        //Validate input 
        String[] fieldsToCheck = {otherProductPriceSh,otherProductPriceCts,otherProductOpticalPriceSh,otherProductOpticalPriceCts};
        String[] labels = {"Price(Ksh)","Price(Cts)","Price on Optical Drive(Ksh)","Price on Optical Drive(Cts)"};
        String whereFound = checkIntInput(fieldsToCheck,labels);
        
        //wrong input
        if(whereFound!=null){
            //JOptionPane.showMessageDialog(null, whereFound+" field cannot contain alphabetic characters. It needs to be a valid number");
            displayError(whereFound+" field cannot contain alphabetic characters. It needs to be a valid number");
        }
        else{
            //right input
            HandleProductLogic handleProductLogic = BeansClass.handleProductLogic();
            Product product = BeansClass.product(otherProductTitle);
            
            product = handleProductLogic.appendProductDetails(
                    product,
                    "",
                    otherProductPriceSh,
                    otherProductPriceCts,
                    otherProductOpticalPriceSh,
                    otherProductOpticalPriceCts,
                    "");
            handleProductLogic.insertProductIntoDatabase(product);
            refresh();
            
            //unset all fields
            otherProductTitleField.setText("");
            otherProductPriceShField.setText("");
            otherProductPriceCtsField.setText("");
            otherProductOpticalBox.setSelected(false);
            otherProductOpticalPriceShField.setText("");
            otherProductOpticalPriceCtsField.setText("");
        }
    }
    
    //DELETE CUSTOM CATEGORY
    public void go(){
        //requisistes
        HandleProductLogic instance = BeansClass.handleProductLogic();
        //get the selected product
        String title = displayCustomCategory.getValue().toString();
        
        if(title!=null){
            //get the product details
            Product prod = instance.fetchOneProduct(title);
            this.product = prod;
            double price = prod.getPrice();
            double price_optical = prod.getPrice_optical_disk();

            //prep the details
            HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
            String strPrice = handleMainLogic.convertDoubleToString(price);
            String strPriceOptical = handleMainLogic.convertDoubleToString(price_optical);

            //set the items
            titleCustomCategory.setText(title);
            priceCustomCategory.setText(strPrice);
            priceOpticalCustomCategory.setText(strPriceOptical);
        }
        
    }
    
    
    public void deleteCustomProduct(){
        HandleProductLogic instance = BeansClass.handleProductLogic();
        instance.deleteProductFromDatabase(this.product);
        
        refresh();
        
        titleCustomCategory.setText("");
        priceCustomCategory.setText("");
        priceOpticalCustomCategory.setText("");
    }
    
    public void getAllCustomItems(){
        //put items on combobox
        HandleProductLogic logicInstance = BeansClass.handleProductLogic();
        ArrayList<String> customProducts = logicInstance.fetchAllProducts();
        
        ObservableList<String> customProductsObservable = FXCollections.observableArrayList(customProducts);
        showProducts.setItems(customProductsObservable); //edit
        displayCustomCategory.setItems(customProductsObservable); //delete
    }
    
    //VIEW PRODUCTS
    @FXML TableView productsTable;
    @FXML TableColumn <Product,String> titleColumn;
    @FXML TableColumn <Product,String> brandColumn;
    @FXML TableColumn <Product,Double> priceColumn;
    @FXML TableColumn <Product,Double> priceOpticalColumn;
    @FXML TableColumn <Product,String>descriptionColumn;
    
    public void viewProducts(){
        //set placeholder
        productsTable.setPlaceholder(new Label("No products to display"));
        
        //setCellFactory
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceOpticalColumn.setCellValueFactory(new PropertyValueFactory<>("price_optical_disk"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        //get table data
        HandleProductLogic handleProductLogic = BeansClass.handleProductLogic();
        ArrayList<Product> productList = handleProductLogic.fetchAllProductObjects();
        ObservableList<Product> allproducts = FXCollections.<Product>observableArrayList(productList);
        
        //set table data
        productsTable.getItems().setAll(allproducts);
    }
    
    public void goBack(){
      HandleMainLogic instance = BeansClass.handleMainLogic();
      instance.callHome();
      hidePage();
    }
    
    public void hidePage(){
        productsTable.getScene().getWindow().hide();
    }
    
    public void displayInfo(String message){
        //Window owner = parentPane.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        //alert.initOwner(owner);
        alert.show();
    }
    
    public void displayError(String message){
        //Window owner = parentPane.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        //alert.initOwner(owner);
        alert.show();
    }
    
    public int getConfirmation(String feedback){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(feedback);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            return 0;
        }
        else{
            return 1;
        }
    }
    
    
}
