package com.evaloper.TodoApp.controller;

import com.evaloper.TodoApp.dto.TaskDto;
import com.evaloper.TodoApp.entities.Task;
import com.evaloper.TodoApp.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")

public class TaskController {
    private TaskService taskService;
    @GetMapping("/{title}")
    public ResponseEntity<Task> viewTaskByTitle(@PathVariable String title) {
        Task task = taskService.viewTaskByTitle(title);
        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        Task updatedTask = taskService.updateTask(id, taskDto);
        if (updatedTask != null) {
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
