/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.Logic;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
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
        Todo todo = getTodoInstance();
        HandleTodoLogic instance = new HandleTodoLogic();
        boolean check = instance.checkIfExists(todo);
        if(check){
            instance.deleteTodo(todo);
        }
    }
    
    private Todo getTodoInstance(){
        Todo todo = new Todo();
        todo.setId("testId");
        todo.setTitle("testTitle");
        todo.setDescription("testDescription");
        todo.setDate(LocalDate.now());
        todo.setProfile("user");
        
        return todo;
    }

    /**
     * Test of checkTitleObeysMaxLength method, of class HandleTodoLogic.
     */
    @Test
    public void testCheckTitleObeysMaxLength() {
        System.out.println("checkTitleObeysMaxLength");
        String title = "testTitle";
        HandleTodoLogic instance = new HandleTodoLogic();
        boolean result = instance.checkTitleObeysMaxLength(title);
        assertTrue("Checks that the input 'title' does not exceed max length", result);
    }

    /**
     * Test of checkIfTimeIsPassed method, of class HandleTodoLogic.
     */
    @Test
    public void testCheckIfTimeIsPassed() {
        System.out.println("checkIfTimeIsPassed");
        Todo todo = new Todo();
        todo.setDate(LocalDate.of(2010, Month.MAY, 16));
        HandleTodoLogic instance = new HandleTodoLogic();
        boolean expResult = true;
        boolean result = instance.checkIfTimeIsPassed(todo);
        assertEquals("Checks whether the time has passed",expResult, result);
    }

    /**
     * Test of appendTodoDetails method, of class HandleTodoLogic.
     */
    @Test
    public void testAppendTodoDetails() {
        System.out.println("appendTodoDetails");
        Todo todo = new Todo();
        String id = "testId";
        String title = "testTitle";
        String description = "testDescription";
        LocalDate date = LocalDate.now();
        String profile = "user";
        HandleTodoLogic instance = new HandleTodoLogic();
        Todo result = instance.appendTodoDetails(todo, id, title, description, date, profile);
        
        assertEquals("Checks that the set details form attributes of the object",id, result.getId());
    }

    /**
     * Test of checkIfExists method, of class HandleTodoLogic.
     */
    @Test
    public void testCheckIfExists() {
        System.out.println("checkIfExists");
        Todo todo = getTodoInstance();
        HandleTodoLogic instance = new HandleTodoLogic();
        boolean expResult = false;
        boolean result = instance.checkIfExists(todo);
        assertEquals("Checks whether the particular item exists",expResult, result);
    }

    /**
     * Test of findOne method, of class HandleTodoLogic.
     */
    @Test
    public void testFindOne() {
        System.out.println("findOne");
        HandleTodoLogic instance = new HandleTodoLogic();
        Todo expResult = getTodoInstance();
        instance.insertTodo(expResult);
        
        Todo result = instance.findOne(expResult.getTitle());
        assertEquals("Checks that the 'To-do' item is the one returned as expected",expResult.getId(), result.getId());
    }

    /**
     * Test of insertTodo method, of class HandleTodoLogic.
     */
    @Test
    public void testInsertTodo() {
        System.out.println("insertTodo");
        Todo todo = getTodoInstance();
        HandleTodoLogic instance = new HandleTodoLogic();
        
        boolean check = instance.checkIfExists(todo);
        if(check){
            instance.deleteTodo(todo);
        }
        
        String insert = instance.insertTodo(todo);
        assertThat("Checks that the insert was a successful process",
                insert,containsString("Successful"));
    }

    /**
     * Test of updateTodo method, of class HandleTodoLogic.
     */
    @Test
    public void testUpdateTodo() {
        System.out.println("updateTodo");
        HandleTodoLogic instance = new HandleTodoLogic();
        Todo todo = getTodoInstance();
        instance.insertTodo(todo);
        todo.setTitle("testTitle2");
        String update = instance.updateTodo(todo);
        assertThat("Checks that the update was a successful process",
                update,containsString("Successful"));
    }

    /**
     * Test of getAllEntries method, of class HandleTodoLogic.
     */
    @Test
    public void testGetAllEntries() {
        System.out.println("getAllEntries");
        HandleTodoLogic instance = new HandleTodoLogic();
        ArrayList<Todo> result = instance.getAllEntries();
        assertNotNull("Checks that retrieving items does not return a null", result);
    }

    /**
     * Test of deleteTodo method, of class HandleTodoLogic.
     */
    @Test
    public void testDeleteTodo() {
        System.out.println("deleteTodo");
        Todo todo = getTodoInstance();;
        HandleTodoLogic instance = new HandleTodoLogic();
        instance.insertTodo(todo);
        
        String delete = instance.deleteTodo(todo);
        assertThat("Checks that the delete was a successful process",
                delete,containsString("Successful"));
    }
    
}
