package ru.ibra.task_tracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ibra.task_tracker.model.entity.Customer;
import ru.ibra.task_tracker.model.dto.TransferDTO;
import ru.ibra.task_tracker.model.exception.CategoryNotFoundException;
import ru.ibra.task_tracker.service.CustomerService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("get/{id}")
    public Customer getCustomer(@PathVariable(value = "id") Long id) {
        try {
            return customerService.getOne(id);
        } catch (CategoryNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("create-customer")
    public Map<String, String> createCustomer(@RequestBody Customer customer){
        try {
            customerService.createCustomer(customer);
            Map<String, String> res = new HashMap<>();
            res.put("status", "SUCCESS");
            return res;
        } catch (Exception e) {
            Map<String, String> res = new HashMap<>();
            res.put("status", "ERROR");
            return res;
        }
    }

    @DeleteMapping("delete-customer/{id}")
    public Long deleteCustomer(@PathVariable Long id){
        try {
            return customerService.deleteCustomer(id);
        } catch (CategoryNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("set-category")
    public Map<String, String> setCategoryToCustomer(@RequestBody TransferDTO dto){
        try {
            customerService.setCategoryToCustomer(dto.getFromId(), dto.getToId());
            Map<String, String> res = new HashMap<>();
            res.put("status", "SUCCESS");
            return res;
        } catch (Exception e) {
            Map<String, String> res = new HashMap<>();
            res.put("status", "ERROR");
            return res;
        }
    }
}
