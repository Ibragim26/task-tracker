package ru.ibra.task_tracker.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ibra.task_tracker.model.entity.Task;
import ru.ibra.task_tracker.model.constant.Priority;
import ru.ibra.task_tracker.model.constant.Status;
import ru.ibra.task_tracker.model.exception.CategoryNotFoundException;
import ru.ibra.task_tracker.model.exception.CustomerNotFoundException;
import ru.ibra.task_tracker.model.exception.TaskNotFoundException;
import ru.ibra.task_tracker.repo.CategoryRepo;
import ru.ibra.task_tracker.repo.CustomerRepo;
import ru.ibra.task_tracker.repo.TaskRepo;
import ru.ibra.task_tracker.service.TaskService;
import ru.ibra.task_tracker.util.MessageFormatUtil;

import java.util.Date;

@RequiredArgsConstructor
@Service
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepo taskRepo;
    private final CategoryRepo categoryRepo;
    private final CustomerRepo customerRepo;



    @Override
    public Task getOne(Long id) {
        if (taskRepo.findById(id).isEmpty()) {
            log.debug(String.format("При запросе таски не получилось найти таски по id = %s", id));
            return null;
        }
        return taskRepo.getReferenceById(id);
    }

    @Override
    public Task createTask(Task task) throws IllegalStateException, CategoryNotFoundException, CustomerNotFoundException {
        if (task.getId() != null && taskRepo.findById(task.getId()).isPresent()){
            log.debug(String.format("You cannot update full entity, id = %s", task.getId()));
            throw new IllegalStateException(String.format("You cannot update full entity, id = %s", task.getId()));
        }
        if (task.getCategoryCode() != null && categoryRepo.findById(task.getCategoryCode()).isEmpty()) {
            log.debug(String.format("При сохранении/обновлении таски не получилось найти категории по id = %s", task.getCategoryCode()));
            throw new CategoryNotFoundException(MessageFormatUtil.notFound("Category", task.getCategoryCode().toString()));
        }
        if (task.getCustomerCode() != null && customerRepo.findById(task.getCustomerCode()).isEmpty()) {
            log.debug(String.format("При сохранении/обновлении таски не получилось найти пользователя по id = %s", task.getCustomerCode()));
            throw new CustomerNotFoundException(MessageFormatUtil.notFound("Customer", task.getCustomerCode().toString()));
        }
        task.setCreateDate(new Date());
        task.setUpdateDate(new Date());
        task.setCategoryCode(task.getCategoryCode());
        task.setCustomerCode(task.getCustomerCode());
        if (task.getCategoryCode() != null)
            task.setCategory(categoryRepo.findById(task.getCategoryCode()).get());
        if (task.getCustomerCode() != null)
            task.setCustomer(customerRepo.findById(task.getCustomerCode()).get());
        return taskRepo.save(task);
    }

    @Override
    public Long deleteTask(Long id) {
        if (taskRepo.findById(id).isEmpty()) {
            log.debug(String.format("При удалении таски не получилось найти таски по id = %s", id));
            return null;
        }
        taskRepo.delete(taskRepo.findById(id).get());
        return id;
    }

    @Override
    public Boolean changeTaskStatus(Long id, Status status) throws TaskNotFoundException {
        Task task;
        if (taskRepo.findById(id).isEmpty()){
            log.debug(MessageFormatUtil.notFound("Task", id.toString()));
            throw new TaskNotFoundException(MessageFormatUtil.notFound("Task", id.toString()));
        }
        task = taskRepo.findById(id).get();
        task.setStatus(status);
        task.setUpdateDate(new Date());
        return true;
    }

    @Override
    public Boolean setTaskPrior(Long id, Priority priority) throws TaskNotFoundException {
        Task task;
        if (taskRepo.findById(id).isEmpty()){
            log.debug(MessageFormatUtil.notFound("Task", id.toString()));
            throw new TaskNotFoundException(MessageFormatUtil.notFound("Task", id.toString()));
        }
        task = taskRepo.findById(id).get();
        task.setTaskPriority(priority);
        task.setUpdateDate(new Date());
        return true;
    }
}
