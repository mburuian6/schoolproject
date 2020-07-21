/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.Logic;

import java.util.ArrayList;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import retail_movie_store_mgmt.User;
import retail_movie_store_mgmt.security.Hashing;

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
        User user = new User();
        String username = "testUser";
        String password = "testpwd";
        String role = "employee";
        User.loggedin = new User("admin");
        
        HandleProfileLogic instance = new HandleProfileLogic();
        user = instance.appendDetailsToUser(user, username, password, role);    
        boolean check = instance.checkIfExists(user);
        if(check){
            instance.deleteUser(user.getUsername());
        }
        
    }

    /**
     * Test of getRoles method, of class HandleProfileLogic.
     */
    @Test
    public void testGetRoles() {
        System.out.println("getRoles");
        String[] result = HandleProfileLogic.getRoles();
        assertNotNull("Checks that application roles are not null",result);
    }

    /**
     * Test of checkUserPrivilegeAdminManager method, of class HandleProfileLogic.
     */
    @Test
    public void testCheckUserPrivilegeAdminManager() {
        System.out.println("checkUserPrivilegeAdminManager");
        User user = new User("admin");
        user.setRole("admin");
        User.loggedin = user ;
        HandleProfileLogic instance = new HandleProfileLogic();
        boolean result = instance.checkUserPrivilegeAdminManager(user);
        assertTrue("Checks that the user had admin/manager privileges",result);
    }

    /**
     * Test of checkUserPrivilegeAdmin method, of class HandleProfileLogic.
     */
    @Test
    public void testCheckUserPrivilegeAdmin() {
        System.out.println("checkUserPrivilegeAdmin");
        User user = new User("admin");
        user.setRole("admin");
        User.loggedin = user ;
        HandleProfileLogic instance = new HandleProfileLogic();
        boolean result = instance.checkUserPrivilegeAdminManager(user);
        assertTrue("Checks that the user had admin privileges",result);
    }

    /**
     * Test of checkNewAdminUser method, of class HandleProfileLogic.
     */
    @Test
    public void testCheckNewAdminUser() {
        System.out.println("checkNewAdminUser");
        User user = new User("admin");
        user.setPassword(Hashing.hashInput("admin"));
        user.setSecurity_ans("admin");
        user.setRole("admin");
        user.setSecurity_qstn(1);
        User.loggedin = user ;
        
        HandleProfileLogic instance = new HandleProfileLogic();
        boolean result = instance.checkNewAdminUser(user);
        assertTrue("Checks that the user is a new default admin user", result);
    }

    /**
     * Test of checkNewSecondaryNewUser method, of class HandleProfileLogic.
     */
    @Test
    public void testCheckNewSecondaryNewUser() {
        System.out.println("checkNewSecondaryNewUser");
        User user = new User("testUser");
        user.setPassword("testpwd");
        user.setSecurity_ans("testans");
        user.setRole("employee");
        user.setSecurity_qstn(1);
        
        HandleProfileLogic instance = new HandleProfileLogic();
        boolean result = instance.checkNewSecondaryNewUser(user);
        assertTrue("Checks that the secondary user is new", result);
    }

    /**
     * Test of appendDetailsToUser method, of class HandleProfileLogic.
     */
    @Test
    public void testAppendDetailsToUser() {
        System.out.println("appendDetailsToUser");
        User user = new User();
        String username = "testUser";
        String password = "testpwd";
        String role = "employee";
        
        HandleProfileLogic instance = new HandleProfileLogic();
        User result = instance.appendDetailsToUser(user, username, password, role);
        
        assertNotNull("Checks that the necessary user details are attched to the user",result);
    }

    /**
     * Test of insertUser method, of class HandleProfileLogic.
     */
    @Test
    public void testInsertUser() {
        System.out.println("insertUser");
        User user = new User();
        String username = "testUser";
        String password = "testpwd";
        String role = "employee";
        
        HandleProfileLogic instance = new HandleProfileLogic();
        user = instance.appendDetailsToUser(user, username, password, role);    
        boolean check = instance.checkIfExists(user);
        if(check){
            instance.deleteUser(username);
        }
        
        String result = instance.insertUser(user);
        assertThat("Check that the insert has gone on successfully",
                result,containsString("Successful"));
    }

    /**
     * Test of checkIfExists method, of class HandleProfileLogic.
     */
    @Test
    public void testCheckIfExists() {
        System.out.println("checkIfExists");
        User user = new User("admin");
        HandleProfileLogic instance = new HandleProfileLogic();
        
        boolean result = instance.checkIfExists(user);
        assertTrue("Checks that the 'admin' default class is present as a sample", result);
    }

    /**
     * Test of getAllUsers method, of class HandleProfileLogic.
     */
    @Test
    public void testGetAllUsers() {
        System.out.println("getAllUsers");
        HandleProfileLogic instance = new HandleProfileLogic();
        ArrayList<User> result = instance.getAllUsers();
        assertNotNull("Check that the users are present", result);
    }

    /**
     * Test of deleteUser method, of class HandleProfileLogic.
     */
    @Test
    public void testDeleteUser() {
        System.out.println("deleteUser");
        User user = new User();
        String username = "testUser";
        String password = "testpwd";
        String role = "employee";
        
        HandleProfileLogic instance = new HandleProfileLogic();
        user = instance.appendDetailsToUser(user, username, password, role);    
        boolean check = instance.checkIfExists(user);
        if(!check){
            instance.insertUser(user);
        }
        
        String result = instance.deleteUser(user.getUsername());
        assertThat("Check that the delete has gone on successfully",
                result,containsString("Successful"));
    }
    
}
