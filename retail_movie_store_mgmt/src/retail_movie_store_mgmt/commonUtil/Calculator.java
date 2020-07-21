/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.commonUtil;

/**
 *
 * @author Ian Mburu
 */


public class Calculator {
    
    //calculate price
    public double calculateTotalCost(double price,int quantity){
        return price*quantity; 
    }
    
    //calculate net cost
    public double calculateNetCost(double totalCost,double netCost){
        return totalCost-netCost;
    }
}
