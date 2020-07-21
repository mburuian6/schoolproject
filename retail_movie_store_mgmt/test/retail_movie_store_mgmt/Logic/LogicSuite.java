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
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Ian Mburu
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({retail_movie_store_mgmt.Logic.HandleMainLogicTest.class, retail_movie_store_mgmt.Logic.HandlePasswordLogicTest.class, retail_movie_store_mgmt.Logic.HandleMediaPurchaseLogicTest.class, retail_movie_store_mgmt.Logic.HandleTodoLogicTest.class, retail_movie_store_mgmt.Logic.HandleSoftwarePurchaseLogicTest.class, retail_movie_store_mgmt.Logic.HandleUsersLogicTest.class, retail_movie_store_mgmt.Logic.HandlePreorderLogicTest.class, retail_movie_store_mgmt.Logic.HandleCustomerLogicTest.class, retail_movie_store_mgmt.Logic.HandleProfileLogicTest.class})
public class LogicSuite {

    //static Database_start_stop db ;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
//        Database_start_stop db = BeansClass.database_begin_end();
//        db.start_db();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        //db.stop_db();
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
