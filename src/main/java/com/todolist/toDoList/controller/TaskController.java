package com.todolist.toDoList.controller;


import com.todolist.toDoList.model.Priority;
import com.todolist.toDoList.model.Status;
import com.todolist.toDoList.model.Task;
import com.todolist.toDoList.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@Controller
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/tasks")
    public String getAllTasks(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "tasks/list";

    }

    // Filter by status
    @GetMapping("/tasks/filter/status")
    public String filterByStatus(@RequestParam Status status, Model model) {
        model.addAttribute("tasks", taskService.findByStatus(status));
        model.addAttribute("filterType", status.toString());
        return "tasks/list";
    }

    // Filter by priority
    @GetMapping("/tasks/filter/priority")
    public String filterByPriority(@RequestParam Priority priority, Model model) {
        model.addAttribute("tasks", taskService.findByPriority(priority));
        model.addAttribute("filterType", priority.toString());
        return "tasks/list";
    }

    // Tasks for today
    @GetMapping("/tasks/today")
    public String todayTasks(Model model) {
        LocalDate today = LocalDate.now();
        model.addAttribute("tasks", taskService.findByDueDate(today));
        model.addAttribute("filterType", "TODAY");
        return "tasks/list";
    }

    // Upcoming tasks (next 7 days)
    @GetMapping("/tasks/upcoming")
    public String upcomingTasks(Model model) {
        LocalDate today = LocalDate.now();
        LocalDate nextWeek = today.plusDays(7);
        model.addAttribute("tasks", taskService.findUpcoming(today, nextWeek));
        model.addAttribute("filterType", "UPCOMING");
        return "tasks/list";
    }


    @GetMapping("/tasks/new")
    public String getNewForm(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("statuses", Status.values());
        model.addAttribute("priorities", Priority.values());
        return "tasks/form";

    }

    @PostMapping("/tasks")
    public String createNewTask (@ModelAttribute Task task) {
        taskService.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/{id}/edit")
    public String findTaskById(@PathVariable Long id, Model model) {
        model.addAttribute("task", taskService.findById(id));
        model.addAttribute("statuses", Status.values());
        model.addAttribute("priorities", Status.values());
        return "tasks/form";

    }

    @PostMapping("/tasks/{id}")
    public String updateTask (@PathVariable Long id, @ModelAttribute Task task) {
        task.setId(id);
        taskService.save(task);
        return "redirect:/tasks";
    }

    @PostMapping("/tasks/{id}/delete")
    public String deleteTaskById(@PathVariable Long id)  {
        taskService.deleteById(id);
        return "redirect:/tasks";
    }
}
