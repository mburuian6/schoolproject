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
import java.time.LocalDate;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import retail_movie_store_mgmt.purchases.PurchaseDate;
import retail_movie_store_mgmt.purchases.software.SoftwarePurchase;

/**
 *
 * @author Ian Mburu
 */
public class SoftwarePurchaseHandle {
    
    private JdbcTemplate jdbcTemp = BeansClass.jdbcTemplate(BeansClass.dataSource());
    
    public Iterable<SoftwarePurchase> findAllEntries() {
        return jdbcTemp.query("SELECT * FROM `software_purchase`;", this::mapRowToAllEntries);
    }
    
    private SoftwarePurchase mapRowToAllEntries(ResultSet rs, int rowNum)throws SQLException {
        return new SoftwarePurchase(
                rs.getString("id"),
                rs.getDate("date").toLocalDate(),
                rs.getString("software"),
                rs.getInt("quantity"),
                rs.getDouble("price"),
                rs.getDouble("total"),
                rs.getString("profile")
        );
    }
    
    public Iterable<SoftwarePurchase> findBetweenDateEntries(Date dateStart, Date dateEnd) {
        Object[]args={dateStart,dateEnd};
        int[]argTypes={Types.DATE,Types.DATE};
        return jdbcTemp.query("SELECT * FROM `software_purchase` WHERE date BETWEEN ? AND ?;",args,argTypes, this::mapRowToAllEntries);
    }
    
    public Iterable<SoftwarePurchase> findThisDateEntries(Date date) {
        Object[]args={date};
        int[]argTypes={Types.DATE};
        return jdbcTemp.query("SELECT * FROM `software_purchase` WHERE date = ?;",args,argTypes, this::mapRowToAllEntries);
    }
    
    public Iterable<PurchaseDate> findAllDateEntries() {
        return jdbcTemp.query("SELECT id,date FROM `software_purchase`;", this::mapRowToAllDates);
    }
    
    private PurchaseDate mapRowToAllDates(ResultSet rs, int rowNum)throws SQLException {
        return new PurchaseDate(
                rs.getString("id"),
                rs.getDate("date").toLocalDate()
        );
    }
    
    public List<SoftwarePurchase> findSoftwarePurchaseEntry(String id) {
        Object[]args={id};
        int[]argTypes={Types.VARCHAR};
        return jdbcTemp.query("SELECT * FROM `software_purchase` WHERE id = ?",args,argTypes, this::mapRowToAllEntries);
    }
    
     public List<SoftwarePurchase> findTodayEntries(LocalDate today) {
        Object[]args={Date.valueOf(today)};
        int[]argTypes={Types.DATE};
        return jdbcTemp.query("SELECT * FROM `software_purchase` WHERE date=?",args,argTypes, this::mapRowToAllEntries);
    }
    
    
    public boolean insertToDb(SoftwarePurchase softwarePurchase){
        String sql = "INSERT INTO software_purchase(id,date,software,quantity,"
                + "price,total,profile) "
                + "VALUES (?,?,?,?,?,?,?);";
        
        Object[]params = {
            softwarePurchase.getId(),
            Date.valueOf(softwarePurchase.getDate()),
            softwarePurchase.getTitle(),
            softwarePurchase.getQuantity(),
            softwarePurchase.getPrice(),
            softwarePurchase.getTotal(),
            softwarePurchase.getProfile()
        };
        
        int[]types = {Types.VARCHAR,Types.DATE,Types.VARCHAR,
            Types.INTEGER,Types.DOUBLE,Types.DOUBLE,Types.VARCHAR};
        try{
            jdbcTemp.update(sql, params, types);
            return true;
        }
        catch(Exception e){
            System.out.println("Error:"+ e.getMessage());
            return false;
        }
    }
    
    public boolean updateInDb(SoftwarePurchase softwarePurchase){
        String sql = "UPDATE software_purchase"
                + "SET software=?, quantity=? ,price=?, total=?"
                + "WHERE id=?";
        
        Object[]params = {
            softwarePurchase.getTitle(),
            softwarePurchase.getQuantity(),
            softwarePurchase.getPrice(),
            softwarePurchase.getTotal(),
            softwarePurchase.getId(),
        };
        
        int[]types = {Types.VARCHAR,Types.INTEGER,Types.DOUBLE,
            Types.DOUBLE};
        try{
            jdbcTemp.update(sql, params, types);
            return true;
        }
        catch(Exception e){
            System.out.println("Error:"+ e.getMessage());
            return false;
        }
    }
    
    public boolean deleteFromDb(SoftwarePurchase softwarePurchase){
        String sql = "DELETE FROM software_purchase WHERE id=?";
        Object[]params={softwarePurchase.getId()};
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
