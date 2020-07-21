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
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
     * Test of verifyNumInput method, of class HandleMainLogic.
     */
    @Test
    public void testVerifyNumInput() {
        System.out.println("verifyNumInput");
        String[] params = {"1","2a"};
        String[] labels = {"First input","Second Input"};
        HandleMainLogic instance = new HandleMainLogic();
        
        String expResult = "Second Input";
        String result = instance.verifyNumInput(params, labels);
        
        assertEquals("Checks that the input is a number",
                expResult, result);
    }

    /**
     * Test of checkForAlphasInNum method, of class HandleMainLogic.
     */
    @Test
    public void testCheckForAlphasInNum() {
        System.out.println("checkForAlphasInNum");
        String input = "12345";
        HandleMainLogic instance = new HandleMainLogic();
        
        boolean expResult = false;
        boolean result = instance.checkForAlphasInNum(input);
        
        assertEquals("Checks that there are no alphabets in the"
                + "input",
                expResult, result);
    }

    /**
     * Test of convertDoubleToString method, of class HandleMainLogic.
     */
    @Test
    public void testConvertDoubleToString() {
        System.out.println("convertDoubleToString");
        double num = 5.0;
        HandleMainLogic instance = new HandleMainLogic();
        String expResult = "5.0";
        
        String result = instance.convertDoubleToString(num);
        
        assertEquals("Checks for correct return value",
                expResult, result);
        assertEquals("Checks for correct class returned",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of convertIntToString method, of class HandleMainLogic.
     */
    @Test
    public void testConvertIntToString() {
        System.out.println("convertIntToString");
        int input = 5;
        HandleMainLogic instance = new HandleMainLogic();
        String expResult = "5";
        String result = instance.convertIntToString(input);
        
        assertEquals("Checks that the value returned"
                + "is correct",expResult, result);
        assertEquals("Checks that the class returned is String"
                ,expResult.getClass(), result.getClass());
    }

    /**
     * Test of convertStrToDouble method, of class HandleMainLogic.
     */
    @Test
    public void testConvertStrToDouble() {
        System.out.println("convertStrToDouble");
        String input = "5.0";
        HandleMainLogic instance = new HandleMainLogic();
        
        Double expResult = 5.0;
        Double result = instance.convertStrToDouble(input);
        
        assertEquals("Check that the String "
                + "is converted to a double",
                expResult.getClass(),
                result.getClass());
        assertEquals("Checks that the value returned is correct",
                expResult, result);
        
    }

    /**
     * Test of convertStrToInt method, of class HandleMainLogic.
     */
    @Test
    public void testConvertStrToInt() {
        System.out.println("convertStrToInt");
        String input = "2";
        HandleMainLogic instance = new HandleMainLogic();
        
        Integer expResult = 2;
        int resultInt = instance.convertStrToInt(input);
        Integer result = resultInt;
        assertEquals("Checks that returned result is an integer",expResult.getClass(), result.getClass());
        assertEquals("Checks that returned result of the string value",expResult, result);
    }

    /**
     * Test of checkStringObeysMaxLength method, of class HandleMainLogic.
     */
    @Test
    public void testCheckStringObeysMaxLength() {
        System.out.println("checkStringObeysMaxLength");
        String input = "This is a test input that obeys rules";
        int maxAmt = 100;
        HandleMainLogic instance = new HandleMainLogic();
        
        boolean expResult = true;
        boolean result = instance.checkStringObeysMaxLength(input, maxAmt);
        assertEquals("Checks that input does not exceed ",expResult, result);
    }

    /**
     * Test of convertToLocalDate method, of class HandleMainLogic.
     */
    @Test
    public void testConvertToLocalDate() {
        System.out.println("convertToLocalDate");
        Date date = new Date();
        HandleMainLogic instance = new HandleMainLogic();
        
        LocalDate result = instance.convertToLocalDate(date);
        assertEquals("Checks that the result is of LocalDate Class",LocalDate.class, result.getClass());
    }

    /**
     * Test of newDay method, of class HandleMainLogic.
     */
    @Test
    public void testNewDay() {
        System.out.println("newDay");
        HandleMainLogic instance = new HandleMainLogic();
        boolean result = instance.newDay();
        boolean expResult = false;
        
        assertEquals("Checks that it is not a new day", expResult, result);
    }
    
}
