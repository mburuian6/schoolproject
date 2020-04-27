/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt;

import BeansPackage.BeansClass;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import retail_movie_store_mgmt.Logic.HandleProfileLogic;

/**
 * FXML Controller class
 *
 * @author Ian Mburu
 */
public class ProfileManagerController implements Initializable {
        
    @FXML AnchorPane parentPane;
    
    @FXML TableView usersTable;
    @FXML TableColumn <User,String> usernameColumn;
    @FXML TableColumn <User,String> roleColumn;
    
    @FXML ComboBox deleteComboBox;
    @FXML ListView listView;
    
    @FXML TextField usernameField;
    @FXML TextField passwordField;
    @FXML ComboBox roleComboBox;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setAllUsersInComboBox();
        viewUsers();
        setAllRolesInComboBox();
    }    
    
    //view
    public ArrayList<User> getAllUsers(){
        HandleProfileLogic handleProfileLogic = BeansClass.handleProfileLogic();
        return handleProfileLogic.getAllUsers();
    }
    
    public void viewUsers(){
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        
        ArrayList<User> allUsersList = getAllUsers();
        if(allUsersList==null){
            //pass
        }
        if(allUsersList.isEmpty()){
            //pass
        }
        else{
            //get table data
            ObservableList<User> allUsersObsList = FXCollections.<User>observableArrayList(allUsersList);
        
            //set table data
            usersTable.getItems().setAll(allUsersList);
            
        }
    }
    
    //delete
    public void setAllUsersInComboBox(){
        ArrayList<User> allUsersList = getAllUsers();
        ArrayList<String> allUsernamesList = new ArrayList();
        
        Iterator <User>allUsersIterator = allUsersList.iterator();
        while(allUsersIterator.hasNext()){
            allUsernamesList.add(allUsersIterator.next().getUsername());
        }
        
        if((allUsernamesList != null) && (!allUsernamesList.isEmpty())){
            ObservableList<String> allUsersObsList = FXCollections.observableArrayList(allUsernamesList);
            deleteComboBox.setItems(allUsersObsList);
            listView.setItems(allUsersObsList);
        }
    }
    
    public void deleteUser(){
        String username = deleteComboBox.getValue().toString();
        
        HandleProfileLogic handleProfileLogic = BeansClass.handleProfileLogic();
        handleProfileLogic.deleteUser(username);
        refresh();
    }
    
    //add
    public void setAllRolesInComboBox(){
        HandleProfileLogic handleProfileLogic = BeansClass.handleProfileLogic();
        String []roles = HandleProfileLogic.getRoles();
        
        ObservableList<String> rolesObsList = FXCollections.observableArrayList(roles);
        roleComboBox.setItems(rolesObsList);
    }
    public void addUser(){
        if(usernameField.getText()==null ||
            passwordField.getText()==null||
            roleComboBox.getValue().toString() == null
            ){
            displayError("Please fill all the fields");
        }
        else{
            String username = usernameField.getText();
            String password = passwordField.getText();
            String role = roleComboBox.getValue().toString();

            User user = BeansClass.user();
            HandleProfileLogic handleProfileLogic = BeansClass.handleProfileLogic();
            handleProfileLogic.appendDetailsToUser(user, username, password, role);

            handleProfileLogic.insertUser(user);
            refresh();
        }
        
    }
    
    public void clearTextFields(){
        usernameField.setText("");
        passwordField.setText("");
    }
    //misc
    public void refresh(){
        setAllUsersInComboBox();
        viewUsers();
        clearTextFields();
    }
    
    public void displayInfo(String message){
        //Window owner = parentPane.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        //alert.initOwner(owner);
        alert.show();
    }
    
    public void displayError(String message){
        //Window owner = parentPane.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        //alert.initOwner(owner);
        alert.show();
    }
    
    public void hidePage(){
        parentPane.getScene().getWindow().hide();
    }
    
    public void back(){
        hidePage();
        
        HandleFxmlPages handleFxmlPages = BeansClass.handleFxmlPages();
        handleFxmlPages.Home();
        
    }
}
