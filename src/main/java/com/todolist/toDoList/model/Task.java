package com.todolist.toDoList.model;

import jakarta.persistence.*;
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
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false) // поле status не может быть null (пустым)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false) // поле priorty также не может быть null (пустым)
    private Priority priority;


    private LocalDate dueDate;

    @CreationTimestamp
    @Column(updatable = false) // не обновляется при изменении
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;

}
