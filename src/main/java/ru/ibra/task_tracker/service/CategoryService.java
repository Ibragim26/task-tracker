package ru.ibra.task_tracker.service;

import org.springframework.stereotype.Service;
import ru.ibra.task_tracker.model.entity.Category;
import ru.ibra.task_tracker.model.entity.Task;
import ru.ibra.task_tracker.model.exception.CategoryNotFoundException;
import ru.ibra.task_tracker.model.constant.Priority;
import ru.ibra.task_tracker.model.constant.Status;
import ru.ibra.task_tracker.model.exception.CustomerNotFoundException;

@Service
public interface CategoryService {

    Category getOne(Long id);
    Category createCategory(Category category) throws CustomerNotFoundException;
    Long deleteCategory(Long id);
    Boolean changeCategoryStatus(Long id, Status status) throws CategoryNotFoundException;
    Boolean setCategoryPrior(Long id, Priority priority) throws CategoryNotFoundException;
    Task setTaskToCategory(Task task, Long categoryId);
    Task setTaskToCategory(Long taskId, Long categoryId);
}
