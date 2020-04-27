/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.Logic;

import BeansPackage.BeansClass;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import retail_movie_store_mgmt.EnterProductController;
import retail_movie_store_mgmt.database.ProductHandle;
import retail_movie_store_mgmt.product.Product;

/**
 *
 * @author Ian Mburu
 */
public class HandleProductLogic {
    
    public Product appendProductDetails(Product product,
            String brand,
            String strPriceSh,
            String strPriceCts,
            String strPrice_optical_diskSh, 
            String strPrice_optical_diskCts,
            String description){
        //concatenate sh and their respective cents
        if(!strPriceCts.contains(".")){
            strPriceCts="."+strPriceCts;
        }
        
        if(!strPrice_optical_diskCts.contains(".")){
            strPrice_optical_diskCts="."+strPrice_optical_diskCts;
        }
        
        String strPrice = strPriceSh.concat(strPriceCts);
        String strPrice_optical_disk = strPrice_optical_diskSh.concat(strPrice_optical_diskCts);
        
//      convert to number
        double price = convertStrToDouble(strPrice); 
        double price_optical_disk = convertStrToDouble(strPrice_optical_disk);
        
        product.setBrand(brand);
        product.setPrice(price);
        product.setPrice_optical_disk(price_optical_disk);
        product.setDescription(description);
        
        return product;
    }
    
    public double convertStrToDouble(String input){
        double doubleVal = Double.valueOf(input);
        return doubleVal;
    }
    
    public void insertProductIntoDatabaseEmpty(Product product){
        //insert selected
        boolean check = checkIfProductExists(product.getProductTitle());
        if(check){
            //pass
                       
        }
        else{
            ProductHandle productHandle = BeansClass.productHandle();
            boolean insertProduct = productHandle.insertProduct(product.getProductTitle(), "n/a", 0, 0, "n/a","novel");
            if(insertProduct){
                displayInfo("Successful Insertion");
            }
            else{
                displayInfo("Error, please check your connection to the database");
            }
        }
    }
    
    public void insertProductIntoDatabase(Product product){
        //insert selected
        boolean check = checkIfProductExists(product.getProductTitle());
        if(check){
            //pass
            int response = getConfirmation("Item already in database. Proceeding will cause it to be updated with the new details.");
            if(response == 0){
                //ok
                updateProductInDatabase(product); 
            }
            else{
                //pass
            }
        }
        else{
            ProductHandle productHandle = BeansClass.productHandle();
            boolean insertProduct = productHandle.insertProduct(
                    product.getProductTitle(),
                    product.getBrand(),
                    product.getPrice(),
                    product.getPrice_optical_disk(), 
                    product.getDescription(),
                    "custom"
            );
            if(insertProduct){
                displayInfo("Successful Insertion");
            }
            else{
                displayInfo("Error, please check your connection to the database");
            }
        }
    }
    
    public void updateProductInDatabase(Product product){
        ProductHandle productHandle = BeansClass.productHandle();
        boolean updateProduct = productHandle.updateProduct(product.getProductTitle(),
                product.getBrand(),
                product.getPrice(),
                product.getPrice_optical_disk(),
                product.getDescription());
        if(updateProduct){
          displayInfo("Successful Update");
        }
        else{
            displayInfo("Error, please check your connection to the database");
        }
    }
    
    public void deleteProductFromDatabase(Product product){
        boolean check = checkIfProductExists(product.getProductTitle());
        if(check){
            ProductHandle productHandle = BeansClass.productHandle();
            boolean deleteFromDb = productHandle.deleteFromDb(product.getProductTitle());
            if(deleteFromDb){
                displayInfo("Successfully deleted product:"+product.getProductTitle());
            }
            else{
                displayInfo("Error, please check your connection to the database");
            }
        }
        else{
        }
    }
    
    public boolean checkIfProductExists(String title){
        ProductHandle productHandle = BeansClass.productHandle(); 
        Product prod = productHandle.findIfExists(title);
        if(prod==null){
            //inexistent
            return false;
        }
        else{
            return true;
        }
    }
    
    public ArrayList<String> fetchAllProducts(){
        ArrayList<String> productList = new ArrayList();
        
        ProductHandle instance = BeansClass.productHandle();
        Iterable productsIterable = instance.findAll();
        Iterator<Product> productsIterator = productsIterable.iterator();
        
        while(productsIterator.hasNext()){
            Product element = productsIterator.next();
            productList.add(element.getProductTitle());
        }    
        return productList;
    }
    
    public ArrayList<String> fetchCustomProducts(){
        ArrayList<String> productArray = new ArrayList();
        ProductHandle instance = BeansClass.productHandle();
        
        List<Product>customProducts = instance.findCustoms("custom");
        if(customProducts==null){
            //inexistent
        }
        else{
            Iterator<Product> iterator = customProducts.iterator();
            while(iterator.hasNext()){
                Product prod = iterator.next();
                productArray.add(prod.getProductTitle());
            }
        }
        return productArray;
    }
   
    public Product fetchOneProduct(String title){
        ProductHandle instance = BeansClass.productHandle();
        Product prod = instance.findOne(title);
        
        return prod;
    }
    
    public ArrayList<Product> fetchAllProductObjects(){
        ArrayList<Product> productList = new ArrayList();
        
        ProductHandle instance = BeansClass.productHandle();
        Iterable productsIterable = instance.findAll();
        Iterator<Product> productsIterator = productsIterable.iterator();
        
        while(productsIterator.hasNext()){
            Product element = productsIterator.next();
            productList.add(element);
        }    
        return productList;
    }
    
    
    
   public void displayInfo(String message){
       EnterProductController enterProductController = BeansClass.enterProductController();
       enterProductController.displayInfo(message);
   }
   
   public void displayError(String message){
       EnterProductController enterProductController = BeansClass.enterProductController();
       enterProductController.displayError(message);
   }
   
   public int getConfirmation(String message){
       EnterProductController enterProductController = BeansClass.enterProductController();
       return enterProductController.getConfirmation(message);
   }
}
