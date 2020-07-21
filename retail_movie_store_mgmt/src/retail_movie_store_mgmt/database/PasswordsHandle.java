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
import retail_movie_store_mgmt.User;

/**
 *
 * @author Ian Mburu
 */
public class PasswordsHandle {
    private JdbcTemplate jdbcTemp = BeansClass.jdbcTemplate(
    BeansClass.dataSource());
    
    public User findOne(String username){
        String sql = "select * from login where username=?";
        return jdbcTemp.queryForObject(sql, this::mapUser,username);
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
    
    public void updatePassword(String username,String password){
        String sql = "update login set password=? where username = ?";
        Object[]params = {password,username};
        int[]types = {Types.VARCHAR,Types.VARCHAR};
        jdbcTemp.update(sql, params, types);
    }
}
