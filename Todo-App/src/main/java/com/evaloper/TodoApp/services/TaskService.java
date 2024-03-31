package com.evaloper.TodoApp.services;

import com.evaloper.TodoApp.dto.TaskDto;
import com.evaloper.TodoApp.entities.Task;
import com.evaloper.TodoApp.entities.User;
import com.evaloper.TodoApp.enums.TaskStatus;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    TaskDto createTask(Long id, TaskDto taskDto);
    public List<TaskDto> getAllCompletedTasks();
    Task updateTask(Long id, TaskDto taskDto);

    Task viewTaskByTitle(String title);

    public List<TaskDto> filterTaskByStatus(TaskStatus taskStatus);

    Optional<Task> viewTaskById (Long id);

    boolean deleteTask(Long id);

    List<TaskDto> findAllTaskByUserId(User userId);





}
