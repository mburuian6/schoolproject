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
public class Mashup extends Media{
    String desc;

    public Mashup(String desc, String title, double price) {
        super(title, price);
        this.desc = desc;
    }

    public Mashup(double price) {
        super(price);
    }

    public Mashup(double price, boolean opticalDisk) {
        super(price, opticalDisk);
    }

    public Mashup(String title) {
        super(title);
    }
    
    
    
}
