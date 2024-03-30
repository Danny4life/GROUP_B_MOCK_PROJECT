package com.evaloper.TodoApp.repository;

import com.evaloper.TodoApp.entities.Task;
import com.evaloper.TodoApp.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByTaskStatus(TaskStatus taskStatus);

}
