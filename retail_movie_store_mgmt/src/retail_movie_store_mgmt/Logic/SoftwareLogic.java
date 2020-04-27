/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.Logic;

import retail_movie_store_mgmt.Logic.HandleMainLogic;
import retail_movie_store_mgmt.commonUtil.DateTime;
import BeansPackage.BeansClass;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import retail_movie_store_mgmt.SoftwareInventoryController;
import retail_movie_store_mgmt.database.SoftwareHandle;
import retail_movie_store_mgmt.product.Software;

/**
 *
 * @author Ian Mburu
 */
public class SoftwareLogic {
    
//    String[] arrInput = {price_sh,price_cts,price_optical_sh,price_optical_cts};
//    String [] labels = {"Price(Ksh)","Price(cts)","Price on Optical Disk(Ksh)","Price on Optical Disk(cts)"};
//    String whereFound = checkIntInput(arrInput,labels);
//    if(whereFound!=null){
//            //wrong input
//            JOptionPane.showMessageDialog(null, whereFound+" field cannot contain alphabetic characters. It needs to be a valid number");
//    }else{
//        proceed;
//    }
        
    //verifies input
    public String verifyNumInput(String[]params,String[]labels){        
        HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
        return handleMainLogic.verifyNumInput(params, labels);
    }
    
    public String getSoftwareId(String title){
        DateTime instance = BeansClass.dateTime();
        int[] dateTime = instance.getDateTime();
        int chosenNum=0;
        
        Random random = new Random();
        int randomNum = random.nextInt(10000);
        
        int sum =0;
        //do addition and find modulus of the addition of these numbers with the random no.
        for(int i=0;i<dateTime.length;i++){
            sum+=dateTime[i];
        }
        
        if(sum>randomNum){
            chosenNum += sum%randomNum;
        }
        else if(sum<randomNum){
            chosenNum += sum%randomNum;
        }
        else{
            return getSoftwareId(title);            
        }
        
        //convert to string
        Integer chosen = chosenNum;
        String log_id = title +"["+chosen.toString()+"]";
        return log_id;
    }
    
    public boolean checkIfSoftwareExists(String title){
        SoftwareHandle softwareHandle = BeansClass.softwareHandle(); 
        Software software = softwareHandle.findIfExists(title);
        if(software==null){
            //inexistent
            return false;
        }
        else{
            return true;
        }
    }
    
    public void insertSoftwareToDb(
            String title,
            String strPrice,
            String strPrice_optical_disk,
            String strInstallation_limit,
            String desc
    ){
        //generate id
        String softwareId =  getSoftwareId(title);
        
        //convert to appropriate data types
        HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
        double price = handleMainLogic.convertStrToDouble(strPrice);
        double price_optical_disk = handleMainLogic.convertStrToDouble(strPrice_optical_disk);
        int installation_limit = handleMainLogic.convertStrToInt(strInstallation_limit);
        
        //check if the software exists 
        boolean check = checkIfSoftwareExists(title);
        if(check){
            SoftwareInventoryController instance = BeansClass.softwareInventoryController();
            int response = instance.receiveConfirmationMethod("A software with the same name exists,do you still want to add this one? "
                    + "This will replace the previously entered software.");
            if(response==0){
                Software software = BeansClass.software(title);
                software = appendProductDetails(software,strPrice,strPrice_optical_disk,strInstallation_limit,desc);
                updateSoftwareInDatabase(software); 
            }
            else{
                //pass
            }
        }
        else{
            //enter to db
            SoftwareHandle softwareHandle = BeansClass.softwareHandle();
            softwareHandle.softwareInsert(softwareId, title, price, price_optical_disk, installation_limit, desc);
            
            //confirm
            SoftwareInventoryController instance = BeansClass.softwareInventoryController();
            instance.displayMessage("Software inserted successfully!");
        }        
    }
    
    public void updateSoftwareInDatabase(Software software){
        SoftwareHandle softwareHandle = BeansClass.softwareHandle();
        boolean updated = softwareHandle.updateSoftware(software);
        
        
        if(updated){
            SoftwareInventoryController instance = BeansClass.softwareInventoryController();
            instance.displayMessage("Software updated successfully!");
        }
        else{
            SoftwareInventoryController instance = BeansClass.softwareInventoryController();
            instance.displayMessage("System Error. Please contact support for details");
        }
    }
    
    public Software appendProductDetails(Software software,
            String strPrice,
            String strPrice_optical_disk, 
            String strInstallationLimit,
            String description){
        
   //   convert to number
        HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
        double price = handleMainLogic.convertStrToDouble(strPrice); 
        double price_optical_disk = handleMainLogic.convertStrToDouble(strPrice_optical_disk);
        int installationLimit = handleMainLogic.convertStrToInt(strInstallationLimit);
        
        software.setPrice(price);
        software.setPrice_optical_disk(price_optical_disk);
        software.setInstallationLimit(installationLimit);
        software.setDesc(description);
                
        return software;
    }
    
    public ArrayList<Software> fetchAllSoftwareObjects(){
        ArrayList<Software> softwareList = new ArrayList();
        
        SoftwareHandle softwareHandle = BeansClass.softwareHandle();
        Iterable softwareIterable = softwareHandle.findAll();
        Iterator<Software> softwareIterator = softwareIterable.iterator();
        
        while(softwareIterator.hasNext()){
            Software element = softwareIterator.next();
            softwareList.add(element);
        }    
        return softwareList;
    }
    
    public Software fetchOneSoftware(String title){
        SoftwareHandle softwareHandle = BeansClass.softwareHandle();
        Software software = softwareHandle.findOne(title);
        return software;
    }
    
    
    
    public void deleteSoftware(String title){
        boolean check = checkIfSoftwareExists(title);
        if(check){
            //proceed to delete
            SoftwareHandle softwareHandle = BeansClass.softwareHandle();
            softwareHandle.deleteOneFromDb(title);
            
            
            SoftwareInventoryController instance = BeansClass.softwareInventoryController();
            instance.displayMessage("Software deleted successfully.");
        }
        else{
            SoftwareInventoryController instance = BeansClass.softwareInventoryController();
            instance.displayMessage("Software not found! Please refresh to view available software.");
        }
    }
}
