package com.revature.services;

import com.revature.daos.ToDoDao;
import com.revature.models.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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

    public ToDo createNewToDo(ToDo toDo){

        ToDo returnedToDo = tdd.save(toDo);

        return returnedToDo;
    }

    // Todo Get all to dos
    public ToDo getAllToDos(){
        List<ToDo> returnedToDos = tdd.findAll();
        return returnedToDos;

    }
    // Todo Get a specific To Do record
    public ToDo getToDoById(int id){
        Optional<ToDo> returnedToDo = tdd.findById(id);
        return returnedToDo.orElseThrow();
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
