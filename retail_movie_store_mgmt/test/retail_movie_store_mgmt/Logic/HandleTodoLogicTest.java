/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.Logic;

import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import retail_movie_store_mgmt.todo.Todo;

/**
 *
 * @author Ian Mburu
 */
public class HandleTodoLogicTest {
    
    public HandleTodoLogicTest() {
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
     * Test of checkTitleObeysMaxLength method, of class HandleTodoLogic.
     */
    @Test
    public void testCheckTitleObeysMaxLength() {
        System.out.println("checkTitleObeysMaxLength");
        String title = "";
        HandleTodoLogic instance = new HandleTodoLogic();
        boolean expResult = false;
        boolean result = instance.checkTitleObeysMaxLength(title);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateTodoId method, of class HandleTodoLogic.
     */
    @Test
    public void testGenerateTodoId() {
        System.out.println("generateTodoId");
        HandleTodoLogic instance = new HandleTodoLogic();
        String expResult = "";
        String result = instance.generateTodoId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkIfTimeIsPassed method, of class HandleTodoLogic.
     */
    @Test
    public void testCheckIfTimeIsPassed() {
        System.out.println("checkIfTimeIsPassed");
        Todo todo = null;
        HandleTodoLogic instance = new HandleTodoLogic();
        boolean expResult = false;
        boolean result = instance.checkIfTimeIsPassed(todo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of appendTodoDetails method, of class HandleTodoLogic.
     */
    @Test
    public void testAppendTodoDetails() {
        System.out.println("appendTodoDetails");
        Todo todo = null;
        String id = "";
        String title = "";
        String description = "";
        LocalDate date = null;
        String profile = "";
        HandleTodoLogic instance = new HandleTodoLogic();
        Todo expResult = null;
        Todo result = instance.appendTodoDetails(todo, id, title, description, date, profile);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkIfExists method, of class HandleTodoLogic.
     */
    @Test
    public void testCheckIfExists() {
        System.out.println("checkIfExists");
        Todo todo = null;
        HandleTodoLogic instance = new HandleTodoLogic();
        boolean expResult = false;
        boolean result = instance.checkIfExists(todo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findOne method, of class HandleTodoLogic.
     */
    @Test
    public void testFindOne() {
        System.out.println("findOne");
        String title = "";
        HandleTodoLogic instance = new HandleTodoLogic();
        Todo expResult = null;
        Todo result = instance.findOne(title);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertTodo method, of class HandleTodoLogic.
     */
    @Test
    public void testInsertTodo() {
        System.out.println("insertTodo");
        Todo todo = null;
        HandleTodoLogic instance = new HandleTodoLogic();
        instance.insertTodo(todo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateTodo method, of class HandleTodoLogic.
     */
    @Test
    public void testUpdateTodo() {
        System.out.println("updateTodo");
        Todo todo = null;
        HandleTodoLogic instance = new HandleTodoLogic();
        instance.updateTodo(todo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllEntries method, of class HandleTodoLogic.
     */
    @Test
    public void testGetAllEntries() {
        System.out.println("getAllEntries");
        HandleTodoLogic instance = new HandleTodoLogic();
        ArrayList<Todo> expResult = null;
        ArrayList<Todo> result = instance.getAllEntries();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteTodo method, of class HandleTodoLogic.
     */
    @Test
    public void testDeleteTodo() {
        System.out.println("deleteTodo");
        Todo todo = null;
        HandleTodoLogic instance = new HandleTodoLogic();
        instance.deleteTodo(todo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
