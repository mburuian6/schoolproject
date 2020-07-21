/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.commonUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Ian Mburu
 */
public class DateTime {
    
    Calendar cal = new GregorianCalendar();
    
    //get both
    public int[] getDateTime(){
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);
        
        int date = cal.get(Calendar.DATE);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        
        int[] dateTime = {year,month,date,hour,min,sec};
        return dateTime;
    }
    
    //get only date
    public LocalDate getTodayDate(){
        LocalDate todayDate  = LocalDate.now();
        return todayDate;
    }
    
    //get date and time
    public LocalDateTime getTodayDateTime(){
        LocalDateTime todayDateTime  = LocalDateTime.now();
        return todayDateTime;
    }
    
    public Long getTimeInMilliseconds(){
        return cal.getTimeInMillis();
    }
    
    
    
}
