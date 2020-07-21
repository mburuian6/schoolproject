/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.ui;

import java.util.Optional;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Window;

/**
 *
 * @author Ian Mburu
 */
public class DialogPane {
    
    public void displayError(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(error);
        alert.show();
    }

    public void displayInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.show();
    }
    
    public void displayError(Window owner, String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(error);
        alert.initOwner(owner);
        alert.show();
    }

    public void displayInfo(Window owner, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    
    public int getConfirmation(String message){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            return 0;
        }
        else{
            return 1;
        }
    }
    
    public String getText(String message){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Text Input Dialog");
        dialog.setContentText(message);
        Optional<String> result = dialog.showAndWait();
        if(result.isPresent()){
            try{
                return result.get();
            }
            catch(Exception ex){
                return null;
            }
        }
        return null;
    }
    
    public void hidePage(Node node){
        node.getScene().getWindow().hide();
    }
    
    
}
