/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.purchases;

import java.time.LocalDate;

/**
 *
 * @author Ian Mburu
 */
public class PurchaseDate {
    String id;
    LocalDate date;

    public PurchaseDate(String id, LocalDate date) {
        this.id = id;
        this.date = date;
    }

    public PurchaseDate() {
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
    
    
    
}
