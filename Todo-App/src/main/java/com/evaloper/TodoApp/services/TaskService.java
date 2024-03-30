package com.evaloper.TodoApp.services;

import com.evaloper.TodoApp.entities.Task;
import com.evaloper.TodoApp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {
    final TaskRepository taskRepository = null;

    // Method to delete a task by its ID
    public default void deleteTaskById(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    void deleteTaskById(Task task);

    List<Task> getAllTasks();
}
