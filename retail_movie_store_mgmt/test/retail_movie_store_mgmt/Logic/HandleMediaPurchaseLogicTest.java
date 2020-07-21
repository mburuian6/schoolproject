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
//        db.stop_db();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
        HandleMediaPurchaseLogic instance = new HandleMediaPurchaseLogic();
        MediaPurchase mediaPurchase = new MediaPurchase();
        mediaPurchase.setId("testId");
        boolean check = instance.checkIfExists(mediaPurchase);
        if(check){
            instance.delete(mediaPurchase);
        }
    }

    /**
     * Test of generatePurchaseId method, of class HandleMediaPurchaseLogic.
     */
    @Test
    public void testGeneratePurchaseId() {
        System.out.println("generatePurchaseId");
        String title = "testTitle";
        HandleMediaPurchaseLogic instance = new HandleMediaPurchaseLogic();
        
        String result = instance.generatePurchaseId(title);
        assertThat("The generated id must have the title within it",
                result,containsString(title));
    }

    /**
     * Test of getDate method, of class HandleMediaPurchaseLogic.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        HandleMediaPurchaseLogic instance = new HandleMediaPurchaseLogic();
        LocalDate expResult = LocalDate.now();
        LocalDate result = instance.getDate();
        
        assertEquals("Check that the date is valid",
                expResult, result);
    }

    /**
     * Test of checkIntInput method, of class HandleMediaPurchaseLogic.
     */
    @Test
    public void testCheckIntInput() {
        System.out.println("checkIntInput");
        String[] params = {"1","2a"};
        String[] labels = {"First Input","Second Input"};
        HandleMediaPurchaseLogic instance = new HandleMediaPurchaseLogic();
        
        String expResult = "Second Input";
        String result = instance.checkIntInput(params, labels);
        
        assertEquals("Check that the input that has any xter other than"
                + "numbers is caught",
                expResult, result);
    }

    /**
     * Test of checkForAlphasInInt method, of class HandleMediaPurchaseLogic.
     */
    @Test
    public void testCheckForAlphasInInt() {
        System.out.println("checkForAlphasInInt");
        String input = "2";
        HandleMediaPurchaseLogic instance = new HandleMediaPurchaseLogic();
        boolean expResult = false;
        boolean result = instance.checkForAlphasInInt(input);
        
        assertEquals("Checks that the input consists only of numbers",
                expResult, result);
    }

    /**
     * Test of appendPurchaseDetails method, of class HandleMediaPurchaseLogic.
     */
    @Test
    public void testAppendPurchaseDetails() {
        System.out.println("appendPurchaseDetails");
        MediaPurchase result = new MediaPurchase();
        String id = "testId";
        String title = "testTitle";
        String strQuantity = "1";
        String strPrice = "1.0";
        
        HandleMediaPurchaseLogic instance = new HandleMediaPurchaseLogic();
        result = instance.appendPurchaseDetails(result, id, title, strQuantity, strPrice);
        assertEquals("Checks that sample details are attached to the object",
                result.getId(),id);
    }

    /**
     * Test of checkIfExists method, of class HandleMediaPurchaseLogic.
     */
    @Test
    public void testCheckIfExists() {
        System.out.println("checkIfExists");
        
        //create the object
        MediaPurchase mediaPurchase = new MediaPurchase();
        String id = "testId";
        String title = "testTitle";
        String strQuantity = "1";
        String strPrice = "1.0";
        
        HandleMediaPurchaseLogic instance = new HandleMediaPurchaseLogic();
        mediaPurchase = instance.appendPurchaseDetails(mediaPurchase, id, title, strQuantity, strPrice);
        boolean insert = instance.insertToDb(mediaPurchase);
        System.out.println("Whether it was inserted: "+ insert);
        
        //check
        boolean result = instance.checkIfExists(mediaPurchase);
        boolean expResult = true;
        
        assertEquals("Checks that the object exists in the db",expResult,result);
    }

//    /**
//     * Test of findOne method, of class HandleMediaPurchaseLogic.
//     */
//    @Test
//    public void testFindOne() {
//        System.out.println("findOne");
//        HandleMediaPurchaseLogic instance = new HandleMediaPurchaseLogic();
//        
//        //create the object
//        MediaPurchase mediaPurchase = new MediaPurchase();
//        String id = "testId";
//        String title = "testTitle";
//        String strQuantity = "1";
//        String strPrice = "1.0";
//        
//        mediaPurchase = instance.appendPurchaseDetails(mediaPurchase, id, title, strQuantity, strPrice);
//        boolean check = instance.checkIfExists(mediaPurchase);
//        if(check){
//            instance.delete(mediaPurchase);
//        }      
//        instance.insertToDb(mediaPurchase);
//        
//        MediaPurchase result = instance.findOne(mediaPurchase);
//        assertNotNull("Checks that the inserted object does not return a null", result);
//    }

    /**
     * Test of insertToDb method, of class HandleMediaPurchaseLogic.
     */
    @Test
    public void testInsertToDb() {
        System.out.println("insertToDb");
        HandleMediaPurchaseLogic instance = new HandleMediaPurchaseLogic();
        
        //create the object
        MediaPurchase mediaPurchase = new MediaPurchase();
        String id = "testId";
        String title = "testTitle";
        String strQuantity = "1";
        String strPrice = "1.0";
        
        mediaPurchase = instance.appendPurchaseDetails(mediaPurchase, id, title, strQuantity, strPrice);
        boolean check = instance.checkIfExists(mediaPurchase);
        if(check){
            instance.delete(mediaPurchase);
        }      
        boolean result = instance.insertToDb(mediaPurchase);
        
        //test
        assertTrue("Checks that the purchase is inserted", result);
    }

    /**
     * Test of getAllEntries method, of class HandleMediaPurchaseLogic.
     */
    @Test
    public void testGetAllEntries() {
        System.out.println("getAllEntries");
        HandleMediaPurchaseLogic instance = new HandleMediaPurchaseLogic();
        ArrayList<MediaPurchase> result = instance.getAllEntries();
        assertNotNull("Checks that the entries returned are not null", result);
    }

    /**
     * Test of getTodayEntries method, of class HandleMediaPurchaseLogic.
     */
    @Test
    public void testGetTodayEntries() {
        System.out.println("getTodayEntries");
        HandleMediaPurchaseLogic instance = new HandleMediaPurchaseLogic();
        
        //create the object
        MediaPurchase mediaPurchase = new MediaPurchase();
        String id = "testId";
        String title = "testTitle";
        String strQuantity = "1";
        String strPrice = "1.0";
        
        mediaPurchase = instance.appendPurchaseDetails(mediaPurchase, id, title, strQuantity, strPrice);
        boolean check = instance.checkIfExists(mediaPurchase);
        if(!check){
            instance.insertToDb(mediaPurchase);
        }
        else{
            instance.delete(mediaPurchase);
        }        
        instance.insertToDb(mediaPurchase);
        
        
        ArrayList<MediaPurchase> result = instance.getTodayEntries();
        assertNotNull("Checks that the returned items are not null", result);
    }

    /**
     * Test of getAllMediaTitles method, of class HandleMediaPurchaseLogic.
     */
    @Test
    public void testGetAllMediaTitles() {
        System.out.println("getAllMediaTitles");
        HandleMediaPurchaseLogic instance = new HandleMediaPurchaseLogic();
        ArrayList<String> result = instance.getAllMediaTitles();
        assertNotNull("Checks that the media titles are not empty", result);
    }

    /**
     * Test of delete method, of class HandleMediaPurchaseLogic.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        HandleMediaPurchaseLogic instance = new HandleMediaPurchaseLogic();
        
        //create the object
        MediaPurchase mediaPurchase = new MediaPurchase();
        String id = "testId";
        String title = "testTitle";
        String strQuantity = "1";
        String strPrice = "1.0";
        
        mediaPurchase = instance.appendPurchaseDetails(mediaPurchase, id, title, strQuantity, strPrice);
        boolean check = instance.checkIfExists(mediaPurchase);
        if(!check){
            instance.insertToDb(mediaPurchase);
        }
        
        boolean result = instance.delete(mediaPurchase);
        assertTrue("Checks that the delete functionality works", result);
    }

    /**
     * Test of getAllMediaDates method, of class HandleMediaPurchaseLogic.
     */
    @Test
    public void testGetAllMediaDates() {
        System.out.println("getAllMediaDates");
        HandleMediaPurchaseLogic instance = new HandleMediaPurchaseLogic();
        ArrayList<PurchaseDate> result = instance.getAllMediaDates();
        assertNotNull("Checks that the date entries are not null", result);
    }

    /**
     * Test of getAllItemsPerDate method, of class HandleMediaPurchaseLogic.
     */
    @Test
    public void testGetAllItemsPerDate() {
        System.out.println("getAllItemsPerDate");
        HandleMediaPurchaseLogic instance = new HandleMediaPurchaseLogic();
        HashMap<String, ArrayList> result = instance.getAllItemsPerDate();
        assertNotNull("Check that the returned item list is not null",
                result);
    }
    
}
