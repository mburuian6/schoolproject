/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.Logic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import retail_movie_store_mgmt.preorders.OrderDate;
import retail_movie_store_mgmt.preorders.Preorder;

/**
 *
 * @author Ian Mburu
 */
public class HandlePreorderLogicTest {
    
    public HandlePreorderLogicTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
        Preorder preorderTestInstance = getPreorderTestInstance();
        HandlePreorderLogic instance = new HandlePreorderLogic();
        boolean check = instance.checkIfExists(preorderTestInstance);
        if(check){
            instance.deletePreorder(preorderTestInstance);
        }
    }

    /**
     * Test of generatePreorderId method, of class HandlePreorderLogic.
     */
    @Test
    public void testGeneratePreorderId() {
        System.out.println("generatePreorderId");
        String customerName = "testCustomerName";
        HandlePreorderLogic instance = new HandlePreorderLogic();
        String result = instance.generatePreorderId(customerName);
        
        assertThat("The generated id must have the customer name within it",
                result,containsString(customerName));
    }

    /**
     * Test of checkIfPickUpDateIsPassed method, of class HandlePreorderLogic.
     */
    @Test
    public void testCheckIfPickUpDateIsPassed() {
        System.out.println("checkIfPickUpDateIsPassed");
        Preorder preorder = new Preorder();
        preorder.setPickupDate(LocalDate.now());
        
        HandlePreorderLogic instance = new HandlePreorderLogic();
        boolean expResult = false;
        boolean result = instance.checkIfPickUpDateIsPassed(preorder);
        assertEquals("Checks if the pickup date is passed",expResult, result);
    }

    /**
     * Test of appendPreorderDetails method, of class HandlePreorderLogic.
     */
    @Test
    public void testAppendPreorderDetails() {
        System.out.println("appendPreorderDetails");
        
        Preorder preorder = new Preorder();
        String id = "testPreorderId";
        String customer_name = "testCustomerName";
        LocalDate orderDate = LocalDate.now();
        LocalDate pickupDate = LocalDate.now();
        String movies_list = "";
        String shows_list = "";
        String software_list = "";
        String peripheral = "";
        String status = "pending";
        
        HandlePreorderLogic instance = new HandlePreorderLogic();
        Preorder result = instance.appendPreorderDetails(preorder, id, customer_name, orderDate, pickupDate, movies_list, shows_list, software_list, peripheral, status);
        assertNotNull("Checks that the returned object is not null", result);
    }

    
    public Preorder getPreorderTestInstance(){
        Preorder preorder = new Preorder();
        String id = "testPreorderId";
        String customer_name = "testCustomerName";
        LocalDate orderDate = LocalDate.now();
        LocalDate pickupDate = LocalDate.now();
        String movies_list = "";
        String shows_list = "";
        String software_list = "";
        String peripheral = "";
        String status = "pending";
        
        HandlePreorderLogic instance = new HandlePreorderLogic();
        Preorder result = instance.appendPreorderDetails(preorder, id, customer_name, orderDate, pickupDate, movies_list, shows_list, software_list, peripheral, status);
        return result;
    }
    /**
     * Test of checkIfExists method, of class HandlePreorderLogic.
     */
    @Test
    public void testCheckIfExists() {
        System.out.println("checkIfExists");
        HandlePreorderLogic instance = new HandlePreorderLogic();
        Preorder preorder = getPreorderTestInstance();
        
        instance.insertPreorder(preorder);
        
        boolean expResult = true;
        boolean result = instance.checkIfExists(preorder);
        assertEquals("Checks if the inserted item exists in db",expResult, result);
    }

    /**
     * Test of findOne method, of class HandlePreorderLogic.
     */
    @Test
    public void testFindOne() {
        System.out.println("findOne");
        Preorder preorder = getPreorderTestInstance();
        String id = preorder.getId();
        HandlePreorderLogic instance = new HandlePreorderLogic();
        
        instance.insertPreorder(preorder);
        Preorder result = instance.findOne(id);
        assertEquals("Checks that the returned item is the one inserted",preorder.getId(), result.getId());
    }

    /**
     * Test of insertPreorder method, of class HandlePreorderLogic.
     */
    @Test
    public void testInsertPreorder() {
        System.out.println("insertPreorder");
        Preorder preorder = getPreorderTestInstance();
        HandlePreorderLogic instance = new HandlePreorderLogic();
        
        boolean check = instance.checkIfExists(preorder);
        if(check){
            instance.deletePreorder(preorder);
        }
        
        String insert = instance.insertPreorder(preorder);
        assertThat("Checks that insert process is a success",
                insert,containsString("Success"));
    }

    /**
     * Test of updatePreorder method, of class HandlePreorderLogic.
     */
    @Test
    public void testUpdatePreorder() {
        System.out.println("updatePreorder");
        Preorder preorder = getPreorderTestInstance();
        HandlePreorderLogic instance = new HandlePreorderLogic();
        instance.insertPreorder(preorder);
        
        preorder.setCustomer_name("testCustomerName2");        
        String update = instance.updatePreorder(preorder);
        assertThat("Checks that update process is a success",
                update,containsString("Success"));
    }

    /**
     * Test of checkStatusDone method, of class HandlePreorderLogic.
     */
    @Test
    public void testCheckStatusDone() {
        System.out.println("checkStatusDone");
        Preorder preorder = getPreorderTestInstance();
        String id = preorder.getId();
        HandlePreorderLogic instance = new HandlePreorderLogic();
        instance.insertPreorder(preorder);
        
        boolean expResult = false;
        boolean result = instance.checkStatusDone(id);
        assertEquals("Checks whether the preorder status is done",expResult, result);
    }

    /**
     * Test of getAllEntries method, of class HandlePreorderLogic.
     */
    @Test
    public void testGetAllEntries() {
        System.out.println("getAllEntries");
        HandlePreorderLogic instance = new HandlePreorderLogic();
        ArrayList<Preorder> result = instance.getAllEntries();
        assertNotNull("Checks that the entries are not null when items are returned", result);
    }

    /**
     * Test of deletePreorder method, of class HandlePreorderLogic.
     */
    @Test
    public void testDeletePreorder() {
        System.out.println("deletePreorder");
        Preorder preorder = getPreorderTestInstance();
        HandlePreorderLogic instance = new HandlePreorderLogic();
        
        boolean check = instance.checkIfExists(preorder);
        if(!check){
            instance.insertPreorder(preorder);
        }
        
        String delete = instance.deletePreorder(preorder);
        assertThat("Checks that delete process is a success",
                delete,containsString("Success"));
    }

    /**
     * Test of getAllPreorderDates method, of class HandlePreorderLogic.
     */
    @Test
    public void testGetAllPreorderDates() {
        System.out.println("getAllPreorderDates");
        HandlePreorderLogic instance = new HandlePreorderLogic();
        ArrayList<OrderDate> result = instance.getAllPreorderDates();
        assertNotNull("Checks that dates of items are not null", result);
    }

    /**
     * Test of getAllItemsPerDate method, of class HandlePreorderLogic.
     */
    @Test
    public void testGetAllItemsPerDate() {
        System.out.println("getAllItemsPerDate");
        HandlePreorderLogic instance = new HandlePreorderLogic();
        HashMap<String, ArrayList> result = instance.getAllItemsPerDate();
        assertNotNull("Checks that organized items are not null", result);
    }
    
}
