package ru.ibra.task_tracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ibra.task_tracker.model.Customer;
import ru.ibra.task_tracker.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("get/{id}")
    public Customer getCustomer(@PathVariable(value = "id") Long id){
        return customerService.getOne(id);
    }

    @PostMapping("create-customer")
    public Customer createCustomer(@RequestBody Customer customer){
        return customerService.createCustomer(customer);
    }

    @PutMapping("update-customer")
    public Customer updateCustomer(@RequestBody Customer customer){
        return customerService.createCustomer(customer);
    }

    @DeleteMapping("delete-customer/{id}")
    public Long deleteCustomer(@PathVariable Long id){
        return customerService.deleteCustomer(id);
    }
}
