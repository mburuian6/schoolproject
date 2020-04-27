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
import retail_movie_store_mgmt.purchases.media.MediaPurchase;

/**
 *
 * @author Ian Mburu
 */
public class MediaPurchaseHandle {
    
    private JdbcTemplate jdbcTemp = BeansClass.jdbcTemplate(BeansClass.dataSource());
    
    public Iterable<MediaPurchase> findAllEntries() {
        return jdbcTemp.query("SELECT * FROM `media_purchase`;", this::mapRowToAllEntries);
    }
    
    private MediaPurchase mapRowToAllEntries(ResultSet rs, int rowNum)throws SQLException {
        return new MediaPurchase(
                rs.getString("id"),
                rs.getDate("date").toLocalDate(),
                rs.getString("media"),
                rs.getInt("quantity"),
                rs.getDouble("price"),
                rs.getDouble("total"),
                rs.getString("profile")
        );
    }
    
    public Iterable<PurchaseDate> findAllDateEntries() {
        return jdbcTemp.query("SELECT id,date FROM `media_purchase`;", this::mapRowToAllDates);
    }
    
    public Iterable<MediaPurchase> findBetweeenDateEntries(Date dateStart, Date dateEnd) {
        Object[]args={dateStart,dateEnd};
        int[]argTypes={Types.DATE,Types.DATE};
        return jdbcTemp.query("SELECT * FROM `media_purchase` WHERE date BETWEEN ? AND ?;",args,argTypes, this::mapRowToAllEntries);
    }
    
    public Iterable<MediaPurchase> findThisDateEntries(Date date) {
        Object[]args={date};
        int[]argTypes={Types.DATE};
        return jdbcTemp.query("SELECT * FROM `media_purchase` WHERE date=?;",args,argTypes, this::mapRowToAllEntries);
    }
    
    private PurchaseDate mapRowToAllDates(ResultSet rs, int rowNum)throws SQLException {
        return new PurchaseDate(
                rs.getString("id"),
                rs.getDate("date").toLocalDate()
        );
    }
    
    public List<MediaPurchase> findMediaPurchaseEntry(String id) {
        Object[]args={id};
        int[]argTypes={Types.VARCHAR};
        return jdbcTemp.query("SELECT * FROM `media_purchase` WHERE id = ? ",args,argTypes, this::mapRowToAllEntries);
    }
    
    public List<MediaPurchase> findTodayEntries(LocalDate today) {
        Object[]args={Date.valueOf(today)};
        int[]argTypes={Types.DATE};
        return jdbcTemp.query("SELECT * FROM `media_purchase` WHERE date=?",args,argTypes, this::mapRowToAllEntries);
    }
    
    
    
    public boolean insertToDb(MediaPurchase mediaPurchase){
        String sql = "INSERT INTO media_purchase(id,date,media,quantity,"
                + "price,total,profile) "
                + "VALUES (?,?,?,?,?,?,?);";
        
        Object[]params = {
            mediaPurchase.getId(),
            Date.valueOf(mediaPurchase.getDate()),
            mediaPurchase.getTitle(),
            mediaPurchase.getQuantity(),
            mediaPurchase.getPrice(),
            mediaPurchase.getTotal(),
            mediaPurchase.getProfile()
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
    
    public boolean updateInDb(MediaPurchase mediaPurchase){
        String sql = "UPDATE media_purchase"
                + "SET media=?, quantity=? ,price=?, total=?"
                + "WHERE id=?";
        
        Object[]params = {
            mediaPurchase.getTitle(),
            mediaPurchase.getQuantity(),
            mediaPurchase.getPrice(),
            mediaPurchase.getTotal(),
            mediaPurchase.getId(),
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
    
    public boolean deleteFromDb(MediaPurchase mediaPurchase){
        String sql = "DELETE FROM media_purchase WHERE id=?";
        Object[]params={mediaPurchase.getId()};
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
