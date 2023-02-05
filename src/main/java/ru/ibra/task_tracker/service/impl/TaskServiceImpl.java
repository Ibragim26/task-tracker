package ru.ibra.task_tracker.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.ibra.task_tracker.model.Task;
import ru.ibra.task_tracker.repo.CategoryRepo;
import ru.ibra.task_tracker.repo.CustomerRepo;
import ru.ibra.task_tracker.repo.TaskRepo;
import ru.ibra.task_tracker.service.TaskService;

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
    public Task createTask(Task task) {
        if (categoryRepo.findById(task.getCategoryCode()).isEmpty()) {
            log.debug(String.format("При сохранении/обновлении таски не получилось найти категории по id = %s", task.getCategoryCode()));
            return null;
        }
        if (customerRepo.findById(task.getCustomerCode()).isEmpty()) {
            log.debug(String.format("При сохранении/обновлении таски не получилось найти пользователя по id = %s", task.getCustomerCode()));
            return null;
        }
        task.setCategory(categoryRepo.findById(task.getCategoryCode()).get());
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
}
