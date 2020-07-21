/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt;

import BeansPackage.BeansClass;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import retail_movie_store_mgmt.Logic.HandlePasswordLogic;
import retail_movie_store_mgmt.ui.DialogPane;

/**
 * FXML Controller class
 *
 * @author Ian Mburu
 */
public class NewPasswordController implements Initializable {
    
    @FXML TextField newPasswordField;
    @FXML TextField confirmNewPasswordField;
    @FXML Label newPasswordfeedback;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void getInput(){
        String newPassword=  newPasswordField.getText();
        String confirmNewPassword =  confirmNewPasswordField.getText(); 
        
        HandlePasswordLogic instance = BeansClass.handlePasswordLogic();
        boolean success = instance.processNewPasswords(newPassword,confirmNewPassword);
        if(success){
            hidePage();
        }
        else{
            hidePage();
        }
    }
    
    public void cancel(){
        hidePage();
        
        HandleFxmlPages handleFxmlPages = BeansClass.handleFxmlPages();
        handleFxmlPages.Home();
    }
    
    
    public void displayInfo(String message){
        DialogPane dialog = BeansClass.dialogPane();
        dialog.displayError(message);
    }
    
    public void displayError(String error){
        DialogPane dialog = BeansClass.dialogPane();
        dialog.displayError(error);
    }
    
    public void hidePage(){
        newPasswordField.getScene().getWindow().hide();
    }
}
