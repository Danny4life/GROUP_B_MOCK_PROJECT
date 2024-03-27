package com.evaloper.TodoApp.entities;

import com.evaloper.TodoApp.enums.PriorityLevel;
import com.evaloper.TodoApp.enums.TaskStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
    private String title;
    private String description;
    private LocalDate dateCreated;
    private LocalTime timeCreated;
    private PriorityLevel priorityLevel;
    private TaskStatus taskStatus;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userid;
}
