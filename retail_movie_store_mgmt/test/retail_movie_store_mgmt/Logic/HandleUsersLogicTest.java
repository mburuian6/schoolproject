/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.Logic;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Ian Mburu
 */
public class HandleUsersLogicTest {
    
    public HandleUsersLogicTest() {
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
     * Test of getUsers method, of class HandleUsersLogic.
     */
    @Test
    public void testGetUsers() {
        System.out.println("getUsers");
        HandleUsersLogic instance = new HandleUsersLogic();
        ArrayList<String> result = instance.getUsers();
        assertNotNull( result);
    }
    
}
