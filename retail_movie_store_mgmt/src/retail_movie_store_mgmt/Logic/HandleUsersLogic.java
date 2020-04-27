/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.Logic;

import BeansPackage.BeansClass;
import java.util.ArrayList;
import java.util.Iterator;
import retail_movie_store_mgmt.User;
import retail_movie_store_mgmt.database.UsersHandle;

/**
 *
 * @author Ian Mburu
 */
public class HandleUsersLogic {
  ArrayList<String> users = new ArrayList<>();
  
  public ArrayList<String> getUsers(){
    UsersHandle lst = BeansClass.usersHandle();  
    Iterable userListIterable = lst.findAllUsers();
    Iterator<User> userListIterator = userListIterable.iterator();
    
    while(userListIterator.hasNext()){
        User element = userListIterator.next();
        users.add(element.getUsername());
    }    
    return users;
  }
}
