/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.Logic;

import BeansPackage.BeansClass;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import retail_movie_store_mgmt.ProfileManagerController;
import retail_movie_store_mgmt.User;
import retail_movie_store_mgmt.database.UsersHandle;
import retail_movie_store_mgmt.security.Hashing;

/**
 *
 * @author Ian Mburu
 */
public class HandleProfileLogic {
    static String []roles = {"admin","manager","employee"};
    
    public static String[] getRoles() {
        return roles;
    }
    
     
    public boolean checkUserPrivilegeAdminManager(User user){       
        if(User.loggedin.getRole().equals(User.getAdminRole()) || User.loggedin.getRole().equals(User.getManagerRole()) ){
            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean checkUserPrivilegeAdmin(User user){       
        if(User.loggedin.getRole().equals(User.getAdminRole()) ){
            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean checkNewAdminUser(User user){
        String defaultPasswordAdmin = Hashing.hashInput(User.getAdminUsername());        
        if((defaultPasswordAdmin.equals(user.getPassword()) && user.getSecurity_qstn()==User.getDefaultQuestion())){
            return true;
        }
        return false;
    }
    
    public boolean checkNewSecondaryNewUser(User user){
        if((user.getSecurity_qstn() == User.getDefaultQuestion())){
            return true;
        }
        return false;
    }
    
    public User appendDetailsToUser(User user, String username,String password,String role){
        user.setUsername(username);
        user.setSecurity_ans(password);
        password = Hashing.hashInput(password);
        user.setPassword(password);
        user.setRole(role);
        user.setSecurity_qstn(User.getDefaultQuestion());
        return user;
    }
    
    public void insertUser(User user){
        UsersHandle usersHandle = BeansClass.usersHandle();
        
        
        ProfileManagerController profileManagerController = BeansClass.profileManagerController();
        boolean checkIfUserExists = checkIfExists(user);
        if(checkIfUserExists){
            profileManagerController.displayError("Error!User has already been inserted");
        }
        else{
            boolean insert = usersHandle.insertToDb(user);
            if(insert){
                profileManagerController.displayInfo("Successful Insertion!");
            }
            else{
                profileManagerController.displayError("Error!Please contact support for help");
            }
        }
        
    }
    
    public boolean checkIfExists(User user){
        UsersHandle usersHandle = BeansClass.usersHandle();
        List<User> userList = usersHandle.findUser(user.getUsername());
        if(userList.isEmpty() || userList == null){
            return false;
        }
        else{
            return true;
        }
    }
    
    public ArrayList<User> getAllUsers(){
        ArrayList<User> allUsersList = new ArrayList();
        UsersHandle usersHandle = BeansClass.usersHandle();
        Iterable<User> allUsersIterable = usersHandle.findAllUsers();
        
        Iterator <User>allUsersIterator = allUsersIterable.iterator();
        while(allUsersIterator.hasNext()){
            allUsersList.add(allUsersIterator.next());
        }
        
        return allUsersList;
    }
    
    
    public void deleteUser(String username){
        User user = BeansClass.user();
        user.setUsername(username);
        UsersHandle usersHandle = BeansClass.usersHandle();
        
        ProfileManagerController profileManagerController = BeansClass.profileManagerController();
        
        if("admin".equals(user.getUsername())){
            profileManagerController.displayError("Error!Cannot delete admin account as it is primary");
        }
        else if(user.getUsername().equals(User.loggedin.getUsername())){
            profileManagerController.displayError("Error!Cannot delete own account while logged in");
        }
        else{
            //del from db
            boolean del = usersHandle.deleteFromDb(user);

            if(del){
                profileManagerController.displayInfo("Successful Deletion!");
            }
            else{
                profileManagerController.displayError("Error!Please contact support for help");
            }
        }
        
    }
}
