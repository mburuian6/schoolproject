/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.Logic;

import BeansPackage.BeansClass;
import java.util.ArrayList;
import retail_movie_store_mgmt.Sales.MediaAndCustomSaleEntry;
import retail_movie_store_mgmt.database.nosql.MediaSalesHandle;

/**
 *
 * @author Ian Mburu
 */
public class HandleReportLogic {
    
    public ArrayList<MediaAndCustomSaleEntry> reportAll(){
        MediaSalesHandle mediaSalesHandle = BeansClass.mediaSalesHandle();
        ArrayList<MediaAndCustomSaleEntry> allItems = mediaSalesHandle.findAll();
        return allItems;
    }
    
    
}
