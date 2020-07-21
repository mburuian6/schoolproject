/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt;

import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
/**
 *
 * @author Ian Mburu
 */
public class HandleFxmlPages {
    
    static boolean inApp= false;
    //make sure args are not null
    public boolean ensureNotEmpty(ArrayList<String>args){
        for(String text: args){
            if(text!=null){
                //pass
            }
            else{
                return false;
            }
        }
        return true;
    }
    
    public boolean ensureNotEmpty(String args){
        if(args==null){
            return false;
        }
        return true;
    }
    
    public void Home(){
        try{            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent root = (Parent)fxmlLoader.load();
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }   
       
    
    public void setNewPassword(){
        try{            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewPassword.fxml"));
            Parent root = (Parent)fxmlLoader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void setNewQuestion(Window window){
        try{ 
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EnterSecurityQuestion.fxml"));
            Parent root = (Parent)fxmlLoader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initOwner(window);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void setNewQuestion(){
        try{ 
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EnterSecurityQuestion.fxml"));
            Parent root = (Parent)fxmlLoader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void answerSecurityQuestion(){
        
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ForgotPassword.fxml"));
                Parent root = (Parent)fxmlLoader.load();

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.show();
            }
            catch(Exception e){
                e.printStackTrace();
            }
    }
    
    public void login(){
        try{            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
            Parent root = (Parent)fxmlLoader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void enterProduct(){
        try{ 
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EnterProduct.fxml"));
            Parent root = (Parent)fxmlLoader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void softwareInventory(){
        try{ 
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SoftwareInventory.fxml"));
            Parent root = (Parent)fxmlLoader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void addSalePopup(Window window){
        try{ 
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddSalePopupPage.fxml"));
            Parent root = (Parent)fxmlLoader.load();

            Stage stage = new Stage();
            stage.centerOnScreen();            
            stage.setScene(new Scene(root));
            
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(window);
            stage.setResizable(false);
            stage.showAndWait();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void addMediaItemsSale(){
        try{ 
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddMediaItemsSale.fxml"));
            Parent root = (Parent)fxmlLoader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void addMediaItemsSale(Window window){
        try{ 
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddMediaItemsSale.fxml"));
            Parent root = (Parent)fxmlLoader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            
            stage.initOwner(window);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void addMediaItemsSaleForm(Window window){
        try{ 
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddMediaSaleForm.fxml"));
            Parent root = (Parent)fxmlLoader.load();

            Stage stage = new Stage();            
            stage.setScene(new Scene(root));
            stage.initOwner(window);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void addMediaItemsSaleForm(){
        try{ 
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddMediaSaleForm.fxml"));
            Parent root = (Parent)fxmlLoader.load();

            Stage stage = new Stage();            
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void deleteMediaSaleForm(Window window){
        try{ 
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteMediaSaleForm.fxml"));
            Parent root = (Parent)fxmlLoader.load();

            Stage stage = new Stage();            
            stage.setScene(new Scene(root));
            stage.initOwner(window);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void deleteMediaSaleForm(){
        try{ 
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteMediaSaleForm.fxml"));
            Parent root = (Parent)fxmlLoader.load();

            Stage stage = new Stage();            
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void addSoftwareSale(){
        try{ 
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddSoftwareSale.fxml"));
            Parent root = (Parent)fxmlLoader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void addSoftwareSale(Window window){
        try{ 
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddSoftwareSale.fxml"));
            Parent root = (Parent)fxmlLoader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initOwner(window);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void addSoftwareSaleForm(Window window){
        try{ 
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddSoftwareSaleForm.fxml"));
            Parent root = (Parent)fxmlLoader.load();

            Stage stage = new Stage();            
            stage.setScene(new Scene(root));
            stage.initOwner(window);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void addSoftwareSaleForm(){
        try{ 
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddSoftwareSaleForm.fxml"));
            Parent root = (Parent)fxmlLoader.load();

            Stage stage = new Stage();            
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void deleteSoftwareSaleForm(Window window){
        try{ 
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteSoftwareSaleForm.fxml"));
            Parent root = (Parent)fxmlLoader.load();

            Stage stage = new Stage();            
            stage.setScene(new Scene(root));
            stage.initOwner(window);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void deleteSoftwareSaleForm(){
        try{ 
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteSoftwareSaleForm.fxml"));
            Parent root = (Parent)fxmlLoader.load();

            Stage stage = new Stage();            
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void viewAllMediaSalesPage(){
        try{ 
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ViewAllMediaSalesPage.fxml"));
            Parent root = (Parent)fxmlLoader.load();

            Stage stage = new Stage();            
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void viewAllSoftwareSales(){
        try{ 
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ViewAllSoftwareSales.fxml"));
            Parent root = (Parent)fxmlLoader.load();

            Stage stage = new Stage();            
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void profileManager(){
        try{ 
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProfileManager.fxml"));
            Parent root = (Parent)fxmlLoader.load();

            Stage stage = new Stage();            
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void toDo(){
        try{ 
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ToDo.fxml"));
            Parent root = (Parent)fxmlLoader.load();

            Stage stage = new Stage();            
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void newPassword(){
        try{ 
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewPassword.fxml"));
            Parent root = (Parent)fxmlLoader.load();

            Stage stage = new Stage();            
            stage.setScene(new Scene(root));
            stage.showAndWait();            
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void newPassword(Window window) {
        try {
            inApp =true;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewPassword.fxml"));
            Parent root = (Parent) fxmlLoader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.initOwner(window);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void customerManager(){
        try{ 
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CustomerManager.fxml"));
            Parent root = (Parent)fxmlLoader.load();

            Stage stage = new Stage();            
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void preorderManager(){
        try{ 
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PreorderManager.fxml"));
            Parent root = (Parent)fxmlLoader.load();

            Stage stage = new Stage();            
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void viewPreorders(){
        try{ 
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ViewPreorders.fxml"));
            Parent root = (Parent)fxmlLoader.load();

            Stage stage = new Stage();            
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void mediaPurchase(){
        try{ 
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MediaPurchase.fxml"));
            Parent root = (Parent)fxmlLoader.load();

            Stage stage = new Stage();            
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void softwarePurchase(){
        try{ 
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SoftwarePurchase.fxml"));
            Parent root = (Parent)fxmlLoader.load();

            Stage stage = new Stage();            
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void viewMediaPurchases(){
        try{ 
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ViewMediaPurchases.fxml"));
            Parent root = (Parent)fxmlLoader.load();

            Stage stage = new Stage();            
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void viewSoftwarePurchases(){
        try{ 
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ViewSoftwarePurchases.fxml"));
            Parent root = (Parent)fxmlLoader.load();

            Stage stage = new Stage();            
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void progressBar(){
        try{ 
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProgressBar.fxml"));
            Parent root = (Parent)fxmlLoader.load();

            Stage stage = new Stage();            
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
