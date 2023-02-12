package ru.ibra.task_tracker.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.PackagePrivate;
import ru.ibra.task_tracker.model.constant.Priority;
import ru.ibra.task_tracker.model.constant.Status;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@PackagePrivate
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(schema = "task_tracker", name = "task")
public class Task {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String taskName;

    @Column(name = "description")
    String description;

    @Column(name = "planned_time_in_minutes")
    Integer plannedTimeInMinutes;

    @Column(name = "elapsed_time_in_minutes")
    Integer elapsedTimeInMinutes;

    @Column(name = "due_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    Date dueDate;

    @Column(name = "execution_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    Date executionDate;

    @Column(name = "create_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    Date createDate;

    @Column(name = "update_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    Date updateDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    Status status;

    @Column(name = "prior_task")
    @Enumerated(EnumType.STRING)
    Priority taskPriority;

    @Column(name = "category_code")
    Long categoryCode;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    Category category;

    @Column(name = "customer_code")
    Long customerCode;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    Customer customer;

}




