/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.Customer;

/**
 *
 * @author Ian Mburu
 */
public class Customer {
    
    String id;
    String username;
    String customer_name;
    String gender;
    String movie_likes;
    String show_likes;

    public Customer(String id, String username, String customer_name, String gender, String movie_likes, String show_likes) {
        this.id = id;
        this.username = username;
        this.customer_name = customer_name;
        this.gender = gender;
        this.movie_likes = movie_likes;
        this.show_likes = show_likes;
    }

    public Customer(){
        
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getMovie_likes() {
        return movie_likes;
    }

    public void setMovie_likes(String movie_likes) {
        this.movie_likes = movie_likes;
    }

    public String getShow_likes() {
        return show_likes;
    }

    public void setShow_likes(String show_likes) {
        this.show_likes = show_likes;
    }
    
    
    
}
