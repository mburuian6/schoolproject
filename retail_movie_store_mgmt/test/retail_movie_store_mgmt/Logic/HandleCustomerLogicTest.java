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
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import retail_movie_store_mgmt.Customer.Customer;
import retail_movie_store_mgmt.database.Database_start_stop;

/**
 *
 * @author Ian Mburu
 */
public class HandleCustomerLogicTest {
    
    public HandleCustomerLogicTest() {
    }

    @Before
    public void setup(){
        Database_start_stop db = BeansClass.database_begin_end();
        db.start_db();
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
        
        //close db
        Database_start_stop db = BeansClass.database_begin_end();
        db.stop_db();
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
        //insert
        HandleCustomerLogic instance = new HandleCustomerLogic();
        instance.insertCustomer(customer);
        //test
        boolean test = instance.checkIfExists(customer);
        
        assertTrue("Check if customer was inserted",test);
    }

    /**
     * Test of updateCustomer method, of class HandleCustomerLogic.
     */
    @Test
    public void testUpdateCustomer() {
        System.out.println("updateCustomer");
        Customer customer = null;
        HandleCustomerLogic instance = new HandleCustomerLogic();
        instance.updateCustomer(customer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllCustomers method, of class HandleCustomerLogic.
     */
    @Test
    public void testGetAllCustomers() {
        System.out.println("getAllCustomers");
        HandleCustomerLogic instance = new HandleCustomerLogic();
        ArrayList<Customer> expResult = null;
        ArrayList<Customer> result = instance.getAllCustomers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findOne method, of class HandleCustomerLogic.
     */
    @Test
    public void testFindOne() {
        System.out.println("findOne");
        String username = "";
        HandleCustomerLogic instance = new HandleCustomerLogic();
        Customer expResult = null;
        Customer result = instance.findOne(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteCustomer method, of class HandleCustomerLogic.
     */
    @Test
    public void testDeleteCustomer() {
        System.out.println("deleteCustomer");
        Customer customer = null;
        HandleCustomerLogic instance = new HandleCustomerLogic();
        instance.deleteCustomer(customer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMovieLikes method, of class HandleCustomerLogic.
     */
    @Test
    public void testGetMovieLikes() {
        System.out.println("getMovieLikes");
        Customer customer = null;
        HandleCustomerLogic instance = new HandleCustomerLogic();
        String[] expResult = null;
        String[] result = instance.getMovieLikes(customer);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getShowsLikes method, of class HandleCustomerLogic.
     */
    @Test
    public void testGetShowsLikes() {
        System.out.println("getShowsLikes");
        Customer customer = null;
        HandleCustomerLogic instance = new HandleCustomerLogic();
        String[] expResult = null;
        String[] result = instance.getShowsLikes(customer);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
