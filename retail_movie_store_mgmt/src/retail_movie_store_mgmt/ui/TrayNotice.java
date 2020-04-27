/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.ui;

import BeansPackage.BeansClass;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author Ian Mburu
 */
public class TrayNotice {
    
    public void showSuccessNotification(String message,boolean checked){
        TimerTask timerTask = new TimerTask(){
            @Override
            public void run(){
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        TrayNotification tray = new TrayNotification();
                        tray.setNotificationType(NotificationType.SUCCESS);
                        tray.setRectangleFill(Color.web("Green"));
                        tray.setTitle("Success!");
                        tray.setMessage(message);
                        if(!tray.isTrayShowing() && !checked){
                            tray.showAndDismiss(Duration.seconds(10));
                        }
                    }
                });
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 300);
    }
    
    public void showInfoNotification(String message,boolean checked){
        TimerTask timerTask = new TimerTask(){
            @Override
            public void run(){
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        TrayNotification tray = new TrayNotification();
                        tray.setNotificationType(NotificationType.INFORMATION);
                        tray.setRectangleFill(Color.web("Blue"));
                        tray.setTitle("Info!");
                        tray.setMessage(message); 
                        if(!tray.isTrayShowing() && !checked){
                            tray.showAndDismiss(Duration.seconds(7));
                        }
                    }
                });
            }
        }; 
        Timer timer = BeansClass.timer();
        timer.schedule(timerTask, 300);
    }
    
}
