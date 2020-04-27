/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.database.nosql;

import BeansPackage.BeansClass;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import org.bson.Document;
import retail_movie_store_mgmt.Sales.MediaAndCustomSaleEntry;

/**
 *
 * @author Ian Mburu
 */
public class MediaSalesHandle{
    //handle ops
    String collectionName = "mediaandcustomssale";
    String databaseName = "retail_movie_mgmt";
    
    public void authenticate (){
        //create credentials
        String userName = "root";
        String database="example";
        String password = "";
        MongoCredential credential = MongoCredential.createCredential(userName, database, password.toCharArray());
    }
    
    public MongoClient getClient(){
        //create client
        MongoClient mongo = new MongoClient("localhost",27017);
        return mongo;
    }
    
    public MongoDatabase getDatabase(){
        //accessing the db
        MongoClient mongo = getClient();
        MongoDatabase mydatabase = mongo.getDatabase(databaseName);
        return mydatabase;
    }
    
    public MongoCollection <Document> getCollection(){
        //retrieving a collection
        MongoDatabase mydatabase =  getDatabase();
        MongoCollection <Document> mycollection = mydatabase.getCollection(collectionName);
        return mycollection;
    }
    
    public void createCollection(MongoDatabase mydatabase){
        //creating a collection
        String collection = "newCollection";
        mydatabase.createCollection(collection);
    }
    
    public boolean insertToDb(MediaAndCustomSaleEntry mediaAndCustomSaleEntry){
        authenticate();
        try{
            MongoCollection <Document> collection = getCollection();

            Document doc = new Document("title",mediaAndCustomSaleEntry.getTitle())
                    .append("sale_id", mediaAndCustomSaleEntry.getSaleid())
                    .append("price", mediaAndCustomSaleEntry.getPrice())
                    .append("quantity", mediaAndCustomSaleEntry.getQuantity())
                    .append("subDiscount", mediaAndCustomSaleEntry.getSub_discount())
                    .append("subTotal", mediaAndCustomSaleEntry.getSub_total())
                    .append("subNetTotal", mediaAndCustomSaleEntry.getSub_netTotal())
                    .append("profile", mediaAndCustomSaleEntry.getSaleProfile())
                    .append("date", mediaAndCustomSaleEntry.getDate())
                    ;
            collection.insertOne(doc);
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public ArrayList<MediaAndCustomSaleEntry> findOneSale(String saleId){
        if(saleId==null){
            System.out.println("empty Saledid:"+ saleId);
            saleId = "none";
        }
        ArrayList<MediaAndCustomSaleEntry> sale = new ArrayList();
        ArrayList<MediaAndCustomSaleEntry> allItems = findAll();
        Iterator<MediaAndCustomSaleEntry> allItemsIterator = allItems.iterator();
        while(allItemsIterator.hasNext()){
            MediaAndCustomSaleEntry mediaAndCustomSaleEntryIterate = allItemsIterator.next() ;
            if(saleId.equals(mediaAndCustomSaleEntryIterate.getSaleid())){
                sale.add(mediaAndCustomSaleEntryIterate);
            }
        }
        return sale;
    }
    
    public boolean findIfExists(MediaAndCustomSaleEntry mediaAndCustomSaleEntry){
        ArrayList<MediaAndCustomSaleEntry> allItems = findAll();
        Iterator<MediaAndCustomSaleEntry> allItemsIterator = allItems.iterator();
        while(allItemsIterator.hasNext()){
            MediaAndCustomSaleEntry mediaAndCustomSaleEntryIterate = allItemsIterator.next() ;
            System.out.println("Current Sale Id:"+ mediaAndCustomSaleEntry.getSaleid());
            if(mediaAndCustomSaleEntryIterate.getSaleid().equals(mediaAndCustomSaleEntry.getSaleid()) &&
                mediaAndCustomSaleEntryIterate.getTitle().equals(mediaAndCustomSaleEntry.getTitle())   
                    ){
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<MediaAndCustomSaleEntry> findAll(){
        ArrayList<MediaAndCustomSaleEntry> allItems = new ArrayList();
        MongoCollection <Document> mycollection = getCollection();
        FindIterable<Document> iterDoc = mycollection.find();
        Iterator <Document>it = iterDoc.iterator();
        
        while(it.hasNext()){
            Document element = it.next();
            //append element data to obj
            MediaAndCustomSaleEntry mediaAndCustomSaleEntry=BeansClass.mediaAndCustomSaleEntry();
            mediaAndCustomSaleEntry = appendDetailsToEntry(
                    mediaAndCustomSaleEntry,
                    element.getString("sale_id"),
                    element.getString("title"),
                    element.getDouble("price"),
                    element.getInteger("quantity"),
                    element.getDouble("subTotal"),
                    element.getDouble("subDiscount"),
                    element.getDouble("subNetTotal"),
                    element.getString("profile"),
                    element.getDate("date")
            );
            allItems.add(mediaAndCustomSaleEntry);
            
        }
        
        allItems.sort(Comparator.comparing((MediaAndCustomSaleEntry o) -> o.getDate()).reversed());
        return allItems;
    }
    
    public ArrayList<MediaAndCustomSaleEntry> findBetweenDates(Date dateStart, Date dateEnd){
        ArrayList<MediaAndCustomSaleEntry> items = new ArrayList();
        MongoCollection <Document> mycollection = getCollection();
        FindIterable<Document> iterDoc = mycollection.find();
        Iterator <Document>it = iterDoc.iterator();
        
        while(it.hasNext()){
            Document element = it.next();
            
            //append element data to obj
            MediaAndCustomSaleEntry mediaAndCustomSaleEntry=BeansClass.mediaAndCustomSaleEntry();
            mediaAndCustomSaleEntry = appendDetailsToEntry(
                    mediaAndCustomSaleEntry,
                    element.getString("sale_id"),
                    element.getString("title"),
                    element.getDouble("price"),
                    element.getInteger("quantity"),
                    element.getDouble("subTotal"),
                    element.getDouble("subDiscount"),
                    element.getDouble("subNetTotal"),
                    element.getString("profile"),
                    element.getDate("date")
            );
            
            LocalDate lclDateStart = convertToLocalDate(dateStart);
            LocalDate lclDateEnd = convertToLocalDate(dateEnd);
            LocalDate lclDateEntry = convertToLocalDate(mediaAndCustomSaleEntry.getDate());
            
            if(lclDateEntry.isAfter(lclDateStart) && lclDateEntry.isBefore(lclDateEnd)){
                items.add(mediaAndCustomSaleEntry);
            }
            if(lclDateEntry.isEqual(lclDateStart) || lclDateEntry.isEqual(lclDateEnd)){
                items.add(mediaAndCustomSaleEntry);
            }
        }
        
        items.sort(Comparator.comparing((MediaAndCustomSaleEntry o) -> o.getDate()).reversed());
        return items;
    }
    
    public ArrayList<MediaAndCustomSaleEntry> findThisDatesEntries(Date date){
        ArrayList<MediaAndCustomSaleEntry> items = new ArrayList();
        MongoCollection <Document> mycollection = getCollection();
        FindIterable<Document> iterDoc = mycollection.find();
        Iterator <Document>it = iterDoc.iterator();
        
        while(it.hasNext()){
            Document element = it.next();
            
            //append element data to obj
            MediaAndCustomSaleEntry mediaAndCustomSaleEntry=BeansClass.mediaAndCustomSaleEntry();
            mediaAndCustomSaleEntry = appendDetailsToEntry(
                    mediaAndCustomSaleEntry,
                    element.getString("sale_id"),
                    element.getString("title"),
                    element.getDouble("price"),
                    element.getInteger("quantity"),
                    element.getDouble("subTotal"),
                    element.getDouble("subDiscount"),
                    element.getDouble("subNetTotal"),
                    element.getString("profile"),
                    element.getDate("date")
            );
            
            LocalDate lclThisDate = convertToLocalDate(date);
            LocalDate lclDateEntry = convertToLocalDate(mediaAndCustomSaleEntry.getDate());
            
            if(lclThisDate.isEqual(lclDateEntry)){
                items.add(mediaAndCustomSaleEntry);
            }
        }
        
        items.sort(Comparator.comparing((MediaAndCustomSaleEntry o) -> o.getDate()).reversed());
        return items;
    }
    
    
//    public ArrayList<LocalDate> findAllDates(){
//        ArrayList<MediaAndCustomSaleEntry> allDates = new ArrayList();
//        MongoCollection <Document> mycollection = getCollection();
//        FindIterable<Document> iterDoc = mycollection.find(new BasicDBObject("date"));
//    }
    
    public boolean delete(MediaAndCustomSaleEntry mediaAndCustomSaleEntry){
        try{
            MongoCollection <Document> mycollection = getCollection();
            BasicDBObject doc = new BasicDBObject();
            System.out.println("Putting title:"+ mediaAndCustomSaleEntry.getTitle());
            doc.put("title", mediaAndCustomSaleEntry.getTitle());
            System.out.println("Putting id:"+ mediaAndCustomSaleEntry.getSaleid());
            doc.put("sale_id", mediaAndCustomSaleEntry.getSaleid());
            
            
            DeleteResult deleteOne = mycollection.deleteOne(doc);
            boolean wasAcknowledged = deleteOne.wasAcknowledged();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    
    
    
    public MediaAndCustomSaleEntry appendDetailsToEntry(
        MediaAndCustomSaleEntry mediaAndCustomSaleEntry,
        String id,
        String title,
        double price,
        int quantity, 
        double sub_total,
        double sub_discount,
        double sub_netTotal,
        String profile,
        Date date
    ){
        mediaAndCustomSaleEntry.setSaleid(id);
        mediaAndCustomSaleEntry.setTitle(title);
        mediaAndCustomSaleEntry.setPrice(price);
        mediaAndCustomSaleEntry.setQuantity(quantity);
        mediaAndCustomSaleEntry.setSub_total(sub_total);
        mediaAndCustomSaleEntry.setSub_discount(sub_discount);
        mediaAndCustomSaleEntry.setSub_netTotal(sub_netTotal);
        mediaAndCustomSaleEntry.setSaleProfile(profile);
        mediaAndCustomSaleEntry.setDate(date);
        return mediaAndCustomSaleEntry;
    }
    
    public LinkedHashMap<String, ArrayList> getAllItemsPerDate() {
        //get all items and extract the distinct dates to a list
        ArrayList<LocalDate> lclDatesList = new ArrayList();
        ArrayList<Date> datesList = new ArrayList(); 
        ArrayList<MediaAndCustomSaleEntry> objList = findAll();
        int index = 0;
        for(MediaAndCustomSaleEntry nextEntry : objList){
            if(lclDatesList.contains(convertToLocalDate(nextEntry.getDate()))){
                //pass
            }
            else{
                datesList.add(index,nextEntry.getDate());
                lclDatesList.add(index, convertToLocalDate(nextEntry.getDate()));
                index= index+1;
            }
        }
        System.out.print("Final DatesList"+datesList);
        //organize all items of that date
        LinkedHashMap <String,ArrayList> hashDates = new LinkedHashMap();
        
        for(Date instance: datesList){
            System.out.println("Date in review:"+ instance);
            LocalDate instanceLocal = convertToLocalDate(instance); //convert to local date to filter out the time
            ArrayList<MediaAndCustomSaleEntry> dateItems = new ArrayList(); //create a list for that date
            for(MediaAndCustomSaleEntry item : objList){
                if(convertToLocalDate(item.getDate()).equals(instanceLocal)){ //if belongs to this date,
                    dateItems.add(item);  //add to this date's list                                          
                }
            }
            //add date and list values entry to hashmap
            hashDates.put(instance.toString(), dateItems);
        }
        return hashDates;
    }
    
    public LocalDate convertToLocalDate(Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    
    
}
