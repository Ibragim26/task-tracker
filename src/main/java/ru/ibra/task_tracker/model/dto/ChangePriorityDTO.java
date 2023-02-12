package ru.ibra.task_tracker.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.PackagePrivate;
import ru.ibra.task_tracker.model.constant.Priority;

@Getter
@Setter
@NoArgsConstructor
@PackagePrivate
public class ChangePriorityDTO {

    Long id;
    Priority priority;
}
