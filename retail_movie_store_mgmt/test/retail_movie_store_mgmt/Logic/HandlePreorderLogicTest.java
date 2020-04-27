/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.Logic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
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
    }

    /**
     * Test of generatePreorderId method, of class HandlePreorderLogic.
     */
    @Test
    public void testGeneratePreorderId() {
        System.out.println("generatePreorderId");
        String customerName = "";
        HandlePreorderLogic instance = new HandlePreorderLogic();
        String expResult = "";
        String result = instance.generatePreorderId(customerName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkIfPickUpDateIsPassed method, of class HandlePreorderLogic.
     */
    @Test
    public void testCheckIfPickUpDateIsPassed() {
        System.out.println("checkIfPickUpDateIsPassed");
        Preorder preorder = null;
        HandlePreorderLogic instance = new HandlePreorderLogic();
        boolean expResult = false;
        boolean result = instance.checkIfPickUpDateIsPassed(preorder);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of appendPreorderDetails method, of class HandlePreorderLogic.
     */
    @Test
    public void testAppendPreorderDetails() {
        System.out.println("appendPreorderDetails");
        Preorder preorder = null;
        String id = "";
        String customer_name = "";
        LocalDate orderDate = null;
        LocalDate pickupDate = null;
        String movies_list = "";
        String shows_list = "";
        String software_list = "";
        String peripheral = "";
        String status = "";
        HandlePreorderLogic instance = new HandlePreorderLogic();
        Preorder expResult = null;
        Preorder result = instance.appendPreorderDetails(preorder, id, customer_name, orderDate, pickupDate, movies_list, shows_list, software_list, peripheral, status);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkIfExists method, of class HandlePreorderLogic.
     */
    @Test
    public void testCheckIfExists() {
        System.out.println("checkIfExists");
        Preorder preorder = null;
        HandlePreorderLogic instance = new HandlePreorderLogic();
        boolean expResult = false;
        boolean result = instance.checkIfExists(preorder);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findOne method, of class HandlePreorderLogic.
     */
    @Test
    public void testFindOne() {
        System.out.println("findOne");
        String id = "";
        HandlePreorderLogic instance = new HandlePreorderLogic();
        Preorder expResult = null;
        Preorder result = instance.findOne(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertPreorder method, of class HandlePreorderLogic.
     */
    @Test
    public void testInsertPreorder() {
        System.out.println("insertPreorder");
        Preorder preorder = null;
        HandlePreorderLogic instance = new HandlePreorderLogic();
        instance.insertPreorder(preorder);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePreorder method, of class HandlePreorderLogic.
     */
    @Test
    public void testUpdatePreorder() {
        System.out.println("updatePreorder");
        Preorder preorder = null;
        HandlePreorderLogic instance = new HandlePreorderLogic();
        instance.updatePreorder(preorder);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkStatusDone method, of class HandlePreorderLogic.
     */
    @Test
    public void testCheckStatusDone() {
        System.out.println("checkStatusDone");
        String id = "";
        HandlePreorderLogic instance = new HandlePreorderLogic();
        boolean expResult = false;
        boolean result = instance.checkStatusDone(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePreorderStatusToDone method, of class HandlePreorderLogic.
     */
    @Test
    public void testUpdatePreorderStatusToDone() {
        System.out.println("updatePreorderStatusToDone");
        Preorder preorder = null;
        HandlePreorderLogic instance = new HandlePreorderLogic();
        instance.updatePreorderStatusToDone(preorder);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of revertPreorderStatusToPending method, of class HandlePreorderLogic.
     */
    @Test
    public void testRevertPreorderStatusToPending() {
        System.out.println("revertPreorderStatusToPending");
        Preorder preorder = null;
        HandlePreorderLogic instance = new HandlePreorderLogic();
        instance.revertPreorderStatusToPending(preorder);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllEntries method, of class HandlePreorderLogic.
     */
    @Test
    public void testGetAllEntries() {
        System.out.println("getAllEntries");
        HandlePreorderLogic instance = new HandlePreorderLogic();
        ArrayList<Preorder> expResult = null;
        ArrayList<Preorder> result = instance.getAllEntries();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletePreorder method, of class HandlePreorderLogic.
     */
    @Test
    public void testDeletePreorder() {
        System.out.println("deletePreorder");
        Preorder preorder = null;
        HandlePreorderLogic instance = new HandlePreorderLogic();
        instance.deletePreorder(preorder);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllPreorderDates method, of class HandlePreorderLogic.
     */
    @Test
    public void testGetAllPreorderDates() {
        System.out.println("getAllPreorderDates");
        HandlePreorderLogic instance = new HandlePreorderLogic();
        ArrayList<OrderDate> expResult = null;
        ArrayList<OrderDate> result = instance.getAllPreorderDates();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllItemsPerDate method, of class HandlePreorderLogic.
     */
    @Test
    public void testGetAllItemsPerDate() {
        System.out.println("getAllItemsPerDate");
        HandlePreorderLogic instance = new HandlePreorderLogic();
        HashMap<String, ArrayList> expResult = null;
        HashMap<String, ArrayList> result = instance.getAllItemsPerDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
