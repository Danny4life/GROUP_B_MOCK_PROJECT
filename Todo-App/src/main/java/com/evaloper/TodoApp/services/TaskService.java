package com.evaloper.TodoApp.services;

import com.evaloper.TodoApp.dto.TaskDto;
import com.evaloper.TodoApp.entities.Task;

import java.util.List;

public interface TaskService {
    TaskDto createTask(Long id, TaskDto taskDto);
    public List<TaskDto> getAllCompletedTasks();
    Task updateTask(Long id, TaskDto taskDto);

    Task viewTaskByTitle(String title);

}
