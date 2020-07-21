/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt;

import BeansPackage.BeansClass;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import retail_movie_store_mgmt.Logic.HandleMainLogic;
import retail_movie_store_mgmt.Sales.MediaAndCustomSaleEntry;
import retail_movie_store_mgmt.Sales.SaleIdGen;
import retail_movie_store_mgmt.database.nosql.MediaSalesHandle;

/**
 *
 * @author Ian Mburu
 */

public class AddMediaSaleLogic {
    
    String saleId;
    static String saleIdC;
    
    public String verifyNumInput(String[]params,String[]labels){
        HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
        return handleMainLogic.verifyNumInput(params, labels);
    }
    
    public String  generateSaleId(){
        SaleIdGen saleIdGen = BeansClass.saleIdGen();
        saleId = "mc_"+saleIdGen.generateSaleId();
        saleIdC = saleId;
        return saleId;
    }
    
    public String getSaleId(){
        return saleIdC;
    }
    
    public String getUser(){
        User user=BeansClass.user();
        return user.getCurrentUsername();
    }
    
    public MediaAndCustomSaleEntry appendDetailsToEntry(
            MediaAndCustomSaleEntry mediaAndCustomSaleEntry,
            String title,
            String strPrice,
            String strQuantity, 
            String strSub_total,
            String strSub_discount,
            String strSub_netTotal
    ){
        HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
        double price = handleMainLogic.convertStrToDouble(strPrice);
        double sub_total = handleMainLogic.convertStrToDouble(strSub_total);;
        double sub_discount = handleMainLogic.convertStrToDouble(strSub_discount);;
        double sub_netTotal = handleMainLogic.convertStrToDouble(strSub_netTotal);;
        int quantity = handleMainLogic.convertStrToInt(strQuantity);
        
        mediaAndCustomSaleEntry.setTitle(title);
        mediaAndCustomSaleEntry.setPrice(price);
        mediaAndCustomSaleEntry.setQuantity(quantity);
        mediaAndCustomSaleEntry.setSub_total(sub_total);
        mediaAndCustomSaleEntry.setSub_discount(sub_discount);
        mediaAndCustomSaleEntry.setSub_netTotal(sub_netTotal);
        mediaAndCustomSaleEntry.setSaleProfile(getUser());
        mediaAndCustomSaleEntry.setDate(new Date());
        
        return mediaAndCustomSaleEntry;
    }
    
    //db methods to get and send data
    
    //insert
    public void insert(ArrayList<MediaAndCustomSaleEntry> sale){
            Iterator <MediaAndCustomSaleEntry> it = sale.iterator();
            MediaSalesHandle salesHandle = BeansClass.mediaSalesHandle();
            AddMediaSaleFormController addMediaSaleFormController = BeansClass.addMediaSaleFormController();
            
            while(it.hasNext()){
                MediaAndCustomSaleEntry mediaAndCustomSaleEntry = it.next();
                mediaAndCustomSaleEntry.setSaleid(getSaleId());
                boolean check = salesHandle.findIfExists(mediaAndCustomSaleEntry);
                if(check){
                    //already in                    
                    addMediaSaleFormController.displayMessage("Item has already been entered in the sale"
                            + "If you wish to ammend, delete this item and enter the item details again.");
                }
                else{
                    //not previously inserted
                    boolean insert = salesHandle.insertToDb(mediaAndCustomSaleEntry);
                    if(insert){
                        //successful
                        addMediaSaleFormController.displayMessage("Successful!");
                    }
                    else{
                        //unsuccessful
                        addMediaSaleFormController.displayMessage("System Error. Contact Support for details.");
                    }
                }
            }
    }
    
    public ArrayList<MediaAndCustomSaleEntry> findAll(){
        MediaSalesHandle mediaSalesHandle = BeansClass.mediaSalesHandle();
        ArrayList <MediaAndCustomSaleEntry> allItems = mediaSalesHandle.findAll();        
        return allItems;
    }
    
    public ArrayList<String> findAllTitles(){
        ArrayList <String> allTitles= new ArrayList();
        ArrayList <MediaAndCustomSaleEntry> allItems = findAll();
        Iterator<MediaAndCustomSaleEntry> iterator = allItems.iterator();
        while(iterator.hasNext()){
            MediaAndCustomSaleEntry element = iterator.next();
            allTitles.add(element.getTitle());
        }
        return allTitles;
    }
    
    public ArrayList<MediaAndCustomSaleEntry> findOneSale(String saleId){
        MediaSalesHandle mediaSalesHandle = BeansClass.mediaSalesHandle();
        ArrayList <MediaAndCustomSaleEntry> sale = mediaSalesHandle.findOneSale(saleId);
        return sale;
    }
    
    public boolean findIfExists(MediaAndCustomSaleEntry mediaAndCustomSaleEntry ){
        MediaSalesHandle salesHandle = BeansClass.mediaSalesHandle();
        return salesHandle.findIfExists(mediaAndCustomSaleEntry);
    }
    
    public ArrayList<MediaAndCustomSaleEntry> findBetweenDatesEntries(LocalDate dateStart,LocalDate dateEnd){
        MediaSalesHandle mediaSalesHandle = BeansClass.mediaSalesHandle();
        HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
        
        Date startDate = handleMainLogic.convertToUtilDate(dateStart);
        Date endDate = handleMainLogic.convertToUtilDate(dateEnd);
        
        ArrayList<MediaAndCustomSaleEntry> entries = mediaSalesHandle.findBetweenDates(startDate, endDate);
        return entries;
    }
    
    public ArrayList<MediaAndCustomSaleEntry> findThisDatesEntries(LocalDate localDate){
        MediaSalesHandle mediaSalesHandle = BeansClass.mediaSalesHandle();
        HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
        
        Date date = handleMainLogic.convertToUtilDate(localDate);
        
        ArrayList<MediaAndCustomSaleEntry> entries = mediaSalesHandle.findThisDatesEntries(date);
        return entries;
    }
    
    public java.sql.Date changeLocalDateToDate(LocalDate date){
        HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
        return handleMainLogic.convertToSqlDate(date);
    }

    public void deleteEntry(MediaAndCustomSaleEntry mediaAndCustomSaleEntry){
        MediaSalesHandle salesHandle = BeansClass.mediaSalesHandle();
        boolean del = salesHandle.delete(mediaAndCustomSaleEntry);
        
        AddMediaSaleFormController addMediaSaleFormController = BeansClass.addMediaSaleFormController();
        if(del){
                //successful
                addMediaSaleFormController.displayMessage("Successful!");
            }
            else{
                //unsuccessful
                addMediaSaleFormController.displayMessage("System Error. Contact Support for details.");
            }
    }
}
