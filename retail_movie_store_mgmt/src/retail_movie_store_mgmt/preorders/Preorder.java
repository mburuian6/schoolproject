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
public class Preorder {
    String id;
    String customer_name;
    LocalDate orderDate;
    LocalDate pickupDate;
    String peripheral;
    String movies_list;
    String shows_list;
    String software_list;
    String profile;
    String status;
    
    public Preorder(String id, String customer_name, LocalDate orderDate, LocalDate pickupDate, String peripheral, String movies_list, String shows_list, String software_list, String profile,String status) {
        this.id = id;
        this.customer_name = customer_name;
        this.orderDate = orderDate;
        this.pickupDate = pickupDate;
        this.peripheral = peripheral;
        this.movies_list = movies_list;
        this.shows_list = shows_list;
        this.software_list = software_list;
        this.profile = profile;
        this.status = status;
    }

    public Preorder() {
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(LocalDate pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getPeripheral() {
        return peripheral;
    }

    public void setPeripheral(String peripheral) {
        this.peripheral = peripheral;
    }

    public String getMovies_list() {
        return movies_list;
    }

    public void setMovies_list(String movies_list) {
        this.movies_list = movies_list;
    }

    public String getShows_list() {
        return shows_list;
    }

    public void setShows_list(String shows_list) {
        this.shows_list = shows_list;
    }

    public String getSoftware_list() {
        return software_list;
    }

    public void setSoftware_list(String software_list) {
        this.software_list = software_list;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
