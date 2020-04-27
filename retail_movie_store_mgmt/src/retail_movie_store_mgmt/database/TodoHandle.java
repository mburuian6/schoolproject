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
import retail_movie_store_mgmt.todo.Todo;

/**
 *
 * @author Ian Mburu
 */
public class TodoHandle {
    private JdbcTemplate jdbcTemp = BeansClass.jdbcTemplate(BeansClass.dataSource());
    
    public Iterable<Todo> findAllEntries(String profile) {
        return jdbcTemp.query("SELECT * FROM `to_do` WHERE profile=?;", this::mapRowToAllEntries,profile);
    }
    
    //row mapper,constructs an object of every result set entry with the properties
    private Todo mapRowToAllEntries(ResultSet rs, int rowNum)throws SQLException {
        return new Todo(
                rs.getString("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getDate("date").toLocalDate(),
                rs.getString("profile")
        );
    }
    
    public List<Todo> findTodoEntry(String title,String profile) {
        //return jdbcTemp.query("SELECT * FROM `to_do` where title = ?", this::mapRowToAllEntries,title);
        Object[]args={title,profile};
        int[]argTypes={Types.VARCHAR,Types.VARCHAR};
        return jdbcTemp.query("SELECT * FROM `to_do` WHERE title = ? AND profile=?",args,argTypes, this::mapRowToAllEntries);
    }
    
    public boolean insertToDb(Todo todo){
        String sql = "INSERT INTO to_do(id,title,description,date,profile) VALUES (?,?,?,?,?);";
        
        Object[]params = {
            todo.getId(),
            todo.getTitle(),
            todo.getDescription(),
            Date.valueOf(todo.getDate()),
            todo.getProfile()
        };
        
        int[]types = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.DATE,Types.VARCHAR};
        try{
            jdbcTemp.update(sql, params, types);
            return true;
        }
        catch(Exception e){
            System.out.println("Error:"+ e.getMessage());
            return false;
        }
    }
    
    public boolean updateInDb(Todo todo){
        String sql = "UPDATE to_do SET title=?,description=?,date=? WHERE id=?";        
        Object[]params = {
            todo.getTitle(),
            todo.getDescription(),
            Date.valueOf(todo.getDate()),
            todo.getId()
        };        
        int[]types = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
        
        try{
            jdbcTemp.update(sql,params,types);
            return true;
        }
        catch(Exception e){
            System.out.println("Error:"+ e.getMessage());
            return false;
        }
    }
    
    
    public boolean deleteFromDb(Todo todo){
        String sql = "DELETE FROM to_do WHERE id=?";
        Object[]params={todo.getId()};
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
