package com.mangakousei.mangakousei_backend.controller;

import com.mangakousei.mangakousei_backend.dto.request.ActivityHistoryRequest;
import com.mangakousei.mangakousei_backend.dto.response.ActivityHistoryResponse;
import com.mangakousei.mangakousei_backend.service.ActivityHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class ActivityHistoryController {

    private final ActivityHistoryService activityHistoryService;


    @GetMapping
    public ResponseEntity<List<ActivityHistoryResponse>> getAllHistory() {
        List<ActivityHistoryResponse> historyList = activityHistoryService.getAllHistory();
        return ResponseEntity.ok(historyList);
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ActivityHistoryResponse>> getHistoryByUserId(@PathVariable Long userId) {
        List<ActivityHistoryResponse> historyList = activityHistoryService.getHistoryByUserId(userId);
        return ResponseEntity.ok(historyList);
    }


    @PostMapping
    public ResponseEntity<ActivityHistoryResponse> createActivity(@RequestBody ActivityHistoryRequest request) {
        ActivityHistoryResponse savedActivity = activityHistoryService.saveActivity(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedActivity);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistory(@PathVariable Long id) {
        activityHistoryService.deleteHistory(id);
        return ResponseEntity.noContent().build();
    }
}