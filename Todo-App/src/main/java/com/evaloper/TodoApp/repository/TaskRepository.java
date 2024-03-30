package com.evaloper.TodoApp.repository;

import com.evaloper.TodoApp.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository <Task, Long>{

    Optional<Task> findByTitle(String title);
}
