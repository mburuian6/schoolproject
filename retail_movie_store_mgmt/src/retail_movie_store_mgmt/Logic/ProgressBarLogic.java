/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.Logic;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author Ian Mburu
 */
public class ProgressBarLogic {
    private DoubleProperty number;
    
    public final double getNumber(){
        if(number != null) return number.get();
            
        return 0;
    }
    
    public final void setNumber(double number) {
        this.numberProperty().set(number);
    }
    
    public final DoubleProperty numberProperty(){
        if(number==null){
            number = new SimpleDoubleProperty(0);
        }
        return number;
    }
    
}
