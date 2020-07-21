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
public class Software extends Product{
    String desc; //e.g. type
    int installationLimit;
    double priceOnOpticalDisk;
    
    public Software(String desc, String title, double price) {
        super(title, price);
        this.desc = desc;
        
    }
    
    public Software(String desc, String title,String type, double price) {
        super(title, price);
        this.desc = desc;
    }

    public Software(String title) {
        super(title);
    }
    
    public Software(String title, double price,double priceOnOpticalDisk, int installationLimit, String desc) {
        super(title, price);
        this.desc = desc;
        this.priceOnOpticalDisk = priceOnOpticalDisk;
        this.installationLimit = installationLimit;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getInstallationLimit() {
        return installationLimit;
    }

    public void setInstallationLimit(int installationLimit) {
        this.installationLimit = installationLimit;
    }

    public double getPriceOnOpticalDisk() {
        return priceOnOpticalDisk;
    }

    public void setPriceOnOpticalDisk(double priceOnOpticalDisk) {
        this.priceOnOpticalDisk = priceOnOpticalDisk;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
    
}
