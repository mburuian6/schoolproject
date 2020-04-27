/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.Logic;

import java.time.LocalDate;
import java.util.Date;
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
public class HandleMainLogicTest {
    
    public HandleMainLogicTest() {
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
     * Test of main method, of class HandleMainLogic.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        HandleMainLogic instance = new HandleMainLogic();
        instance.main();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkNewUser method, of class HandleMainLogic.
     */
    @Test
    public void testCheckNewUser() {
        System.out.println("checkNewUser");
        User user = null;
        HandleMainLogic instance = new HandleMainLogic();
        boolean expResult = false;
        boolean result = instance.checkNewUser(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of callHome method, of class HandleMainLogic.
     */
    @Test
    public void testCallHome() {
        System.out.println("callHome");
        HandleMainLogic instance = new HandleMainLogic();
        instance.callHome();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of callLogin method, of class HandleMainLogic.
     */
    @Test
    public void testCallLogin() {
        System.out.println("callLogin");
        HandleMainLogic instance = new HandleMainLogic();
        instance.callLogin();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of callSetNewPassword method, of class HandleMainLogic.
     */
    @Test
    public void testCallSetNewPassword() {
        System.out.println("callSetNewPassword");
        HandleMainLogic instance = new HandleMainLogic();
        instance.callSetNewPassword();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetQuestion method, of class HandleMainLogic.
     */
    @Test
    public void testResetQuestion() {
        System.out.println("resetQuestion");
        HandleMainLogic instance = new HandleMainLogic();
        instance.resetQuestion();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of callSetNewQuestion method, of class HandleMainLogic.
     */
    @Test
    public void testCallSetNewQuestion() {
        System.out.println("callSetNewQuestion");
        HandleMainLogic instance = new HandleMainLogic();
        instance.callSetNewQuestion();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of callAnswerSecurityQuestion method, of class HandleMainLogic.
     */
    @Test
    public void testCallAnswerSecurityQuestion() {
        System.out.println("callAnswerSecurityQuestion");
        HandleMainLogic instance = new HandleMainLogic();
        instance.callAnswerSecurityQuestion();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of callEnterProduct method, of class HandleMainLogic.
     */
    @Test
    public void testCallEnterProduct() {
        System.out.println("callEnterProduct");
        HandleMainLogic instance = new HandleMainLogic();
        instance.callEnterProduct();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verifyNumInput method, of class HandleMainLogic.
     */
    @Test
    public void testVerifyNumInput() {
        System.out.println("verifyNumInput");
        String[] params = null;
        String[] labels = null;
        HandleMainLogic instance = new HandleMainLogic();
        String expResult = "";
        String result = instance.verifyNumInput(params, labels);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkForAlphasInNum method, of class HandleMainLogic.
     */
    @Test
    public void testCheckForAlphasInNum() {
        System.out.println("checkForAlphasInNum");
        String input = "";
        HandleMainLogic instance = new HandleMainLogic();
        boolean expResult = false;
        boolean result = instance.checkForAlphasInNum(input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of convertDoubleToString method, of class HandleMainLogic.
     */
    @Test
    public void testConvertDoubleToString() {
        System.out.println("convertDoubleToString");
        double num = 0.0;
        HandleMainLogic instance = new HandleMainLogic();
        String expResult = "";
        String result = instance.convertDoubleToString(num);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of convertIntToString method, of class HandleMainLogic.
     */
    @Test
    public void testConvertIntToString() {
        System.out.println("convertIntToString");
        int input = 0;
        HandleMainLogic instance = new HandleMainLogic();
        String expResult = "";
        String result = instance.convertIntToString(input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of convertStrToDouble method, of class HandleMainLogic.
     */
    @Test
    public void testConvertStrToDouble() {
        System.out.println("convertStrToDouble");
        String input = "";
        HandleMainLogic instance = new HandleMainLogic();
        double expResult = 0.0;
        double result = instance.convertStrToDouble(input);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of convertStrToInt method, of class HandleMainLogic.
     */
    @Test
    public void testConvertStrToInt() {
        System.out.println("convertStrToInt");
        String input = "";
        HandleMainLogic instance = new HandleMainLogic();
        int expResult = 0;
        int result = instance.convertStrToInt(input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkStringObeysMaxLength method, of class HandleMainLogic.
     */
    @Test
    public void testCheckStringObeysMaxLength() {
        System.out.println("checkStringObeysMaxLength");
        String input = "";
        int maxAmt = 0;
        HandleMainLogic instance = new HandleMainLogic();
        boolean expResult = false;
        boolean result = instance.checkStringObeysMaxLength(input, maxAmt);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of convertToLocalDate method, of class HandleMainLogic.
     */
    @Test
    public void testConvertToLocalDate() {
        System.out.println("convertToLocalDate");
        Date date = null;
        HandleMainLogic instance = new HandleMainLogic();
        LocalDate expResult = null;
        LocalDate result = instance.convertToLocalDate(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of newDay method, of class HandleMainLogic.
     */
    @Test
    public void testNewDay() {
        System.out.println("newDay");
        HandleMainLogic instance = new HandleMainLogic();
        boolean expResult = false;
        boolean result = instance.newDay();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
