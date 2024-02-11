package com.revature.controllers;

import com.revature.models.ToDo;
import com.revature.services.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
@RestController // RestController is a combo of two annotations: Controller and ResponseBody
@RequestMapping("/todo") // -> Denotes the path to access this controller to be http://localhost:8080/icecream
public class ToDoController {

    // Todo Dependency Injection for our service class
    private ToDoService tds;

    @Autowired
    public ToDoController(ToDoService tds){
        this.tds = tds;
    }

    // Todo Handler for creating an ice cream record

    @PostMapping
    public ToDo createToDoHandler(@RequestBody ToDo toDo){
        // NOTE: You MUST have a no-args constructor for this class so we can convert between JSON and a Java Object
        ToDo savedToDoData = tds.createNewToDo(toDo);

        return savedToDoData;

    }

    // Todo Handler for getting an ice cream record by its id
    @GetMapping("{id}") // Means the get request goes to http://localhost:8080/{id}
    public ResponseEntity<ToDo> getToDoById(@PathVariable int id){
        ToDo returnedToDo;

        try{
            returnedToDo = tds.getToDoById(id);
        } catch (NoSuchElementException e){
            // This block gets executed if the code does not successfully find the record
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // If the record was successfully found we can return the record with 200 response
        return new ResponseEntity<>(returnedToDo, HttpStatus.OK);
    }

    // Todo Handler for updating an ice cream record
    @PutMapping("{id}") // Means the get request goes to http://localhost:8080/{id}
    public ResponseEntity<ToDo> updateToDoById(@PathVariable int id, @RequestBody ToDo toDo){
        ToDo returnedToDo;

        try{
            returnedToDo = tds.updateToDoById(id, toDo);
        } catch (NoSuchElementException e){
            // This block gets executed if the code does not successfully find the record
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // If the record was successfully found we can return the record with 200 response
        return new ResponseEntity<>(returnedToDo, HttpStatus.OK);
    }


}
