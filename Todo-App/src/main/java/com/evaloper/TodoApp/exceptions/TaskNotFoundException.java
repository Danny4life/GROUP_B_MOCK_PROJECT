package com.evaloper.TodoApp.exceptions;

public class TaskNotFoundException extends RuntimeException{

    public TaskNotFoundException(String message) {
        super(message);
    }
}
