package com.todolist.toDoList.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data // уже есть геттеры и сеттеры
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tasks")


public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Сообщение не может быть пустым")
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull (message = "Должен быть заполнен")
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull (message = "Приоритет должен быть выбран")
    private Priority priority;


    private LocalDate dueDate;

    @CreationTimestamp
    @Column(updatable = false) // не обновляется при изменении
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;

}
