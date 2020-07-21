package retail_movie_store_mgmt;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import BeansPackage.BeansClass;
import java.awt.Desktop;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import retail_movie_store_mgmt.Logic.HandleQuestionsLogic;



/**
 * FXML Controller class
 *
 * @author Ian Mburu
 */

public class EnterSecurityQuestionController implements Initializable {
    private int index;
    private String q,ans;
    @FXML private ComboBox showQuestions;
    @FXML private TextField answer;
    @FXML private Button cancelBtn;
    @FXML Hyperlink learnMoreLink;
    
    HandleQuestionsLogic questionListObj = BeansClass.handleQuestionsLogic();
    HashMap<Integer,String> hm = questionListObj.getHashMap();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //get questions and place them in combobox
        ObservableList<String> questions = FXCollections.observableArrayList();
        
        if(!hm.isEmpty()){
            Set<Map.Entry<Integer, String>> set = hm.entrySet();
            for(Map.Entry<Integer, String> me : set) {
                questions.add(me.getValue());
            }          
        }        
        
        showQuestions.setItems(questions);
        
        boolean inApp = HandleFxmlPages.inApp;
        if(inApp){
            cancelBtn.setDisable(true);
        }
    }    
    
    public void setupLink(){
        if(Desktop.isDesktopSupported()){
            try{
                Desktop.getDesktop().browse(new URI("https://mburuian6.github.io/"));
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public void getAnswer(){
        q = showQuestions.getValue().toString();        
        ans = answer.getText();
        
        String[]qans = {q,ans};//pack required variables: questions,ans
        
        HandleQuestionsLogic instance = BeansClass.handleQuestionsLogic();
        instance.getNewSecurityQuestion(qans,hm);//hand them over to be processed
        hidePage();
    }
    
    public String[] returnResponse(){
        String []arr = {q,ans};
        return arr;
    }
    
    public void hidePage(){
        showQuestions.getScene().getWindow().hide();
    }
    
    public void back(){
        showQuestions.getScene().getWindow().hide();
        
        HandleFxmlPages handleFxmlPages = BeansClass.handleFxmlPages();
        handleFxmlPages.Home();
    }
    
}
