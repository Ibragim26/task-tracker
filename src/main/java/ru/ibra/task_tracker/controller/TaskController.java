package ru.ibra.task_tracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ibra.task_tracker.model.dto.ChangePriorityDTO;
import ru.ibra.task_tracker.model.entity.Task;
import ru.ibra.task_tracker.model.dto.ChangeStatusDTO;
import ru.ibra.task_tracker.service.TaskService;

import java.util.HashMap;
import java.util.Map;

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
    public Map<String, String> createTask(@RequestBody Task task){
        try {
            taskService.createTask(task);
            Map<String, String> res = new HashMap<>();
            res.put("status", "SUCCESS");
            return res;
        } catch (Exception e) {
            Map<String, String> res = new HashMap<>();
            res.put("status", "ERROR");
            return res;
        }
    }

    @DeleteMapping("delete-task/{id}")
    public Long deleteTask(@PathVariable Long id){
        return taskService.deleteTask(id);
    }

    @PostMapping("change-status")
    public Map<String, String> changeTaskStatus(@RequestBody ChangeStatusDTO dto){
        try {
            taskService.changeTaskStatus(dto.getId(), dto.getStatus());
            Map<String, String> res = new HashMap<>();
            res.put("status", "SUCCESS");
            return res;
        } catch (Exception e) {
            Map<String, String> res = new HashMap<>();
            res.put("status", "ERROR");
            return res;
        }
    }

    @PostMapping("change-priority")
    public Map<String, String> changeTaskPriority(@RequestBody ChangePriorityDTO dto){
        try {
            taskService.setTaskPrior(dto.getId(), dto.getPriority());
            Map<String, String> res = new HashMap<>();
            res.put("status", "SUCCESS");
            return res;
        } catch (Exception e) {
            Map<String, String> res = new HashMap<>();
            res.put("status", "ERROR");
            return res;
        }
    }
}
