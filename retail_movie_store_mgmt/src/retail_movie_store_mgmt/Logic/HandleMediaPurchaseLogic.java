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
import retail_movie_store_mgmt.database.MediaPurchaseHandle;
import retail_movie_store_mgmt.purchases.PurchaseDate;
import retail_movie_store_mgmt.purchases.media.MediaPurchase;
import retail_movie_store_mgmt.ui.DialogPane;

/**
 *
 * @author Ian Mburu
 */
public class HandleMediaPurchaseLogic {
    
    public String generatePurchaseId(String title){
        DateTime dtime = BeansClass.dateTime();
        LocalDateTime todayDateTime = dtime.getTodayDateTime();
        
        String id = title +"-"+ todayDateTime+"-media";
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
    
    public MediaPurchase appendPurchaseDetails(
            MediaPurchase mediaPurchase,
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
        mediaPurchase.setId(id);
        mediaPurchase.setTitle(title);
        mediaPurchase.setQuantity(quantity);
        mediaPurchase.setPrice(price);
        mediaPurchase.setTotal(total);
        mediaPurchase.setDate(today);
        mediaPurchase.setProfile(User.loggedin.getUsername());
        
        return mediaPurchase;
    }
    
    public boolean checkIfExists(MediaPurchase mediaPurchase){
        MediaPurchaseHandle mediaPurchaseHandle = BeansClass.mediaPurchaseHandle();
        List<MediaPurchase> mediaPurchaseList = mediaPurchaseHandle.findMediaPurchaseEntry(mediaPurchase.getId());
        if(mediaPurchaseList.isEmpty() || mediaPurchaseList == null){
            return false;
        }
        else{
            return true;
        }
    }
    
    public MediaPurchase findOne(MediaPurchase mediaPurchase){
        MediaPurchaseHandle mediaPurchaseHandle = BeansClass.mediaPurchaseHandle();
        List<MediaPurchase> mediaPurchaseList = mediaPurchaseHandle.findMediaPurchaseEntry(mediaPurchase.getId());
        if(mediaPurchaseList.isEmpty()){
            return mediaPurchaseList.get(0);
        }
        else{
            return null;
        }
    }
    
    public boolean insertToDb(MediaPurchase mediaPurchase){
        MediaPurchaseHandle mediaPurchaseHandle = BeansClass.mediaPurchaseHandle();
        
        boolean check =checkIfExists(mediaPurchase);
        if(check){
            displayInfo("Item already exists");
        }
        else{
            boolean insert = mediaPurchaseHandle.insertToDb(mediaPurchase);
            if(insert){
                displayInfo("Successful insertion");
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<MediaPurchase>  getAllEntries(){
        MediaPurchaseHandle mediaPurchaseHandle = BeansClass.mediaPurchaseHandle();
        ArrayList<MediaPurchase> allEntriesList = new ArrayList();
        Iterable<MediaPurchase> allEntries = mediaPurchaseHandle.findAllEntries();
        Iterator<MediaPurchase> allEntriesIt = allEntries.iterator();
        
        while(allEntriesIt.hasNext()){
            allEntriesList.add(allEntriesIt.next());
        }
        
        allEntriesList.sort(Comparator.comparing((MediaPurchase o) -> o.getDate()).reversed());
        return allEntriesList;
    }
    
    public ArrayList<MediaPurchase>  getEntriesBetweenDates(LocalDate lcldateStart,LocalDate lcldateEnd){
        HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
        java.sql.Date startDate = handleMainLogic.convertToSqlDate(lcldateStart);
        java.sql.Date endDate = handleMainLogic.convertToSqlDate(lcldateEnd);
        
        MediaPurchaseHandle mediaPurchaseHandle = BeansClass.mediaPurchaseHandle();
        ArrayList<MediaPurchase> allEntriesList = new ArrayList();
        Iterable<MediaPurchase> allEntries = mediaPurchaseHandle.findBetweeenDateEntries(startDate, endDate);
        Iterator<MediaPurchase> allEntriesIt = allEntries.iterator();
        
        while(allEntriesIt.hasNext()){
            allEntriesList.add(allEntriesIt.next());
        }
        
        allEntriesList.sort(Comparator.comparing((MediaPurchase o) -> o.getDate()).reversed());
        return allEntriesList;
    }
    
    public ArrayList<MediaPurchase> getThisDateEntries(LocalDate localDate){
        HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
        java.sql.Date date = handleMainLogic.convertToSqlDate(localDate);
        
        MediaPurchaseHandle mediaPurchaseHandle = BeansClass.mediaPurchaseHandle();
        ArrayList<MediaPurchase> allEntriesList = new ArrayList();
        Iterable<MediaPurchase> allEntries = mediaPurchaseHandle.findThisDateEntries(date);
        Iterator<MediaPurchase> allEntriesIt = allEntries.iterator();
        
        while(allEntriesIt.hasNext()){
            allEntriesList.add(allEntriesIt.next());
        }
        
        allEntriesList.sort(Comparator.comparing((MediaPurchase o) -> o.getDate()).reversed());
        return allEntriesList;
    }
    
    public ArrayList<MediaPurchase> getTodayEntries(){
        MediaPurchaseHandle mediaPurchaseHandle = BeansClass.mediaPurchaseHandle();
        ArrayList<MediaPurchase> allEntriesList = new ArrayList();
        List<MediaPurchase> allEntries = mediaPurchaseHandle.findTodayEntries(getDate());
        
        for(MediaPurchase mediaPurchase: allEntries){
            allEntriesList.add(mediaPurchase);
        }
        
        allEntriesList.sort(Comparator.comparing((MediaPurchase o) -> o.getDate()).reversed());
        return allEntriesList;
    }
    
    
    
    public ArrayList<String> getAllMediaTitles(){
        HandleProductLogic instance = BeansClass.handleProductLogic();
        ArrayList<String> allEntriesList = instance.fetchAllProducts();
        return allEntriesList;
    }
    
    public boolean delete(MediaPurchase mediaPurchase){
        MediaPurchaseHandle mediaPurchaseHandle = BeansClass.mediaPurchaseHandle();
        boolean del = mediaPurchaseHandle.deleteFromDb(mediaPurchase);
        
        if(del){
            displayInfo("Successful Deletion!");
            return true;
        }
        else{
            displayError("Error!Please contact support for help");
            return false;
        }
    }
    
        public ArrayList<PurchaseDate> getAllMediaDates(){
        ArrayList<PurchaseDate> allMediaDatesList = new ArrayList();
        MediaPurchaseHandle mediaPurchaseHandle = BeansClass.mediaPurchaseHandle();
        Iterable<PurchaseDate> allMediaDatesIterable = mediaPurchaseHandle.findAllDateEntries();
        int index = 0;
        
        Iterator <PurchaseDate>allMediaDatesIterator = allMediaDatesIterable.iterator();
        while(allMediaDatesIterator.hasNext()){
            PurchaseDate next = allMediaDatesIterator.next();
            if(allMediaDatesList.contains(next)){
                //pass
            }
            else{
                allMediaDatesList.add(index,next);
                index=index+1;
            }
        }
        return allMediaDatesList;
    }
    
    public LinkedHashMap<String, ArrayList> getAllItemsPerDate() {
        ArrayList<PurchaseDate> purchaseDatesList = getAllMediaDates();
        ArrayList<MediaPurchase> mediaPurchases = getAllEntries();
        int index=0;
        
        LinkedHashMap <String,ArrayList> hashDates = new LinkedHashMap();
        
        for(PurchaseDate purchaseDate : purchaseDatesList){
            LocalDate date = purchaseDate.getDate();
            ArrayList<MediaPurchase> dateItems = new ArrayList();
            for(MediaPurchase softwarePurchase: mediaPurchases){
                if(date.equals(softwarePurchase.getDate())){
                    dateItems.add(softwarePurchase);
                }
            }
            hashDates.putIfAbsent(date.toString(), dateItems);
        }
        return hashDates;
    }
    
    
    public void displayInfo(String message){
        DialogPane dp = BeansClass.dialogPane();
        dp.displayInfo(message);
    }
    
    public void displayError(String message){
        DialogPane dp = BeansClass.dialogPane();
        dp.displayError(message);
    }
    
}
