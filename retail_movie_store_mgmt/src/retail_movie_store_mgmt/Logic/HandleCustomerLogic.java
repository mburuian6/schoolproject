/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.Logic;

import BeansPackage.BeansClass;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import retail_movie_store_mgmt.Customer.Customer;
import retail_movie_store_mgmt.commonUtil.DateTime;
import retail_movie_store_mgmt.database.CustomerHandle;

/**
 *
 * @author Ian Mburu
 */
public class HandleCustomerLogic {
    
    
    public String generateCustomerId(String username){
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
    
    //attaches properties to the customer object
    public Customer appendCustomerDetails(
            Customer customer,
            String id, 
            String username, 
            String customer_name,
            String gender,
            String movie_likes,
            String show_likes
    ){
        customer.setId(id);
        customer.setUsername(username);
        customer.setCustomer_name(customer_name);
        customer.setGender(gender);
        customer.setMovie_likes(movie_likes);
        customer.setShow_likes(show_likes);
        
        return customer;
    }
    
    //checks if customer is already inserted
    public boolean checkIfExists(Customer customer){
        CustomerHandle customerHandle = BeansClass.customerHandle();
        List<Customer> customerList = customerHandle.findCustomer(customer.getUsername());
        if(customerList.isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }
    
    //inserts new user to db
    public String insertCustomer(Customer customer){
        CustomerHandle customerHandle = BeansClass.customerHandle();
        
        boolean checkIfUserExists = checkIfExists(customer);
        if(checkIfUserExists){
            return "Error!User has already been inserted";
        }
        else{
            boolean insert = customerHandle.insertToDb(customer);
            if(insert){
                return("Successful Insertion!");
            }
            else{
                return("Error!Please contact support for help");
            }
        }
    }
    
    //update customer details
    public String updateCustomer(Customer customer){
        CustomerHandle customerHandle = BeansClass.customerHandle();
                
        boolean update = customerHandle.update(customer);
        if(update){
            return("Successful Update!");
        }
        else{
            return("Error!Please contact support for help");
        }
    }
    
    //retrieve all customers
    public ArrayList<Customer> getAllCustomers(){
        ArrayList<Customer> allCustomersList = new ArrayList();
        CustomerHandle customerHandle = BeansClass.customerHandle();
        Iterable<Customer> allCustomersIterable = customerHandle.findAllCustomers();
        
        Iterator <Customer>allCustomerIterator = allCustomersIterable.iterator();
        while(allCustomerIterator.hasNext()){
            allCustomersList.add(allCustomerIterator.next());
        }
        
        return allCustomersList;
    }
    
    //checks if todo is already inserted
    public Customer findOne(String username){
        Customer customer = BeansClass.customer();
        customer.setUsername(username);
        
        CustomerHandle customerHandle = BeansClass.customerHandle();
        List<Customer> customerList = customerHandle.findCustomer(customer.getUsername());
        if(customerList.isEmpty() || customerList == null){
            return null;
        }
        else{
            Customer foundCustomer = customerList.get(0);
            return foundCustomer;
        }
    }
    
    public String deleteCustomer(Customer customer){
        CustomerHandle customerHandle = BeansClass.customerHandle();
        
        //del from db
        boolean del = customerHandle.deleteFromDb(customer);

        if(del){
            return("Successful Deletion!");
        }
        else{
            return("Error!Please contact support for help");
        }
    }
    
    public String displayInfo(String message){
        return message;
    }
    
    public String displayError(String error){
        return error;
    }
    
    public String[] getMovieLikes(Customer customer){
        String moviesStr = customer.getMovie_likes();
        return  moviesStr.split(",");        
    }
    
    public String[] getShowsLikes(Customer customer){
        String showsStr = customer.getShow_likes();
        String[]showsArr = showsStr.split(",");
        return showsArr;
    }
}
