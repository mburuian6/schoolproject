/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.product;

/**
 *
 * @author Ian Mburu
 */

public class Product {
    String title;
    double price;
    String brand;
    double price_optical_disk; 
    String description;
    private String id;
    
    //individual instance  
    public Product(String title,double price){
        this.title = title;
        this.price = price;
    }
    
    //some sales abstraction-excpetions: s/w,h/w
    public Product(double price){        
        this.price = price;
    }
    
    //database object
    public Product(String title,String brand,double price,double price_optical_disk,String description){
        this.title = title;
        this.brand = brand;
        this.price = price;
        this.price_optical_disk= price_optical_disk;
        this.description = description;
    }
    
    public Product(){
        
    }
    
    //enter product
    public Product(String title){
        this.title = title;
    }
    
    
    public String getProductTitle(){
        return this.title;
    }
    
    public String getTitle(){
        return this.title;
    }
    
    public void setTitle(String title){
        this.title = title;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice_optical_disk() {
        return this.price_optical_disk;
    }

    public void setPrice_optical_disk(double price_optical_disk) {
        this.price_optical_disk = price_optical_disk;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
}
