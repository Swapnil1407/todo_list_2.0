package org.example.todoapp.repositories;

import org.example.todoapp.entities.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
}
