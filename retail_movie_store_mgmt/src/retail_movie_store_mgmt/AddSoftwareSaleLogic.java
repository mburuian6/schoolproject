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
import retail_movie_store_mgmt.Sales.SaleIdGen;
import retail_movie_store_mgmt.Sales.SoftwareSaleEntry;
import retail_movie_store_mgmt.database.nosql.SoftwareSalesHandle;

/**
 *
 * @author Ian Mburu
 */
public class AddSoftwareSaleLogic {
    
    String saleId;
    static String saleIdC;
    
    public String verifyNumInput(String[]params,String[]labels){
        HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
        return handleMainLogic.verifyNumInput(params, labels);
    }
    
    public String  generateSaleId(){
        SaleIdGen saleIdGen = BeansClass.saleIdGen();
        saleId = "s_"+saleIdGen.generateSaleId();
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
    
    public void appendDetailsToEntry(
            SoftwareSaleEntry softwareSaleEntry,
            String title,
            String strPrice,
            String strQuantity, 
            String strSub_total,
            String strSub_discount,
            String strSub_netTotal
    ){
        User user = BeansClass.user();
        
        HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
        double price = handleMainLogic.convertStrToDouble(strPrice);
        double sub_total = handleMainLogic.convertStrToDouble(strSub_total);;
        double sub_discount = handleMainLogic.convertStrToDouble(strSub_discount);;
        double sub_netTotal = handleMainLogic.convertStrToDouble(strSub_netTotal);;
        int quantity = handleMainLogic.convertStrToInt(strQuantity);
        
        softwareSaleEntry.setTitle(title);
        softwareSaleEntry.setPrice(price);
        softwareSaleEntry.setQuantity(quantity);
        softwareSaleEntry.setSub_total(sub_total);
        softwareSaleEntry.setSub_discount(sub_discount);
        softwareSaleEntry.setSub_netTotal(sub_netTotal);
        softwareSaleEntry.setSaleProfile(user.getCurrentUsername());
        softwareSaleEntry.setDate(new Date());
    }
    
    
    //db methods
    public void insert(ArrayList<SoftwareSaleEntry> sale){
        Iterator <SoftwareSaleEntry> it = sale.iterator();
        SoftwareSalesHandle salesHandle = BeansClass.softwareSalesHandle();
        AddSoftwareSaleFormController addSoftwareSaleFormController = BeansClass.addSoftwareSaleFormController();

        while(it.hasNext()){
            SoftwareSaleEntry softwareSaleEntry = it.next();
            softwareSaleEntry.setSaleid(getSaleId());
            boolean check = salesHandle.findIfExists(softwareSaleEntry);
            if(check){
                //already in                    
                addSoftwareSaleFormController.displayMessage("Item has already been entered in the sale"
                        + "If you wish to ammend, delete this item and enter the item details again.");
            }
            else{
                //not previously inserted
                boolean insert = salesHandle.insertToDb(softwareSaleEntry);
                if(insert){
                    //successful
                    addSoftwareSaleFormController.displayMessage("Successful!");
                }
                else{
                    //unsuccessful
                    addSoftwareSaleFormController.displayMessage("System Error. Contact Support for details.");
                }
            }
        }
    } //end of insert method
        
    public ArrayList<SoftwareSaleEntry> findAll(){
        SoftwareSalesHandle softwareSalesHandle = BeansClass.softwareSalesHandle();
        ArrayList <SoftwareSaleEntry> allItems = softwareSalesHandle.findAll();        
        return allItems;
    }
    
    public ArrayList<String> findAllTitles(){
        ArrayList <String> allTitles= new ArrayList();
        ArrayList <SoftwareSaleEntry> allItems = findAll();
        Iterator<SoftwareSaleEntry> iterator = allItems.iterator();
        while(iterator.hasNext()){
            SoftwareSaleEntry element = iterator.next();
            allTitles.add(element.getTitle());
        }
        return allTitles;
    }
    
    public ArrayList<SoftwareSaleEntry> findOneSale(String saleId){
        SoftwareSalesHandle softwareSalesHandle = BeansClass.softwareSalesHandle();
        ArrayList <SoftwareSaleEntry> sale = softwareSalesHandle.findOneSale(saleId);
        return sale;
    }
    
        public ArrayList<SoftwareSaleEntry> findBetweenDatesEntries(LocalDate dateStart,LocalDate dateEnd){
        SoftwareSalesHandle softwareSalesHandle = BeansClass.softwareSalesHandle();
        HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
        
        Date startDate = handleMainLogic.convertToUtilDate(dateStart);
        Date endDate = handleMainLogic.convertToUtilDate(dateEnd);
        
        ArrayList<SoftwareSaleEntry> entries = softwareSalesHandle.findBetweenDates(startDate, endDate);
        return entries;
    }
    
    public ArrayList<SoftwareSaleEntry> findThisDatesEntries(LocalDate localDate){
        SoftwareSalesHandle softwareSalesHandle = BeansClass.softwareSalesHandle();
        HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
        
        Date date = handleMainLogic.convertToUtilDate(localDate);
        
        ArrayList<SoftwareSaleEntry> entries = softwareSalesHandle.findThisDatesEntries(date);
        return entries;
    }
    
    public java.sql.Date changeLocalDateToDate(LocalDate date){
        HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
        return handleMainLogic.convertToSqlDate(date);
    }
    
    public boolean findIfExists(SoftwareSaleEntry softwareSaleEntry ){
        SoftwareSalesHandle softwareSalesHandle = BeansClass.softwareSalesHandle();
        return softwareSalesHandle.findIfExists(softwareSaleEntry);
    }

    public void deleteEntry(SoftwareSaleEntry softwareSaleEntry){
        SoftwareSalesHandle softwareSalesHandle = BeansClass.softwareSalesHandle();
        boolean del = softwareSalesHandle.delete(softwareSaleEntry);
        
        AddSoftwareSaleFormController addSoftwareSaleFormController = BeansClass.addSoftwareSaleFormController();
        if(del){
                //successful
                addSoftwareSaleFormController.displayMessage("Successful!");
            }
            else{
                //unsuccessful
                addSoftwareSaleFormController.displayMessage("System Error. Contact Support for details.");
            }
    }
}
