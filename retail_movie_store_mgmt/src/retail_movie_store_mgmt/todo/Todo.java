/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.todo;

import java.time.LocalDate;

/**
 *
 * @author Ian Mburu
 */
public class Todo {
    String id;
    String title;
    String description;
    LocalDate date;
    String profile;
    
    public Todo(String id,String title, String description, LocalDate date,String profile) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.profile = profile;
    }

    public Todo(){
        
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
    
    
    
}
