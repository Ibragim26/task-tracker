package ru.ibra.task_tracker.service;

import org.springframework.stereotype.Service;
import ru.ibra.task_tracker.model.Task;

@Service
public interface TaskService {

    Task getOne(Long id);
    Task createTask(Task task);
    Long deleteTask(Long id);

}
