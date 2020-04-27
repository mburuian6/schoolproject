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
import retail_movie_store_mgmt.User;

/**
 *
 * @author Ian Mburu
 */
public class UsersHandle {
    private JdbcTemplate jdbcTemp = BeansClass.jdbcTemplate(BeansClass.dataSource());
    
    public Iterable<User> findAllUserNames() {
        return jdbcTemp.query("select username from login", this::mapRowToAllUsernames);
    }
    private User mapRowToAllUsernames(ResultSet rs, int rowNum)throws SQLException {
        return new User(rs.getString("username"));
    }
    public Iterable<User> findAllUsers() {
        return jdbcTemp.query("select * from login", this::mapRowToAllUsers);
    }
    private User mapRowToAllUsers(ResultSet rs, int rowNum)throws SQLException {
        return new User(
                rs.getString("username"),
                rs.getString("role")
        );
    }
    
    public List<User> findUser(String username) {
        return jdbcTemp.query("select * from login where username=?", this::mapRowToAllUsers,username);
    }
    
    public boolean insertToDb(User user){
        String sql = "INSERT INTO login(username,password,security_qstn,security_ans,role) VALUES (?,?,?,?,?);";
        Object[]params = {
            user.getUsername(),
            user.getPassword(),
            user.getSecurity_qstn(),
            user.getSecurity_ans(),
            user.getRole()
        };
        
        int[]types = {Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.VARCHAR,Types.VARCHAR};
        try{
            jdbcTemp.update(sql, params, types);
            return true;
        }
        catch(Exception e){
            System.out.println("Error:"+ e.getMessage());
            return false;
        }
    }
    
    public boolean UpdateInDb(User user){
        String sql = "UPDATE login SET password,role,security_qstn,security_ans VALUES (?,?,?,?) WHERE username = ?;";
        Object[]params = {
            user.getUsername(),
            user.getPassword(),
            user.getSecurity_qstn(),
            user.getSecurity_ans(),
            user.getRole()
        };
        
        int[]types = {Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.VARCHAR,Types.VARCHAR};
        try{
            jdbcTemp.update(sql, params, types);
            return true;
        }
        catch(Exception e){
            System.out.println("Error:"+ e.getMessage());
            return false;
        }
    }
    
    
    public boolean deleteFromDb(User user){
        String sql = "DELETE FROM login WHERE username=?";
        Object[]params={user.getUsername()};
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
