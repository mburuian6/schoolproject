/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.Logic;

import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Ian Mburu
 */
public class HandleQuestionsLogicTest {
    
    public HandleQuestionsLogicTest() {
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
     * Test of getHashMap method, of class HandleQuestionsLogic.
     */
    @Test
    public void testGetHashMap() {
        System.out.println("getHashMap");
        HandleQuestionsLogic instance = new HandleQuestionsLogic();
        HashMap expResult = null;
        HashMap result = instance.getHashMap();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNewSecurityQuestion method, of class HandleQuestionsLogic.
     */
    @Test
    public void testGetNewSecurityQuestion() {
        System.out.println("getNewSecurityQuestion");
        String[] qans = null;
        HashMap<Integer, String> hm = null;
        HandleQuestionsLogic instance = new HandleQuestionsLogic();
        instance.getNewSecurityQuestion(qans, hm);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateSecurityQuestion method, of class HandleQuestionsLogic.
     */
    @Test
    public void testUpdateSecurityQuestion() {
        System.out.println("updateSecurityQuestion");
        HandleQuestionsLogic instance = new HandleQuestionsLogic();
        instance.updateSecurityQuestion();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSecurityQuestion method, of class HandleQuestionsLogic.
     */
    @Test
    public void testGetSecurityQuestion() {
        System.out.println("getSecurityQuestion");
        String username = "";
        HandleQuestionsLogic instance = new HandleQuestionsLogic();
        String expResult = "";
        String result = instance.getSecurityQuestion(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkSecurityQuestion method, of class HandleQuestionsLogic.
     */
    @Test
    public void testCheckSecurityQuestion() {
        System.out.println("checkSecurityQuestion");
        String username = "";
        String answer = "";
        HandleQuestionsLogic instance = new HandleQuestionsLogic();
        boolean expResult = false;
        boolean result = instance.checkSecurityQuestion(username, answer);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
