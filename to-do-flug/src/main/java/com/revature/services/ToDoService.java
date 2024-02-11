package com.revature.services;

import com.revature.daos.ToDoDao;
import com.revature.models.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ToDoService {

    private ToDoDao tdd;

    // Constructor with @Autowired on top so Spring can wire our beans together
    @Autowired
    public ToDoService(ToDoDao tdd){
        this.tdd = tdd;
    }

    // Todo Create a new note entry method

    public ToDo createToDo(ToDo toDo){

        ToDo returnedToDo = tdd.save(toDo);

        return returnedToDo;
    }

    // Todo Get a specific To Do record

    public ToDo getToDoById(int id){
        // Now that we've taken in the id value, we can simply look up the record and return it
        Optional<ToDo> returnedToDo = tdd.findById(id);
        // getReferenceById will also work perfectly fine, but I want to try using our Optional Class

        return returnedToDo.orElseThrow();
        // By choosing to throw an exception we can use a try-catch block in our controller layer and appropriately
        // return a 404 status code instead of a 200
    }

    // Todo Update an To Do

    // This means I'll have access to the id field of the record to update as well as the new fields from the body of
    // the request

    public ToDo updateToDoById(int id, ToDo toDo){
        // Get the to do from the database with the id
        Optional<ToDo> possibleReturnedToDo = tdd.findById(id);
        // We'll extract the record with the orElseThrow

        ToDo returnedToDo = possibleReturnedToDo.orElseThrow();

        // Update the relevant fields
        returnedToDo.setText(toDo.getText());
        returnedToDo.setCompleted(toDo.isCompleted());
        
        // Save the resulting ice cream record
        return tdd.save(returnedToDo);
    }

}
