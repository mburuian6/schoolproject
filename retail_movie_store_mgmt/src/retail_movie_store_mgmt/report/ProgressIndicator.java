/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.report;

import BeansPackage.BeansClass;
import retail_movie_store_mgmt.HandleFxmlPages;
import retail_movie_store_mgmt.ProgressBarController;

/**
 *
 * @author Ian Mburu
 */
public class ProgressIndicator {
    
    ProgressBarController progressBarController = new ProgressBarController();
    
    public void beginGeneration(){
        HandleFxmlPages handleFxmlPages = BeansClass.handleFxmlPages();
        handleFxmlPages.progressBar();
    }
    
    public void finishGeneration(){
        progressBarController.setMyNum();
    }
    
    
}
