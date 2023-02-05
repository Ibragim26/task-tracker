package ru.ibra.task_tracker.service;

import org.springframework.stereotype.Service;
import ru.ibra.task_tracker.model.Category;

@Service
public interface CategoryService {

    Category getOne(Long id);
    Category createCategory(Category category);
    Long deleteCategory(Long id);

}
