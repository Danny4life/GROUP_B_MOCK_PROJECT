package com.evaloper.TodoApp.services;


import com.evaloper.TodoApp.dto.TaskDto;
import com.evaloper.TodoApp.entities.Task;

import java.util.List;
//TaskService
//i. CreateTask
//ii. ViewTaskDetailsByTitle
//iii. ViewTaskByID
//iv. ViewAllTask
//v. EditTask
//vi. DeleteTask
//vii. FilterTaskByStatus
//viii. ViewCompletedTask(View list of completed task)
public interface TaskService {

    Task updateTask(Long id, TaskDto taskDto);

    Task viewTaskByTitle(String title);

}
