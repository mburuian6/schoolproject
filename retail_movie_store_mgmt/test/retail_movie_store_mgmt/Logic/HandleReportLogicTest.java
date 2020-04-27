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
import retail_movie_store_mgmt.Sales.MediaAndCustomSaleEntry;

/**
 *
 * @author Ian Mburu
 */
public class HandleReportLogicTest {
    
    public HandleReportLogicTest() {
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
     * Test of reportAll method, of class HandleReportLogic.
     */
    @Test
    public void testReportAll() {
        System.out.println("reportAll");
        HandleReportLogic instance = new HandleReportLogic();
        ArrayList<MediaAndCustomSaleEntry> expResult = null;
        ArrayList<MediaAndCustomSaleEntry> result = instance.reportAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
