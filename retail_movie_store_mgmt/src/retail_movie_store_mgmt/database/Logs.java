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
import org.springframework.jdbc.core.JdbcTemplate;
import retail_movie_store_mgmt.logs.Log;

/**
 *
 * @author Ian Mburu
 */
public class Logs {
    
    private JdbcTemplate jdbcTemp = BeansClass.jdbcTemplate(
    BeansClass.dataSource());
       
    //log a user who Logs in
    public void userlogin(String log_id,String username){
        String sql = "insert into login_log (log_id,username)values(?,?)";
                
        Object[]params = {log_id,username};        
        int[]types = {Types.VARCHAR,Types.VARCHAR};
        jdbcTemp.update(sql, params, types);
    }
    
    public void userlogout(String log_id){
        String sql = "update login_log, set time_out=? where log_id=? ";
        String query = "UPDATE `login_log` SET `time_out`=DEFAULT WHERE log_id=?";        
        Object[]params = {log_id};        
        int[]types = {Types.VARCHAR};
        jdbcTemp.update(query, params, types);
    }
    
    
    public Iterable<Log> findAll(){
        String sql = "SELECT * FROM login_log";
        return jdbcTemp.query(sql, this::mapLog);
    }
    
    private Log mapLog(ResultSet rs,int row) throws SQLException{
            return new Log(
                    rs.getInt("number"),
                    rs.getString("log_id"),
                    rs.getString("username"),
                    rs.getTimestamp("time_in").toLocalDateTime(),
                    rs.getTimestamp("time_out").toLocalDateTime()
            ); 
    }
    
    
}
