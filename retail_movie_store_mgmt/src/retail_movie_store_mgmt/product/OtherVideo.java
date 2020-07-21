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
public class OtherVideo extends Media{
    String desc;

    public OtherVideo(String desc, String title, double price) {
        super(title, price);
        this.desc = desc;
    }

    public OtherVideo(double price) {
        super(price);
    }

    public OtherVideo(String title, double price) {
        super(title, price);
    }
    
    
    
}
