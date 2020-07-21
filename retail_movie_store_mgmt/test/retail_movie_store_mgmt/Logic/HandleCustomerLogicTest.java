/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.Logic;

import BeansPackage.BeansClass;
import java.util.ArrayList;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.After;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import retail_movie_store_mgmt.Customer.Customer;

/**
 *
 * @author Ian Mburu
 */
public class HandleCustomerLogicTest {
    
    public HandleCustomerLogicTest() {
    }

    @Before
    public void setup(){
//        Database_start_stop db = BeansClass.database_begin_end();
//        db.start_db();
    }
    
    @After
    public void tearDown(){
        //clean testUser
        Customer customer = BeansClass.customer();
        customer.setId("testId");
        
        HandleCustomerLogic instance = new HandleCustomerLogic(); 
        if(instance.checkIfExists(customer)){
            instance.deleteCustomer(customer);
        }
        
//        //close db
//        Database_start_stop db = BeansClass.database_begin_end();
//        db.stop_db();
    }
    /**
     * Test of generateCustomerId method, of class HandleCustomerLogic.
     */
    @Test
    public void testGenerateCustomerId() {
        System.out.println("generateCustomerId");
        String username = "testUsername";
        
        HandleCustomerLogic instance = new HandleCustomerLogic();        
        String result = instance.generateCustomerId(username);
        
        assertThat("The generated id must have the username within it",
                result,containsString(username));
    }

    /**
     * Test of appendCustomerDetails method, of class HandleCustomerLogic.
     */
    @Test
    public void testAppendCustomerDetails() {
        System.out.println("appendCustomerDetails");
        Customer customer = BeansClass.customer();
        
        String id = "testId";
        String username = "testUsername";
        String customer_name = "testName";
        String gender = "testGender";
        String movie_likes = "testMovie_likes";
        String show_likes = "testShow_likes";
        
        HandleCustomerLogic instance = new HandleCustomerLogic();
        Customer result = instance.appendCustomerDetails(
                customer, id, username, customer_name, gender, movie_likes, show_likes);
        
        assertSame("Must return the same object",customer, result);
        assertNotNull("Must return non-null object attributes; sample:Id",
                customer.getId());
    }

    /**
     * Test of checkIfExists method, of class HandleCustomerLogic.
     */
    @Test
    public void testCheckIfExists() {
        System.out.println("checkIfExists");        
        Customer customer = BeansClass.customer();
        
        HandleCustomerLogic instance = new HandleCustomerLogic();
        instance.appendCustomerDetails(customer, "testId", "testUsername", "testName", "testGender", "testMovies", "testShows");
        instance.insertCustomer(customer);
        
        boolean result = instance.checkIfExists(customer);
        assertTrue("Must bring 'true' to confirm customer presence",
                result);
    }

    /**
     * Test of insertCustomer method, of class HandleCustomerLogic.
     */
    @Test
    public void testInsertCustomer() {
        System.out.println("insertCustomer");
        Customer customer = new Customer();
        customer.setId("testId");
        customer.setCustomer_name("testName");
        customer.setUsername("testUsername");
        customer.setGender("testGender");
        customer.setMovie_likes("testMovies");
        customer.setShow_likes("testShows");
        
        //delete first if it exist
        HandleCustomerLogic instance = new HandleCustomerLogic();
        boolean existent = instance.checkIfExists(customer);
        if(existent){
            instance.deleteCustomer(customer);
        }
        
        //insert
        String result = instance.insertCustomer(customer);

        assertThat("Check that the insert",
                result,containsString("Successful"));
    }

    /**
     * Test of updateCustomer method, of class HandleCustomerLogic.
     */
    @Test
    public void testUpdateCustomer() {
        System.out.println("updateCustomer");
        Customer customer = new Customer();
        customer.setId("testId");
        customer.setCustomer_name("testUser");
        customer.setGender("testGender");
        
        //insert
        HandleCustomerLogic instance = new HandleCustomerLogic();
        boolean check = instance.checkIfExists(customer);
        if(!check){
            instance.insertCustomer(customer);
        }
        
        //update
        String result = instance.updateCustomer(customer);
        assertThat("Check that the update",
                result,containsString("Successful"));
    }

    /**
     * Test of getAllCustomers method, of class HandleCustomerLogic.
     */
    @Test
    public void testGetAllCustomers() {
        System.out.println("getAllCustomers");
        Customer expResult = new Customer();
        expResult.setUsername("testUsername");
        //insert
        HandleCustomerLogic instance = new HandleCustomerLogic();
        boolean check = instance.checkIfExists(expResult);
        if(!check){
            instance.insertCustomer(expResult);
        }
        
        ArrayList<Customer> result = instance.getAllCustomers();
        assertNotNull("Check that the result returned is not null", result);
    }

    /**
     * Test of findOne method, of class HandleCustomerLogic.
     */
    @Test
    public void testFindOne() {
        System.out.println("findOne");
        Customer customer = new Customer();
        customer.setId("testId");
        customer.setCustomer_name("testName");
        customer.setUsername("testUsername");
        customer.setGender("testGender");
        customer.setMovie_likes("testMovies");
        customer.setShow_likes("testShows");
        
        //insert
        HandleCustomerLogic instance = new HandleCustomerLogic();
        boolean check = instance.checkIfExists(customer);
        if(!check){
            instance.insertCustomer(customer);
        }
        
        Customer result = instance.findOne(customer.getUsername());
        assertNotNull("Check that returned customer is not null",result);
    }

    /**
     * Test of deleteCustomer method, of class HandleCustomerLogic.
     */
    @Test
    public void testDeleteCustomer() {
        System.out.println("deleteCustomer");
        
        HandleCustomerLogic instance = new HandleCustomerLogic();
        Customer customer = instance.findOne("testUsername");
        
        if(customer!= null){
            String result = instance.deleteCustomer(customer);
            assertThat("Check that the delete",
                result,containsString("Successful"));
        }
    }


    
}
