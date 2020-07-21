/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.database;

import BeansPackage.BeansClass;
import java.io.IOException;
import org.springframework.stereotype.Component;
import retail_movie_store_mgmt.ui.DialogPane;

/**
 *
 * @author Ian Mburu
 */
@Component
public class Database_start_stop {
    
    public boolean start_db(){
        try {
            //start xampp
            Process process = Runtime.getRuntime().exec("C:\\xampp\\xampp_start.exe");
            return true;
        } catch (IOException ex) {
            DialogPane dialogPane = BeansClass.dialogPane();
            dialogPane.displayInfo("Connection error. Could not start the database");
            return false;
        }
    }
    
    public boolean stop_db(){
        try {
            //stop xampp
            Process process = Runtime.getRuntime().exec("C:\\xampp\\xampp_stop.exe");
            return true;
        } catch (IOException ex) {
            DialogPane dialogPane = BeansClass.dialogPane();
            dialogPane.displayInfo("Connection error. Could not stop the database");
            return false;
        }
    }
}
