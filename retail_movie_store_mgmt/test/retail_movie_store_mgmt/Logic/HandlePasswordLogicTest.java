/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.Logic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Ian Mburu
 */
public class HandlePasswordLogicTest {
    
    public HandlePasswordLogicTest() {
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
     * Test of checkPassword method, of class HandlePasswordLogic.
     */
    @Test
    public void testCheckPassword() {
        System.out.println("checkPassword");
        String user = "";
        String password = "";
        HandlePasswordLogic instance = new HandlePasswordLogic();
        boolean expResult = false;
        boolean result = instance.checkPassword(user, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePassword method, of class HandlePasswordLogic.
     */
    @Test
    public void testUpdatePassword() {
        System.out.println("updatePassword");
        String password = "";
        HandlePasswordLogic instance = new HandlePasswordLogic();
        instance.updatePassword(password);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of processNewPasswords method, of class HandlePasswordLogic.
     */
    @Test
    public void testProcessNewPasswords() {
        System.out.println("processNewPasswords");
        String newPassword = "";
        String confirmNewPassword = "";
        HandlePasswordLogic instance = new HandlePasswordLogic();
        boolean expResult = false;
        boolean result = instance.processNewPasswords(newPassword, confirmNewPassword);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of processLoginPasswords method, of class HandlePasswordLogic.
     */
    @Test
    public void testProcessLoginPasswords() {
        System.out.println("processLoginPasswords");
        String user = "";
        String password = "";
        HandlePasswordLogic instance = new HandlePasswordLogic();
        boolean expResult = false;
        boolean result = instance.processLoginPasswords(user, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
