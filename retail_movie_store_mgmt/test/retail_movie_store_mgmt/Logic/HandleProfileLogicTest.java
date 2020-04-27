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
import retail_movie_store_mgmt.User;

/**
 *
 * @author Ian Mburu
 */
public class HandleProfileLogicTest {
    
    public HandleProfileLogicTest() {
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
     * Test of getRoles method, of class HandleProfileLogic.
     */
    @Test
    public void testGetRoles() {
        System.out.println("getRoles");
        String[] expResult = null;
        String[] result = HandleProfileLogic.getRoles();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkUserPrivilegeAdminManager method, of class HandleProfileLogic.
     */
    @Test
    public void testCheckUserPrivilegeAdminManager() {
        System.out.println("checkUserPrivilegeAdminManager");
        User user = null;
        HandleProfileLogic instance = new HandleProfileLogic();
        boolean expResult = false;
        boolean result = instance.checkUserPrivilegeAdminManager(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkUserPrivilegeAdmin method, of class HandleProfileLogic.
     */
    @Test
    public void testCheckUserPrivilegeAdmin() {
        System.out.println("checkUserPrivilegeAdmin");
        User user = null;
        HandleProfileLogic instance = new HandleProfileLogic();
        boolean expResult = false;
        boolean result = instance.checkUserPrivilegeAdmin(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkNewAdminUser method, of class HandleProfileLogic.
     */
    @Test
    public void testCheckNewAdminUser() {
        System.out.println("checkNewAdminUser");
        User user = null;
        HandleProfileLogic instance = new HandleProfileLogic();
        boolean expResult = false;
        boolean result = instance.checkNewAdminUser(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkNewSecondaryNewUser method, of class HandleProfileLogic.
     */
    @Test
    public void testCheckNewSecondaryNewUser() {
        System.out.println("checkNewSecondaryNewUser");
        User user = null;
        HandleProfileLogic instance = new HandleProfileLogic();
        boolean expResult = false;
        boolean result = instance.checkNewSecondaryNewUser(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of appendDetailsToUser method, of class HandleProfileLogic.
     */
    @Test
    public void testAppendDetailsToUser() {
        System.out.println("appendDetailsToUser");
        User user = null;
        String username = "";
        String password = "";
        String role = "";
        HandleProfileLogic instance = new HandleProfileLogic();
        User expResult = null;
        User result = instance.appendDetailsToUser(user, username, password, role);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertUser method, of class HandleProfileLogic.
     */
    @Test
    public void testInsertUser() {
        System.out.println("insertUser");
        User user = null;
        HandleProfileLogic instance = new HandleProfileLogic();
        instance.insertUser(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkIfExists method, of class HandleProfileLogic.
     */
    @Test
    public void testCheckIfExists() {
        System.out.println("checkIfExists");
        User user = null;
        HandleProfileLogic instance = new HandleProfileLogic();
        boolean expResult = false;
        boolean result = instance.checkIfExists(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllUsers method, of class HandleProfileLogic.
     */
    @Test
    public void testGetAllUsers() {
        System.out.println("getAllUsers");
        HandleProfileLogic instance = new HandleProfileLogic();
        ArrayList<User> expResult = null;
        ArrayList<User> result = instance.getAllUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteUser method, of class HandleProfileLogic.
     */
    @Test
    public void testDeleteUser() {
        System.out.println("deleteUser");
        String username = "";
        HandleProfileLogic instance = new HandleProfileLogic();
        instance.deleteUser(username);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
