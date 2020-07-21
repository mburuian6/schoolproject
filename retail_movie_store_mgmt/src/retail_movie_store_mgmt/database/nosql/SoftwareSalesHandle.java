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
import retail_movie_store_mgmt.Sales.SoftwareSaleEntry;

/**
 *
 * @author Ian Mburu
 */
public class SoftwareSalesHandle {
    //handle ops
    String collectionName = "softwaresales";
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
    
    public boolean insertToDb(SoftwareSaleEntry softwareSaleEntry){
        authenticate();
        try{
            MongoCollection <Document> collection = getCollection();

            Document doc = new Document("title",softwareSaleEntry.getTitle())
                    .append("sale_id", softwareSaleEntry.getSaleid())
                    .append("price", softwareSaleEntry.getPrice())
                    .append("quantity", softwareSaleEntry.getQuantity())
                    .append("subDiscount", softwareSaleEntry.getSub_discount())
                    .append("subTotal", softwareSaleEntry.getSub_total())
                    .append("subNetTotal", softwareSaleEntry.getSub_netTotal())
                    .append("profile", softwareSaleEntry.getSaleProfile())
                    .append("date", softwareSaleEntry.getDate())
                    ;
            collection.insertOne(doc);
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public ArrayList<SoftwareSaleEntry> findOneSale(String saleId){
        if(saleId==null){
            saleId = "none";
        }
        ArrayList<SoftwareSaleEntry> sale = new ArrayList();
        ArrayList<SoftwareSaleEntry> allItems = findAll();
        Iterator<SoftwareSaleEntry> allItemsIterator = allItems.iterator();
        while(allItemsIterator.hasNext()){
            SoftwareSaleEntry softwareSaleEntryIterate = allItemsIterator.next() ;
            if(saleId.equals(softwareSaleEntryIterate.getSaleid())){
                sale.add(softwareSaleEntryIterate);
            }
        }
        return sale;
    }
    
    public boolean findIfExists(SoftwareSaleEntry softwareSaleEntry){
        ArrayList<SoftwareSaleEntry> allItems = findAll();
        Iterator<SoftwareSaleEntry> allItemsIterator = allItems.iterator();
        while(allItemsIterator.hasNext()){
            SoftwareSaleEntry softwareSaleEntryIterate = allItemsIterator.next() ;
            System.out.println("Current Sale Id:"+ softwareSaleEntry.getSaleid());
            if(softwareSaleEntryIterate.getSaleid().equals(softwareSaleEntry.getSaleid()) &&
                softwareSaleEntryIterate.getTitle().equals(softwareSaleEntry.getTitle())   
                    ){
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<SoftwareSaleEntry> findAll(){
        ArrayList<SoftwareSaleEntry> allItems = new ArrayList();
        MongoCollection <Document> mycollection = getCollection();
        FindIterable<Document> iterDoc = mycollection.find();
        Iterator <Document>it = iterDoc.iterator();
        
        while(it.hasNext()){
            Document element = it.next();
            //append element data to obj
            SoftwareSaleEntry softwareSaleEntry=BeansClass.softwareSaleEntry();
            softwareSaleEntry = appendDetailsToEntry(
                    softwareSaleEntry,
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
            allItems.add(softwareSaleEntry);
        }
        
        allItems.sort(Comparator.comparing((SoftwareSaleEntry o) -> o.getDate()).reversed());
        return allItems;
    }
    
    public ArrayList<SoftwareSaleEntry> findBetweenDates(Date dateStart, Date dateEnd){
        ArrayList<SoftwareSaleEntry> items = new ArrayList();
        MongoCollection <Document> mycollection = getCollection();
        FindIterable<Document> iterDoc = mycollection.find();
        Iterator <Document>it = iterDoc.iterator();
        
        while(it.hasNext()){
            Document element = it.next();
            //append element data to obj
            SoftwareSaleEntry softwareSaleEntry=BeansClass.softwareSaleEntry();
            softwareSaleEntry = appendDetailsToEntry(
                    softwareSaleEntry,
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
            LocalDate lclDateEntry = convertToLocalDate(softwareSaleEntry.getDate());
            
            if(lclDateEntry.isAfter(lclDateStart) && lclDateEntry.isBefore(lclDateEnd)){
                items.add(softwareSaleEntry);
            }
            else if(lclDateEntry.equals(lclDateStart) || lclDateEntry.equals(lclDateEnd)){
                items.add(softwareSaleEntry);
            }
        }
        
        items.sort(Comparator.comparing((SoftwareSaleEntry o) -> o.getDate()).reversed());
        return items;
    }
    
    public ArrayList<SoftwareSaleEntry> findThisDatesEntries(Date date){
        ArrayList<SoftwareSaleEntry> items = new ArrayList();
        MongoCollection <Document> mycollection = getCollection();
        FindIterable<Document> iterDoc = mycollection.find();
        Iterator <Document>it = iterDoc.iterator();
        
        while(it.hasNext()){
            Document element = it.next();
            //append element data to obj
            SoftwareSaleEntry softwareSaleEntry=BeansClass.softwareSaleEntry();
            softwareSaleEntry = appendDetailsToEntry(
                    softwareSaleEntry,
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
            LocalDate lclDateEntry = convertToLocalDate(softwareSaleEntry.getDate());
            
            if(lclThisDate.isEqual(lclDateEntry)){
                items.add(softwareSaleEntry);
            }
        }
        
        items.sort(Comparator.comparing((SoftwareSaleEntry o) -> o.getDate()).reversed());
        return items;
    }
    
    public boolean delete(SoftwareSaleEntry softwareSaleEntry){
        try{
            MongoCollection <Document> mycollection = getCollection();
            BasicDBObject doc = new BasicDBObject();
            doc.put("title", softwareSaleEntry.getTitle());
            doc.put("sale_id", softwareSaleEntry.getSaleid());
            
            
            DeleteResult deleteOne = mycollection.deleteOne(doc);
            boolean wasAcknowledged = deleteOne.wasAcknowledged();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    
    public SoftwareSaleEntry appendDetailsToEntry(
        SoftwareSaleEntry softwareSaleEntry,
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
        softwareSaleEntry.setSaleid(id);
        softwareSaleEntry.setTitle(title);
        softwareSaleEntry.setPrice(price);
        softwareSaleEntry.setQuantity(quantity);
        softwareSaleEntry.setSub_total(sub_total);
        softwareSaleEntry.setSub_discount(sub_discount);
        softwareSaleEntry.setSub_netTotal(sub_netTotal);
        softwareSaleEntry.setSaleProfile(profile);
        softwareSaleEntry.setDate(date);
        return softwareSaleEntry;
    }
    
    public LinkedHashMap<String, ArrayList> getAllItemsPerDate() {
        //get all items and extract the distinct dates to a list
        ArrayList<LocalDate> lclDatesList = new ArrayList();
        ArrayList<Date> datesList = new ArrayList(); 
        ArrayList<SoftwareSaleEntry> objList = findAll();
        int index = 0;
        for(SoftwareSaleEntry nextEntry : objList){
            if(lclDatesList.contains(convertToLocalDate(nextEntry.getDate()))){
                //pass
            }
            else{
                datesList.add(index,nextEntry.getDate());
                lclDatesList.add(index, convertToLocalDate(nextEntry.getDate()));
                index= index+1;
            }
        }
        //organize all items of that date
        LinkedHashMap <String,ArrayList> hashDates = new LinkedHashMap();
        
        for(Date instance: datesList){
            LocalDate instanceLocal = convertToLocalDate(instance); //convert to local date to filter out the time
            ArrayList<SoftwareSaleEntry> dateItems = new ArrayList(); //create a list for that date
            for(SoftwareSaleEntry item : objList){
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
