/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.logs;

import java.time.LocalDateTime;

/**
 *
 * @author Ian Mburu
 */
public class Log {
    String logId;
    String username;
    LocalDateTime timeIn; 
    LocalDateTime timeOut; 
    int number;
    public Log(int number,String logId, String username, LocalDateTime timeIn, LocalDateTime timeOut) {
        this.number = number;
        this.logId = logId;
        this.username = username;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(LocalDateTime timeIn) {
        this.timeIn = timeIn;
    }

    public LocalDateTime getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(LocalDateTime timeOut) {
        this.timeOut = timeOut;
    }
    
    
    
}
