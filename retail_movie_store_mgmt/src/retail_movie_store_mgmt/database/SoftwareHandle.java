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
import retail_movie_store_mgmt.product.Software;

/**
 *
 * @author Ian Mburu
 */
public class SoftwareHandle {
    private final JdbcTemplate jdbcTemp = BeansClass.jdbcTemplate(BeansClass.dataSource());
    
    public void softwareInsert(
            String software_id,
            String title,
            double price,
            double price_optical_disk,
            int installation_limit,
            String desc
    ){
        String sql = "INSERT INTO software_inventory (software_id,title,price,price_optical_disk,installation_limit,description)VALUES(?,?,?,?,?,?)";
                
        Object[]params = {software_id,title,price,price_optical_disk,installation_limit,desc};        
        int[]types = {Types.VARCHAR,Types.VARCHAR,Types.DOUBLE,Types.DOUBLE,Types.INTEGER,Types.VARCHAR};
        jdbcTemp.update(sql, params, types);
    }
    
    public boolean updateSoftware(Software software) {
        try{
            
            String query = "UPDATE software_inventory SET price=?,price_optical_disk=?,installation_limit=?,description=? WHERE title=?;";
            Object[]params = {software.getPrice(),
                software.getPriceOnOpticalDisk(),
                software.getInstallationLimit(),
                software.getDesc(),
                software.getTitle()};
            int[]types = {Types.DOUBLE,Types.DOUBLE,Types.INTEGER,Types.VARCHAR,Types.VARCHAR};
            jdbcTemp.update(query, params, types);
            return true;
        }
        catch(Exception e){
            System.out.println("Error:"+ e.getMessage());
            return false;
        }
    }
    
    
    
    public Software findOne(String title){
            String sql = "SELECT * FROM software_inventory WHERE title=?";
            return jdbcTemp.queryForObject(sql, this::mapSoftware,title);        
    }
    
    public Software findIfExists(String title){
            String sql = "SELECT * FROM software_inventory WHERE title=?";
            List<Software> result = jdbcTemp.query(sql, this::mapSoftware,title);
            if(result.isEmpty()){
                return null;
            }
            else{
                return result.get(0);
            }       
    }
    
    public Iterable<Software> findAll(){
        String sql = "SELECT * FROM software_inventory";
        return jdbcTemp.query(sql, this::mapSoftware);
    }
    
    private Software mapSoftware(ResultSet rs,int row) throws SQLException{
            return new Software(
                    rs.getString("title"),
                    rs.getDouble("price"),
                    rs.getDouble("price_optical_disk"),
                    rs.getInt("installation_limit"),
                    rs.getString("description")
            ); 
    }
    
    public void deleteOneFromDb(String title){
        String sql = "DELETE FROM software_inventory WHERE title=?";
        Object[]params={title};
        int[]types = {Types.VARCHAR};
        jdbcTemp.update(sql,params,types);
    }
    
}
