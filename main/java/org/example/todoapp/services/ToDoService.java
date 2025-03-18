package org.example.todoapp.services;

import org.example.todoapp.entities.ToDo;
import org.example.todoapp.repositories.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToDoService {
    @Autowired
    private ToDoRepository toDoRepository;

    //Get all the items
    public List<ToDo> getAllToDoItems() {
        ArrayList<ToDo> todoList=new ArrayList<>();
        toDoRepository.findAll().forEach(toDo -> todoList.add(toDo));
        return todoList;
    }

    //get single item
    public ToDo getToDoItemById(Long id) {
        return toDoRepository.findById(id).get();
    }

    //update the status of single item
    public boolean updateStatus(Long id) {
        ToDo toDo=getToDoItemById(id);
        toDo.setStatus("Completed");

        return saveOrUpdateToDoItem(toDo);
    }

    //save or update item
    public boolean saveOrUpdateToDoItem(ToDo toDo) {
        ToDo updateObj=toDoRepository.save(toDo);

        return getToDoItemById(updateObj.getId()) != null;
    }

    //delete an item
    public boolean deleteToDoItem(Long id) {
        toDoRepository.deleteById(id);

        if(toDoRepository.findById(id).isEmpty())
            return true;
        return false;
    }
}
