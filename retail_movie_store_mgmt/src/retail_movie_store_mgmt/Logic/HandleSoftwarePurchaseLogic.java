/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.Logic;

import BeansPackage.BeansClass;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import retail_movie_store_mgmt.User;
import retail_movie_store_mgmt.commonUtil.DateTime;
import retail_movie_store_mgmt.database.SoftwarePurchaseHandle;
import retail_movie_store_mgmt.product.Software;
import retail_movie_store_mgmt.purchases.PurchaseDate;
import retail_movie_store_mgmt.purchases.software.SoftwarePurchase;

/**
 *
 * @author Ian Mburu
 */
public class HandleSoftwarePurchaseLogic {
    
    public String generatePurchaseId(String title){
        DateTime dtime = BeansClass.dateTime();
        LocalDateTime todayDateTime = dtime.getTodayDateTime();
        
        String id = title +"-"+ todayDateTime+"-software";
        return id;
    }
    
    public LocalDate getDate(){
        DateTime dtime = BeansClass.dateTime();
        return dtime.getTodayDate();
    }
    
    public String checkIntInput(String[]params,String[]labels){        
        for(int i=0;i<params.length;i++){
            String input = params[i];
            boolean found = checkForAlphasInInt(input);
            if(found){
                return labels[i];
            }
        }
        return null;
    }
    
    public boolean checkForAlphasInInt(String input){
        String lowercase = "[a-z]";
        String uppercase = "[A-Z]";
        String specialChars = "-";
        Pattern patt1 = Pattern.compile(lowercase);
        Pattern patt2 = Pattern.compile(uppercase);
        Pattern patt3 = Pattern.compile(specialChars);
        
        Matcher matt1 = patt1.matcher(input);
        Matcher matt2 = patt2.matcher(input);
        Matcher matt3 = patt3.matcher(input);
                
        if(matt1.find()){
            return true;
        }
        else if(matt2.find()){
            return true;
        }
        else if(matt3.find()){
            return true;
        }
        return false;
    }
    
    public SoftwarePurchase appendPurchaseDetails(
            SoftwarePurchase softwarePurchase,
            String id,
            String title,
            String strQuantity,
            String strPrice
    ){
        //get today's date
        LocalDate today = getDate();
        
        //reshape the data
        HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
        int quantity = handleMainLogic.convertStrToInt(strQuantity);
        double price = handleMainLogic.convertStrToDouble(strPrice);
        
        //get total
        double total = price*quantity;
        
        //assign for this entry
        softwarePurchase.setId(id);
        softwarePurchase.setTitle(title);
        softwarePurchase.setQuantity(quantity);
        softwarePurchase.setPrice(price);
        softwarePurchase.setTotal(total);
        softwarePurchase.setDate(today);
        try{
            softwarePurchase.setProfile(User.loggedin.getUsername());
        }
        catch(Exception e){
            softwarePurchase.setProfile("user");
        }
        
        return softwarePurchase;
    }
    
    public boolean checkIfExists(SoftwarePurchase softwarePurchase){
        SoftwarePurchaseHandle softwarePurchaseHandle = BeansClass.softwarePurchaseHandle();
        List<SoftwarePurchase> softwarePurchaseList = softwarePurchaseHandle.findSoftwarePurchaseEntry(softwarePurchase.getId());
        if(softwarePurchaseList.isEmpty() || softwarePurchaseList == null){
            return false;
        }
        else{
            return true;
        }
    }
    
    public SoftwarePurchase findOne(SoftwarePurchase softwarePurchase){
        SoftwarePurchaseHandle softwarePurchaseHandle = BeansClass.softwarePurchaseHandle();
        List<SoftwarePurchase> softwarePurchaseList = softwarePurchaseHandle.findSoftwarePurchaseEntry(softwarePurchase.getId());
        if(softwarePurchaseList.isEmpty()){
            return softwarePurchaseList.get(0);
        }
        else{
            return null;
        }
    }
    
    public String insertToDb(SoftwarePurchase softwarePurchase){
        SoftwarePurchaseHandle softwarePurchaseHandle = BeansClass.softwarePurchaseHandle();
        
        boolean check =checkIfExists(softwarePurchase);
        if(check){
            return ("Item already exists");
        }
        else{
            boolean insert = softwarePurchaseHandle.insertToDb(softwarePurchase);
            if(insert){
                return("Successful insertion");
            }
        }
        return null;
    }
    
    public ArrayList<SoftwarePurchase>  getAllEntries(){
        SoftwarePurchaseHandle softwarePurchaseHandle = BeansClass.softwarePurchaseHandle();
        ArrayList<SoftwarePurchase> allEntriesList = new ArrayList();
        Iterable<SoftwarePurchase> allEntries = softwarePurchaseHandle.findAllEntries();
        Iterator<SoftwarePurchase> allEntriesIt = allEntries.iterator();
        
        while(allEntriesIt.hasNext()){
            allEntriesList.add(allEntriesIt.next());
        }
        
        allEntriesList.sort(Comparator.comparing((SoftwarePurchase o) -> o.getDate()).reversed());
        return allEntriesList;
    }
    
    public ArrayList<SoftwarePurchase>  getEntriesBetweenDates(LocalDate lcldateStart,LocalDate lcldateEnd){
        HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
        java.sql.Date startDate = handleMainLogic.convertToSqlDate(lcldateStart);
        java.sql.Date endDate = handleMainLogic.convertToSqlDate(lcldateEnd);
        
        SoftwarePurchaseHandle softwarePurchaseHandle = BeansClass.softwarePurchaseHandle();
        ArrayList<SoftwarePurchase> allEntriesList = new ArrayList();
        Iterable<SoftwarePurchase> allEntries = softwarePurchaseHandle.findBetweenDateEntries(startDate, endDate);
        Iterator<SoftwarePurchase> allEntriesIt = allEntries.iterator();
        
        while(allEntriesIt.hasNext()){
            allEntriesList.add(allEntriesIt.next());
        }
        
        allEntriesList.sort(Comparator.comparing((SoftwarePurchase o) -> o.getDate()).reversed());
        return allEntriesList;
    }
    
    public ArrayList<SoftwarePurchase> getThisDateEntries(LocalDate localDate){
        HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
        java.sql.Date date = handleMainLogic.convertToSqlDate(localDate);
        
        SoftwarePurchaseHandle softwarePurchaseHandle = BeansClass.softwarePurchaseHandle();
        ArrayList<SoftwarePurchase> allEntriesList = new ArrayList();
        Iterable<SoftwarePurchase> allEntries = softwarePurchaseHandle.findThisDateEntries(date);
        Iterator<SoftwarePurchase> allEntriesIt = allEntries.iterator();
        
        while(allEntriesIt.hasNext()){
            allEntriesList.add(allEntriesIt.next());
        }
        
        allEntriesList.sort(Comparator.comparing((SoftwarePurchase o) -> o.getDate()).reversed());
        return allEntriesList;
    }
    
    
    
    public ArrayList<SoftwarePurchase> getTodayEntries(){
        SoftwarePurchaseHandle softwarePurchaseHandle = BeansClass.softwarePurchaseHandle();
        ArrayList<SoftwarePurchase> allEntriesList = new ArrayList();
        List<SoftwarePurchase> allEntries = softwarePurchaseHandle.findTodayEntries(getDate());
        
        for(SoftwarePurchase softwarePurchase: allEntries){
            allEntriesList.add(softwarePurchase);
        }
        
        allEntriesList.sort(Comparator.comparing((SoftwarePurchase o) -> o.getDate()).reversed());
        return allEntriesList;
    }
    
    public ArrayList<String> getAllSoftwareTitles(){
        SoftwareLogic instance = BeansClass.softwareLogic();
        ArrayList<Software> allEntriesList = instance.fetchAllSoftwareObjects();
        ArrayList<String> allEntriesNames = new ArrayList();
        
        for(Software software : allEntriesList){
            allEntriesNames.add(software.getTitle());
        }
        return allEntriesNames;
    }
    
    public boolean delete(SoftwarePurchase softwarePurchase){
        SoftwarePurchaseHandle softwarePurchaseHandle = BeansClass.softwarePurchaseHandle();
        boolean del = softwarePurchaseHandle.deleteFromDb(softwarePurchase);
        
        if(del){
            return true;
        }
        else{
            return false;
        }
    }
    
    public ArrayList<PurchaseDate> getAllSoftwareDates(){
        ArrayList<PurchaseDate> allSoftwareDatesList = new ArrayList();
        SoftwarePurchaseHandle softwarePurchaseHandle = BeansClass.softwarePurchaseHandle();
        Iterable<PurchaseDate> allSoftwareDatesIterable = softwarePurchaseHandle.findAllDateEntries();
        int index = 0;
        
        Iterator <PurchaseDate>allSoftwareDatesIterator = allSoftwareDatesIterable.iterator();
        while(allSoftwareDatesIterator.hasNext()){
            PurchaseDate next = allSoftwareDatesIterator.next();
            if(allSoftwareDatesList.contains(next)){
                //pass
            }
            else{
                allSoftwareDatesList.add(index,next);
                index=index+1;
            }
        }
        return allSoftwareDatesList;
    }
    
    public LinkedHashMap<String, ArrayList> getAllItemsPerDate() {
        ArrayList<PurchaseDate> purchaseDatesList = getAllSoftwareDates();
        ArrayList<SoftwarePurchase> softwarePurchases = getAllEntries();
        int index=0;
        
        LinkedHashMap <String,ArrayList> hashDates = new LinkedHashMap();
        
        for(PurchaseDate purchaseDate : purchaseDatesList){
            LocalDate date = purchaseDate.getDate();
            ArrayList<SoftwarePurchase> dateItems = new ArrayList();
            for(SoftwarePurchase softwarePurchase: softwarePurchases){
                if(date.equals(softwarePurchase.getDate())){
                    dateItems.add(softwarePurchase);
                }
            }
            hashDates.putIfAbsent(date.toString(), dateItems);
        }
        return hashDates;
    }
    
    
    
}
