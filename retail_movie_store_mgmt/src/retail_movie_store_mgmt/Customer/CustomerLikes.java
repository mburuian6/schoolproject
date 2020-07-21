/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.Customer;

import BeansPackage.BeansClass;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import retail_movie_store_mgmt.database.CustomerHandle;

/**
 *
 * @author Ian Mburu
 */
public class CustomerLikes {
    static String[]moviesArr ;
    static String[]showsArr;
    
    private String key = "5e8c1aa1ce0736223da5f7eb6681d9f3";
    String urlTrending= "https://api.themoviedb.org/3/trending/all/day?api_key="+key;
    String urlAllGenres = "https://api.themoviedb.org/3/genre/movie/list?api_key="+key;
    String urlByGenre = "https://api.themoviedb.org/3/genre/movie/list?api_key="+key+"&language=en-US";
    
    int trendingRequired = 2;
    int offset=0;
    public void extractLikes(String username){
        CustomerHandle customerHandle =BeansClass.customerHandle();
        List<Customer> customerList = customerHandle.findCustomer(username);
        Customer customer = customerList.get(0);
        
        String movies = customer.getMovie_likes();
        if(movies!=null){
            moviesArr= movies.split(",");
            moviesArr[0] = moviesArr[1];
        }
        
        String shows = customer.getShow_likes();
        if(shows!=null){
            showsArr = shows.split(",");
        }
        
    }
    
    public String[] randomizeMovieSuggestionIdentity(){
        //movie
        Random random = new Random();
        int randomNum = random.nextInt(moviesArr.length);
        int randomNum2 = random.nextInt(moviesArr.length);
        String[]movieGenres = {moviesArr[randomNum],moviesArr[randomNum2]};
        return movieGenres;
    }
    
    public String[] randomizeShowsSuggestionIdentity(){
        //movie
        Random random = new Random();
        int randomNum = random.nextInt(showsArr.length);
        int randomNum2 = random.nextInt(showsArr.length);
        String[]showGenres = {showsArr[randomNum],showsArr[randomNum2]};
        return showGenres;
    }
    
    public static Map<String,Object> jsonToMap(String str){
        Map<String,Object> map = new Gson().fromJson(
                str, new TypeToken<HashMap<String,Object>>(){}
                .getType()
                );
        return map;
    }
    
    
    public void main(String username) {
        CustomerLikes c = new CustomerLikes();
        c.extractLikes(username);
        
        //get trending movies
        ArrayList<String>  trendingMovies = getTrendingMovies(trendingRequired);
        for(String name:trendingMovies){
            System.out.println("Movie:"+ name);
        }
        //get other movies
        
        
        //get shows
        
    }
    
    public static void main(String[] args) {
        String username="ian m";
        CustomerLikes c = new CustomerLikes();
        c.main(username);
    }
    
    public Map<String, Object> connection(String urlString){
        StringBuilder result = new StringBuilder();
        try{
            URL url = new URL(urlString);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");

            try (BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())))) {
                String output;
                while ((output = br.readLine()) != null) {
                    result.append(output);
                }
            }

            Map<String,Object> respMap = jsonToMap(result.toString());
            return respMap;
        }
        catch(MalformedURLException me){
            me.printStackTrace();
            return null;
        }
        catch(IOException ie){
            ie.printStackTrace();
            return null;
        }
    }
    
    //get trending movies
    public ArrayList<String> getTrendingMovies(int required){
        Map<String, Object> trendingMovies = connection(urlTrending);
        Set<String> keys= trendingMovies.keySet();
        Object[] toArray = keys.toArray();
        ArrayList<String> movieNames=new ArrayList();
        
        for(int i=0;i<required;i++){
            int index = getRandomNumber(toArray.length);
            String name = toArray[index].toString();
            Map<String,Object> trendingMovie1 = jsonToMap(trendingMovies.get(name).toString());
            movieNames.add(trendingMovie1.get("nameKey").toString());
        }
        
        return movieNames;
    }
    
//    public ArrayList<Integer> getGenreIds(){
//        Map<String, Object> moviesGenres = connection(urlAllGenres);
//        Map<String, Object> genreMap = connection(urlAllGenres);
//        String[] randomGenres = randomizeMovieSuggestionIdentity();
//        ArrayList<Integer> ids= new ArrayList();
//        
//        
//        
//    }
    
     public ArrayList<String> getSuggestedMovies(int required){
        Map<String, Object> trendingMovies = connection(urlTrending);
        Set<String> keys= trendingMovies.keySet();
        Object[] toArray = keys.toArray();
        ArrayList<String> movieNames=new ArrayList();
        
        for(int i=0;i<required;i++){
            int index = getRandomNumber(toArray.length);
            String name = toArray[index].toString();
            Map<String,Object> trendingMovie1 = jsonToMap(trendingMovies.get(name).toString());
            movieNames.add(trendingMovie1.get("nameKey").toString());
        }
        
        return movieNames;
    }
    
    
    
    public int getRandomNumber(int bound){
        Random random = new Random();
        return random.nextInt(bound);
    }
    
    public void getSuggestions(){
        
    }
    
}
