package ru.ibra.task_tracker.controller;

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
import ru.ibra.task_tracker.model.entity.Task;
import ru.ibra.task_tracker.model.constant.Priority;
import ru.ibra.task_tracker.model.constant.Status;
import ru.ibra.task_tracker.model.dto.ChangePriorityDTO;
import ru.ibra.task_tracker.model.dto.ChangeStatusDTO;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
class TaskControllerTest {

    Task task;

    @Autowired
    private TaskController taskController;

    @BeforeEach
    public void init(){

        task = Task.builder()
                .id(1L)
                .taskName("TEST_TASK_NAME")
                .description("TEST_TASK_DESCRIPTION")
                .plannedTimeInMinutes(50)
                .elapsedTimeInMinutes(40)
                .dueDate(new Date())
                .executionDate(new Date(12, Calendar.JANUARY, 2023))
                .status(Status.NOT_STARTED)
                .taskPriority(Priority.LITTLE)
                .build();
    }


    @Test
    void createTask() {
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "SUCCESS");
        assertEquals(taskController.createTask(task).get("status"), map.get("status"));
    }

    @Test
    void getTask() {
        assertNotNull(taskController.getTask(1L));
    }

    @Test
    void deleteTask() {
    }

    @Test
    void changeTaskStatus() {
        ChangeStatusDTO dto = new ChangeStatusDTO();
        dto.setId(1L);
        dto.setStatus(Status.IN_PROGRESS);
        assertEquals(taskController.changeTaskStatus(dto).get("status"), "SUCCESS");
    }

    @Test
    void changeTaskPriority() {
        ChangePriorityDTO dto = new ChangePriorityDTO();
        dto.setId(1L);
        dto.setPriority(Priority.MEDIUM);
        assertEquals(taskController.changeTaskPriority(dto).get("status"), "SUCCESS");
    }
}