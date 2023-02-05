package ru.ibra.task_tracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ibra.task_tracker.model.Task;
import ru.ibra.task_tracker.service.TaskService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("get/{id}")
    public Task getTask(@PathVariable(value = "id") Long id){
        return taskService.getOne(id);
    }

    @PostMapping("create-task")
    public Task createTask(@RequestBody Task task){
        return taskService.createTask(task);
    }

    @PutMapping("update-task")
    public Task updateTask(@RequestBody Task task){
        return taskService.createTask(task);
    }

    @DeleteMapping("delete-task/{id}")
    public Long deleteTask(@PathVariable Long id){
        return taskService.deleteTask(id);
    }
}
