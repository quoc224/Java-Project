package com.mangakousei.mangakousei_backend.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CreateTaskReq {
    @NotNull
    private Long regionId;

    @NotNull
    private Long taskTypeId;

    @NotNull
    private Long assignedTo;

    @NotNull @Future
    private LocalDateTime deadline;

    private String description;
}