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
import retail_movie_store_mgmt.purchases.media.MediaPurchase;

/**
 *
 * @author Ian Mburu
 */
public class HandleMediaPurchaseLogicTest {
    
    public HandleMediaPurchaseLogicTest() {
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
     * Test of generatePurchaseId method, of class HandleMediaPurchaseLogic.
     */
    @Test
    public void testGeneratePurchaseId() {
        System.out.println("generatePurchaseId");
        String title = "";
        HandleMediaPurchaseLogic instance = new HandleMediaPurchaseLogic();
        String expResult = "";
        String result = instance.generatePurchaseId(title);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDate method, of class HandleMediaPurchaseLogic.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        HandleMediaPurchaseLogic instance = new HandleMediaPurchaseLogic();
        LocalDate expResult = null;
        LocalDate result = instance.getDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkIntInput method, of class HandleMediaPurchaseLogic.
     */
    @Test
    public void testCheckIntInput() {
        System.out.println("checkIntInput");
        String[] params = null;
        String[] labels = null;
        HandleMediaPurchaseLogic instance = new HandleMediaPurchaseLogic();
        String expResult = "";
        String result = instance.checkIntInput(params, labels);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkForAlphasInInt method, of class HandleMediaPurchaseLogic.
     */
    @Test
    public void testCheckForAlphasInInt() {
        System.out.println("checkForAlphasInInt");
        String input = "";
        HandleMediaPurchaseLogic instance = new HandleMediaPurchaseLogic();
        boolean expResult = false;
        boolean result = instance.checkForAlphasInInt(input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of appendPurchaseDetails method, of class HandleMediaPurchaseLogic.
     */
    @Test
    public void testAppendPurchaseDetails() {
        System.out.println("appendPurchaseDetails");
        MediaPurchase mediaPurchase = null;
        String id = "";
        String title = "";
        String strQuantity = "";
        String strPrice = "";
        HandleMediaPurchaseLogic instance = new HandleMediaPurchaseLogic();
        MediaPurchase expResult = null;
        MediaPurchase result = instance.appendPurchaseDetails(mediaPurchase, id, title, strQuantity, strPrice);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkIfExists method, of class HandleMediaPurchaseLogic.
     */
    @Test
    public void testCheckIfExists() {
        System.out.println("checkIfExists");
        MediaPurchase mediaPurchase = null;
        HandleMediaPurchaseLogic instance = new HandleMediaPurchaseLogic();
        boolean expResult = false;
        boolean result = instance.checkIfExists(mediaPurchase);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findOne method, of class HandleMediaPurchaseLogic.
     */
    @Test
    public void testFindOne() {
        System.out.println("findOne");
        MediaPurchase mediaPurchase = null;
        HandleMediaPurchaseLogic instance = new HandleMediaPurchaseLogic();
        MediaPurchase expResult = null;
        MediaPurchase result = instance.findOne(mediaPurchase);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertToDb method, of class HandleMediaPurchaseLogic.
     */
    @Test
    public void testInsertToDb() {
        System.out.println("insertToDb");
        MediaPurchase mediaPurchase = null;
        HandleMediaPurchaseLogic instance = new HandleMediaPurchaseLogic();
        boolean expResult = false;
        boolean result = instance.insertToDb(mediaPurchase);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllEntries method, of class HandleMediaPurchaseLogic.
     */
    @Test
    public void testGetAllEntries() {
        System.out.println("getAllEntries");
        HandleMediaPurchaseLogic instance = new HandleMediaPurchaseLogic();
        ArrayList<MediaPurchase> expResult = null;
        ArrayList<MediaPurchase> result = instance.getAllEntries();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTodayEntries method, of class HandleMediaPurchaseLogic.
     */
    @Test
    public void testGetTodayEntries() {
        System.out.println("getTodayEntries");
        HandleMediaPurchaseLogic instance = new HandleMediaPurchaseLogic();
        ArrayList<MediaPurchase> expResult = null;
        ArrayList<MediaPurchase> result = instance.getTodayEntries();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllMediaTitles method, of class HandleMediaPurchaseLogic.
     */
    @Test
    public void testGetAllMediaTitles() {
        System.out.println("getAllMediaTitles");
        HandleMediaPurchaseLogic instance = new HandleMediaPurchaseLogic();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.getAllMediaTitles();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class HandleMediaPurchaseLogic.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        MediaPurchase mediaPurchase = null;
        HandleMediaPurchaseLogic instance = new HandleMediaPurchaseLogic();
        boolean expResult = false;
        boolean result = instance.delete(mediaPurchase);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllMediaDates method, of class HandleMediaPurchaseLogic.
     */
    @Test
    public void testGetAllMediaDates() {
        System.out.println("getAllMediaDates");
        HandleMediaPurchaseLogic instance = new HandleMediaPurchaseLogic();
        ArrayList<PurchaseDate> expResult = null;
        ArrayList<PurchaseDate> result = instance.getAllMediaDates();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllItemsPerDate method, of class HandleMediaPurchaseLogic.
     */
    @Test
    public void testGetAllItemsPerDate() {
        System.out.println("getAllItemsPerDate");
        HandleMediaPurchaseLogic instance = new HandleMediaPurchaseLogic();
        HashMap<String, ArrayList> expResult = null;
        HashMap<String, ArrayList> result = instance.getAllItemsPerDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayInfo method, of class HandleMediaPurchaseLogic.
     */
    @Test
    public void testDisplayInfo() {
        System.out.println("displayInfo");
        String message = "";
        HandleMediaPurchaseLogic instance = new HandleMediaPurchaseLogic();
        instance.displayInfo(message);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayError method, of class HandleMediaPurchaseLogic.
     */
    @Test
    public void testDisplayError() {
        System.out.println("displayError");
        String message = "";
        HandleMediaPurchaseLogic instance = new HandleMediaPurchaseLogic();
        instance.displayError(message);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
