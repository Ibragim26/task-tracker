package ru.ibra.task_tracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ibra.task_tracker.model.entity.Category;
import ru.ibra.task_tracker.model.dto.ChangePriorityDTO;
import ru.ibra.task_tracker.model.dto.ChangeStatusDTO;
import ru.ibra.task_tracker.model.dto.TransferDTO;
import ru.ibra.task_tracker.service.CategoryService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("get/{id}")
    public Category getCategory(@PathVariable(value = "id") Long id){
        return categoryService.getOne(id);
    }

    @PostMapping("create-category")
    public Map<String, String> createCategory(@RequestBody Category category){
        try {
            categoryService.createCategory(category);
            Map<String, String> res = new HashMap<>();
            res.put("status", "SUCCESS");
            return res;
        } catch (Exception e) {
            Map<String, String> res = new HashMap<>();
            res.put("status", "ERROR");
            return res;
        }
    }

    @DeleteMapping("delete-category/{id}")
    public Long deleteCategory(@PathVariable Long id){
        return categoryService.deleteCategory(id);
    }

    @PostMapping("change-status")
    public Map<String, String> changeCategoryStatus(@RequestBody ChangeStatusDTO dto){
        try {
            categoryService.changeCategoryStatus(dto.getId(), dto.getStatus());
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
    public Map<String, String> changeCategoryPriority(@RequestBody ChangePriorityDTO dto){
        try {
            categoryService.setCategoryPrior(dto.getId(), dto.getPriority());
            Map<String, String> res = new HashMap<>();
            res.put("status", "SUCCESS");
            return res;
        } catch (Exception e) {
            Map<String, String> res = new HashMap<>();
            res.put("status", "ERROR");
            return res;
        }
    }

    @PostMapping("set-task")
    public Map<String, String> setTaskToCategory(@RequestBody TransferDTO dto){
        try {
            categoryService.setTaskToCategory(dto.getFromId(), dto.getToId());
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
