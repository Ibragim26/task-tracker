package ru.ibra.task_tracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ibra.task_tracker.model.Category;
import ru.ibra.task_tracker.service.CategoryService;

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
    public Category createCategory(@RequestBody Category Category){
        return categoryService.createCategory(Category);
    }

    @PutMapping("update-category")
    public Category updateCategory(@RequestBody Category Category){
        return categoryService.createCategory(Category);
    }

    @DeleteMapping("delete-category/{id}")
    public Long deleteCategory(@PathVariable Long id){
        return categoryService.deleteCategory(id);
    }
}
