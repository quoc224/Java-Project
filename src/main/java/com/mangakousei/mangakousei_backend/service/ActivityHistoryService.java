package com.mangakousei.mangakousei_backend.service;

import com.mangakousei.mangakousei_backend.dto.request.ActivityHistoryRequest;
import com.mangakousei.mangakousei_backend.dto.response.ActivityHistoryResponse;
import java.util.List;

public interface ActivityHistoryService {

    List<ActivityHistoryResponse> getAllHistory();

    List<ActivityHistoryResponse> getHistoryByUserId(Long userId);

    ActivityHistoryResponse saveActivity(ActivityHistoryRequest request);

    void deleteHistory(Long id);
}