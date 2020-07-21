/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.Sales;

import java.util.Date;

/**
 *
 * @author Ian Mburu
 */
public class MediaAndCustomSaleEntry extends SaleEntry{
    String title;
    double price;
    int quantity;
    double sub_total;
    double sub_discount;
    double sub_netTotal;
    Date date;
    
    String saleid;
    String saleProfile;
    public MediaAndCustomSaleEntry(
            String title,
            double price,
            int quantity, 
            double sub_total,
            double sub_discount,
            double sub_netTotal
    ){
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.sub_total = sub_total;
        this.sub_discount = sub_discount;
        this.sub_netTotal = sub_netTotal;
    }
    
    public MediaAndCustomSaleEntry(){
        
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSub_total() {
        return sub_total;
    }

    public void setSub_total(double sub_total) {
        this.sub_total = sub_total;
    }

    public double getSub_discount() {
        return sub_discount;
    }

    public void setSub_discount(double sub_discount) {
        this.sub_discount = sub_discount;
    }

    public double getSub_netTotal() {
        return sub_netTotal;
    }

    public void setSub_netTotal(double sub_netTotal) {
        this.sub_netTotal = sub_netTotal;
    }

    public String getSaleid() {
        return saleid;
    }

    public void setSaleid(String saleid) {
        this.saleid = saleid;
    }

    public String getSaleProfile() {
        return saleProfile;
    }

    public void setSaleProfile(String saleProfile) {
        this.saleProfile = saleProfile;
    }
    
    
    
    
    
    
}
