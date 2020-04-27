/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.database;

import BeansPackage.BeansClass;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import retail_movie_store_mgmt.User;

/**
 *
 * @author Ian Mburu
 */
public class SecurityQueryCheck {
    private JdbcTemplate jdbcTemp = BeansClass.jdbcTemplate(
    BeansClass.dataSource());
    
    
    public User findOne(int id){
        String sql = "select * from login where id=?";
        return jdbcTemp.queryForObject(sql, this::mapUser,id);
    }
    
    private User mapUser(ResultSet rs,int row) throws SQLException{
            return new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getInt("security_qstn"),
                    rs.getString("security_ans"),
                    rs.getString("role")
            ); 
    }
    
    
}
