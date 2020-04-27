/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_movie_store_mgmt.Logic;

import BeansPackage.BeansClass;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import retail_movie_store_mgmt.ToDoController;
import retail_movie_store_mgmt.User;
import retail_movie_store_mgmt.commonUtil.DateTime;
import retail_movie_store_mgmt.database.TodoHandle;
import retail_movie_store_mgmt.todo.Todo;

/**
 *
 * @author Ian Mburu
 */
public class HandleTodoLogic {
    int maxTitleLength = 100;
    
    public boolean checkTitleObeysMaxLength(String title){
        HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
        return handleMainLogic.checkStringObeysMaxLength(title, maxTitleLength);
    }
    
    public String generateTodoId(){
        DateTime dtime = BeansClass.dateTime();
        int [] arr = dtime.getDateTime();
        String id = "";
        String profile = User.loggedin.getUsername();
        
        for(int i : arr){
            HandleMainLogic handleMainLogic = BeansClass.handleMainLogic();
            String subId = handleMainLogic.convertIntToString(i);
            id = id+subId;
        }
        
        id = profile+"_"+id;
        return id;
    }
    
    public boolean checkIfTimeIsPassed(Todo todo){
        //get current date
        DateTime dateTime = BeansClass.dateTime();
        LocalDate todayDate = dateTime.getTodayDate();
        
        try{
            return todayDate.isAfter(todo.getDate());
        }
        catch(Exception e){
            return false;
        }
    }
    
    //attaches properties to the todo object
    public Todo appendTodoDetails(
            Todo todo,
            String id, 
            String title, 
            String description,
            LocalDate date,
            String profile
    ){
        todo.setId(id);
        todo.setTitle(title);
        todo.setDescription(description);
        todo.setDate(date);
        todo.setProfile(profile);
        
        return todo;
    }
    
    //checks if todo is already inserted
    public boolean checkIfExists(Todo todo){
        TodoHandle todoHandle = BeansClass.todoHandle();
        List<Todo> todoList = todoHandle.findTodoEntry(todo.getTitle(),User.loggedin.getUsername());
        if(todoList.isEmpty() || todoList == null){
            return false;
        }
        else{
            return true;
        }
    }
    
    //checks if todo is already inserted
    public Todo findOne(String title){
        Todo todo = BeansClass.todo();
        todo.setTitle(title);
        
        TodoHandle todoHandle = BeansClass.todoHandle();
        List<Todo> todoList = todoHandle.findTodoEntry(todo.getTitle(),User.loggedin.getUsername());
        if(todoList.isEmpty() || todoList == null){
            return null;
        }
        else{
            Todo foundTodo = todoList.get(0);
            return foundTodo;
        }
    }
    
    //inserts new entry to db
    public void insertTodo(Todo todo){
        TodoHandle todoHandle = BeansClass.todoHandle();
        ToDoController toDoController = BeansClass.toDoController();
        
        boolean checkIfTodoExists = checkIfExists(todo);
        if(checkIfTodoExists){
            toDoController.displayError("Error!Task has already been inserted");
        }
        else{
            boolean insert = todoHandle.insertToDb(todo);
            if(insert){
                toDoController.displayInfo("Successful Insertion!");
            }
            else{
                toDoController.displayError("Error!Please contact support for help");
            }
        }
    }
    
    //update todo details
    public void updateTodo(Todo todo){
        TodoHandle todoHandle = BeansClass.todoHandle();
        ToDoController toDoController = BeansClass.toDoController();
                
        boolean update = todoHandle.updateInDb(todo);
        if(update){
            toDoController.displayInfo("Successful Update!");
        }
        else{
            toDoController.displayError("Error!Please contact support for help");
        }
    }
    
    //retrieve all entries for this profile
    public ArrayList<Todo> getAllEntries(){
        String profile = User.loggedin.getUsername();
        ArrayList<Todo> allTodoList = new ArrayList();
        TodoHandle todoHandle = BeansClass.todoHandle();
        Iterable<Todo> allTodoIterable = todoHandle.findAllEntries(profile);
        
        Iterator <Todo>allTodoIterator = allTodoIterable.iterator();
        while(allTodoIterator.hasNext()){
            allTodoList.add(allTodoIterator.next());
        }
        
        allTodoList.sort(Comparator.comparing((Todo o) -> o.getDate()).reversed());
        return allTodoList;
    }
    
    public void deleteTodo(Todo todo){
        TodoHandle todoHandle = BeansClass.todoHandle();
        ToDoController toDoController = BeansClass.toDoController();
        
        //del from db
        boolean del = todoHandle.deleteFromDb(todo);

        if(del){
            toDoController.displayInfo("Successful Deletion!");
        }
        else{
            toDoController.displayError("Error!Please contact support for help");
        }
    }
    
    
}
