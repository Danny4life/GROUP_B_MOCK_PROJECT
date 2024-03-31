package com.evaloper.TodoApp.entities;

import com.evaloper.TodoApp.enums.PriorityLevel;
import com.evaloper.TodoApp.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "task_tbl")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDate dateCreated;
    private LocalTime timeCreated;
    private LocalDate deadline;
    @Enumerated(EnumType.STRING)
    private PriorityLevel priorityLevel;
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;
}
