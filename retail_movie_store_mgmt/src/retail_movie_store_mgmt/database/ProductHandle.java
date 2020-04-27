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
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import retail_movie_store_mgmt.product.Product;

/**
 *
 * @author Ian Mburu
 */
public class ProductHandle {
    /**
     * For auto-increment values:
     * input null at insertion point
     * 
     */
    
    private JdbcTemplate jdbcTemp = BeansClass.jdbcTemplate(BeansClass.dataSource());
    
    public boolean insertProduct(String title,String brand,double price,double price_optical_disk,String description,String tag){
        String sql = "INSERT INTO product(title,brand,price,price_optical_disk,description,tag) VALUES(?,?,?,?,?,?)";
        Object[]params = {title,brand,price,price_optical_disk,description,tag};
        int[]types = {Types.VARCHAR,Types.VARCHAR,Types.DOUBLE,Types.DOUBLE,Types.VARCHAR,Types.VARCHAR};
        try{
            jdbcTemp.update(sql, params, types);
            return true;
        }
        catch(DataAccessException e){
            return false;
        }
    }
    
    public boolean updateProduct(String title,String brand,double price,double price_optical_disk,String description){
        String sql = "UPDATE product SET brand=?,price=?,price_optical_disk=?,description=? WHERE title=?";
        Object[]params = {brand,price,price_optical_disk,description,title};
        int[]types = {Types.VARCHAR,Types.DOUBLE,Types.DOUBLE,Types.VARCHAR,Types.VARCHAR};
        try{
            jdbcTemp.update(sql, params, types);
            return true;
        }
        catch(DataAccessException e){
            return false;
        }
    }
    
    public Iterable<Product> findAll(){
        String sql = "SELECT * FROM product";
        return jdbcTemp.query(sql, this::mapProduct);
    }
    
    public Product findOne(String title){
            String sql = "SELECT * FROM product WHERE title=?";
            return jdbcTemp.queryForObject(sql, this::mapProduct,title);        
    }
    
    public List<Product> findCustoms(String tag){
            String sql = "SELECT * FROM product WHERE tag=?";
            return jdbcTemp.query(sql, this::mapProduct,tag);        
    }
    
    public Product findIfExists(String title){
            String sql = "SELECT * FROM product WHERE title=?";
            List<Product> result = jdbcTemp.query(sql, this::mapProduct,title);
            if(result.isEmpty()){
                return null;
            }
            else{
                return result.get(0);
            }
    }
    
    private Product mapProduct(ResultSet rs,int row) throws SQLException{
            return new Product(
                    rs.getString("title"),
                    rs.getString("brand"),
                    rs.getDouble("price"),
                    rs.getDouble("price_optical_disk"),
                    rs.getString("description")
            ); 
    }
    
    public boolean deleteFromDb(String title){
        String sql = "DELETE FROM product WHERE title=?";
        Object[]params={title};
        int[]types = {Types.VARCHAR};
        try{
            jdbcTemp.update(sql,params,types);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    
    public void deleteAllFromDb(){
        String sql = "DELETE FROM `product` WHERE 1";
        jdbcTemp.update(sql);
    }
}
