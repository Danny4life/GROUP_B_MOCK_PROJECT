package com.evaloper.TodoApp.services.Impl;

import com.evaloper.TodoApp.dto.TaskDto;
import com.evaloper.TodoApp.entities.Task;
import com.evaloper.TodoApp.entities.User;
import com.evaloper.TodoApp.enums.TaskStatus;
import com.evaloper.TodoApp.exceptions.TaskNotFoundException;
import com.evaloper.TodoApp.exceptions.UserNotFoundException;
import com.evaloper.TodoApp.repository.TaskRepository;
import com.evaloper.TodoApp.repository.UserRepository;
import com.evaloper.TodoApp.services.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public TaskDto createTask(Long id, TaskDto taskDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Task task = Task.builder()
                .title(taskDto.getTitle())
                .description(taskDto.getDescription())
                .dateCreated(taskDto.getDateCreated())
                .timeCreated(taskDto.getTimeCreated())
                .deadline(taskDto.getDeadline())
                .priorityLevel(taskDto.getPriorityLevel())
                .taskStatus(taskDto.getTaskStatus())
                .userId(user)
                .build();

        taskRepository.save(task);
        return taskDto;
    }

    @Override
    public List<TaskDto> getAllCompletedTasks() {
        List<Task> completedTasks = taskRepository.findByTaskStatus(TaskStatus.COMPLETED);
        return convertToTaskDtoList(completedTasks);
    }


    @Override
    public Task updateTask(Long id, TaskDto taskDto) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            Task existingTask = taskOptional.get();
            existingTask.setTitle(taskDto.getTitle());
            existingTask.setDescription(taskDto.getDescription());
            existingTask.setDateCreated(taskDto.getDateCreated());
            existingTask.setTimeCreated(taskDto.getTimeCreated());
            existingTask.setDeadline(taskDto.getDeadline());
            existingTask.setPriorityLevel(taskDto.getPriorityLevel());
            existingTask.setTaskStatus(taskDto.getTaskStatus());

            return taskRepository.save(existingTask);
        } else {
            return null;
        }
    }


    @Override
    public Task viewTaskByTitle(String title) {
        Optional<Task> taskOptional = taskRepository.findByTitle(title);
        return taskOptional.orElse(null);
    }

    @Override
    public List<TaskDto> filterTaskByStatus(TaskStatus taskStatus) {
        List<Task> filteredTask = taskRepository.findByTaskStatus(taskStatus);
        return convertToTaskDtoList(filteredTask);
    }

    @Override
    public Optional<Task> viewTaskById(Long id) {
        return taskRepository.findById(id);
    }


    @Override
    public boolean deleteTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(()-> new TaskNotFoundException("Task Not Found"));
        taskRepository.delete(task);
        return true;
    }

    @Override
    public List<TaskDto> findAllTaskByUserId(User userId) {
        List<Task> tasks = taskRepository.findByUserId(userId);

        return tasks.stream().map(this::convertToTaskDto).collect(Collectors.toList());
    }


    private List<TaskDto> convertToTaskDtoList(List<Task> tasks) {
        return tasks.stream()
                .map(this::convertToTaskDto)
                .collect(Collectors.toList());
    }

    private TaskDto convertToTaskDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        taskDto.setDateCreated(task.getDateCreated());
        taskDto.setTimeCreated(task.getTimeCreated());
        taskDto.setDeadline(task.getDeadline());
        taskDto.setPriorityLevel(task.getPriorityLevel());
        taskDto.setTaskStatus(task.getTaskStatus());

        return taskDto;
    }


}
