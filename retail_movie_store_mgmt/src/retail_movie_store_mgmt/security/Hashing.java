/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.security;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 *
 * @author Ian Mburu
 */
public class Hashing {
    static int xtersv1 = 0;
    static int xtersv2 = 0;
    //hash input and return hashed output
    public static String hashInput(String input){
        try{
            
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(input.getBytes());
            
            BigInteger no = new BigInteger(1,messageDigest);
            
            String hashtext = no.toString(16) ;
            
            while(hashtext.length()<32){
                hashtext = "0"+ hashtext;
            }
            
            return hashtext;
        }
        catch(Exception e){
            return "No such algorithm";
        }
    }
    
    public static void main(String[] args) {
        String input = "admin";
        String hash1 = hashInput(input);
    }
}
