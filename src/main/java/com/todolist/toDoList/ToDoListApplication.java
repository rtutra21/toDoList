package com.todolist.toDoList;

import com.todolist.toDoList.model.Priority;
import com.todolist.toDoList.model.Status;
import com.todolist.toDoList.model.Task;
import com.todolist.toDoList.repository.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class ToDoListApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToDoListApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(TaskRepository repository) {
		return args -> {
			Task task1 = Task.builder() // создание первой таски на репозитории
					.title("Дедлайн по проекту")
					.description("Сдать пет-проект до конца месяца")
					.status(Status.NEW)
					.priority(Priority.HIGH)
					.dueDate(LocalDate.of(2025, 12, 10))
					.build();

			Task task2 = Task.builder() // создание второй таски на репозитории
					.title("Празднование дня рождения")
					.description("У друга отмечается день рождения")
					.status(Status.IN_PROGRESS)
					.priority(Priority.MEDIUM)
					.dueDate(LocalDate.of(2025, 01, 01))
					.build();

			repository.save(task1); // сохранение задач в репозитории
			repository.save(task2);

			System.out.println("Все задачи на сегодня:");
			repository.findAll()
					.forEach(task -> System.out.printf
							("id: %d, title: %s, description: %s, status: %s, priority: %s, dueDate: %s%n",
									task.getId(),
									task.getTitle(),
									task.getDescription(),
									task.getStatus(),
									task.getPriority(),
									task.getDueDate()));

		};
	}

}
