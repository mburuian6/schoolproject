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
public class Audio extends Media{

    public Audio(String title, double price) {
        super(title, price);
    }

    public Audio(double price) {
        super(price);
    }

    public Audio(double price, boolean opticalDisk) {
        super(price, opticalDisk);
    }
    
    
    
    
}
