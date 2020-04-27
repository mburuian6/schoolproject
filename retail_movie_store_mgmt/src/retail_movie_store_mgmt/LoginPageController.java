/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt;

import BeansPackage.BeansClass;
import com.jfoenix.controls.JFXPasswordField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import retail_movie_store_mgmt.Logic.HandleMainLogic;
import retail_movie_store_mgmt.Logic.HandlePasswordLogic;
import retail_movie_store_mgmt.Logic.HandleUsersLogic;

/**
 * FXML Controller class
 *
 * @author Ian Mburu
 */
public class LoginPageController implements Initializable {
    ArrayList<String> usersList;
    @FXML ComboBox showUsersField;
    @FXML JFXPasswordField passwordField;
    ActionEvent event;
    static String userAttempt;
    String feedback;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        HandleUsersLogic instance = BeansClass.handleUsersLogic();
        usersList = instance.getUsers();
        ObservableList<String> users = FXCollections.observableArrayList(usersList);
        showUsersField.setItems(users);
        showUsersField.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent evt){
                if(showUsersField.getValue().toString() != null){
                    userAttempt = showUsersField.getValue().toString();
                }
            }
        });
        
    }    
    
    
    public void handle(KeyEvent t){
        if(t.getCode()== KeyCode.ENTER){
            getInput();
        }
    }
    
    public void getInput(){
        try{
            String user = showUsersField.getValue().toString();        
            String password = passwordField.getText();
            HandlePasswordLogic instance = BeansClass.handlePasswordLogic();
            boolean success = instance.processLoginPasswords(user,password);
            hidePage(success); 
        }
        catch(NullPointerException ne){
            displayError("Please fill all login credentials");
        }
        
    }
    
    public void setEvent(ActionEvent event){
        this.event = event;
    }
    public ActionEvent getEvent(){
        return event;
    }
    
    
    public void displayInfo(String message){
        //Window owner = parentPane.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        //alert.initOwner(owner);
        alert.show();
    }
    
    public void displayError(String message){
        //Window owner = parentPane.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        
        //alert.initOwner(owner);
        alert.show();
    }
    
    public void cancel(){
        Platform.exit();
    }
    
    
    public void forgotPassword(){
            HandleMainLogic instance = BeansClass.handleMainLogic();
            instance.callAnswerSecurityQuestion();
            hidePage();
    }
    
    public void hidePage(){
        showUsersField.getScene().getWindow().hide();
    }
    
    public void hidePage(boolean success){
        if(success){
            //hide page
            showUsersField.getScene().getWindow().hide();
        }
        else{
            //display page
            
        }
    }
}
