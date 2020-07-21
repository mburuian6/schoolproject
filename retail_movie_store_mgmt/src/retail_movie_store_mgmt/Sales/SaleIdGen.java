/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.Sales;

import BeansPackage.BeansClass;
import retail_movie_store_mgmt.commonUtil.DateTime;
import retail_movie_store_mgmt.Logic.HandleMainLogic;
import retail_movie_store_mgmt.User;

/**
 *
 * @author Ian Mburu
 */
public class SaleIdGen {
    
    public String generateSaleId(){
        //getUser
        User user = BeansClass.user();
        String username = user.getCurrentUsername();
        
        DateTime dtime = BeansClass.dateTime();
        int [] arr = dtime.getDateTime();
        String id = "";
        for(int i : arr){
            HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
            String subId = handleMainLogic.convertIntToString(i);
            id = id+subId;
        }
        
        id = username+"-"+id;
        return id;
    }
    
     
}
