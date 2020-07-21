/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.Logic;

import BeansPackage.BeansClass;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Component;
import retail_movie_store_mgmt.EnterSecurityQuestionController;
import retail_movie_store_mgmt.ForgotPasswordController;
import retail_movie_store_mgmt.LogsLogic;
import retail_movie_store_mgmt.User;
import retail_movie_store_mgmt.database.Logs;
import retail_movie_store_mgmt.database.PasswordsHandle;
import retail_movie_store_mgmt.database.SecurityQuestionsHandle;
import retail_movie_store_mgmt.securityQuestion.SecurityQuestion;

/**
 *
 * @author Ian Mburu
 */

@Component
public class HandleQuestionsLogic {
    HashMap<Integer,String> hm = new HashMap<>();
    String[] qans;
    int key;
        
    public HashMap getHashMap(){
        SecurityQuestionsHandle lst = BeansClass.securityQuestionsHandle();
        Iterable qstnListIterable = lst.findAllQuestions();
        Iterator<SecurityQuestion> qstnListIterator = qstnListIterable.iterator();
        while(qstnListIterator.hasNext()){
            SecurityQuestion element = qstnListIterator.next();
            hm.put(element.id, element.question);
        }
        
        //remove the default question set
        HashMap<Integer,String> hm_revised = new HashMap<>();
        hm_revised = hm;
        hm_revised.remove(1);
        return hm_revised;
    }    
    
    public void getNewSecurityQuestion(String[]qans,HashMap<Integer,String>hm){
        EnterSecurityQuestionController instance = BeansClass.enterSecurityQuestionController();
        this.qans = qans;
        //question,answer                
        //get its key
       
        Set<Map.Entry<Integer, String>> set = hm.entrySet();
        for(Map.Entry<Integer, String> me : set) {
            if((me.getValue()).equals(qans[0])){
                key = me.getKey();                
            }
        }        
        
        //update question        
        updateSecurityQuestion();
        //go back to main logic
        HandleMainLogic logicInstance = BeansClass.handleMainLogic();
        logicInstance.main();
    }
    
    public void updateSecurityQuestion(){
        SecurityQuestionsHandle instance = BeansClass.securityQuestionsHandle();
        User.loggedin.setSecurity_qstn(key);
        User.loggedin.setSecurity_ans(qans[1]);
        instance.updateSecurityQuestion(key,qans[1],User.loggedin.getUsername());
    }
    
    
    public String getSecurityQuestion(String username){
        PasswordsHandle instance = BeansClass.passwordsHandle();
        User user = instance.findOne(username);
        
        int secQstnId = user.getSecurity_qstn();
        
        SecurityQuestionsHandle securityQuestionsHandle= BeansClass.securityQuestionsHandle();
        SecurityQuestion secQstn = securityQuestionsHandle.findOne(secQstnId);
        
        return secQstn.question;
    }
    
    public boolean checkSecurityQuestion(String username,String answer){
        PasswordsHandle instance = BeansClass.passwordsHandle();
        User user = instance.findOne(username);
        ForgotPasswordController forgotPasswordController = BeansClass.forgotPasswordController();
        String dbAnswer = user.getSecurity_ans();
        if(answer.isEmpty()){
            forgotPasswordController.displayError("Answer cannot be empty");
            return false;
        }
        else{
            if(dbAnswer.equalsIgnoreCase(answer)){
                //login the user
                User.loggedin = user;
                
                //log the user
                String log_id = LogsLogic.getLogId(User.loggedin.getUsername());
                User.log_id = log_id;
                Logs log = BeansClass.logs();
                log.userlogin(log_id,User.loggedin.getUsername());
                
                
                //int option = JOptionPane.showConfirmDialog(null, "Do you want to change your password?");
                int option = forgotPasswordController.getConfirmation(username+",do you want to change your password?");
                //yes=0,no=1,cancel=2
                if(option==0){
                    //yes
                    HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
                    handleMainLogic.callSetNewPassword();
                    return true;
                }
                else if(option==1){
                    //no
                    HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
                    handleMainLogic.callHome();
                    return true;
                }
            }
            else{
                forgotPasswordController.displayError("Wrong answer try again");
                return false;
            }
            return false;
        }
    }
    
}
