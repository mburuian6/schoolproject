/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt;

import BeansPackage.BeansClass;
import animatefx.animation.BounceIn;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXToolbar;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import retail_movie_store_mgmt.Logic.HandleProfileLogic;
import retail_movie_store_mgmt.report.MediaSalesReport;
import retail_movie_store_mgmt.report.ProductReport;
import retail_movie_store_mgmt.report.SoftwareSalesReport;
import retail_movie_store_mgmt.ui.DialogPane;
import retail_movie_store_mgmt.ui.SalesBarChartBuilder;

/**
 * FXML Controller class
 *
 * @author Ian Mburu
 */
public class HomeController implements Initializable {
    
    @FXML AnchorPane parentPane;
    @FXML ListView todoListView;
    @FXML  StackPane mainStackPane;
    @FXML
    JFXToolbar toolbar;
    @FXML
    JFXHamburger hamburger;
    @FXML
    AnchorPane contentPane;
    @FXML
    JFXDrawer drawer;
    @FXML
    Label toDoHeaderLbl;
    @FXML
    Label userNumber;
    @FXML
    Label customerNumber;
    @FXML
    Label preorderNumber;
    @FXML
    PieChart pieChart;
    @FXML
    BarChart salesBarChart;
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        //set to do list
        setupTodoList();
        
        //drawer
        setupDrawer();
        
//        AnchorPane temporaryPane = contentPane;
        
        //usersPieChart
        setUpPieChart();
        
        //sales donut charts - replaced by sales barchart
//        setUpdonutCharts();
        
        //call attention to do section
        new BounceIn(toDoHeaderLbl).setDelay(Duration.seconds(1)).play();
        
        //get other stats: customer,preorder,users
        setOtherStats();
        
        //salesbarchart
        setSalesBarChart();
                
    }    
    
    public void setSalesBarChart(){
        SalesBarChartBuilder salesBarChartBuild = BeansClass.salesBarChartBuilder();
        salesBarChart.setTitle("Sales Bar Chart");
        
//        final CategoryAxis xAxis = new CategoryAxis();
//        final NumberAxis yAxis = new NumberAxis();
        
        salesBarChart.getXAxis().setLabel("Month");
        salesBarChart.getYAxis().setLabel("");
        try{
            ObservableList<XYChart.Series> getChart = salesBarChartBuild.createBarChart();
            salesBarChart.setData(getChart);
        }
        catch(Exception e){
            salesBarChart.setData(null);
        }
    }
    
    public void setupTodoList(){
        HomeLogic homeLogic = BeansClass.homeLogic();
        ArrayList<String> todayList = homeLogic.bringTodaysTodoList();
        if(todayList.isEmpty()){
            //pass
        }
        else{
            ObservableList todayObsList= FXCollections.observableArrayList(todayList);
            todoListView.setItems(todayObsList);
        }        
    }
    
    public void setupDrawer(){
        String menuPath = "SideMenu.fxml";
//        drawer.setMinWidth(150);
        try {
            VBox menu = FXMLLoader.load(getClass().getResource(menuPath));            
            drawer.setSidePane(menu);
            
            HamburgerBackArrowBasicTransition transition = 
                    new HamburgerBackArrowBasicTransition(hamburger);
            transition.setRate(-1);
            
            hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    (Event event)->{
                        transition.setRate(transition.getRate()*-1);
                        transition.play();
                        if(drawer.isClosed()){
                            drawer.open();
                        }
                        else{
                            drawer.close();
                        }
                    });
            
            
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    public void setUpdonutCharts(){
//        //media
//        HomeLogic homeLogic = BeansClass.homeLogic();
//        ObservableList<PieChart.Data> mediaSalesData = homeLogic.getMediaSalesData();
//        
//        DonutChartBuilder chart1 = BeansClass.donutChartBuilder(mediaSalesData);
//        chart1.setTitle("Media Sales");
//        chart1.setMaxSize(282, 244);
//        chart1.addInnerCircleIfNotPresent();
//        chart1.updateInnerCircleLayout();
//        
//        mediaPane.getChildren().add(chart1);
//        
//        ObservableList<PieChart.Data> softwareSalesData = homeLogic.getSoftwareSalesData();
//        DonutChartBuilder chart2 = BeansClass.donutChartBuilder(softwareSalesData);
//        chart2.setTitle("Software Sales");
//        chart2.setMaxSize(282, 244);
//        chart2.addInnerCircleIfNotPresent();
//        chart2.updateInnerCircleLayout();
//        
//        softwarePane.getChildren().add(chart2);
//    }
    
    public void setUpPieChart(){
        HomeLogic homeLogic= BeansClass.homeLogic();
        ObservableList<PieChart.Data> data = homeLogic.getUserRoleStats();
        System.out.println(data);        
        pieChart.setData(data);

//        RoleBarchartBuilder chart = BeansClass.roleBarChartBuilder();
//        barChart = chart.produceChart(); 
        
    }
    
    public void setOtherStats(){
        HomeLogic homeLogic = BeansClass.homeLogic();
        //users
        int users = homeLogic.getNumberOfUsers();
        userNumber.setText(String.valueOf(users));
        
        //customers
        int customers = homeLogic.getNumberOfCustomers();
        customerNumber.setText(String.valueOf(customers));
        
        //preorders
        int preorders = homeLogic.getNumberOfPendingPreorders();
        preorderNumber.setText(String.valueOf(preorders));
    }
    
    
    @FXML
    public void viewSoftwareSales(){
        HandleFxmlPages handleFxmlPages = BeansClass.handleFxmlPages();
        handleFxmlPages.viewAllSoftwareSales();
        hidePage();
    }
    
    @FXML
    public void viewMediaSales(){
        HandleFxmlPages handleFxmlPages = BeansClass.handleFxmlPages();
        handleFxmlPages.viewAllMediaSalesPage();
        hidePage();
    }
        
    @FXML
    public void viewPreorders(){
        HandleFxmlPages handleFxmlPages = BeansClass.handleFxmlPages();
        handleFxmlPages.viewPreorders();
        hidePage();
    }
    
    
    @FXML
    public void viewSoftwarePurchases(){
        HandleProfileLogic handleProfileLogic = BeansClass.handleProfileLogic();
        boolean privilege = handleProfileLogic.checkUserPrivilegeAdminManager(User.loggedin);
        if(privilege){
            hidePage();
            HandleFxmlPages handleFxmlPages = BeansClass.handleFxmlPages();
            handleFxmlPages.viewSoftwarePurchases();            
        }
        else{
            displayError("You are not allowed to access this functionality");
        }
    }
    
    @FXML
    public void viewMediaPurchases(){
        HandleProfileLogic handleProfileLogic = BeansClass.handleProfileLogic();
        boolean privilege = handleProfileLogic.checkUserPrivilegeAdminManager(User.loggedin);
        if(privilege){
            HandleFxmlPages handleFxmlPages = BeansClass.handleFxmlPages();
            handleFxmlPages.viewMediaPurchases();
            hidePage();
        }
        else{
            displayError("You are not allowed to access this functionality");
        }
    }
        
    public void displayError(String message){
        DialogPane dialog = BeansClass.dialogPane();
        dialog.displayError(message);
    }
    
    
    @FXML
    public void getProductReport(){
        HandleProfileLogic handleProfileLogic = BeansClass.handleProfileLogic();
        boolean privilege = handleProfileLogic.checkUserPrivilegeAdminManager(User.loggedin);
        if(privilege){
            Thread thread = new Thread(){
                public void run(){
                    ProductReport productReport = BeansClass.productReport();
                    productReport.produceReport();
                }
                
            };
            thread.start();
        }
        else{
            displayError("You are not allowed to access this functionality");
        }
    }
    
    @FXML
    public void getMediaSalesReport(){
        HandleProfileLogic handleProfileLogic = BeansClass.handleProfileLogic();
        boolean privilege = handleProfileLogic.checkUserPrivilegeAdminManager(User.loggedin);
        if(privilege){
            Thread thread = new Thread(){
                public void run(){
                    MediaSalesReport mediaSalesReport = BeansClass.mediaSalesReport();
                    mediaSalesReport.produceReport();                    
                }
                
            };
            thread.start();
        }
        else{
            displayError("You are not allowed to access this functionality");
        }
    }
    
    @FXML
    public void getSoftwareSalesReport(){
        HandleProfileLogic handleProfileLogic = BeansClass.handleProfileLogic();
        boolean privilege = handleProfileLogic.checkUserPrivilegeAdminManager(User.loggedin);
        if(privilege){
            Thread thread = new Thread(){
                public void run(){
                    SoftwareSalesReport softwareSalesReport = BeansClass.softwareSalesReport();
                    softwareSalesReport.produceReport();                    
                }                
            };
            thread.start();
        }
        else{
            displayError("You are not allowed to access this functionality");
        }
    }
    
    public void hidePage(){
        try{
            parentPane.getScene().getWindow().hide();
        }
        catch(Exception e){
            //pass
            System.out.println("Error: "+ e.getMessage());
        }        
    }
    
    
}
