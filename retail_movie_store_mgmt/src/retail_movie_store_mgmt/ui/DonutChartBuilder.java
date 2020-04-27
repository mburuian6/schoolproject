/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.ui;

import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Ian Mburu
 */
public class DonutChartBuilder extends PieChart{
    private final Circle innerCircle;
    
    public DonutChartBuilder(ObservableList<PieChart.Data> pieData){
        super(pieData);
        
        innerCircle = new Circle();
        innerCircle.setFill(Color.WHITESMOKE);
        innerCircle.setStroke(Color.WHITE);
        innerCircle.setStrokeWidth(3);

        addInnerCircleIfNotPresent();
        updateInnerCircleLayout();
        
    }
    
    @Override
    protected void layoutChartChildren(double top,double left,
            double contentWidth, double contentHeight
    ){
        super.layoutChartChildren(top, left, contentWidth, contentHeight);
    }
    
    public void addInnerCircleIfNotPresent(){
        if(getData().size() > 0 ){
            Node pie = getData().get(0).getNode();
            
            if(pie.getParent() instanceof Pane){
                Pane parent = (Pane) pie.getParent();
                
                if(!parent.getChildren().contains(innerCircle)){
                    parent.getChildren().add(innerCircle);
                }
            }
        }
    }
    
    
    public void updateInnerCircleLayout(){
        double minX = Double.MAX_VALUE, minY=Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE, maxY=Double.MIN_VALUE;
        
        for(PieChart.Data data : getData()) {
            Node node = data.getNode();
            Bounds bounds = node.getBoundsInParent();
            
            if(bounds.getMinX() <minX) minX=bounds.getMinX();
            if(bounds.getMinY() <minY) minY=bounds.getMinY();
            if(bounds.getMaxX() >maxX) maxX=bounds.getMaxX();
            if(bounds.getMaxX() >maxY) maxY=bounds.getMaxY();
        }
        
        innerCircle.setCenterX(minX+ (maxX-minX)/2);
        innerCircle.setCenterY(minY+ (maxY-minY)/2);
        
        innerCircle.setRadius((maxX-minX) /4);
        
    }
}
