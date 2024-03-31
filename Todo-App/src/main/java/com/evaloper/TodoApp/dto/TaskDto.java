package com.evaloper.TodoApp.dto;

import com.evaloper.TodoApp.enums.PriorityLevel;
import com.evaloper.TodoApp.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDto {
    @NotBlank(message = "Book title must not be blank")
    private  String title;
    private String description;
    private LocalDate dateCreated;
    private LocalTime timeCreated;
    private LocalDate deadline;
    private PriorityLevel priorityLevel;
    private TaskStatus taskStatus;

}
