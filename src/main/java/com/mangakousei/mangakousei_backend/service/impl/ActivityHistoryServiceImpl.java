package com.mangakousei.mangakousei_backend.service.impl;

import com.mangakousei.mangakousei_backend.dto.request.ActivityHistoryRequest;
import com.mangakousei.mangakousei_backend.dto.response.ActivityHistoryResponse;
import com.mangakousei.mangakousei_backend.entity.system.ActivityHistory;
import com.mangakousei.mangakousei_backend.exception.CustomAppException;
import com.mangakousei.mangakousei_backend.mapper.ActivityHistoryMapper;
import com.mangakousei.mangakousei_backend.repository.ActivityHistoryRepository;
import com.mangakousei.mangakousei_backend.service.ActivityHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityHistoryServiceImpl implements ActivityHistoryService {

    private final ActivityHistoryRepository activityHistoryRepository;
    private final ActivityHistoryMapper activityHistoryMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ActivityHistoryResponse> getAllHistory() {
        return activityHistoryRepository.findAll()
                .stream()
                .map(activityHistoryMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ActivityHistoryResponse> getHistoryByUserId(Long userId) {


        List<ActivityHistory> histories = activityHistoryRepository.findByUserId(userId);
        return histories.stream()
                .map(activityHistoryMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ActivityHistoryResponse saveActivity(ActivityHistoryRequest request) {

        ActivityHistory activityHistory = activityHistoryMapper.toEntity(request);


        ActivityHistory savedActivity = activityHistoryRepository.save(activityHistory);


        return activityHistoryMapper.toResponse(savedActivity);
    }

    @Override
    @Transactional
    public void deleteHistory(Long id) {

        if (!activityHistoryRepository.existsById(id)) {
            throw new CustomAppException("Không tìm thấy lịch sử hoạt động với ID: " + id, HttpStatus.NOT_FOUND);
        }
        activityHistoryRepository.deleteById(id);
    }
}