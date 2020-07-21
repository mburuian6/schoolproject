/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt;

import retail_movie_store_mgmt.Logic.HandleMainLogic;
import retail_movie_store_mgmt.commonUtil.DateTime;
import BeansPackage.BeansClass;
import java.math.BigInteger;
import java.util.Random;
import retail_movie_store_mgmt.database.Logs;
/**
 *
 * @author Ian Mburu
 */
public class LogsLogic {
    
    public static String getLogId(String username){
        DateTime instance = BeansClass.dateTime();
        int[] dateTime = instance.getDateTime();
        int bit_length = 128;
        int chosenNum=0;
        
        //use the random num to generate a big integer probably prime
        Random random = new Random();
        int randomNum = random.nextInt(1000000);
        BigInteger bigInteger = BigInteger.probablePrime(bit_length, random);
        
        int sum =0;
        //do addition and find modulus of the addition of these numbers with the random no.
        for(int i=0;i<dateTime.length;i++){
            sum+=dateTime[i];
        }
         
        if(sum>randomNum){
            chosenNum += sum%bigInteger.intValue();
        }
        else if(sum<randomNum){
            chosenNum += sum%bigInteger.intValue();
        }
        else{
            return getLogId(username);            
        }
        
        //to ensure no similarity
        String timeId =  generateTimeId();
        
        //convert to string
        Integer chosen = chosenNum;
        String log_id = username +"_"+timeId+"_"+"["+chosen.toString()+"]";
        return log_id;
    }
    
    public static String generateTimeId(){
        //getUser
        DateTime dtime = BeansClass.dateTime();
        int [] arr = dtime.getDateTime();
        String id = "";
        for(int i : arr){
            HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
            String subId = handleMainLogic.convertIntToString(i);
            id = id+subId;
        }
        
        return id;
    }
    
    
    public void logout(){        
        //update logout time
        Logs logs = BeansClass.logs();
        logs.userlogout(User.log_id);
        
        //cleanup;set all user variables to null
        User.loggedin = null;
        User.log_id = null;
    }
}
