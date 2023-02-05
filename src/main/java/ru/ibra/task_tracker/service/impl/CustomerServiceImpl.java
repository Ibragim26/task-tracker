package ru.ibra.task_tracker.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.ibra.task_tracker.model.Customer;
import ru.ibra.task_tracker.repo.CustomerRepo;
import ru.ibra.task_tracker.service.CustomerService;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;

    @Override
    public Customer getOne(Long id) {
        if (customerRepo.findById(id).isEmpty()) {
            log.debug(String.format("При запросе пользователя не получилось найти пользователя по id = %s", id));
            return null;
        }
        return customerRepo.findById(id).get();
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public Long deleteCustomer(Long id) {
        if (customerRepo.findById(id).isEmpty()) {
            log.debug(String.format("При удалении пользователя не получилось найти пользователя по id = %s", id));
            return null;
        }
        customerRepo.delete(customerRepo.findById(id).get());
        return id;
    }
}
