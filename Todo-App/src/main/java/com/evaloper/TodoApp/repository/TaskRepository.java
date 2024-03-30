package com.evaloper.TodoApp.repository;

import com.evaloper.TodoApp.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
//    void deleteTaskById(Task task);
//
//    List<Task> getAllTasks();


}
