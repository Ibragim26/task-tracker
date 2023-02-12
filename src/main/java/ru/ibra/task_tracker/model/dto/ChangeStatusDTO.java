package ru.ibra.task_tracker.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.PackagePrivate;
import ru.ibra.task_tracker.model.constant.Status;

@Getter
@Setter
@NoArgsConstructor
@PackagePrivate
public class ChangeStatusDTO {

    Long id;
    Status status;
}
