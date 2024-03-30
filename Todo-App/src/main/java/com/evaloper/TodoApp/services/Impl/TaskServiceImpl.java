package com.evaloper.TodoApp.services.Impl;

import com.evaloper.TodoApp.entities.Task;
import com.evaloper.TodoApp.repository.TaskRepository;
import com.evaloper.TodoApp.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    @Override
    public void deleteTaskById(Task task) {

    }

    @Override
    public List<Task> getAllTasks() {
        return null;
    }
}
