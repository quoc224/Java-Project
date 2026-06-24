package com.mangakousei.mangakousei_backend.dto.request;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityHistoryRequest {

    @NotNull(message = "ID người dùng không được để trống")
    private Long userId;

    @NotBlank(message = "Loại hành động không được để trống")
    private String actionType;


    private String details;
}