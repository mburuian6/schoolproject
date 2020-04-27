/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.database.nosql;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ian Mburu
 */
public class Database_start_stopNoSql {
    public static boolean start_db(){
        try {
            //start mongo
            Process process = Runtime.getRuntime().exec("C:\\Program Files\\MongoDB\\Server\\4.2\\bin\\mongod.exe");
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Database_start_stopNoSql.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return false;
        }
    }
    
    public static boolean stop_db(){
        try {
            //stop mongo
            Process process = Runtime.getRuntime().exec("CTRL+C");
            //Runtime.getRuntime().exec("exit");
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Database_start_stopNoSql.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return false;
        }
    }
    
    public static void main(String[] args) {
        
    }
}
