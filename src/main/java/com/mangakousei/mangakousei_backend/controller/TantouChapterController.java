package com.mangakousei.mangakousei_backend.controller;

import com.mangakousei.mangakousei_backend.dto.request.SetPageDeadlineReq;
import com.mangakousei.mangakousei_backend.dto.response.ApiResponse;
import com.mangakousei.mangakousei_backend.dto.response.ChapterRes;
import com.mangakousei.mangakousei_backend.dto.response.PageDeadlineRes;
import com.mangakousei.mangakousei_backend.exception.CustomAppException;
import com.mangakousei.mangakousei_backend.service.ChapterService;
import com.mangakousei.mangakousei_backend.util.SecurityUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tantou")
@RequiredArgsConstructor
public class TantouChapterController {

    private final ChapterService chapterService;

    @GetMapping("/series/{seriesId}/chapters")
    public ResponseEntity<?> getChapters(@PathVariable Long seriesId) {
        if (!SecurityUtils.isTantou()) {
            throw new CustomAppException("Không có quyền", HttpStatus.FORBIDDEN);
        }
        List<ChapterRes> chapters = chapterService.getChaptersBySeries(seriesId);
        return ResponseEntity.ok(ApiResponse.success("Fetched chapters", chapters));
    }

    @PostMapping("/chapters/{chapterId}/page-deadlines")
    public ResponseEntity<?> setDeadline(@PathVariable Long chapterId,
                                         @Valid @RequestBody SetPageDeadlineReq req) {
        if (!SecurityUtils.isTantou()) {
            throw new CustomAppException("Không có quyền", HttpStatus.FORBIDDEN);
        }
        PageDeadlineRes result = chapterService.setPageDeadline(chapterId, req);
        return ResponseEntity.ok(ApiResponse.success("Deadline đã được set", result));
    }

    @PutMapping("/page-deadlines/{deadlineId}")
    public ResponseEntity<?> updateDeadline(@PathVariable Long deadlineId,
                                            @Valid @RequestBody SetPageDeadlineReq req) {
        if (!SecurityUtils.isTantou()) {
            throw new CustomAppException("Không có quyền", HttpStatus.FORBIDDEN);
        }
        PageDeadlineRes result = chapterService.updatePageDeadline(deadlineId, req);
        return ResponseEntity.ok(ApiResponse.success("Deadline đã được cập nhật", result));
    }

    @DeleteMapping("/page-deadlines/{deadlineId}")
    public ResponseEntity<?> deleteDeadline(@PathVariable Long deadlineId) {
        if (!SecurityUtils.isTantou()) {
            throw new CustomAppException("Không có quyền", HttpStatus.FORBIDDEN);
        }
        chapterService.deletePageDeadline(deadlineId);
        return ResponseEntity.ok(ApiResponse.success("Deadline đã được xoá", null));
    }
}