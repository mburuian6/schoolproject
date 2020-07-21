/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Ian Mburu
 */
public class Retail_movie_store_mgmtTest {
    
    public Retail_movie_store_mgmtTest() {
    }

    /**
     * Test of start method, of class Retail_movie_store_mgmt.
     */
//    @Test
//    public void testStart() {
//        System.out.println("start");
//        Stage stage = new Stage();
//        Retail_movie_store_mgmt instance = new Retail_movie_store_mgmt();
//        instance.start(stage);
//    }

    /**
     * Test of stop method, of class Retail_movie_store_mgmt.
     */
//    @Test
//    public void testStop() {
//        System.out.println("stop");
//        Retail_movie_store_mgmt instance = new Retail_movie_store_mgmt();
//        instance.stop();
//    }

    /**
     * Test of cleanUp method, of class Retail_movie_store_mgmt.
     */
    @Test
    public void testCleanUpUser() {
        System.out.println("cleanUp User");
        Retail_movie_store_mgmt instance = new Retail_movie_store_mgmt();
        instance.cleanUp();
        
        assertSame("User set to null",User.loggedin,null);
        assertSame("Userlogid set to null",User.log_id,null);
    }
    
//    @Test
//    public void testCleanUpTodo() {
//        System.out.println("cleanUp ToDo thread");
//        Retail_movie_store_mgmt instance = new Retail_movie_store_mgmt();
//        instance.cleanUp();
//        
//        ToDoController toDoController = BeansClass.toDoController();
//        TimerTask listTimerTask = toDoController.getListTimerTask();
//        
//        boolean cancel = false;
//        if(toDoController.getListTimerTask()!= null){
//            cancel = toDoController.getListTimerTask().cancel();
//        }
//        
//        assertSame("Todo Threads set to null",cancel,true);
//    }

    /**
     * Test of main method, of class Retail_movie_store_mgmt.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Retail_movie_store_mgmt.main(args);
    }

    /**
     * Test of start_database method, of class Retail_movie_store_mgmt.
     */
    @Test
    public void testStart_database() {
        System.out.println("start_database");
        Retail_movie_store_mgmt instance = new Retail_movie_store_mgmt();
        boolean start_database = instance.start_database();
        
        assertTrue("Database started",start_database);
    }

    /**
     * Test of stop_database method, of class Retail_movie_store_mgmt.
     */
    @Test
    public void testStop_database() {
        System.out.println("stop_database");
        Retail_movie_store_mgmt instance = new Retail_movie_store_mgmt();
        boolean stop_database = instance.stop_database();
        
        assertTrue("Database stopped",stop_database);
    }
    
}
