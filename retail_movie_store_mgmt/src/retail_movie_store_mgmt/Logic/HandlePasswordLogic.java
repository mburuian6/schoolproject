/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.Logic;

import BeansPackage.BeansClass;
import retail_movie_store_mgmt.LoginPageController;
import retail_movie_store_mgmt.LogsLogic;
import retail_movie_store_mgmt.NewPasswordController;
import retail_movie_store_mgmt.User;
import retail_movie_store_mgmt.database.Logs;
import retail_movie_store_mgmt.database.PasswordsHandle;
import retail_movie_store_mgmt.security.Hashing;

/**
 *
 * @author Ian Mburu
 */
public class HandlePasswordLogic {
    User currentUser;
    
    public boolean checkPassword(String user, String password){
        //get user credentials attempting to login and compare with db password
        
        //getting db pass
        PasswordsHandle passwordsHandle = BeansClass.passwordsHandle();
        User dbUser = passwordsHandle.findOne(user);
        String dbPassword = dbUser.getPassword();
        
        //formatting input password
        String hashedPassword = Hashing.hashInput(password);
        
        //compare
        if(dbPassword.equals(hashedPassword)){
            currentUser = dbUser;
            return true;
        }
        else{
            return false;
        }
        
    }
    
    public void updatePassword(String password){
        //replace existing password in db
        PasswordsHandle instance = BeansClass.passwordsHandle();
        instance.updatePassword(User.loggedin.getUsername(), password);
        User.loggedin.setPassword(password);
    }
    
    public boolean processNewPasswords(String newPassword,String confirmNewPassword){
        //confirm they are equal 
        if(newPassword.equals(confirmNewPassword)){
            //confirm it is not admin
            if(!newPassword.equals(User.getDefaultQuestion())){
                String hashedPassword = Hashing.hashInput(newPassword);
                updatePassword(hashedPassword);
                //give control to main logic
                HandleMainLogic instance = BeansClass.handleMainLogic();
                instance.resetQuestion();
                //hide page
                return true;
            }
            else{
                NewPasswordController instance =BeansClass.newPasswordController();
                instance.displayError("New password cannot be the same as default. Enter another one.");
                return false;
            }
        }
        else{
            NewPasswordController instance =BeansClass.newPasswordController();
            instance.displayError("Please ensure that both password entries are identical.");
            return false;
        }
    }
    
    public boolean processLoginPasswords(String user,String password){
        //check passwords authenticity
        boolean assertion = checkPassword(user,password);        
                
        if(!assertion){                        
            LoginPageController instance = BeansClass.loginPageController();
            try{
                instance.displayError("Incorrect password for this account. Try again"); 
            }
            catch(Exception e ){}
            return false;
        }
        else{
            //correct password, set the user so that any info will have to be accessed via the user class
            User.loggedin =currentUser;
            //log the user
            String log_id = LogsLogic.getLogId(User.loggedin.getUsername());
            User.log_id = log_id;
            Logs log = BeansClass.logs();
            log.userlogin(log_id,User.loggedin.getUsername());
            
            //give control to the handlemainlogic class
            HandleMainLogic instance = BeansClass.handleMainLogic();
            instance.main();
            return true;
            }
    }
    
    
}
