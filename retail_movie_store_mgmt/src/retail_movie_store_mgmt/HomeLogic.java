/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt;

import BeansPackage.BeansClass;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import retail_movie_store_mgmt.Customer.Customer;
import retail_movie_store_mgmt.Logic.HandleCustomerLogic;
import retail_movie_store_mgmt.Logic.HandlePreorderLogic;
import retail_movie_store_mgmt.Logic.HandleProductLogic;
import retail_movie_store_mgmt.Logic.HandleProfileLogic;
import retail_movie_store_mgmt.Logic.HandleTodoLogic;
import retail_movie_store_mgmt.Logic.SoftwareLogic;
import retail_movie_store_mgmt.Sales.MediaAndCustomSaleEntry;
import retail_movie_store_mgmt.Sales.SoftwareSaleEntry;
import retail_movie_store_mgmt.commonUtil.DateTime;
import retail_movie_store_mgmt.database.nosql.MediaSalesHandle;
import retail_movie_store_mgmt.database.nosql.SoftwareSalesHandle;
import retail_movie_store_mgmt.preorders.Preorder;
import retail_movie_store_mgmt.product.Product;
import retail_movie_store_mgmt.product.Software;
import retail_movie_store_mgmt.todo.Todo;

/**
 *
 * @author Ian Mburu
 */
public class HomeLogic {
    
    //show today's todo list
    public ArrayList<String> bringTodaysTodoList(){
        HandleTodoLogic handleTodoLogic = BeansClass.handleTodoLogic();
        ArrayList<Todo> allEntriesList = handleTodoLogic.getAllEntries();
        ArrayList<String> todayList = new ArrayList();
        
        Iterator <Todo> allEntriesIterator = allEntriesList.iterator();
        
        while(allEntriesIterator.hasNext()){
            
                Todo todo = allEntriesIterator.next();
                
                boolean check = checkIfTodoIsForToday(todo);
                if(check){
                    todayList.add(todo.getTitle());
                }
                else{
                    //pass
                }
            }
        return todayList;
    }
    
    public boolean checkIfTodoIsForToday(Todo todo){
        //get current date
        DateTime dateTime = BeansClass.dateTime();
        LocalDate todayDate = dateTime.getTodayDate();
        
        try{ 
            return todayDate.isEqual(todo.getDate());            
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        
    }
    
    public ObservableList<PieChart.Data> getUserRoleStats(){
        HandleProfileLogic instance = BeansClass.handleProfileLogic();
        ArrayList<User> allUsers = instance.getAllUsers();
        
        HashMap<String,Double> roles = new HashMap(HandleProfileLogic.getRoles().length); 
        double admin = 0;
        double manager = 0;
        double employee = 0;
                
        for(User user : allUsers){
            if(user.getRole().equals("admin") ){
                admin = admin+1;
                if(roles.containsKey("admin")){                    
                    roles.remove("admin");
                    roles.put("admin", admin);
                }
                else{
                    roles.put("admin", admin);
                }
            }
            else if(user.getRole().equals("manager")){
                manager = manager+1;
                if(roles.containsKey("admin")){
                    roles.remove("manager");
                    roles.put("manager", manager);
                }
                else{
                    roles.put("manager", manager);
                }
            }
            if(user.getRole().equals("employee")){
                employee = employee+1;
                if(roles.containsKey("admin")){
                    roles.remove("employee");
                    roles.put("employee", employee);
                }
                else{
                    roles.put("employee", employee);
                }
            }
        }
        
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        Set<Map.Entry<String, Double>> set = roles.entrySet();
        for(Map.Entry<String, Double> me : set) {
           data.add(new PieChart.Data(me.getKey(), me.getValue()));
        }
        
        return data;
    }
    
    public HashMap getUserRoleMap(){
        HandleProfileLogic instance = BeansClass.handleProfileLogic();
        ArrayList<User> allUsers = instance.getAllUsers();
        
        HashMap<String,Double> roles = new HashMap(HandleProfileLogic.getRoles().length); 
        double admin = 0;
        double manager = 0;
        double employee = 0;
                
        for(User user : allUsers){
            if(user.getRole().equals("admin") ){
                admin = admin+1;
                if(roles.containsKey("admin")){                    
                    roles.remove("admin");
                    roles.put("admin", admin);
                }
                else{
                    roles.put("admin", admin);
                }
            }
            else if(user.getRole().equals("manager")){
                manager = manager+1;
                if(roles.containsKey("admin")){
                    roles.remove("manager");
                    roles.put("manager", manager);
                }
                else{
                    roles.put("manager", manager);
                }
            }
            if(user.getRole().equals("employee")){
                employee = employee+1;
                if(roles.containsKey("admin")){
                    roles.remove("employee");
                    roles.put("employee", employee);
                }
                else{
                    roles.put("employee", employee);
                }
            }
        }
                
        return roles;
    }
    
    //append sales data to each product
    public ObservableList<PieChart.Data> getMediaSalesData(){
        //getAllProducts
        HandleProductLogic handleProductLogic = BeansClass.handleProductLogic();
        ArrayList<Product> allProductObjects = handleProductLogic.fetchAllProductObjects();
                
        //get sales data
        MediaSalesHandle mediaSalesHandle = BeansClass.mediaSalesHandle();
        ArrayList<MediaAndCustomSaleEntry> allSales = mediaSalesHandle.findAll();
        
        HashMap<String,Double> entries = new HashMap(allProductObjects.size());
        
        for(Product product: allProductObjects){
            double amount=0;
            for(MediaAndCustomSaleEntry sale: allSales){
                if(sale.getTitle().equals(product.getTitle())){
                    //increment amount
                    amount= amount+1;
                    //confirmed sale is of that product
                    if(entries.containsKey(product.getTitle())){
                        entries.remove(product.getTitle());
                        entries.put(product.getTitle(), amount);
                    }else{
                        //no previous entry
                        entries.put(product.getTitle(), amount);
                    }
                }
            }
        }
        
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        Set<Map.Entry<String, Double>> set = entries.entrySet();
        for(Map.Entry<String, Double> me : set) {
           data.add(new PieChart.Data(me.getKey(), me.getValue()));
        }
        
        return data;
    }
    
    //append sales data to each product
    public ObservableList<PieChart.Data> getSoftwareSalesData(){
        //getAllProducts
        SoftwareLogic softwareLogic = BeansClass.softwareLogic();
        ArrayList<Software> softwareObjects = softwareLogic.fetchAllSoftwareObjects();
                
        //get sales data
        SoftwareSalesHandle sotfwareSalesHandle = BeansClass.softwareSalesHandle();
        ArrayList<SoftwareSaleEntry> allEntries = sotfwareSalesHandle.findAll();
        
        HashMap<String,Double> entries = new HashMap(allEntries.size());
        
        for(Software software: softwareObjects){
            double amount=0;
            for(SoftwareSaleEntry sale: allEntries){
                if(sale.getTitle().equals(software.getTitle())){
                    //increment amount
                    amount= amount+1;
                    //confirmed sale is of that product
                    if(entries.containsKey(software.getTitle())){
                        entries.remove(software.getTitle());
                        entries.put(software.getTitle(), amount);
                    }else{
                        //no previous entry
                        entries.put(software.getTitle(), amount);
                    }
                }
            }
        }
        
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        Set<Map.Entry<String, Double>> set = entries.entrySet();
        for(Map.Entry<String, Double> me : set) {
           data.add(new PieChart.Data(me.getKey(), me.getValue()));
        }
        
        return data;
    }
    
    public static void main(String[] args) {
        HomeLogic h = new HomeLogic();
        h.getMediaSalesData();
    }

    public int getNumberOfUsers(){
        HandleProfileLogic instance = BeansClass.handleProfileLogic();
        ArrayList<User> allUsers = instance.getAllUsers();
        
        if(allUsers != null)  return allUsers.size();
        
        return 0;
    }
    
    public int getNumberOfCustomers(){
        HandleCustomerLogic instance = BeansClass.handleCustomerLogic();
        ArrayList<Customer> allCustomers = instance.getAllCustomers();
        
        if(allCustomers != null)  return allCustomers.size();
        
        return 0;
    }
    
    public int getNumberOfPendingPreorders(){
        HandlePreorderLogic instance = BeansClass.handlePreorderLogic();
        ArrayList<Preorder> allPreorders = instance.getAllEntries();
        int pending = 0;
        
        for(Preorder preorder: allPreorders){
            if(preorder.getStatus().equals("pending")){
                pending=pending+1;
            }
        }
        
        return pending;
    }
    
    
    
}
