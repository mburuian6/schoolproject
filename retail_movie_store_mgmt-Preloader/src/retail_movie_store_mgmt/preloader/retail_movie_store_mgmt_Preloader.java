/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.preloader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.STYLESHEET_CASPIAN;
import javafx.application.Preloader;
import javafx.application.Preloader.ProgressNotification;
import javafx.application.Preloader.StateChangeNotification;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Simple Starter_preloader Using the ProgressBar Control
 *
 * @author Ian Mburu
 */
public class retail_movie_store_mgmt_Preloader extends Preloader {
    
    ProgressBar bar;
    Label label_name,label_slogan;
    VBox labels;
    Stage stage;
    
    private Scene createPreloaderScene() {
        bar = new ProgressBar();
        BorderPane p = new BorderPane();
        
        //labels in vbox
        label_name = new Label("RMS");
        label_name.setFont(Font.font(STYLESHEET_CASPIAN, 50));
        label_name.setLayoutX(46);
        label_name.setLayoutY(122);
        label_name.setPrefSize(173,73);
        
        label_slogan = new Label("Your business partner");
        label_slogan.setFont(Font.font(STYLESHEET_CASPIAN, 12));
        label_slogan.setLayoutX(219);
        label_slogan.setLayoutY(159);
        label_slogan.setPrefSize(144,33);
        
        labels = new VBox();
        labels.getChildren().addAll(label_name,label_slogan);
        
        //in borderpane
        p.setCenter(labels);
        p.setBottom(bar);
        
        return new Scene(p, 300, 150);        
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setScene(createPreloaderScene());        
        stage.show();
        
        
    }
    
    @Override
    public void handleStateChangeNotification(StateChangeNotification scn) {
        if (scn.getType() == StateChangeNotification.Type.BEFORE_START) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(retail_movie_store_mgmt_Preloader.class.getName()).log(Level.SEVERE, null, ex);
            }
            stage.hide();
        }
    }
    
    @Override
    public void handleProgressNotification(ProgressNotification pn) {
        bar.setProgress(pn.getProgress());
    }    
    
}
