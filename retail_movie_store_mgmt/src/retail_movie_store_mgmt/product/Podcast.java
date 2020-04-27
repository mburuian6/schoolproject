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
public class Podcast extends Media{

    public Podcast(double price, boolean opticalDisk) {
        super(price, opticalDisk);
    }
    String desc;

    public Podcast(String desc, String title, double price) {
        super(title, price);
        this.desc = desc;
    }

    public Podcast(double price) {
        super(price);
    }

    public Podcast(String title) {
        super(title);
    }
    
    
    
}
