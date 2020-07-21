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
public class Media extends Product{
    boolean opticalDisk;
    
    public Media(String title, double price) {
        super(title, price);
    }

    public Media(double price) {
        super(price);
    }
    
    public Media(double price,boolean opticalDisk) {
        super(price);
        this.opticalDisk = opticalDisk;
    }

    public Media(String title) {
        super(title);
    }
    
    
}
