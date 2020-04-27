/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.database;

import BeansPackage.BeansClass;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import retail_movie_store_mgmt.preorders.OrderDate;
import retail_movie_store_mgmt.preorders.Preorder;

/**
 *
 * @author Ian Mburu
 */
public class PreorderHandle {
    
    private JdbcTemplate jdbcTemp = BeansClass.jdbcTemplate(BeansClass.dataSource());
    
    public Iterable<Preorder> findAllEntries() {
        return jdbcTemp.query("SELECT * FROM `preorders`;", this::mapRowToAllEntries);
    }
    
    private Preorder mapRowToAllEntries(ResultSet rs, int rowNum)throws SQLException {
        return new Preorder(
                rs.getString("id"),
                rs.getString("customer_name"),
                rs.getDate("order_date").toLocalDate(),
                rs.getDate("pickup_date").toLocalDate(),
                rs.getString("customer_peripheral"),
                rs.getString("movies_list"),
                rs.getString("shows_list"),
                rs.getString("software_list"),
                rs.getString("profile"),
                rs.getString("status")
        );
    }
    
    public List<Preorder> findPreorderEntry(String id) {
        Object[]args={id};
        int[]argTypes={Types.VARCHAR};
        return jdbcTemp.query("SELECT * FROM `preorders` WHERE id = ?",args,argTypes, this::mapRowToAllEntries);
    }
    
    public Iterable<OrderDate> findAllPreorderDates() {
        return jdbcTemp.query("SELECT id,order_date FROM `preorders`", this::mapRowToAllPreorderDates);
    }
    
    private OrderDate mapRowToAllPreorderDates(ResultSet rs, int rowNum)throws SQLException {
        return new OrderDate(
                rs.getString("id"),
                rs.getDate("order_date").toLocalDate()
        );
    }
    
    public boolean insertToDb(Preorder preorder){
        String sql = "INSERT INTO preorders(id,customer_name,order_date,pickup_date,"
                + "movies_list,shows_list,software_list,customer_peripheral,profile) "
                + "VALUES (?,?,?,?,?,?,?,?,?);";
        
        Object[]params = {
            preorder.getId(),
            preorder.getCustomer_name(),
            Date.valueOf(preorder.getOrderDate()),
            Date.valueOf(preorder.getPickupDate()),
            preorder.getMovies_list(),
            preorder.getShows_list(),
            preorder.getSoftware_list(),
            preorder.getPeripheral(),
            preorder.getProfile()
        };
        
        int[]types = {Types.VARCHAR,Types.VARCHAR,Types.DATE,
            Types.DATE,Types.VARCHAR,Types.VARCHAR,
            Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
        try{
            jdbcTemp.update(sql, params, types);
            return true;
        }
        catch(Exception e){
            System.out.println("Error:"+ e.getMessage());
            return false;
        }
    }
    
    public boolean updateInDb(Preorder preorder){
        String sql = "UPDATE preorders "
                + " SET customer_name=?, pickup_date=? ,movies_list=?, "
                + "shows_list=? ,software_list=? ,customer_peripheral=? ,status=? "
                + " WHERE id=? ";        
        Object[]params = {
            preorder.getCustomer_name(),
            Date.valueOf(preorder.getPickupDate()),
            preorder.getMovies_list(),
            preorder.getShows_list(),
            preorder.getSoftware_list(),
            preorder.getPeripheral(),
            preorder.getStatus(),
            preorder.getId()
        };        
        int[]types = {Types.VARCHAR,Types.DATE,Types.VARCHAR,Types.VARCHAR,
            Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
        
        try{
            jdbcTemp.update(sql,params,types);
            return true;
        }
        catch(Exception e){
            System.out.println("Error:"+ e.getMessage());
            return false;
        }
    }
    
    public boolean updateStatusInDb(Preorder preorder){
        String sql = "UPDATE preorders"
                + " SET status=?"
                + "WHERE id=?";        
        Object[]params = {
            preorder.getStatus()
        };        
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
    
    public boolean deleteFromDb(Preorder preorder){
        String sql = "DELETE FROM preorders WHERE id=?";
        Object[]params={preorder.getId()};
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
