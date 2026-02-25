package com.todolist.toDoList;

import com.todolist.toDoList.exception.TaskNotFoundException;
import com.todolist.toDoList.model.Status;
import com.todolist.toDoList.model.Task;
import com.todolist.toDoList.repository.TaskRepository;
import com.todolist.toDoList.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // включает поддержку Mockito в Junit5

public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;  // мок репозитория

    @InjectMocks
    private TaskService taskService;  // реальный сервис, в который Mockito сам подставит мок

    //    Тестирование успешного случая (when + thenReturn)
    @Test
    void findById_ShouldReturnTask_WhenTaskExists() {
        // Подготовка (given)
        Long id = 1L;
        Task expectedTask = Task.builder().id(id).title("Тест").build();

        // Настраиваем мок: когда вызовут findById с этим id, вернуть expectedTask
        when(taskRepository.findById(id)).thenReturn(Optional.of(expectedTask));

        // Действие (when)
        Task actualTask = taskService.findById(id);

        // Проверка (then)
        assertEquals(expectedTask, actualTask);
        verify(taskRepository, times(1)).findById(id);  // проверяем, что метод репозитория вызвался ровно 1 раз
    }

    //    Тестирование исключений (assertThrows)
//Для проверки, что метод выбрасывает исключение, используем assertThrows
    @Test
    void findById_ShouldThrowException_WhenTaskNotFound() {
        // Подготовка
        Long id = 999L;
        when(taskRepository.findById(id)).thenReturn(Optional.empty());  // мок возвращает пустой Optional

        // Действие и проверка
        TaskNotFoundException exception = assertThrows(TaskNotFoundException.class, () -> {
            taskService.findById(id);
        });

        // Можно проверить сообщение исключения (опционально)
        assertTrue(exception.getMessage().contains("не найдена"));

        verify(taskRepository, times(1)).findById(id);
    }

//    Тестирование void-методов (delete)

    //    Для методов, которые ничего не возвращают (например, deleteById),
//    используем verify для проверки, что они были вызваны с правильными параметрами
    @Test
    void deleteById_ShouldCallRepositoryDelete() {
        // Подготовка
        Long id = 1L;

        // Действие
        taskService.deleteById(id);

        // Проверка: убеждаемся, что метод репозитория deleteById вызвался с этим id
        verify(taskRepository, times(1)).deleteById(id);
    }

    //    Тестирование методов с параметрами (findByStatus)
    @Test
    void findByStatus_ShouldReturnFilteredTasks() {
        // Подготовка
        Status status = Status.NEW;
        List<Task> expectedTasks = List.of(
                Task.builder().id(1L).title("Задача 1").status(status).build(),
                Task.builder().id(2L).title("Задача 2").status(status).build()
        );

        when(taskRepository.findByStatus(status)).thenReturn(expectedTasks);

        // Действие
        List<Task> actualTasks = taskService.findByStatus(status);

        // Проверка
        assertEquals(expectedTasks, actualTasks);
        assertEquals(2, actualTasks.size());
        verify(taskRepository, times(1)).findByStatus(status);
    }

    //    Проверка, что метод сохраняет задачу (save)
    @Test
    void save_ShouldReturnSavedTask() {
        // Подготовка
        Task taskToSave = Task.builder().title("Новая задача").build();
        Task savedTask = Task.builder().id(1L).title("Новая задача").build();

        when(taskRepository.save(taskToSave)).thenReturn(savedTask);

        // Действие
        Task result = taskService.save(taskToSave);

        // Проверка
        assertEquals(savedTask, result);
        assertEquals(1L, result.getId());
        verify(taskRepository, times(1)).save(taskToSave);
    }
}
