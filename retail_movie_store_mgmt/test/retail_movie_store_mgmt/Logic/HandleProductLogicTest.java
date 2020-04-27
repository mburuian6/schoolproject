/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.Logic;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import retail_movie_store_mgmt.product.Product;

/**
 *
 * @author Ian Mburu
 */
public class HandleProductLogicTest {
    
    public HandleProductLogicTest() {
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
     * Test of appendProductDetails method, of class HandleProductLogic.
     */
    @Test
    public void testAppendProductDetails() {
        System.out.println("appendProductDetails");
        Product product = null;
        String brand = "";
        String strPriceSh = "";
        String strPriceCts = "";
        String strPrice_optical_diskSh = "";
        String strPrice_optical_diskCts = "";
        String description = "";
        HandleProductLogic instance = new HandleProductLogic();
        Product expResult = null;
        Product result = instance.appendProductDetails(product, brand, strPriceSh, strPriceCts, strPrice_optical_diskSh, strPrice_optical_diskCts, description);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of convertStrToDouble method, of class HandleProductLogic.
     */
    @Test
    public void testConvertStrToDouble() {
        System.out.println("convertStrToDouble");
        String input = "";
        HandleProductLogic instance = new HandleProductLogic();
        double expResult = 0.0;
        double result = instance.convertStrToDouble(input);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertProductIntoDatabaseEmpty method, of class HandleProductLogic.
     */
    @Test
    public void testInsertProductIntoDatabaseEmpty() {
        System.out.println("insertProductIntoDatabaseEmpty");
        Product product = null;
        HandleProductLogic instance = new HandleProductLogic();
        instance.insertProductIntoDatabaseEmpty(product);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertProductIntoDatabase method, of class HandleProductLogic.
     */
    @Test
    public void testInsertProductIntoDatabase() {
        System.out.println("insertProductIntoDatabase");
        Product product = null;
        HandleProductLogic instance = new HandleProductLogic();
        instance.insertProductIntoDatabase(product);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateProductInDatabase method, of class HandleProductLogic.
     */
    @Test
    public void testUpdateProductInDatabase() {
        System.out.println("updateProductInDatabase");
        Product product = null;
        HandleProductLogic instance = new HandleProductLogic();
        instance.updateProductInDatabase(product);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteProductFromDatabase method, of class HandleProductLogic.
     */
    @Test
    public void testDeleteProductFromDatabase() {
        System.out.println("deleteProductFromDatabase");
        Product product = null;
        HandleProductLogic instance = new HandleProductLogic();
        instance.deleteProductFromDatabase(product);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkIfProductExists method, of class HandleProductLogic.
     */
    @Test
    public void testCheckIfProductExists() {
        System.out.println("checkIfProductExists");
        String title = "";
        HandleProductLogic instance = new HandleProductLogic();
        boolean expResult = false;
        boolean result = instance.checkIfProductExists(title);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fetchAllProducts method, of class HandleProductLogic.
     */
    @Test
    public void testFetchAllProducts() {
        System.out.println("fetchAllProducts");
        HandleProductLogic instance = new HandleProductLogic();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.fetchAllProducts();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fetchCustomProducts method, of class HandleProductLogic.
     */
    @Test
    public void testFetchCustomProducts() {
        System.out.println("fetchCustomProducts");
        HandleProductLogic instance = new HandleProductLogic();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.fetchCustomProducts();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fetchOneProduct method, of class HandleProductLogic.
     */
    @Test
    public void testFetchOneProduct() {
        System.out.println("fetchOneProduct");
        String title = "";
        HandleProductLogic instance = new HandleProductLogic();
        Product expResult = null;
        Product result = instance.fetchOneProduct(title);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fetchAllProductObjects method, of class HandleProductLogic.
     */
    @Test
    public void testFetchAllProductObjects() {
        System.out.println("fetchAllProductObjects");
        HandleProductLogic instance = new HandleProductLogic();
        ArrayList<Product> expResult = null;
        ArrayList<Product> result = instance.fetchAllProductObjects();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayInfo method, of class HandleProductLogic.
     */
    @Test
    public void testDisplayInfo() {
        System.out.println("displayInfo");
        String message = "";
        HandleProductLogic instance = new HandleProductLogic();
        instance.displayInfo(message);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayError method, of class HandleProductLogic.
     */
    @Test
    public void testDisplayError() {
        System.out.println("displayError");
        String message = "";
        HandleProductLogic instance = new HandleProductLogic();
        instance.displayError(message);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConfirmation method, of class HandleProductLogic.
     */
    @Test
    public void testGetConfirmation() {
        System.out.println("getConfirmation");
        String message = "";
        HandleProductLogic instance = new HandleProductLogic();
        int expResult = 0;
        int result = instance.getConfirmation(message);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
