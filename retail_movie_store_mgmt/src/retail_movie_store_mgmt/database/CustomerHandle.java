/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.database;

import BeansPackage.BeansClass;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import retail_movie_store_mgmt.Customer.Customer;

/**
 *
 * @author Ian Mburu
 */
public class CustomerHandle {
    private JdbcTemplate jdbcTemp = BeansClass.jdbcTemplate(BeansClass.dataSource());
    
    public Iterable<Customer> findAllCustomers() {
        return jdbcTemp.query("select * from customers", this::mapRowToAllCustomers);
    }
    
    private Customer mapRowToAllCustomers(ResultSet rs, int rowNum)throws SQLException {
        return new Customer(
                rs.getString("id"),
                rs.getString("username"),
                rs.getString("customer_name"),
                rs.getString("gender"),
                rs.getString("movie_likes"),
                rs.getString("shows_likes")
        );
    }
    
    public List<Customer> findCustomer(String username) {
        return jdbcTemp.query("select * from customers where username=?", this::mapRowToAllCustomers,username);
    }
    
    public boolean insertToDb(Customer customer){
        String sql = "INSERT INTO customers(id,username,customer_name,gender,movie_likes,shows_likes) VALUES (?,?,?,?,?,?);";
        Object[]params = {
            customer.getId(),
            customer.getUsername(),
            customer.getCustomer_name(),
            customer.getGender(),
            customer.getMovie_likes(),
            customer.getShow_likes()
        };
        
        int[]types = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
        try{
            jdbcTemp.update(sql, params, types);
            return true;
        }
        catch(Exception e){
            System.out.println("Error:"+ e.getMessage());
            return false;
        }
    }
    
    public boolean update(Customer customer){
        String sql = "UPDATE customers SET customer_name=?,gender=?,movie_likes=?,shows_likes=? WHERE username=?";        
        Object[]params = {
            customer.getCustomer_name(),
            customer.getGender(),
            customer.getMovie_likes(),
            customer.getShow_likes(),
            customer.getUsername()
        };        
        int[]types = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
        
        try{
            jdbcTemp.update(sql,params,types);
            return true;
        }
        catch(Exception e){
            System.out.println("Error:"+ e.getMessage());
            return false;
        }
    }
    
    public boolean deleteFromDb(Customer customer){
        String sql = "DELETE FROM customers WHERE username=?";
        Object[]params={customer.getUsername()};
        int[]types = {Types.VARCHAR};
        try{
            jdbcTemp.update(sql,params,types);
            return true;
        }
        catch(Exception e){
            System.out.println("Error:"+ e.getMessage());
            return false;
        }
    }
    
}
