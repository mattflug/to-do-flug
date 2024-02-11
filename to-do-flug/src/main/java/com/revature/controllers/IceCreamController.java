package com.revature.controllers;

import com.revature.models.ToDo;
import com.revature.services.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
@RestController // RestController is a combo of two annotations: Controller and ResponseBody
@RequestMapping("/icecream") // -> Denotes the path to access this controller to be http://localhost:8080/icecream
public class IceCreamController {
    /*
    The Controller layer is specifically responsible for controller HTTP Traffic, so it receives the HTTP requests,
    sends off the appropriate info to the service layer and the formats the HTTP response
     */

    // Todo Dependency Injection for our service class
    private ToDoService ics;

    @Autowired
    public IceCreamController(ToDoService ics){
        this.ics = ics;
    }

    // Todo Handler for creating an ice cream record
    /*
     To CREATE a new record in the DB we generally use a POST request
     For reference:
        GET -> Reading info
        PUT -> Updating info
        DELETE -> Deleting info
     */
    @PostMapping
    public ToDo createIceCreamHandler(@RequestBody ToDo iceCream){
        // NOTE: You MUST have a no-args constructor for this class so we can convert between JSON and a Java Object
        ToDo savedIceCreamData = ics.createNewIceCream(iceCream);

        return savedIceCreamData;

    }


    // Todo Handler for getting an ice cream record by its id
    @GetMapping("{id}") // Means the get request goes to http://localhost:8080/{id}
    public ResponseEntity<ToDo> getIceCreamById(@PathVariable int id){
        // If we want FULL control over the result of an HTTP Request we'll need to return not just the object
        // but an http response entity

        // Now that we have full control of our response we should be able to check and make sure the user is accessing
        // a resource that actually exist
        ToDo returnedIceCream;

        try{
            returnedIceCream = ics.getIceCreamById(id);
        } catch (NoSuchElementException e){
            // This block gets executed if the code does not successfully find the record
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // If the record was successfully found we can return the record with 200 response
        return new ResponseEntity<>(returnedIceCream, HttpStatus.OK);
    }

    // Todo Handler for updating an ice cream record
    @PutMapping("{id}") // Means the get request goes to http://localhost:8080/{id}
    public ResponseEntity<ToDo> updateIceCreamById(@PathVariable int id, @RequestBody ToDo iceCream){
        ToDo returnedIceCream;

        try{
            returnedIceCream = ics.updateIceCreamById(id, iceCream);
        } catch (NoSuchElementException e){
            // This block gets executed if the code does not successfully find the record
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // If the record was successfully found we can return the record with 200 response
        return new ResponseEntity<>(returnedIceCream, HttpStatus.OK);
    }


}
