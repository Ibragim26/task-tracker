package ru.ibra.task_tracker.model.exception;

public class TaskNotFoundException extends Exception{

    public TaskNotFoundException(String message) {
        super(message);
    }

}
