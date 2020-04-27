/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.Logic;

import BeansPackage.BeansClass;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import retail_movie_store_mgmt.HandleFxmlPages;
import retail_movie_store_mgmt.User;
import retail_movie_store_mgmt.commonUtil.DateTime;

/**
 *
 * @author Ian Mburu
 */

public class HandleMainLogic {
    public void main(){
        //pre-requisites
        User currentUser = User.loggedin;                       
        HandleMainLogic instance = BeansClass.handleMainLogic();
        
        //check user status
        boolean newUser = instance.checkNewUser(currentUser);
        if(newUser){
            //reset credentials
            callSetNewPassword();
        }
        else{
            //go to homepage
            callHome();           
        }
    }
    
    //checks if the user is new 
    //check if pwd is admin and question is the firstin db to assert status
    public boolean checkNewUser(User user){   
        HandleProfileLogic handleProfileLogic = BeansClass.handleProfileLogic();
        boolean checkAdmin = handleProfileLogic.checkNewAdminUser(user);
        boolean checkSecondary = handleProfileLogic.checkNewSecondaryNewUser(user);;
        if(checkAdmin || checkSecondary){
            return true;
        }
        else{
            return false;
        }
    }
    
    //calling fxml pages methods
    public void callHome(){
        HandleFxmlPages instance= BeansClass.handleFxmlPages();
        instance.Home();        
    }
    
    public void callLogin(){
        HandleFxmlPages instance= BeansClass.handleFxmlPages();
        instance.login();        
    }
    
    public void callSetNewPassword(){
        HandleFxmlPages instance= BeansClass.handleFxmlPages();
        instance.setNewPassword();
    }
    
    public void resetQuestion(){
        callSetNewQuestion();
    }
    
    public void callSetNewQuestion(){
        HandleFxmlPages instance= BeansClass.handleFxmlPages();        
        instance.setNewQuestion();
    }
    
    public void callAnswerSecurityQuestion(){
        HandleFxmlPages instance= BeansClass.handleFxmlPages();
        instance.answerSecurityQuestion();
    }
    
    public void callEnterProduct(){
        HandleFxmlPages instance= BeansClass.handleFxmlPages();
        instance.enterProduct();
    }
    
    //verify input methods
    public String verifyNumInput(String[]params,String[]labels){        
        for(int i=0;i<params.length;i++){
            String input = params[i];
            boolean found = checkForAlphasInNum(input);
            if(found){
                return labels[i];
            }
        }
        return null;
    }
    
    //checks if there is any alphabet character in the input
    public boolean checkForAlphasInNum(String input){
        String lowercase = "[a-z]";
        String uppercase = "[A-Z]";
        String specialChars = "-";
        Pattern patt1 = Pattern.compile(lowercase);
        Pattern patt2 = Pattern.compile(uppercase);
        Pattern patt3 = Pattern.compile(specialChars);
        
        Matcher matt1 = patt1.matcher(input);
        Matcher matt2 = patt2.matcher(input);
        Matcher matt3 = patt3.matcher(input);
                
        if(matt1.find()){
            return true;
        }
        else if(matt2.find()){
            return true;
        }
        else if(matt3.find()){
            return true;
        }
        return false;
    }
    
    //converting data types methods
    public String convertDoubleToString(double num){
        Double numObj= num; 
        return numObj.toString();
    }
    
    public String convertIntToString(int input){
        Integer Input = input;
        return Input.toString();
    }
    
    public double convertStrToDouble(String input){
        try{
            double doubleVal = Double.valueOf(input);
            return doubleVal;
        }
        catch(Exception e){
            return 0;
        }
        
    }
    
    public int convertStrToInt(String input){
        try{
            int integer = Integer.valueOf(input);
            return integer;
        }
        catch(Exception e){
            return 0;
        }
    }
    
    public boolean checkStringObeysMaxLength(String input,int maxAmt){
        try{
            return input.length()<= maxAmt;
        }
        catch(NullPointerException e){
            return false;
        }
        
    }
    
    public LocalDate convertToLocalDate(Date date){
        LocalDateTime conv = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return conv.toLocalDate();
    }
    
    public java.sql.Date convertToSqlDate(LocalDate localDate){
        return java.sql.Date.valueOf(localDate);
    }
    
    public Date convertToUtilDate(LocalDate localDate){
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        return date;
    }
    
    public boolean newDay(){
        DateTime dateTime = BeansClass.dateTime();
        LocalTime localTime = dateTime.getTodayDateTime().toLocalTime();
        if(localTime.getHour() == 0){
            return true;
        }
        else{
            return false;
        }
    }
    
}
