package ru.ibra.task_tracker.service;

import org.springframework.stereotype.Service;
import ru.ibra.task_tracker.model.entity.Task;
import ru.ibra.task_tracker.model.constant.Priority;
import ru.ibra.task_tracker.model.constant.Status;
import ru.ibra.task_tracker.model.exception.CategoryNotFoundException;
import ru.ibra.task_tracker.model.exception.CustomerNotFoundException;
import ru.ibra.task_tracker.model.exception.TaskNotFoundException;

@Service
public interface TaskService {

    Task getOne(Long id);
    Task createTask(Task task) throws IllegalStateException, CategoryNotFoundException, CustomerNotFoundException;
    Long deleteTask(Long id);
    Boolean changeTaskStatus(Long id, Status status) throws TaskNotFoundException;
    Boolean setTaskPrior(Long id, Priority priority) throws TaskNotFoundException;

}
