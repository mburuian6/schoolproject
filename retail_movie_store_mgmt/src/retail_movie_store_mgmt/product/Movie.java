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
public class Movie extends Media{
    String title;
    double price;
    public Movie(String title,double price){
        super(title,price);
    }
    
    public Movie(double price){
        super(price);
    }

    public Movie(double price, boolean opticalDisk) {
        super(price, opticalDisk);
    }

    public Movie(String title) {
        super(title);
    }
    
    
    
}
