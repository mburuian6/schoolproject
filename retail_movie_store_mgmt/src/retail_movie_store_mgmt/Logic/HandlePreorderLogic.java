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
import retail_movie_store_mgmt.User;
import retail_movie_store_mgmt.commonUtil.DateTime;
import retail_movie_store_mgmt.database.PreorderHandle;
import retail_movie_store_mgmt.preorders.OrderDate;
import retail_movie_store_mgmt.preorders.Preorder;

/**
 *
 * @author Ian Mburu
 */
public class HandlePreorderLogic {
    
    public String generatePreorderId(String customerName){
        DateTime dtime = BeansClass.dateTime();
        LocalDateTime todayDateTime = dtime.getTodayDateTime();
        
        String id = customerName +"-"+ todayDateTime;
        return id;
    }
    
    public boolean checkIfPickUpDateIsPassed(Preorder preorder){
        //get current date
        DateTime dateTime = BeansClass.dateTime();
        LocalDate todayDate = dateTime.getTodayDate();
        
        try{
            return todayDate.isAfter(preorder.getPickupDate());
        }
        catch(Exception e){
            return false;
        }
    }
    
    public Preorder appendPreorderDetails(
            Preorder preorder,
            String id,
            String customer_name,
            LocalDate orderDate,
            LocalDate pickupDate,
            String movies_list,
            String shows_list,
            String software_list,
            String peripheral,
            String status
    ){
        preorder.setId(id);
        preorder.setCustomer_name(customer_name);
        preorder.setOrderDate(orderDate);
        preorder.setPickupDate(pickupDate);
        preorder.setMovies_list(movies_list);
        preorder.setShows_list(shows_list);
        preorder.setSoftware_list(software_list);
        preorder.setPeripheral(peripheral);
        preorder.setStatus(status); //status=pending/done
        
        try{
            preorder.setProfile(User.loggedin.getUsername());
        }
        catch(NullPointerException ne){
            preorder.setProfile("user");
        }
        return preorder;
    }
    
    public boolean checkIfExists(Preorder preorder){
        PreorderHandle preorderHandle = BeansClass.preorderHandle();
        List<Preorder> preorderList = preorderHandle.findPreorderEntry(preorder.getId());
        if(preorderList.isEmpty() || preorderList == null){
            return false;
        }
        else{
            return true;
        }
    }
    
    public Preorder findOne(String id){
        PreorderHandle preorderHandle = BeansClass.preorderHandle();
        List<Preorder> preorderList = preorderHandle.findPreorderEntry(id);
        if(preorderList.isEmpty()){
            return null;
        }
        else{
            Preorder foundPreorder = preorderList.get(0);
            return foundPreorder;
        }
    }
    
    public String insertPreorder(Preorder preorder){
        PreorderHandle preorderHandle = BeansClass.preorderHandle();
        
        boolean checkIfTodoExists = checkIfExists(preorder);
        if(checkIfTodoExists){
            return("Error!Preorder has already been inserted");
        }
        else{
            boolean insert = preorderHandle.insertToDb(preorder);
            if(insert){
                return("Successful Insertion!");
            }
            else{
                return("Error!Please contact support for help");
            }
        }
    }
    
    public String updatePreorder(Preorder preorder){
        PreorderHandle preorderHandle = BeansClass.preorderHandle();
                
        boolean update = preorderHandle.updateInDb(preorder);
        if(update){
            return("Successful Update!");
        }
        else{
            return("Error!Please contact support for help");
        }
    }
    
    public boolean checkStatusDone(String id){
        PreorderHandle preorderHandle = BeansClass.preorderHandle();
        
        Preorder preorderDb = findOne(id);
        if(preorderDb.getStatus().equals("pending")){
            return false;
        }
        else{
            return true;
        }
    }
    
    public ArrayList<Preorder> getAllEntries(){
        ArrayList<Preorder> allPreorderList = new ArrayList();
        PreorderHandle preorderHandle = BeansClass.preorderHandle();
        Iterable<Preorder> allPreorderIterable = preorderHandle.findAllEntries();
        
        Iterator <Preorder>allPreorderIterator = allPreorderIterable.iterator();
        while(allPreorderIterator.hasNext()){
            allPreorderList.add(allPreorderIterator.next());
        }
        
        allPreorderList.sort(Comparator.comparing((Preorder o) -> o.getOrderDate()).reversed());
        return allPreorderList;
    }
    
    
    public String deletePreorder(Preorder preorder){
        PreorderHandle preorderHandle = BeansClass.preorderHandle();
        
        //del from db
        boolean del = preorderHandle.deleteFromDb(preorder);

        if(del){
            return("Successful Deletion!");
        }
        else{
            return("Error!Please contact support for help");
        }
    }
    
    public ArrayList<OrderDate> getAllPreorderDates(){
        ArrayList<OrderDate> allPreorderDatesList = new ArrayList();
        PreorderHandle preorderHandle = BeansClass.preorderHandle();
        Iterable<OrderDate> allPreorderDatesIterable = preorderHandle.findAllPreorderDates();
        int index = 0;
        
        Iterator <OrderDate>allPreorderDatesIterator = allPreorderDatesIterable.iterator();
        while(allPreorderDatesIterator.hasNext()){
            OrderDate next = allPreorderDatesIterator.next();
            if(allPreorderDatesList.contains(next)){
                //pass
            }
            else{
                allPreorderDatesList.add(index,next);
                index=index+1;
            }
        }
        return allPreorderDatesList;
    }
    
    public LinkedHashMap<String, ArrayList> getAllItemsPerDate() {
        ArrayList<OrderDate> orderDatesList = getAllPreorderDates();
        ArrayList<Preorder> preorderList = getAllEntries();
        int index=0;
        
        LinkedHashMap <String,ArrayList> hashDates = new LinkedHashMap();
        
        for(OrderDate orderDate : orderDatesList){
            LocalDate date = orderDate.getOrderDate();
            ArrayList<Preorder> dateItems = new ArrayList();
            for(Preorder preorder: preorderList){
                if(date.equals(preorder.getOrderDate())){
                    dateItems.add(preorder);
                }
            }
            hashDates.putIfAbsent(date.toString(), dateItems);
        }
        return hashDates;
    }
    
}
