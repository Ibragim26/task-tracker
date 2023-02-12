package ru.ibra.task_tracker.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.ibra.task_tracker.model.constant.Priority;
import ru.ibra.task_tracker.model.constant.Status;
import ru.ibra.task_tracker.model.dto.TransferDTO;
import ru.ibra.task_tracker.model.entity.Category;
import ru.ibra.task_tracker.model.entity.Customer;
import ru.ibra.task_tracker.model.entity.Task;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
class CustomerControllerTest {

    Customer customer;

    @Autowired
    private CustomerController customerController;

    @BeforeEach
    public void init(){
        customer = Customer.builder()
                .id(1L)
                .username("TEST_USERNAME")
                .email("TEST_EMAIL")
                .password("TEST_PASSWORD")
                .build();
    }

    @Test
    void createCustomer() {
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "SUCCESS");
        assertEquals(customerController.createCustomer(customer).get("status"), map.get("status"));
    }

    @Test
    void getCustomer() {
        assertNotNull(customerController.getCustomer(1L));
    }

    @Test
    void deleteCustomer() {
    }

    @Test
    void setTaskToCategory() {
        TransferDTO dto = new TransferDTO();
        dto.setFromId(1L);
        dto.setToId(1L);
        assertEquals(customerController.setCategoryToCustomer(dto).get("status"), "SUCCESS");
    }
}