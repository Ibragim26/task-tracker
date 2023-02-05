package ru.ibra.task_tracker.service;

import org.springframework.stereotype.Service;
import ru.ibra.task_tracker.model.Customer;

@Service
public interface CustomerService {

    Customer getOne(Long id);
    Customer createCustomer(Customer customer);
    Long deleteCustomer(Long id);

}
