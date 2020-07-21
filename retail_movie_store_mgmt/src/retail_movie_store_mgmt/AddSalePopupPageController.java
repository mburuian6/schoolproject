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
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import retail_movie_store_mgmt.ui.DialogPane;

/**
 * FXML Controller class
 *
 * @author Ian Mburu
 */
public class AddSalePopupPageController implements Initializable {
    @FXML AnchorPane parentPane;
    @FXML CheckBox mediaAndCustomsBox;
    @FXML CheckBox softwareBox;
    
    private Stage stage;
    static int[] pageCount = new int[1];
            
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public Stage getStage(){
        Stage stage = (Stage)mediaAndCustomsBox.getScene().getWindow();
        return stage;
    }
    
    public int[] getSalePagesNumber(){
        return pageCount;
    }
    
    public void setSalePagesNumber(int[]salesPagesCount){
        pageCount = salesPagesCount;
    }
    public String generateNewMediaSaleId(){
        AddMediaSaleLogic addMediaSaleLogic = BeansClass.addMediaSaleLogic();
        return addMediaSaleLogic.generateSaleId();
    }
    
    public String generateNewSoftwareSaleId(){
        AddSoftwareSaleLogic addSoftwareSaleLogic = BeansClass.addSoftwareSaleLogic();
        return addSoftwareSaleLogic.generateSaleId();
    }
    
    public void submit(ActionEvent event){
        
        if(mediaAndCustomsBox.isSelected()){
            if(mediaAndCustomsBox.isSelected() && softwareBox.isSelected()){
                pageCount[0] = 2;
                String newSaleId = generateNewSoftwareSaleId();
            }
            else{
                pageCount[0] = 1;
            }
            
            //generate their saleid
            String newSaleId = generateNewMediaSaleId();
            
            HandleFxmlPages handleFxmlPages = BeansClass.handleFxmlPages();
            handleFxmlPages.addMediaItemsSale(SideMenuController.window);
            
            hideAndOwner(event);
            //owner.hidePage();
        }
        else if(softwareBox.isSelected()){
            //call the software sales page
            HandleFxmlPages handleFxmlPages = BeansClass.handleFxmlPages();
            handleFxmlPages.addSoftwareSale(SideMenuController.window);
            
            //generate their saleid
            String newSaleId = generateNewSoftwareSaleId();
            
            hideAndOwner(event);
//            owner.hidePage();            
        }
        else if(!mediaAndCustomsBox.isSelected() || !softwareBox.isSelected()){
            displayError("Empty selection. Please choose one item to sell");
        }
        
    }
    
    public void cancel(){
//        HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
//        handleMainLogic.callHome();
        
        hidePage();
    }
    
    public void displayError(String error){
        DialogPane dialog = BeansClass.dialogPane();
        Window window = parentPane.getScene().getWindow();
        dialog.displayError(window, error);
    }
    
    public void hideAndOwner(ActionEvent event){
        mediaAndCustomsBox.getScene().getWindow().hide();        
//        ((Node)event.getSource()).getScene().getWindow().hide();
        
    }
    
    public void hidePage(){        
       parentPane.getScene().getWindow().hide();
    }
}
