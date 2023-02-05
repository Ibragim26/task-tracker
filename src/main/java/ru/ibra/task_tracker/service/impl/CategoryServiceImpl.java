package ru.ibra.task_tracker.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ibra.task_tracker.model.Category;
import ru.ibra.task_tracker.repo.CategoryRepo;
import ru.ibra.task_tracker.repo.CustomerRepo;
import ru.ibra.task_tracker.service.CategoryService;


@RequiredArgsConstructor
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final CustomerRepo customerRepo;


    @Override
    public Category getOne(Long id) {
        if (categoryRepo.findById(id).isEmpty()) {
            log.debug(String.format("При запросе категории не получилось найти категории по id = %s", id));
            return null;
        }
        return categoryRepo.findById(id).get();
    }

    @Override
    public Category createCategory(Category category) {
        if (customerRepo.findById(category.getCustomerCode()).isEmpty()) {
            log.debug(String.format("При сохранении/обновлении категории не получилось найти пользователя по id = %s", category.getCustomerCode()));
            return null;
        }
        category.setCustomer(customerRepo.findById(category.getCustomerCode()).get());
        return categoryRepo.save(category);
    }

    @Override
    public Long deleteCategory(Long id) {
        if (categoryRepo.findById(id).isEmpty()) {
            log.debug(String.format("При удалении категории не получилось найти категории по id = %s", id));
            return null;
        }
        categoryRepo.delete(categoryRepo.findById(id).get());
        return id;
    }
}
