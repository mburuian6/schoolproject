/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt;

import BeansPackage.BeansClass;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Window;
import retail_movie_store_mgmt.Logic.HandleMainLogic;
import retail_movie_store_mgmt.Logic.HandleProfileLogic;
import retail_movie_store_mgmt.report.LogsReport;
import retail_movie_store_mgmt.ui.DialogPane;

/**
 * FXML Controller class
 *
 * @author Ian Mburu
 */
public class SideMenuController implements Initializable {
    
    @FXML Label userLabel;
    static Window window;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        userLabel.setText(User.loggedin.getUsername());
        
    }    
    
    public void generateLogReport(){
        HandleProfileLogic handleProfileLogic = BeansClass.handleProfileLogic();
        boolean privilege = handleProfileLogic.checkUserPrivilegeAdminManager(User.loggedin);
        
//        ProgressIndicator pi = new ProgressIndicator();
//        pi.beginGeneration();
        if(privilege){
            Thread thread = new Thread(){
                public void run(){                    
                    LogsReport logsReport = BeansClass.logsReport();
                    logsReport.mainMethod();
                }
                
            };
            thread.start();
        }
        else{
            displayError("You are not allowed to access this functionality");
        }
    }

    @FXML
    private void openTodo(ActionEvent event) {
        HandleFxmlPages handleFxmlPages = BeansClass.handleFxmlPages();
        handleFxmlPages.toDo();   
        hidePage();
    }

    @FXML
    private void openUsers(ActionEvent event) {
        HandleProfileLogic handleProfileLogic = BeansClass.handleProfileLogic();
        boolean privilege = handleProfileLogic.checkUserPrivilegeAdminManager(User.loggedin);
        if(privilege){
            hidePage();
            
            HandleFxmlPages handleFxmlPages = BeansClass.handleFxmlPages();
            handleFxmlPages.profileManager();
        }
        else{
            displayError("You are not allowed to access this functionality");
        }
    }


    @FXML
    private void openCustomers(ActionEvent event) {
        HandleFxmlPages handleFxmlPages = BeansClass.handleFxmlPages();
        handleFxmlPages.customerManager();
        hidePage();
    }

    @FXML
    private void openLogout(ActionEvent event) {
        //manual input of logout
        //aspected
            LogsLogic instance = BeansClass.logsLogic();
            instance.logout();

            HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
            handleMainLogic.callLogin();
            
//            hide page
            hidePage();
    }

    //call the page as a popup and let it decide where to go
    @FXML
    private void openAddSale(ActionEvent event) {
        HandleFxmlPages handleFxmlPages = BeansClass.handleFxmlPages();
        handleFxmlPages.addSalePopup(userLabel.getScene().getWindow());
        this.window = userLabel.getScene().getWindow();
    }

    @FXML
    private void openMediaInventory(ActionEvent event) {
        HandleProfileLogic handleProfileLogic = BeansClass.handleProfileLogic();
        boolean privilege = handleProfileLogic.checkUserPrivilegeAdminManager(User.loggedin);
        if(privilege){
           HandleMainLogic instance = BeansClass.handleMainLogic();
           instance.callEnterProduct(); 
           
           //hide page
           hidePage();
        }
        else{
            displayError("You are not allowed to access this functionality");
        }
    }

    @FXML
    private void openSoftwareInventory(ActionEvent event) {
        HandleProfileLogic handleProfileLogic = BeansClass.handleProfileLogic();
        boolean privilege = handleProfileLogic.checkUserPrivilegeAdminManager(User.loggedin);
        if(privilege){
           HandleFxmlPages instance = BeansClass.handleFxmlPages();
           instance.softwareInventory(); 
           
//            hide page
            hidePage();
        }
        else{
            displayError( "You are not allowed to access this functionality");
        }
    }

    @FXML
    private void openMediaPurchase(ActionEvent event) {
        HandleFxmlPages handleFxmlPages = BeansClass.handleFxmlPages();
        handleFxmlPages.mediaPurchase();
        hidePage();
    }

    @FXML
    private void openSoftwarePurchase(ActionEvent event) {
        HandleFxmlPages handleFxmlPages = BeansClass.handleFxmlPages();
        handleFxmlPages.softwarePurchase();
        hidePage();
    }

    @FXML
    private void openPreorders(ActionEvent event) {
        HandleFxmlPages handleFxmlPages = BeansClass.handleFxmlPages();
        handleFxmlPages.preorderManager();
        hidePage();
    }

    @FXML
    private void changePassword(ActionEvent event) {
        HandleFxmlPages handleFxmlPages = BeansClass.handleFxmlPages();
        handleFxmlPages.newPassword(userLabel.getScene().getWindow());
    }
    
    public void hidePage(){
        userLabel.getScene().getWindow().hide();
    }
    
    public void displayError(String error){
        DialogPane dialog = BeansClass.dialogPane();
        dialog.displayError(error);
    }
    
    public void displayError(Window window, String error){
        DialogPane dialog = BeansClass.dialogPane();
        dialog.displayError(window,error);
    }
    
}
