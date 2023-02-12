package ru.ibra.task_tracker.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.PackagePrivate;

@Setter
@Getter
@NoArgsConstructor
@PackagePrivate
public class TransferDTO {

    Long fromId;
    Long toId;
}
