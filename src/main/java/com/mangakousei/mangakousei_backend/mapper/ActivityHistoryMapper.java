package com.mangakousei.mangakousei_backend.mapper;

import com.mangakousei.mangakousei_backend.dto.request.ActivityHistoryRequest;
import com.mangakousei.mangakousei_backend.dto.response.ActivityHistoryResponse;
import com.mangakousei.mangakousei_backend.entity.system.ActivityHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ActivityHistoryMapper {


    ActivityHistoryResponse toResponse(ActivityHistory entity);


    @Mapping(target = "id", ignore = true) // ID do Database tự sinh (AUTO_INCREMENT)
    @Mapping(target = "timestamp", ignore = true) // Timestamp do @PrePersist tự sinh
    ActivityHistory toEntity(ActivityHistoryRequest request);
}