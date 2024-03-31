package com.evaloper.TodoApp.controller;

import com.evaloper.TodoApp.dto.TaskDto;
import com.evaloper.TodoApp.entities.Task;
import com.evaloper.TodoApp.entities.User;
import com.evaloper.TodoApp.enums.TaskStatus;
import com.evaloper.TodoApp.exceptions.TaskNotFoundException;
import com.evaloper.TodoApp.services.Impl.TaskServiceImpl;
import com.evaloper.TodoApp.services.TaskService;
import com.evaloper.TodoApp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;

    @PostMapping("create-task/{id}")
    public ResponseEntity<TaskDto> createBook(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        taskDto = taskService.createTask(id, taskDto);

        return new ResponseEntity<>(taskDto, HttpStatus.CREATED);
    }

    @GetMapping("/view-all-task/{id}")
    public ResponseEntity<List<TaskDto>> viewAllTasks(@PathVariable Long id){
        User user =  userService.findById(id);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<TaskDto> allTasks = taskService.findAllTaskByUserId(user);
        return new ResponseEntity<>(allTasks, HttpStatus.OK);
    }


    @DeleteMapping("/delete-task/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteTask(@PathVariable Long id) {

        boolean deleted = false;
        deleted = taskService.deleteTask(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Delete", deleted);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/completed-tasks")
    public ResponseEntity<List<TaskDto>> getAllCompletedTasks() {
        List<TaskDto> completedTasks = taskService.getAllCompletedTasks();
        return new ResponseEntity<>(completedTasks, HttpStatus.OK);
    }

    @GetMapping("/{title}")
    public ResponseEntity<Task> viewTaskByTitle(@PathVariable String title) {
        Task task = taskService.viewTaskByTitle(title);
        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update-task/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        Task updatedTask = taskService.updateTask(id, taskDto);
        if (updatedTask != null) {
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TaskDto>> filterTaskByStatus(@PathVariable TaskStatus status) {
        List<TaskDto> tasks = taskService.filterTaskByStatus(status);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/view-task/{id}")
    public ResponseEntity<Task> viewTaskById(@PathVariable Long id) {
        Optional<Task> task = taskService.viewTaskById(id);
        if (task.isPresent()) {
            return ResponseEntity.ok(task.get());
        } else {
            throw new TaskNotFoundException("Task with ID " + id + "does not exist!");
        }
    }
}