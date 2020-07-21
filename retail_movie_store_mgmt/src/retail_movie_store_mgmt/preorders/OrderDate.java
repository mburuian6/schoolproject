/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.preorders;

import java.time.LocalDate;

/**
 *
 * @author Ian Mburu
 */
public class OrderDate {
    String id;
    LocalDate orderDate;

    public OrderDate(String id,LocalDate orderDate) {
        this.orderDate = orderDate;
        this.id =id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
    
    
}
