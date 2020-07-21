/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt;

import BeansPackage.BeansClass;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import retail_movie_store_mgmt.Customer.Customer;
import retail_movie_store_mgmt.Logic.HandleCustomerLogic;
import retail_movie_store_mgmt.report.CustomerReport;
import retail_movie_store_mgmt.ui.DialogPane;

/**
 * FXML Controller class
 *
 * @author Ian Mburu
 */
public class CustomerManagerController implements Initializable {

    @FXML
    ListView allCustomersListView;
    @FXML
    TextField usernameField;
    @FXML
    TextField nameField;
    @FXML
    RadioButton femaleRadioButton;
    @FXML
    RadioButton maleRadioButton;
    @FXML
    RadioButton customRadioButton;

    //movies
    @FXML
    CheckBox actionMovieBox;
    @FXML
    CheckBox adventureMovieBox;
    @FXML
    CheckBox animationMovieBox;
    @FXML
    CheckBox historyMovieBox;
    @FXML
    CheckBox comedyMovieBox;
    @FXML
    CheckBox crimeMovieBox;
    @FXML
    CheckBox documentaryMovieBox;
    @FXML
    CheckBox horrorMovieBox;
    @FXML
    CheckBox musicMovieBox;
    @FXML
    CheckBox scifiMovieBox;
    @FXML
    CheckBox thrillerMovieBox;
    @FXML
    CheckBox romanceMovieBox;
    @FXML
    CheckBox warMovieBox;

    //tv series
    @FXML
    CheckBox actionShowsBox;
    @FXML
    CheckBox adventureShowsBox;
    @FXML
    CheckBox animationShowsBox;
    @FXML
    CheckBox historyShowsBox;
    @FXML
    CheckBox comedyShowsBox;
    @FXML
    CheckBox crimeShowsBox;
    @FXML
    CheckBox documentaryShowsBox;
    @FXML
    CheckBox horrorShowsBox;
    @FXML
    CheckBox musicShowsBox;
    @FXML
    CheckBox scifiShowsBox;
    @FXML
    CheckBox thrillerShowsBox;
    @FXML
    CheckBox romanceShowsBox;
    @FXML
    CheckBox warShowsBox;

    @FXML
    Button newCustomerSubmitBtn;
    @FXML
    PieChart usersPieChart;
    @FXML
    PieChart moviePieChart;
    @FXML
    Label percentageLabel;
    @FXML
    Label numLabel;
    private Customer customer;
    private ArrayList<Customer> allCustomersList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setUpAllCustomer();

        //listview click events        
        allCustomersListView.setStyle("font-family: serif; font-weight: bold");
        MultipleSelectionModel<String> beforeListViewModel = allCustomersListView.getSelectionModel();// Get the list view selection model.
        beforeListViewModel.selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> changed, String oldVal, String newVal) {
                // Display the selection: newVal
                emptyAllFields();
                setCustomer(newVal);
            }
        });

        //limit username to 100 columns
        int maxText = 100;
        usernameField.textProperty().addListener(new ChangeListener<String>() {
            public void changed(final ObservableValue<? extends String> ov, final String oldVal, final String newVal) {
                if (usernameField.getText().length() > maxText) {
                    String s = usernameField.getText().substring(0, maxText);
                    usernameField.setText(s);
                }
            }
        });

        Tooltip usernameToolTip = new Tooltip("Once set, username cannot be changed");
        usernameField.setTooltip(usernameToolTip);

        //piechart
        setUsersPieChart();

        setupMovieStats();
    }

    public void setUsersPieChart() {
//        ArrayList<Customer> allCustomers = getAllCustomers();

        //setting the hashmap
        HashMap<String, Double> customerGenders = new HashMap(3);
        double males = 0;
        double females = 0;
        double customs = 0;

        for (Customer customer : allCustomersList) {
            if (customer.getGender().equals(maleRadioButton.getText())) {
                //add to males
                males = males + 1;
                if (customerGenders.containsKey("males")) {
                    customerGenders.remove("males");
                    customerGenders.put("males", males);
                } else {
                    customerGenders.put("males", males);
                }
            } else if (customer.getGender().equals(femaleRadioButton.getText())) {
                //add to females
                females = females + 1;
                if (customerGenders.containsKey("females")) {
                    customerGenders.remove("females");
                    customerGenders.put("females", females);
                } else {
                    customerGenders.put("females", females);
                }
            } else if (customer.getGender().equals(customRadioButton.getText())) {
                //add to custom genders
                customs = customs + 1;
                if (customerGenders.containsKey("custom gender")) {
                    customerGenders.remove("custom gender");
                    customerGenders.put("custom gender", customs);
                } else {
                    customerGenders.put("custom gender", customs);
                }
            }
        }

        //setting pie data
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        Set<Map.Entry<String, Double>> set = customerGenders.entrySet();
        for (Map.Entry<String, Double> me : set) {
            data.add(new PieChart.Data(me.getKey(), me.getValue()));
        }

        usersPieChart.setData(data);

        for (PieChart.Data dataObj : usersPieChart.getData()) {
            dataObj.getNode().addEventHandler(
                    MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    numLabel.setText(String.valueOf(dataObj.getPieValue()));
                    double percent = Math.abs((dataObj.getPieValue() / allCustomersList.size()) * 100);
                    percentageLabel.setText(String.valueOf(percent) + "%");
                }
            });
        }
    }

    public void setupMovieStats() {
        ArrayList<Customer> allCustomersList = getAllCustomers();
        HashMap<String, Double> movieGenres = new HashMap();
        double action = 0;
        double adventure = 0;
        double animation = 0;
        double history = 0;
        double comedy = 0;
        double crime = 0;
        double documentary = 0;
        double horror = 0;
        double music = 0;
        double scifi = 0;
        double thriller = 0;
        double romance = 0;
        double war = 0;

        for (Customer customer : allCustomersList) {
            if (customer.getMovie_likes().contains(actionMovieBox.getText()) || customer.getShow_likes().contains(actionShowsBox.getText())) {
                action = action + 1;
                if(movieGenres.containsKey("action")){
                    movieGenres.remove("action");                    
                    movieGenres.put("action", action);
                }
                else{
                    movieGenres.put("action", action);
                }
            }
            if (customer.getMovie_likes().contains(adventureMovieBox.getText()) || customer.getShow_likes().contains(adventureShowsBox.getText())) {
                adventure = adventure + 1;
                if(movieGenres.containsKey("adventure")){
                    movieGenres.remove("adventure");                
                    movieGenres.put("adventure", adventure);
                }
                else{
                    movieGenres.put("adventure", adventure);
                }
            }
            if (customer.getMovie_likes().contains(animationMovieBox.getText()) || customer.getShow_likes().contains(animationShowsBox.getText())) {
                animation = animation + 1;
                if(movieGenres.containsKey("animation")){
                    movieGenres.remove("animation");                
                    movieGenres.put("animation", animation);
                }
                else{
                    movieGenres.put("animation", animation);
                }                
            } 
            if (customer.getMovie_likes().contains(historyMovieBox.getText()) || customer.getShow_likes().contains(historyMovieBox.getText())) {
                history=history+1;
                if(movieGenres.containsKey("history")){
                    movieGenres.remove("history");
                    movieGenres.put("history", history);
                }
                else{                    
                    movieGenres.put("history", history);
                }                
            } 
            if (customer.getMovie_likes().contains(comedyMovieBox.getText()) || customer.getShow_likes().contains(comedyShowsBox.getText())) {
                comedy = comedy + 1;
                if(movieGenres.containsKey("comedy")){
                    movieGenres.remove("comedy");
                    movieGenres.put("comedy", comedy);
                }
                else{
                    movieGenres.put("comedy", comedy);
                }                
            } 
            if (customer.getMovie_likes().contains(crimeMovieBox.getText()) || customer.getShow_likes().contains(crimeShowsBox.getText())) {
                crime = crime + 1;
                if(movieGenres.containsKey("crime")){
                    movieGenres.remove("crime");
                    movieGenres.put("crime", crime);
                }
                else{
                    movieGenres.put("crime", crime);
                }                
            } 
            if (customer.getMovie_likes().contains(documentaryMovieBox.getText()) || customer.getShow_likes().contains(documentaryShowsBox.getText())) {
                documentary = documentary + 1;
                if(movieGenres.containsKey("documentary")){
                    movieGenres.remove("documentary");
                    movieGenres.put("documentary", documentary);
                }else{
                    movieGenres.put("documentary", documentary);
                }                
            } 
            if (customer.getMovie_likes().contains(horrorMovieBox.getText()) || customer.getShow_likes().contains(horrorShowsBox.getText())) {
                horror = horror + 1;
                if(movieGenres.containsKey("horror")){
                    movieGenres.remove("horror");            
                    movieGenres.put("horror", horror);
                }else{
                    movieGenres.put("horror", horror);
                }
            } 
            if (customer.getMovie_likes().contains(musicMovieBox.getText()) || customer.getShow_likes().contains(musicShowsBox.getText())) {
                music = music + 1;
                if(movieGenres.containsKey("music")){
                    movieGenres.remove("music");            
                    movieGenres.put("music", music);
                }else{
                    movieGenres.put("music", music);
                }
            } 
            if (customer.getMovie_likes().contains(scifiMovieBox.getText()) || customer.getShow_likes().contains(scifiShowsBox.getText())) {
                scifi = scifi + 1;
                if (movieGenres.containsKey("scifi")) {
                    movieGenres.remove("scifi");
                    movieGenres.put("scifi", scifi);
                } else {
                    movieGenres.put("scifi", scifi);
                }

            } 
            if (customer.getMovie_likes().contains(thrillerMovieBox.getText()) || customer.getShow_likes().contains(thrillerShowsBox.getText())) {
                thriller = thriller + 1;
                if (movieGenres.containsKey("thriller")) {
                    movieGenres.remove("thriller");
                    movieGenres.put("thriller", thriller);
                } else {
                    movieGenres.put("thriller", thriller);
                }              
            } 
            if (customer.getMovie_likes().contains(romanceMovieBox.getText()) || customer.getShow_likes().contains(romanceShowsBox.getText())) {
                romance = romance + 1;
                if (movieGenres.containsKey("romance")) {
                    movieGenres.remove("romance");                
                    movieGenres.put("romance", romance);
                } else {
                    movieGenres.put("romance", romance);
                }                  
            } 
            if (customer.getMovie_likes().contains(warMovieBox.getText()) || customer.getShow_likes().contains(warShowsBox.getText())) {
                war = war + 1;
                if (movieGenres.containsKey("war")) {
                    movieGenres.remove("war");
                    movieGenres.put("war", war);
                } else {
                    movieGenres.put("war", war);
                }
            }
        }
        
        System.out.println("movieGenres"+movieGenres);
        //setting pie data
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        Set<Map.Entry<String, Double>> set = movieGenres.entrySet();
        for (Map.Entry<String, Double> me : set) {
            data.add(new PieChart.Data(me.getKey(), me.getValue()));
        }
        
        moviePieChart.setData(data);
    }

    public void setListViewListener() {
        MultipleSelectionModel<String> beforeListViewModel = allCustomersListView.getSelectionModel();// Get the list view selection model.
        beforeListViewModel.selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> changed, String oldVal, String newVal) {
                // Display the selection: newVal
                setCustomer(newVal);
            }
        });
    }

    public Customer findOne(String username) {
        for (Customer customer : allCustomersList) {
            if (customer.getUsername().equalsIgnoreCase(username)) {
                return customer;
            }
        }
        return null;
    }

    public void setCustomer(String username) {
        HandleCustomerLogic handleCustomerLogic = BeansClass.handleCustomerLogic();
//        Customer customer = handleCustomerLogic.findOne(username);
        Customer customer = findOne(username);
        if (customer == null) {
            //pass
        } else {
            this.customer = customer;
            usernameField.setText(customer.getUsername());
            nameField.setText(customer.getCustomer_name());

            if (customer.getGender().equalsIgnoreCase(femaleRadioButton.getText())) {
                femaleRadioButton.setSelected(true);
                maleRadioButton.setSelected(false);
                customRadioButton.setSelected(false);
            } else if (customer.getGender().equalsIgnoreCase(maleRadioButton.getText())) {
                maleRadioButton.setSelected(true);
                femaleRadioButton.setSelected(false);
                maleRadioButton.setSelected(false);
            } else if (customer.getGender().equalsIgnoreCase(customRadioButton.getText())) {
                customRadioButton.setSelected(true);
                maleRadioButton.setSelected(false);
                femaleRadioButton.setSelected(false);
            }

            setMovies(handleCustomerLogic.getMovieLikes(customer));
            setShows(handleCustomerLogic.getShowsLikes(customer));
            //usernameField.setDisable(true);
        }
    }

    public void enableUsernameField() {
        usernameField.setDisable(false);
    }

    public void setMovies(String[] movies) {
        for (String movie : movies) {
            if (movie.equalsIgnoreCase(actionMovieBox.getText())) {
                actionMovieBox.setSelected(true);
            }
            if (movie.equalsIgnoreCase(adventureMovieBox.getText())) {
                adventureMovieBox.setSelected(true);
            }
            if (movie.equalsIgnoreCase(animationMovieBox.getText())) {
                animationMovieBox.setSelected(true);
            }
            if (movie.equalsIgnoreCase(historyMovieBox.getText())) {
                historyMovieBox.setSelected(true);
            }
            if (movie.equalsIgnoreCase(comedyMovieBox.getText())) {
                comedyMovieBox.setSelected(true);
            }
            if (movie.equalsIgnoreCase(crimeMovieBox.getText())) {
                crimeMovieBox.setSelected(true);
            }
            if (movie.equalsIgnoreCase(documentaryMovieBox.getText())) {
                documentaryMovieBox.setSelected(true);
            }
            if (movie.equalsIgnoreCase(horrorMovieBox.getText())) {
                horrorMovieBox.setSelected(true);
            }
            if (movie.equalsIgnoreCase(musicMovieBox.getText())) {
                musicMovieBox.setSelected(true);
            }
            if (movie.equalsIgnoreCase(scifiMovieBox.getText())) {
                scifiMovieBox.setSelected(true);
            }
            if (movie.equalsIgnoreCase(thrillerMovieBox.getText())) {
                thrillerMovieBox.setSelected(true);
            }
            if (movie.equalsIgnoreCase(romanceMovieBox.getText())) {
                romanceMovieBox.setSelected(true);
            }
            if (movie.equalsIgnoreCase(warMovieBox.getText())) {
                warMovieBox.setSelected(true);
            }
        }
    }

    public void setShows(String[] movies) {
        for (String movie : movies) {
            if (movie.equalsIgnoreCase(actionShowsBox.getText())) {
                actionShowsBox.setSelected(true);
            }
            if (movie.equalsIgnoreCase(adventureShowsBox.getText())) {
                adventureShowsBox.setSelected(true);
            }
            if (movie.equalsIgnoreCase(animationShowsBox.getText())) {
                animationShowsBox.setSelected(true);
            }
            if (movie.equalsIgnoreCase(historyShowsBox.getText())) {
                historyShowsBox.setSelected(true);
            }
            if (movie.equalsIgnoreCase(comedyShowsBox.getText())) {
                comedyShowsBox.setSelected(true);
            }
            if (movie.equalsIgnoreCase(crimeShowsBox.getText())) {
                crimeShowsBox.setSelected(true);
            }
            if (movie.equalsIgnoreCase(documentaryShowsBox.getText())) {
                documentaryShowsBox.setSelected(true);
            }
            if (movie.equalsIgnoreCase(horrorShowsBox.getText())) {
                horrorShowsBox.setSelected(true);
            }
            if (movie.equalsIgnoreCase(musicShowsBox.getText())) {
                musicShowsBox.setSelected(true);
            }
            if (movie.equalsIgnoreCase(scifiShowsBox.getText())) {
                scifiShowsBox.setSelected(true);
            }
            if (movie.equalsIgnoreCase(thrillerShowsBox.getText())) {
                thrillerShowsBox.setSelected(true);
            }
            if (movie.equalsIgnoreCase(romanceShowsBox.getText())) {
                romanceShowsBox.setSelected(true);
            }
            if (movie.equalsIgnoreCase(warShowsBox.getText())) {
                warShowsBox.setSelected(true);
            }
        }
    }

    public ArrayList<Customer> getAllCustomers() {
        HandleCustomerLogic handleCustomerLogic = BeansClass.handleCustomerLogic();
        return handleCustomerLogic.getAllCustomers();
    }

    public void setUpAllCustomer() {
        ArrayList<String> allCustomersUsernames = new ArrayList();
        ArrayList<Customer> allCustomersList = getAllCustomers();
        this.allCustomersList = allCustomersList;
        Iterator<Customer> allCustomersIterator = allCustomersList.iterator();

        while (allCustomersIterator.hasNext()) {
            allCustomersUsernames.add(allCustomersIterator.next().getUsername());
        }

        ObservableList allCustomersUsernamesObsList = FXCollections.observableArrayList(allCustomersUsernames);
        allCustomersListView.setPlaceholder(new Label("No customers"));
        allCustomersListView.setItems(allCustomersUsernamesObsList);

        //newCustomerSubmitBtn.setDisable(true);
    }

    public String getGender() {
        if (femaleRadioButton.isSelected()) {
            return femaleRadioButton.getText();
        } else if (maleRadioButton.isSelected()) {
            return maleRadioButton.getText();
        } else {
            return customRadioButton.getText();
        }
    }

    public String getMovies() {
        String movie_likes = "";
        if (actionMovieBox.isSelected()) {
            movie_likes = movie_likes + "," + actionMovieBox.getText();
        }
        if (adventureMovieBox.isSelected()) {
            movie_likes = movie_likes + "," + adventureMovieBox.getText();
        }
        if (animationMovieBox.isSelected()) {
            movie_likes = movie_likes + "," + animationMovieBox.getText();
        }
        if (historyMovieBox.isSelected()) {
            movie_likes = movie_likes + "," + historyMovieBox.getText();
        }
        if (comedyMovieBox.isSelected()) {
            movie_likes = movie_likes + "," + comedyMovieBox.getText();
        }
        if (crimeMovieBox.isSelected()) {
            movie_likes = movie_likes + "," + crimeMovieBox.getText();
        }
        if (documentaryMovieBox.isSelected()) {
            movie_likes = movie_likes + "," + documentaryMovieBox.getText();
        }
        if (horrorMovieBox.isSelected()) {
            movie_likes = movie_likes + "," + horrorMovieBox.getText();
        }
        if (musicMovieBox.isSelected()) {
            movie_likes = movie_likes + "," + musicMovieBox.getText();
        }
        if (scifiMovieBox.isSelected()) {
            movie_likes = movie_likes + "," + scifiMovieBox.getText();
        }
        if (thrillerMovieBox.isSelected()) {
            movie_likes = movie_likes + "," + thrillerMovieBox.getText();
        }
        if (romanceMovieBox.isSelected()) {
            movie_likes = movie_likes + "," + romanceMovieBox.getText();
        }
        if (warMovieBox.isSelected()) {
            movie_likes = movie_likes + "," + warMovieBox.getText();
        }
        return movie_likes;
    }

    public String getShows() {
        String show_likes = "";
        if (actionShowsBox.isSelected()) {
            show_likes = show_likes + "," + actionShowsBox.getText();
        }
        if (adventureShowsBox.isSelected()) {
            show_likes = show_likes + "," + adventureShowsBox.getText();
        }
        if (animationShowsBox.isSelected()) {
            show_likes = show_likes + "," + animationShowsBox.getText();
        }
        if (historyShowsBox.isSelected()) {
            show_likes = show_likes + "," + historyShowsBox.getText();
        }
        if (comedyShowsBox.isSelected()) {
            show_likes = show_likes + "," + comedyShowsBox.getText();
        }
        if (crimeShowsBox.isSelected()) {
            show_likes = show_likes + "," + crimeShowsBox.getText();
        }
        if (documentaryShowsBox.isSelected()) {
            show_likes = show_likes + "," + documentaryShowsBox.getText();
        }
        if (horrorShowsBox.isSelected()) {
            show_likes = show_likes + "," + horrorShowsBox.getText();
        }
        if (musicShowsBox.isSelected()) {
            show_likes = show_likes + "," + musicShowsBox.getText();
        }
        if (scifiShowsBox.isSelected()) {
            show_likes = show_likes + "," + scifiShowsBox.getText();
        }
        if (thrillerShowsBox.isSelected()) {
            show_likes = show_likes + "," + thrillerShowsBox.getText();
        }
        if (romanceShowsBox.isSelected()) {
            show_likes = show_likes + "," + romanceShowsBox.getText();
        }
        if (warShowsBox.isSelected()) {
            show_likes = show_likes + "," + warShowsBox.getText();
        }
        return show_likes;
    }

    public void enterCustomer() {
        Customer customer = BeansClass.customer();
        //enableUsernameField();
        //check for null
        String username = usernameField.getText().trim();
        if (username == null || username.equals("")) {
            displayError("To enter a new customer entry, please make sure the username field is filled.");
        } else {
            username = usernameField.getText();
            String customer_name = nameField.getText();

            String gender = getGender();
            String movieLikes = getMovies();
            String showLikes = getShows();

            HandleCustomerLogic handleCustomerLogic = BeansClass.handleCustomerLogic();
            customer = handleCustomerLogic.appendCustomerDetails(customer, handleCustomerLogic.generateCustomerId(username),
                     username, customer_name, gender, movieLikes, showLikes);
            String feedback = handleCustomerLogic.insertCustomer(customer);
            if(feedback.contains("error")){
                displayError(feedback);
            }
            else{
                displayInfo(feedback);
            }
            
            refresh();
        }
    }

    public void updateCustomer() {
        String username = usernameField.getText().trim();
        if (username == null || username.equals("")) {
            displayError("To update please click on the desired customer username in the left panel list");
        } else {

            String customer_name = customer.getCustomer_name();

            String gender = getGender();
            String movieLikes = getMovies();
            String showLikes = getShows();
            HandleCustomerLogic handleCustomerLogic = BeansClass.handleCustomerLogic();

            customer = handleCustomerLogic.appendCustomerDetails(customer, customer.getId(),
                     customer.getUsername(), customer_name, gender, movieLikes, showLikes);

            String updatedFeedback = handleCustomerLogic.updateCustomer(customer);
            if(updatedFeedback.contains("Error")){
                displayError(updatedFeedback);
            }
            else{
                displayInfo(updatedFeedback);
            }
            refresh();
        }
    }

    public void deleteCustomer() {
        String username = usernameField.getText().trim();
        if (username == null || username.equals("")) {
            displayError("To delete please click on the desired customer username in the left panel list");
        } else {
            HandleCustomerLogic handleCustomerLogic = BeansClass.handleCustomerLogic();
            String deleted = handleCustomerLogic.deleteCustomer(customer);
            if(deleted.contains("Error")){
                displayError(deleted);
            }
            else displayInfo(deleted);
            
            refresh();
        }
    }

    public void refresh() {
        setUpAllCustomer();
        emptyAllFields();
        setUsersPieChart();
        setupMovieStats();
    }

    public void emptyAllFields() {
        usernameField.setText("");
        nameField.setText("");
        femaleRadioButton.setSelected(false);
        maleRadioButton.setSelected(false);
        customRadioButton.setSelected(false);

        //movies
        actionMovieBox.setSelected(false);
        adventureMovieBox.setSelected(false);
        animationMovieBox.setSelected(false);
        historyMovieBox.setSelected(false);
        comedyMovieBox.setSelected(false);
        crimeMovieBox.setSelected(false);
        documentaryMovieBox.setSelected(false);
        horrorMovieBox.setSelected(false);
        musicMovieBox.setSelected(false);
        scifiMovieBox.setSelected(false);
        thrillerMovieBox.setSelected(false);
        romanceMovieBox.setSelected(false);
        warMovieBox.setSelected(false);

        //tv series
        actionShowsBox.setSelected(false);
        adventureShowsBox.setSelected(false);
        animationShowsBox.setSelected(false);
        historyShowsBox.setSelected(false);
        comedyShowsBox.setSelected(false);
        crimeShowsBox.setSelected(false);
        documentaryShowsBox.setSelected(false);
        horrorShowsBox.setSelected(false);
        musicShowsBox.setSelected(false);
        scifiShowsBox.setSelected(false);
        thrillerShowsBox.setSelected(false);
        romanceShowsBox.setSelected(false);
        warShowsBox.setSelected(false);

        newCustomerSubmitBtn.setDisable(false);
    }

    public void displayError(String error) {
        DialogPane dialog = BeansClass.dialogPane();
        dialog.displayError(error);
    }

    public void displayInfo(String message) {
        DialogPane dialog = BeansClass.dialogPane();
        dialog.displayInfo(message);
    }

    public void hidePage() {
        allCustomersListView.getScene().getWindow().hide();
    }

    public void generateReport() {
        CustomerReport customerReport = BeansClass.customerReport();
        customerReport.mainMethod();
    }

    public void back() {
        HandleFxmlPages handleFxmlPages = BeansClass.handleFxmlPages();
        handleFxmlPages.Home();
        hidePage();
    }
}
