package com.todolist.toDoList.service;


import com.todolist.toDoList.exception.TaskNotFoundException;
import com.todolist.toDoList.model.Status;
import com.todolist.toDoList.model.Task;
import com.todolist.toDoList.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService  {

    private final TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Задача с id" + id + "не найдена"));
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> findByStatus(Status status) {
        return taskRepository.findByStatus(status);
    }

}
