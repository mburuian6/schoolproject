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
public class TvSeries extends Product{
    String desc;
    int episodes;
    public TvSeries(String title,String desc,double price,int episodes){
        super(title,price);
        this.desc = desc;
        this.episodes = episodes;
    }

    public TvSeries(String title, double price) {
        super(title, price);
    }

    public TvSeries(double price) {
        super(price);
    }

    public TvSeries(String title, String brand, double price, double price_optical_disk, String description) {
        super(title, brand, price, price_optical_disk, description);
    }

    public TvSeries(String title) {
        super(title);
    }
    
    
}
