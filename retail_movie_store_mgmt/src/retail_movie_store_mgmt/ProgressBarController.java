/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressIndicator;
import retail_movie_store_mgmt.Logic.ProgressBarLogic;


public class ProgressBarController implements Initializable {
    public static ProgressBarLogic myNum = new ProgressBarLogic();
    @FXML
    private ProgressIndicator pi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        myNum.numberProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
                
                pi.progressProperty().bind(myNum.numberProperty());
            }
        });
         
        myNum.setNumber(-1);
    }    

    public ProgressBarLogic getMyNum() {
        return myNum;
    }

    public void setMyNum() {
        myNum.setNumber(2);
    }
    
    
}
