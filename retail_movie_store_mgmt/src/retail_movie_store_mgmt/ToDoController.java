/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt;

import BeansPackage.BeansClass;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import retail_movie_store_mgmt.Logic.HandleMainLogic;
import retail_movie_store_mgmt.Logic.HandleTodoLogic;
import retail_movie_store_mgmt.todo.Todo;

/**
 * FXML Controller class
 *
 * @author Ian Mburu
 */
public class ToDoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML AnchorPane anchorPane;
    @FXML ListView beforeListView;
    @FXML ListView afterListView;
    
    @FXML TextField titleField;
    @FXML DatePicker datePicker;
    @FXML TextArea descriptionArea;
    @FXML Button newValSubmitBtn;
    
    Todo todo;
    TimerTask timerTask;
    private ArrayList<Todo> allEntriesList;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //initial
        setUpListViewsInitial();
        
        //limit title textfield
        int maxText=100;
        titleField.textProperty().addListener(new ChangeListener<String>(){
            public void changed(final ObservableValue<? extends String> ov, final String oldVal,final String newVal){
                if(titleField.getText().length()>maxText){
                    String s = titleField.getText().substring(0,maxText);
                    titleField.setText(s);
                }
                newValSubmitBtn.setDisable(false);
            }
        });
        
        //listview click events
        MultipleSelectionModel<String> beforeListViewModel = beforeListView.getSelectionModel();// Get the list view selection model.
        beforeListViewModel.selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> changed,String oldVal, String newVal) {
                // Display the selection: newVal
                setItem(newVal);
            }
        });
        
    }    

    public ArrayList<Todo> getAllEntries(){
        HandleTodoLogic handleTodoLogic = BeansClass.handleTodoLogic();
        return handleTodoLogic.getAllEntries();
    }
    
    public void setUpListViewsInitial(){
        allEntriesList = getAllEntries();
        ArrayList<String> beforeList = new ArrayList();
        ArrayList<String> afterList = new ArrayList();
        
        HandleTodoLogic handleTodoLogic = BeansClass.handleTodoLogic();
        
        Iterator <Todo> allEntriesIterator = allEntriesList.iterator();
        while(allEntriesIterator.hasNext()){
                Todo todo = allEntriesIterator.next();
                boolean check = handleTodoLogic.checkIfTimeIsPassed(todo);
                if(check){
                    boolean add = afterList.add(todo.getTitle());
                    
                }
                else if(!check){
                    boolean add = beforeList.add(todo.getTitle());
                }
            
        }
                
        ObservableList beforeObsList = FXCollections.observableArrayList(beforeList);
        beforeListView.setItems(beforeObsList);
        ObservableList afterObsList= FXCollections.observableArrayList(afterList);
        afterListView.setItems(afterObsList );
        keepRefreshingLists();
    }
    
    public void setUpListViewsOnChange(){
        allEntriesList = getAllEntries();
        ArrayList<String> beforeList = new ArrayList();
        ArrayList<String> afterList = new ArrayList();
                
        HandleTodoLogic handleTodoLogic = BeansClass.handleTodoLogic();
        
        Iterator <Todo> allEntriesIterator = allEntriesList.iterator();
        while(allEntriesIterator.hasNext()){
            Todo todo = allEntriesIterator.next();
            boolean check = handleTodoLogic.checkIfTimeIsPassed(todo);
            if(check){
                boolean add = afterList.add(todo.getTitle());
            }
            else if(!check){
                boolean add = beforeList.add(todo.getTitle());
            }
        }
                
        ObservableList beforeObsList = FXCollections.observableArrayList(beforeList);
        beforeListView.setItems(beforeObsList);
        ObservableList afterObsList= FXCollections.observableArrayList(afterList);
        afterListView.setItems(afterObsList );
        
    }
    
    
    
    public void keepRefreshingLists(){
        long delay = 10*1000; //sec-> ms
        long periodThereafter=1*60*60*1000; //hour -> min -> sec -> ms
        long periodThereafterShorter = 10*1000;
        
        Timer timer = BeansClass.timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                String user = User.loggedin.getUsername();
                if(user != null){
                    try {
                            HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
                            boolean newDay = handleMainLogic.newDay();
                            if (newDay) {
                                setUpListViewsOnChange();
                            }                    
                    } catch (NoSuchElementException ex) {
                        //pass
                    }
                }
                else{
                   timerTask.cancel();
                   timer.cancel();
                }
            }
        };

        timer.schedule(timerTask, delay,periodThereafterShorter);
               
    }
    
    public TimerTask getListTimerTask(){
        return timerTask;
    }
    
    public Todo findOne(String title){
        for(Todo todo: allEntriesList){
            if(todo.getTitle().equals(title)){
                return todo;
            }
        }
        return null;
    }
    
    public void setItem(String title){
        HandleTodoLogic handleTodoLogic= BeansClass.handleTodoLogic();
//        Todo todo = handleTodoLogic.findOne(title);
        Todo todo = findOne(title);
        if(todo == null){
            //pass
        }
        else{
            this.todo = todo;
            titleField.setText(todo.getTitle());
            datePicker.setValue(todo.getDate());
            descriptionArea.setText(todo.getDescription());
            newValSubmitBtn.setDisable(true);
        }
    }
    
    public void emptyFields(){
        titleField.setText("");
        datePicker.setValue(LocalDate.now());
        descriptionArea.setText("");
    }
    
    public void submitNewItem(){
       String title = titleField.getText();
       LocalDate date=datePicker.getValue();
       String description = descriptionArea.getText();
       
        if(datePicker.getValue()==null ||
                titleField.getText()==null ||
                descriptionArea.getText()==null){
            displayError("Error! Please make sure no field is empty.");
        }
        else if(date.isBefore(LocalDate.now())){
            displayError("Error! Cannot record for past dates.");
        }
        else{
           HandleTodoLogic handleTodoLogic= BeansClass.handleTodoLogic();
           String id = handleTodoLogic.generateTodoId();

           Todo todo = BeansClass.todo();

           todo=handleTodoLogic.appendTodoDetails(todo, id, title, description, date, User.loggedin.getUsername());
           String insert = handleTodoLogic.insertTodo(todo);
            if (insert.contains("Successful")) {
                displayInfo("Successful Insertion!");
            }
            else if(insert.contains("Error!Task has already been inserted")){
                displayError("Error!Task has already been inserted");
            }
            else if(insert.contains("Error!Please contact support for help")){
                displayError("Error!Please contact support for help");
            }

           refresh();
        }
       
    }
    
    public void updateItem(){
       String title = titleField.getText();
       LocalDate date=datePicker.getValue();
       String description = descriptionArea.getText();
       try{
           if(title==null || date == null || description==null){
                displayError("Error! Please click on desired item to update in 'before' list then update details");
            }
           else if(date.isBefore(LocalDate.now())){
                displayError("Error! Cannot record for past dates.");
            }
            else{
               HandleTodoLogic handleTodoLogic= BeansClass.handleTodoLogic();
               
               todo=handleTodoLogic.appendTodoDetails(todo, todo.getId(), title, description, date, User.loggedin.getUsername());
               String update = handleTodoLogic.updateTodo(todo);
                if(update.contains("Successful")){
                    displayInfo("Successful Update!");
                }
                else if(update.contains("Error")){
                    displayError("Error!Please contact support for help");
                }
               refresh();
            }
       }
       catch(Exception e){
           displayError("Error! Please make sure input is correct.");
       }
    }
    
    public void deleteItem(){
       String title = titleField.getText();
       LocalDate date=datePicker.getValue();
       String description = descriptionArea.getText();
       try{
           if(title==null || date == null || description==null){
                displayError("Error! Please click on desired item to delete in 'before' list then delete");
            }
            else{
               HandleTodoLogic handleTodoLogic= BeansClass.handleTodoLogic();
               
               todo=handleTodoLogic.appendTodoDetails(todo, todo.getId(), title, description, date, User.loggedin.getUsername());
               String delete = handleTodoLogic.deleteTodo(todo);
               if(delete.contains("Successful")){
                    displayInfo("Successful Deletion!");
                }
                else if(delete.contains("Error")){
                    displayError("Error!Please contact support for help");
                }
               refresh();
            }
       }
       catch(Exception e){
           displayError("Error! Please make sure input is correct.");
       }
    }
    
    public void refresh(){
        setUpListViewsOnChange();
        emptyFields();
    }
    
    public void displayError(String error) {
        //Window owner = parentPane.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(error);
        //alert.initOwner(owner);
        alert.show();
    }

    public void displayInfo(String message) {
        //Window owner = parentPane.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        //alert.initOwner(owner);
        alert.show();
    }
    
    public void hidePage(){
        anchorPane.getScene().getWindow().hide();
    }
    
    public void back(){
        anchorPane.getScene().getWindow().hide();
        
        HandleFxmlPages handleFxmlPages = BeansClass.handleFxmlPages();
        handleFxmlPages.Home();        
    }
}
