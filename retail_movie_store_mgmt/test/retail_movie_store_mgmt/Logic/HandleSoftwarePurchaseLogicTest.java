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
import retail_movie_store_mgmt.purchases.PurchaseDate;
import retail_movie_store_mgmt.purchases.software.SoftwarePurchase;

/**
 *
 * @author Ian Mburu
 */
public class HandleSoftwarePurchaseLogicTest {
    
    public HandleSoftwarePurchaseLogicTest() {
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
     * Test of generatePurchaseId method, of class HandleSoftwarePurchaseLogic.
     */
    @Test
    public void testGeneratePurchaseId() {
        System.out.println("generatePurchaseId");
        String title = "";
        HandleSoftwarePurchaseLogic instance = new HandleSoftwarePurchaseLogic();
        String expResult = "";
        String result = instance.generatePurchaseId(title);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDate method, of class HandleSoftwarePurchaseLogic.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        HandleSoftwarePurchaseLogic instance = new HandleSoftwarePurchaseLogic();
        LocalDate expResult = null;
        LocalDate result = instance.getDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkIntInput method, of class HandleSoftwarePurchaseLogic.
     */
    @Test
    public void testCheckIntInput() {
        System.out.println("checkIntInput");
        String[] params = null;
        String[] labels = null;
        HandleSoftwarePurchaseLogic instance = new HandleSoftwarePurchaseLogic();
        String expResult = "";
        String result = instance.checkIntInput(params, labels);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkForAlphasInInt method, of class HandleSoftwarePurchaseLogic.
     */
    @Test
    public void testCheckForAlphasInInt() {
        System.out.println("checkForAlphasInInt");
        String input = "";
        HandleSoftwarePurchaseLogic instance = new HandleSoftwarePurchaseLogic();
        boolean expResult = false;
        boolean result = instance.checkForAlphasInInt(input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of appendPurchaseDetails method, of class HandleSoftwarePurchaseLogic.
     */
    @Test
    public void testAppendPurchaseDetails() {
        System.out.println("appendPurchaseDetails");
        SoftwarePurchase softwarePurchase = null;
        String id = "";
        String title = "";
        String strQuantity = "";
        String strPrice = "";
        HandleSoftwarePurchaseLogic instance = new HandleSoftwarePurchaseLogic();
        SoftwarePurchase expResult = null;
        SoftwarePurchase result = instance.appendPurchaseDetails(softwarePurchase, id, title, strQuantity, strPrice);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkIfExists method, of class HandleSoftwarePurchaseLogic.
     */
    @Test
    public void testCheckIfExists() {
        System.out.println("checkIfExists");
        SoftwarePurchase softwarePurchase = null;
        HandleSoftwarePurchaseLogic instance = new HandleSoftwarePurchaseLogic();
        boolean expResult = false;
        boolean result = instance.checkIfExists(softwarePurchase);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findOne method, of class HandleSoftwarePurchaseLogic.
     */
    @Test
    public void testFindOne() {
        System.out.println("findOne");
        SoftwarePurchase softwarePurchase = null;
        HandleSoftwarePurchaseLogic instance = new HandleSoftwarePurchaseLogic();
        SoftwarePurchase expResult = null;
        SoftwarePurchase result = instance.findOne(softwarePurchase);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertToDb method, of class HandleSoftwarePurchaseLogic.
     */
    @Test
    public void testInsertToDb() {
        System.out.println("insertToDb");
        SoftwarePurchase softwarePurchase = null;
        HandleSoftwarePurchaseLogic instance = new HandleSoftwarePurchaseLogic();
        boolean expResult = false;
        boolean result = instance.insertToDb(softwarePurchase);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllEntries method, of class HandleSoftwarePurchaseLogic.
     */
    @Test
    public void testGetAllEntries() {
        System.out.println("getAllEntries");
        HandleSoftwarePurchaseLogic instance = new HandleSoftwarePurchaseLogic();
        ArrayList<SoftwarePurchase> expResult = null;
        ArrayList<SoftwarePurchase> result = instance.getAllEntries();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTodayEntries method, of class HandleSoftwarePurchaseLogic.
     */
    @Test
    public void testGetTodayEntries() {
        System.out.println("getTodayEntries");
        HandleSoftwarePurchaseLogic instance = new HandleSoftwarePurchaseLogic();
        ArrayList<SoftwarePurchase> expResult = null;
        ArrayList<SoftwarePurchase> result = instance.getTodayEntries();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllSoftwareTitles method, of class HandleSoftwarePurchaseLogic.
     */
    @Test
    public void testGetAllSoftwareTitles() {
        System.out.println("getAllSoftwareTitles");
        HandleSoftwarePurchaseLogic instance = new HandleSoftwarePurchaseLogic();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.getAllSoftwareTitles();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class HandleSoftwarePurchaseLogic.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        SoftwarePurchase softwarePurchase = null;
        HandleSoftwarePurchaseLogic instance = new HandleSoftwarePurchaseLogic();
        boolean expResult = false;
        boolean result = instance.delete(softwarePurchase);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllSoftwareDates method, of class HandleSoftwarePurchaseLogic.
     */
    @Test
    public void testGetAllSoftwareDates() {
        System.out.println("getAllSoftwareDates");
        HandleSoftwarePurchaseLogic instance = new HandleSoftwarePurchaseLogic();
        ArrayList<PurchaseDate> expResult = null;
        ArrayList<PurchaseDate> result = instance.getAllSoftwareDates();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllItemsPerDate method, of class HandleSoftwarePurchaseLogic.
     */
    @Test
    public void testGetAllItemsPerDate() {
        System.out.println("getAllItemsPerDate");
        HandleSoftwarePurchaseLogic instance = new HandleSoftwarePurchaseLogic();
        HashMap<String, ArrayList> expResult = null;
        HashMap<String, ArrayList> result = instance.getAllItemsPerDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayInfo method, of class HandleSoftwarePurchaseLogic.
     */
    @Test
    public void testDisplayInfo() {
        System.out.println("displayInfo");
        String message = "";
        HandleSoftwarePurchaseLogic instance = new HandleSoftwarePurchaseLogic();
        instance.displayInfo(message);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayError method, of class HandleSoftwarePurchaseLogic.
     */
    @Test
    public void testDisplayError() {
        System.out.println("displayError");
        String message = "";
        HandleSoftwarePurchaseLogic instance = new HandleSoftwarePurchaseLogic();
        instance.displayError(message);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
