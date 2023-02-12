package ru.ibra.task_tracker.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ibra.task_tracker.model.entity.Category;
import ru.ibra.task_tracker.model.entity.Task;
import ru.ibra.task_tracker.model.constant.*;
import ru.ibra.task_tracker.model.exception.CategoryNotFoundException;
import ru.ibra.task_tracker.model.exception.CustomerNotFoundException;
import ru.ibra.task_tracker.repo.CategoryRepo;
import ru.ibra.task_tracker.repo.CustomerRepo;
import ru.ibra.task_tracker.repo.TaskRepo;
import ru.ibra.task_tracker.service.CategoryService;
import ru.ibra.task_tracker.util.MessageFormatUtil;

import java.util.Date;


@RequiredArgsConstructor
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final CustomerRepo customerRepo;
    private final TaskRepo taskRepo;



    @Override
    public Category getOne(Long id) {
        if (categoryRepo.findById(id).isEmpty()) {
            log.debug(String.format("При запросе категории не получилось найти категории по id = %s", id));
            return null;
        }
        return categoryRepo.findById(id).get();
    }

    @Override
    public Category createCategory(Category category) throws CustomerNotFoundException {
        if (category.getId() != null && categoryRepo.findById(category.getId()).isPresent()){
            log.debug(String.format("You cannot update full entity, id = %s", category.getId()));
            throw new IllegalStateException(String.format("You cannot update full entity, id = %s", category.getId()));
        }
        if (category.getCustomerCode() != null && customerRepo.findById(category.getCustomerCode()).isEmpty()) {
            log.debug(String.format("При сохранении/обновлении категории не получилось найти пользователя по id = %s", category.getCustomerCode()));
            throw new CustomerNotFoundException(MessageFormatUtil.notFound("Customer", category.getCustomerCode().toString()));
        }
        category.setCreateDate(new Date());
        category.setUpdateDate(new Date());
        if (category.getCustomerCode() != null ) {
            category.setCustomer(customerRepo.findById(category.getCustomerCode()).get());
        }
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

    @Override
    public Boolean changeCategoryStatus(Long id, Status status) throws CategoryNotFoundException {
        Category category;
        if (categoryRepo.findById(id).isEmpty()){
            log.debug(MessageFormatUtil.notFound("Category", id.toString()));
            throw new CategoryNotFoundException(MessageFormatUtil.notFound("Category", id.toString()));
        }
        category = categoryRepo.findById(id).get();
        category.setStatus(status);
        category.setUpdateDate(new Date());
        return true;
    }

    @Override
    public Boolean setCategoryPrior(Long id, Priority priority) throws CategoryNotFoundException {
        Category category;
        if (categoryRepo.findById(id).isEmpty()){
            log.debug(MessageFormatUtil.notFound("Category", id.toString()));
            throw new CategoryNotFoundException(MessageFormatUtil.notFound("Category", id.toString()));
        }
        category = categoryRepo.findById(id).get();
        category.setCategoryPriority(priority);
        category.setUpdateDate(new Date());
        return true;
    }

    @Override
    public Task setTaskToCategory(Task task, Long categoryId) {
        if (categoryRepo.findById(categoryId).isEmpty()) {
            log.debug(String.format("При запросе категории не получилось найти категории по id = %s", categoryId));
            return null;
        }
        Category category = categoryRepo.findById(categoryId).get();
        category.getTasks().add(task);
        task.setCategory(category);
        task.setCategoryCode(categoryId);
        task.setUpdateDate(new Date());
        category.setUpdateDate(new Date());
        taskRepo.save(task);
        categoryRepo.save(category);
        return task;
    }

    @Override
    public Task setTaskToCategory(Long taskId, Long categoryId) {
        if (taskRepo.findById(taskId).isEmpty()) {
            log.debug(String.format("При запросе категории не получилось найти таску по id = %s", taskId));
            return null;
        }
        Task task = taskRepo.findById(taskId).get();
        setTaskToCategory(task, categoryId);
        return task;
    }
}
