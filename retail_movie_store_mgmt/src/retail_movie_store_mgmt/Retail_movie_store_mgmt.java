/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt;

import BeansPackage.BeansClass;
import animatefx.animation.FadeIn;
import java.io.IOException;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import retail_movie_store_mgmt.database.Database_start_stop;
import retail_movie_store_mgmt.ui.DialogPane;
/**
 *
 * @author Ian Mburu
 */

public class Retail_movie_store_mgmt extends Application {
    Database_start_stop db = BeansClass.database_begin_end();
    
    @Override
    public void init(){
        //start_database();
    }
    
    @Override
    public void start(Stage stage) {
        
        ApplicationContext context = new AnnotationConfigApplicationContext(BeansClass.class);
//        Parent root = FXMLLoader.load(getClass().getResource("\\ui\\EnterSecurityQuestion.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("\\ui\\fxml\\LoginPage.fxml"));
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
            Scene scene = new Scene(root);
        
            stage.setScene(scene);
            stage.show();
            new FadeIn(root).play();
        } catch (IOException ie) {
            DialogPane displayError = BeansClass.dialogPane();
            displayError.displayError("Application Error. Please check your connection.");
        } 
//        catch(RuntimeException re){
//            DialogPane displayError = BeansClass.dialogPane();
//            displayError.displayError("Error. Please check your connection.");
//        } 
        catch(CannotGetJdbcConnectionException ce){
            DialogPane displayError = BeansClass.dialogPane();
            displayError.displayError("Error. Please check your connection.");
        }

        
    }
    
    @Override
    public void stop(){
        //stop_database();
        
        //clean up
        try{
            cleanUp();
        }
        catch(Exception ex){
            DialogPane displayError = BeansClass.dialogPane();
            displayError.displayError("Error. Please check your connection.");
        }
    }
    
    public void cleanUp(){
//        HomeController homeController = BeansClass.homeController();
//        homeController.logoutFromHome();
        //set log out time
        LogsLogic instance = BeansClass.logsLogic();
        instance.logout();
        
        //close all other threads
        ToDoController toDoController = BeansClass.toDoController();
        if(toDoController.getListTimerTask()!= null){
            TimerTask timerTask = toDoController.getListTimerTask();
            timerTask.cancel();
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //retail_movie_store_mgmt_Preloader one = new retail_movie_store_mgmt_Preloader();
        launch(args);
        
    }
    
    public boolean start_database(){
        boolean started_db = db.start_db();
        return started_db;
    }
    
    public boolean stop_database(){
        boolean stopped_db = db.stop_db();
        return stopped_db;
    }
}
