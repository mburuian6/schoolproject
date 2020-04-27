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
import retail_movie_store_mgmt.product.Software;

/**
 *
 * @author Ian Mburu
 */
public class SoftwareLogicTest {
    
    public SoftwareLogicTest() {
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
     * Test of verifyNumInput method, of class SoftwareLogic.
     */
    @Test
    public void testVerifyNumInput() {
        System.out.println("verifyNumInput");
        String[] params = null;
        String[] labels = null;
        SoftwareLogic instance = new SoftwareLogic();
        String expResult = "";
        String result = instance.verifyNumInput(params, labels);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSoftwareId method, of class SoftwareLogic.
     */
    @Test
    public void testGetSoftwareId() {
        System.out.println("getSoftwareId");
        String title = "";
        SoftwareLogic instance = new SoftwareLogic();
        String expResult = "";
        String result = instance.getSoftwareId(title);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkIfSoftwareExists method, of class SoftwareLogic.
     */
    @Test
    public void testCheckIfSoftwareExists() {
        System.out.println("checkIfSoftwareExists");
        String title = "";
        SoftwareLogic instance = new SoftwareLogic();
        boolean expResult = false;
        boolean result = instance.checkIfSoftwareExists(title);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertSoftwareToDb method, of class SoftwareLogic.
     */
    @Test
    public void testInsertSoftwareToDb() {
        System.out.println("insertSoftwareToDb");
        String title = "";
        String strPrice = "";
        String strPrice_optical_disk = "";
        String strInstallation_limit = "";
        String desc = "";
        SoftwareLogic instance = new SoftwareLogic();
        instance.insertSoftwareToDb(title, strPrice, strPrice_optical_disk, strInstallation_limit, desc);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateSoftwareInDatabase method, of class SoftwareLogic.
     */
    @Test
    public void testUpdateSoftwareInDatabase() {
        System.out.println("updateSoftwareInDatabase");
        Software software = null;
        SoftwareLogic instance = new SoftwareLogic();
        instance.updateSoftwareInDatabase(software);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of appendProductDetails method, of class SoftwareLogic.
     */
    @Test
    public void testAppendProductDetails() {
        System.out.println("appendProductDetails");
        Software software = null;
        String strPrice = "";
        String strPrice_optical_disk = "";
        String strInstallationLimit = "";
        String description = "";
        SoftwareLogic instance = new SoftwareLogic();
        Software expResult = null;
        Software result = instance.appendProductDetails(software, strPrice, strPrice_optical_disk, strInstallationLimit, description);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fetchAllSoftwareObjects method, of class SoftwareLogic.
     */
    @Test
    public void testFetchAllSoftwareObjects() {
        System.out.println("fetchAllSoftwareObjects");
        SoftwareLogic instance = new SoftwareLogic();
        ArrayList<Software> expResult = null;
        ArrayList<Software> result = instance.fetchAllSoftwareObjects();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fetchOneSoftware method, of class SoftwareLogic.
     */
    @Test
    public void testFetchOneSoftware() {
        System.out.println("fetchOneSoftware");
        String title = "";
        SoftwareLogic instance = new SoftwareLogic();
        Software expResult = null;
        Software result = instance.fetchOneSoftware(title);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteSoftware method, of class SoftwareLogic.
     */
    @Test
    public void testDeleteSoftware() {
        System.out.println("deleteSoftware");
        String title = "";
        SoftwareLogic instance = new SoftwareLogic();
        instance.deleteSoftware(title);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
