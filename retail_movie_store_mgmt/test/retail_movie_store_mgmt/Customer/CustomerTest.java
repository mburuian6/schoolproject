/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.Customer;

import BeansPackage.BeansClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Ian Mburu
 */
public class CustomerTest {
    
    public CustomerTest() {
        
    }
    
    Customer customer1;
    @Before
    public void setUp(){
        customer1 = BeansClass.customer();
    }
    
    /**
     * Test of setId method, of class Customer.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        String id = "testId";
        customer1.setId(id);
        
        assertNotNull("Must ensure that the id is not null",
                customer1.getId());
        assertEquals("Must ensure that the set id is the actual id",
                id,customer1.getId());
    }

    /**
     * Test of setUsername method, of class Customer.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "testUsername";
        customer1.setUsername(username);
        
        assertNotNull("Must ensure that the username is not null",
                customer1.getUsername());
        assertEquals("Must ensure that the set username"
                + " is the actual username",
                username,customer1.getUsername());
    }

    /**
     * Test of setCustomer_name method, of class Customer.
     */
    @Test
    public void testSetCustomer_name() {
        System.out.println("setCustomer_name");
        String customer_name = "testFirstName testSecondName";
        customer1.setCustomer_name(customer_name);
        
        assertNotNull("Must ensure that the name is not null",
                customer1.getCustomer_name());
        assertEquals("Must ensure that the set name"
                + " is the actual name",
                customer_name,customer1.getCustomer_name());
    }

    /**
     * Test of setGender method, of class Customer.
     */
    @Test
    public void testSetGender() {
        System.out.println("setGender");
        String gender = "testGender";
        customer1.setGender(gender);
        
        assertNotNull("Must ensure that the gender is not null",
                customer1.getGender());
        assertEquals("Must ensure that the set gender is the actual gender",
                gender,customer1.getGender());
    }

    /**
     * Test of setMovie_likes method, of class Customer.
     */
    @Test
    public void testSetMovie_likes() {
        System.out.println("setMovie_likes");
        String movie_likes = ", testSample1, testSample2";
        customer1.setMovie_likes(movie_likes);
        
        assertNotNull("Must ensure that movie_likes is not null",
                customer1.getMovie_likes());
        assertEquals("Must ensure that set movie_likes"
                + " is the actual movie_likes",
                movie_likes,customer1.getMovie_likes());
    }

    /**
     * Test of setShow_likes method, of class Customer.
     */
    @Test
    public void testSetShow_likes() {
        System.out.println("setShow_likes");
        String show_likes = ", testSample1, testSample2";
        customer1.setShow_likes(show_likes);
        
        assertNotNull("Must ensure that show_likes is not null",
                customer1.getShow_likes());
        assertEquals("Must ensure that set shows_likes"
                + " is the actual shows_likes",
                show_likes,customer1.getShow_likes());
    }
    
}
