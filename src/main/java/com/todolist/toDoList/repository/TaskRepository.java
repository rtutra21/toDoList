package com.todolist.toDoList.repository;

import com.todolist.toDoList.model.Priority;
import com.todolist.toDoList.model.Status;
import com.todolist.toDoList.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;



public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByStatus(Status status);
    List<Task> findByPriority(Priority priority);
    List<Task> findByDueDate(LocalDate dueDate);
    List<Task> findByDueDateBetween(LocalDate dueDateAfter, LocalDate dueDateBefore);

    Long id(Long id);
}
