package ru.ibra.task_tracker.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ibra.task_tracker.model.entity.Category;
import ru.ibra.task_tracker.model.entity.Customer;
import ru.ibra.task_tracker.model.exception.CategoryNotFoundException;
import ru.ibra.task_tracker.model.exception.CustomerNotFoundException;
import ru.ibra.task_tracker.repo.CategoryRepo;
import ru.ibra.task_tracker.repo.CustomerRepo;
import ru.ibra.task_tracker.service.CustomerService;
import ru.ibra.task_tracker.util.MessageFormatUtil;

import java.util.Date;


@RequiredArgsConstructor
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;
    private final CategoryRepo categoryRepo;

    @Override
    public Customer getOne(Long id) throws CategoryNotFoundException {
        if (customerRepo.findById(id).isEmpty()) {
            log.debug(String.format("При запросе пользователя не получилось найти пользователя по id = %s", id));
            throw new CategoryNotFoundException(MessageFormatUtil.notFound("Task", id.toString()));
        }
        return customerRepo.findById(id).get();
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public Long deleteCustomer(Long id) throws CategoryNotFoundException {
        if (customerRepo.findById(id).isEmpty()) {
            log.debug(String.format("При удалении пользователя не получилось найти пользователя по id = %s", id));
            throw new CategoryNotFoundException(MessageFormatUtil.notFound("Task", id.toString()));
        }
        customerRepo.delete(customerRepo.findById(id).get());
        return id;
    }

    @Override
    public Category setCategoryToCustomer(Category category, Customer customer) throws CategoryNotFoundException, CustomerNotFoundException {
        customer.getCategories().add(category);
        category.setUpdateDate(new Date());
        category.setCustomer(customer);
        category.setCustomerCode(customer.getId());
        customerRepo.save(customer);
        categoryRepo.save(category);
        return category;
    }

    @Override
    public Category setCategoryToCustomer(Long categoryId, Long customerId) throws CategoryNotFoundException, CustomerNotFoundException {
        Category category;
        if (categoryRepo.findById(categoryId).isEmpty()){
            log.debug(MessageFormatUtil.notFound("Category", categoryId.toString()));
            throw new CategoryNotFoundException(MessageFormatUtil.notFound("Task", categoryId.toString()));
        }
        Customer customer;
        if (customerRepo.findById(customerId).isEmpty()){
            log.debug(MessageFormatUtil.notFound("Category", categoryId.toString()));
            throw new CustomerNotFoundException(MessageFormatUtil.notFound("Customer", customerId.toString()));
        }
        category = categoryRepo.findById(categoryId).get();
        customer = customerRepo.findById(customerId).get();
        setCategoryToCustomer(category, customer);
        return null;
    }


}
