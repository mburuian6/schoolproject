/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.purchases.software;

import java.time.LocalDate;
import retail_movie_store_mgmt.purchases.Purchase;

/**
 *
 * @author Ian Mburu
 */
public class SoftwarePurchase extends Purchase{
    String id; 
    LocalDate date;
    String title;
    int quantity;
    double price;
    double total;
    String profile;
    public SoftwarePurchase(String id, LocalDate date, String title, int quantity, double price, double total,String profile) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.profile = profile;
    }

    public SoftwarePurchase() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
    
    
    
    
}
