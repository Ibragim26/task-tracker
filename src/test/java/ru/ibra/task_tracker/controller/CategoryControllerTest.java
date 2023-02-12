package ru.ibra.task_tracker.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import ru.ibra.task_tracker.model.dto.ChangePriorityDTO;
import ru.ibra.task_tracker.model.dto.ChangeStatusDTO;
import ru.ibra.task_tracker.model.dto.TransferDTO;
import ru.ibra.task_tracker.model.entity.Category;
import ru.ibra.task_tracker.model.constant.Priority;
import ru.ibra.task_tracker.model.constant.Status;
import ru.ibra.task_tracker.model.entity.Customer;
import ru.ibra.task_tracker.model.entity.Task;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@AutoConfigureMockMvc
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
class CategoryControllerTest {

    Category category;

    @Autowired
    private CategoryController categoryController;

    @BeforeEach
    public void init(){

        category = Category.builder()
                .id(2L)
                .categoryName("TEST_CATEGORY_NAME")
                .description("TEST_TASK_DESCRIPTION")
                .plannedTimeInMinutes(50)
                .elapsedTimeInMinutes(40)
                .dueDate(new Date())
                .executionDate(new Date(12, Calendar.JANUARY, 2023))
                .status(Status.NOT_STARTED)
                .categoryPriority(Priority.LITTLE)
                .build();
    }

    @Test
    void createCategory() {
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "SUCCESS");
        assertEquals(categoryController.createCategory(category).get("status"), map.get("status"));
    }

    @Test
    void getCategory() {
        assertNotNull(categoryController.getCategory(1L));
    }

    @Test
    void deleteCategory() {
    }

    @Test
    void changeCategoryStatus() {
        ChangeStatusDTO dto = new ChangeStatusDTO();
        dto.setId(1L);
        dto.setStatus(Status.IN_PROGRESS);
        assertEquals(categoryController.changeCategoryStatus(dto).get("status"), "SUCCESS");
    }

    @Test
    void testChangeCategoryPriority() {
        ChangePriorityDTO dto = new ChangePriorityDTO();
        dto.setId(1L);
        dto.setPriority(Priority.MEDIUM);
        assertEquals(categoryController.changeCategoryPriority(dto).get("status"), "SUCCESS");
    }

    @Test
    void setTaskToCategory() {
        TransferDTO dto = new TransferDTO();
        dto.setFromId(1L);
        dto.setToId(1L);
        assertEquals(categoryController.setTaskToCategory(dto).get("status"), "SUCCESS");
    }
}