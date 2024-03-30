package com.evaloper.TodoApp.services;

import com.evaloper.TodoApp.dto.TaskDto;

import java.util.List;

public interface TaskService {
    TaskDto createTask(Long id, TaskDto taskDto);
    public List<TaskDto> getAllCompletedTasks();

}
