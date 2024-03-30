package com.evaloper.TodoApp.services.Impl;

import com.evaloper.TodoApp.dto.TaskDto;
import com.evaloper.TodoApp.entities.Task;
import com.evaloper.TodoApp.entities.User;
import com.evaloper.TodoApp.repository.TaskRepository;
import com.evaloper.TodoApp.repository.UserRepository;
import com.evaloper.TodoApp.services.TaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public Task viewTaskByTitle(String title) {
        Optional<Task> taskOptional = taskRepository.findByTitle(title);
        return taskOptional.orElse(null);
    }

@Override
    public Task updateTask(Long id, TaskDto updatedTask) {
   String email = SecurityContextHolder.getContext().getAuthentication().getName();
   Optional<User> userOptional = userRepository.findByEmail(email);
   if (userOptional.isPresent()){
       User user = userOptional.get();
       Optional<Task> taskOptional = taskRepository.findById(id);
       if (taskOptional.isPresent()) {
           Task existingTask = taskOptional.get();
           existingTask.setTitle(updatedTask.getTitle());
           existingTask.setDescription(updatedTask.getDescription());
           existingTask.setDateCreated(updatedTask.getDateCreated());
           existingTask.setTimeCreated(updatedTask.getTimeCreated());
           existingTask.setPriorityLevel(updatedTask.getPriorityLevel());
           existingTask.setTaskStatus(updatedTask.getTaskStatus());
           existingTask.setUserid(user);
           return taskRepository.save(existingTask);
       } else {
           return null;
       }
   }else {
       throw new UsernameNotFoundException("User not found");
   }

    }
}
