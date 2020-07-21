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
import static org.junit.Assert.assertTrue;
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
        HandleSoftwarePurchaseLogic instance = new HandleSoftwarePurchaseLogic();
        SoftwarePurchase softwarePurchase = new SoftwarePurchase();
        softwarePurchase.setId("testId");
        boolean check = instance.checkIfExists(softwarePurchase);
        if(check){
            instance.delete(softwarePurchase);
        }
    }

    /**
     * Test of generatePurchaseId method, of class HandleSoftwarePurchaseLogic.
     */
    @Test
    public void testGeneratePurchaseId() {
        System.out.println("generatePurchaseId");
        String title = "testTitle";
        HandleSoftwarePurchaseLogic instance = new HandleSoftwarePurchaseLogic();
        
        String result = instance.generatePurchaseId(title);
        assertThat("The generated id must have the title within it",
                result,containsString(title));
    }

    /**
     * Test of getDate method, of class HandleSoftwarePurchaseLogic.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        HandleSoftwarePurchaseLogic instance = new HandleSoftwarePurchaseLogic();
        LocalDate expResult = LocalDate.now();
        LocalDate result = instance.getDate();
        
        assertEquals("Check that the date is valid",
                expResult, result);
    }

    /**
     * Test of checkIntInput method, of class HandleSoftwarePurchaseLogic.
     */
    @Test
    public void testCheckIntInput() {
        System.out.println("checkIntInput");
        String[] params = {"1","2a"};
        String[] labels = {"First Input","Second Input"};
        HandleSoftwarePurchaseLogic instance = new HandleSoftwarePurchaseLogic();
        
        String expResult = "Second Input";
        String result = instance.checkIntInput(params, labels);
        
        assertEquals("Check that the input that has any xter other than"
                + "numbers is caught",
                expResult, result);
    }

    /**
     * Test of checkForAlphasInInt method, of class HandleSoftwarePurchaseLogic.
     */
    @Test
    public void testCheckForAlphasInInt() {
        System.out.println("checkForAlphasInInt");
        String input = "2";
        HandleSoftwarePurchaseLogic instance = new HandleSoftwarePurchaseLogic();
        boolean expResult = false;
        boolean result = instance.checkForAlphasInInt(input);
        
        assertEquals("Checks that the input consists only of numbers",
                expResult, result);
    }

    /**
     * Test of appendPurchaseDetails method, of class HandleSoftwarePurchaseLogic.
     */
    @Test
    public void testAppendPurchaseDetails() {
        System.out.println("appendPurchaseDetails");
        SoftwarePurchase result = new SoftwarePurchase();
        String id = "testId";
        String title = "testTitle";
        String strQuantity = "1";
        String strPrice = "1.0";
        
        HandleSoftwarePurchaseLogic instance = new HandleSoftwarePurchaseLogic();
        result = instance.appendPurchaseDetails(result, id, title, strQuantity, strPrice);
        assertEquals("Checks that sample details are attached to the object",
                result.getId(),id);
    }

    /**
     * Test of checkIfExists method, of class HandleSoftwarePurchaseLogic.
     */
    @Test
    public void testCheckIfExists() {
        System.out.println("checkIfExists");
        
        //create the object
        SoftwarePurchase softwarePurchase = new SoftwarePurchase();
        String id = "testId";
        String title = "testTitle";
        String strQuantity = "1";
        String strPrice = "1.0";
        
        HandleSoftwarePurchaseLogic instance = new HandleSoftwarePurchaseLogic();
        softwarePurchase = instance.appendPurchaseDetails(softwarePurchase, id, title, strQuantity, strPrice);
        String insert = instance.insertToDb(softwarePurchase);
        
        //check
        boolean result = instance.checkIfExists(softwarePurchase);
        boolean expResult = true;
        
        assertEquals("Checks that the object exists in the db",expResult,result);
    }

    /**
     * Test of findOne method, of class HandleSoftwarePurchaseLogic.
     
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
    
    */

    /**
     * Test of insertToDb method, of class HandleSoftwarePurchaseLogic.
     */
    @Test
    public void testInsertToDb() {
        System.out.println("insertToDb");
        HandleSoftwarePurchaseLogic instance = new HandleSoftwarePurchaseLogic();
        
        //create the object
        SoftwarePurchase softwarePurchase = new SoftwarePurchase();
        String id = "testId";
        String title = "testTitle";
        String strQuantity = "1";
        String strPrice = "1.0";
        
        softwarePurchase = instance.appendPurchaseDetails(softwarePurchase, id, title, strQuantity, strPrice);
        boolean check = instance.checkIfExists(softwarePurchase);
        if(check){
            instance.delete(softwarePurchase);
        }      
        String result = instance.insertToDb(softwarePurchase);
        
        //test
        assertThat("Checks that the purchase is inserted",
                result,containsString("Successful"));
    }

    /**
     * Test of getAllEntries method, of class HandleSoftwarePurchaseLogic.
     */
    @Test
    public void testGetAllEntries() {
        System.out.println("getAllEntries");
        HandleSoftwarePurchaseLogic instance = new HandleSoftwarePurchaseLogic();
        ArrayList<SoftwarePurchase> result = instance.getAllEntries();
        assertNotNull("Checks that the entries returned are not null", result);
    }

    /**
     * Test of getTodayEntries method, of class HandleSoftwarePurchaseLogic.
     */
    @Test
    public void testGetTodayEntries() {
        System.out.println("getTodayEntries");
        HandleSoftwarePurchaseLogic instance = new HandleSoftwarePurchaseLogic();
        
        //create the object
        SoftwarePurchase softwarePurchase = new SoftwarePurchase();
        String id = "testId";
        String title = "testTitle";
        String strQuantity = "1";
        String strPrice = "1.0";
        
        softwarePurchase = instance.appendPurchaseDetails(softwarePurchase, id, title, strQuantity, strPrice);
        boolean check = instance.checkIfExists(softwarePurchase);
        if(!check){
            instance.insertToDb(softwarePurchase);
        }
        else{
            instance.delete(softwarePurchase);
        }        
        instance.insertToDb(softwarePurchase);
        
        
        ArrayList<SoftwarePurchase> result = instance.getTodayEntries();
        assertNotNull("Checks that the returned items are not null", result);
    }

    /**
     * Test of getAllSoftwareTitles method, of class HandleSoftwarePurchaseLogic.
     */
    @Test
    public void testGetAllSoftwareTitles() {
        System.out.println("getAllSoftwareTitles");
        HandleSoftwarePurchaseLogic instance = new HandleSoftwarePurchaseLogic();
        ArrayList<String> result = instance.getAllSoftwareTitles();
        assertNotNull("Checks that the media titles are not empty", result);
    }

    /**
     * Test of delete method, of class HandleSoftwarePurchaseLogic.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        HandleSoftwarePurchaseLogic instance = new HandleSoftwarePurchaseLogic();
        
        //create the object
        SoftwarePurchase softwarePurchase = new SoftwarePurchase();
        String id = "testId";
        String title = "testTitle";
        String strQuantity = "1";
        String strPrice = "1.0";
        
        softwarePurchase = instance.appendPurchaseDetails(softwarePurchase, id, title, strQuantity, strPrice);
        boolean check = instance.checkIfExists(softwarePurchase);
        if(!check){
            instance.insertToDb(softwarePurchase);
        }
        
        boolean result = instance.delete(softwarePurchase);
        assertTrue("Checks that the delete functionality works", result);
    }

    /**
     * Test of getAllSoftwareDates method, of class HandleSoftwarePurchaseLogic.
     */
    @Test
    public void testGetAllSoftwareDates() {
        System.out.println("getAllSoftwareDates");
        HandleSoftwarePurchaseLogic instance = new HandleSoftwarePurchaseLogic();
        ArrayList<PurchaseDate> result = instance.getAllSoftwareDates();
        assertNotNull("Checks that the date entries are not null", result);
    }

    /**
     * Test of getAllItemsPerDate method, of class HandleSoftwarePurchaseLogic.
     */
    @Test
    public void testGetAllItemsPerDate() {
        HandleMediaPurchaseLogic instance = new HandleMediaPurchaseLogic();
        HashMap<String, ArrayList> result = instance.getAllItemsPerDate();
        assertNotNull("Check that the returned item list is not null",
                result);
    }
    
}
