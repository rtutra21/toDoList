package com.todolist.toDoList.repository;

import com.todolist.toDoList.model.Status;
import com.todolist.toDoList.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findById();

    Task save();

    void deleteById(Long id);

    List<Task> findByStatus(Status status);
}
