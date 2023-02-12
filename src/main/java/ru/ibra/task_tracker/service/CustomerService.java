package ru.ibra.task_tracker.service;

import org.springframework.stereotype.Service;
import ru.ibra.task_tracker.model.entity.Category;
import ru.ibra.task_tracker.model.entity.Customer;
import ru.ibra.task_tracker.model.exception.CategoryNotFoundException;
import ru.ibra.task_tracker.model.exception.CustomerNotFoundException;
import ru.ibra.task_tracker.model.exception.TaskNotFoundException;

@Service
public interface CustomerService {

    Customer getOne(Long id) throws CategoryNotFoundException;
    Customer createCustomer(Customer customer);
    Long deleteCustomer(Long id) throws CategoryNotFoundException;
    Category setCategoryToCustomer(Long categoryId, Long id) throws TaskNotFoundException, CategoryNotFoundException, CustomerNotFoundException;
    Category setCategoryToCustomer(Category category, Customer id) throws CategoryNotFoundException, CustomerNotFoundException;

}
