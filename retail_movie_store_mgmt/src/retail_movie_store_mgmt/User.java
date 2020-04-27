/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt;

import org.springframework.stereotype.Component;

/**
 *
 * @author Ian Mburu
 */
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//
//@Data
//@RequiredArgsConstructor
@Component
public class User {
    int id;
    String username;
    String password;
    int security_qstn;
    String role;
    String security_ans;
    
    public static User loggedin;
    public static String log_id;
    
    static String adminRole = "admin";
    static String adminUsername = "admin";
    static String managerRole = "manager";
    static String employeeRole = "employee";
    static int defaultQuestion = 1;
    static String defaultPassword= "admin";
    
    public User(int id, String username, String password, int security_qstn, String security_ans,String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.security_qstn = security_qstn;
        this.security_ans = security_ans;     
        this.role = role;
    }
    
    public User(String username, String role) {
        this.username = username;
        this.role = role;      
    }
    
    public User(String username){
        this.username = username;
    }
    
    public User(){
        
    }
    public void setCurrentUser(User currentUser){
        loggedin =  currentUser;
    }
    
    public User getCurrentUser(){
        return loggedin;
    }
    
    public int getUserId(){
        return loggedin.id;
    }
    public String getCurrentUsername(){
        return loggedin.getUsername();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSecurity_qstn() {
        return security_qstn;
    }

    public void setSecurity_qstn(int security_qstn) {
        this.security_qstn = security_qstn;
    }

    public String getSecurity_ans() {
        return security_ans;
    }

    public void setSecurity_ans(String security_ans) {
        this.security_ans = security_ans;
    }

    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    //get defaults

    public static String getAdminRole() {
        return adminRole;
    }

    public static String getAdminUsername() {
        return adminUsername;
    }

    public static String getEmployeeRole() {
        return employeeRole;
    }

    public static int getDefaultQuestion() {
        return defaultQuestion;
    }

    public static String getManagerRole() {
        return managerRole;
    }
    
    
    
}
