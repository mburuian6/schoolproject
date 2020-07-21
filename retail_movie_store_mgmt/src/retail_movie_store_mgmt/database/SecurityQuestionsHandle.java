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
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import retail_movie_store_mgmt.securityQuestion.SecurityQuestion;

import retail_movie_store_mgmt.User;
/**
 *
 * @author Ian Mburu
 */
@Repository
public class SecurityQuestionsHandle {
    //ArrayList<String> securityQuestions = new ArrayList<>();
    private JdbcTemplate jdbc = BeansClass.jdbcTemplate(BeansClass.dataSource());
      
    //retrieve the questions
    public Iterable<SecurityQuestion> findAllQuestions() {
        return jdbc.query("select id,qstn from security_qstns", this::mapRowToQuestion);
    }
    
    //retrieve one particular question
    public SecurityQuestion findOne(int id) {
        String sql = "select * from security_qstns where id=?";
        return jdbc.queryForObject(sql, this::mapRowToQuestion,id);
    }
    
    private SecurityQuestion mapRowToQuestion(ResultSet rs, int rowNum)throws SQLException {
        return new SecurityQuestion(
            rs.getInt("id"),
            rs.getString("qstn")
            );
    }
    
    public void updateSecurityQuestion(int qstn,String ans,String username){
        String sql = "update login set security_qstn=?,security_ans=? where username = ?";
        Object[]params = {qstn,ans,username};
        int[]types = {Types.INTEGER,Types.VARCHAR,Types.VARCHAR};
        jdbc.update(sql, params, types);
    }
    
       
    
}
