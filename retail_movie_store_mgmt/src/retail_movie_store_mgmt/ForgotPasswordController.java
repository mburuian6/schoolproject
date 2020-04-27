/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt;

import BeansPackage.BeansClass;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import retail_movie_store_mgmt.Logic.HandleMainLogic;
import retail_movie_store_mgmt.Logic.HandleQuestionsLogic;

/**
 * FXML Controller class
 *
 * @author Ian Mburu
 */
public class ForgotPasswordController implements Initializable {

    @FXML Label questionLabel;
    @FXML Label userLabel;
    @FXML TextField answerField;
    @FXML Button submitBtn;
    String user = LoginPageController.userAttempt;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //set user
        try{
            if(user==null){
                userLabel.setText("No user account selected");
                questionLabel.setText("Unapplicable. Please head back and choose account to reset password.");
                answerField.setDisable(true);
                submitBtn.setDisable(true);

            }
            else{
                userLabel.setText("User Account: "+user);
                //set question
                HandleQuestionsLogic handleQuestionsLogic = BeansClass.handleQuestionsLogic();
                String question = handleQuestionsLogic.getSecurityQuestion(user);
                questionLabel.setText(question);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        
    }

    public void getAnswer(){
        String answer = answerField.getText();
        
        //hidePage();
        HandleQuestionsLogic instance = BeansClass.handleQuestionsLogic();
        boolean check = instance.checkSecurityQuestion(user,answer);
        if(check){
            hidePage();
        }
        else{
            //pass
        }
    }
    
    public void cancel(){
        hidePage();
        HandleMainLogic instance = BeansClass.handleMainLogic();
        instance.callLogin();        
    }
    
    public void displayInfo(String feedback){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(feedback);
        alert.show();
    }
    public void displayError(String feedback){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(feedback);
        alert.show();
    }
    
    public int getConfirmation(String feedback){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(feedback);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            return 0;
        }
        else{
            return 1;
        }
    }
    
    
    public void hidePage(){
        questionLabel.getScene().getWindow().hide();
    } 
}
