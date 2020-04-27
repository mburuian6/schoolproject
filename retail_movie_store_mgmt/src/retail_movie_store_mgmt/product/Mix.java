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
public class Mix extends Media{
    String desc;
    public Mix(String title, double price,String desc) {
        super(title, price);
        this.desc = desc;
    }

    public Mix(double price) {
        super(price);
    }

    public Mix(double price, boolean opticalDisk) {
        super(price, opticalDisk);
    }    

    public Mix(String title) {
        super(title);
    }
    
    
}
