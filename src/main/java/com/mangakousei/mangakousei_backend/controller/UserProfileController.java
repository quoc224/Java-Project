package com.mangakousei.mangakousei_backend.controller;

import com.mangakousei.mangakousei_backend.dto.request.ChangePasswordReq;
import com.mangakousei.mangakousei_backend.dto.request.UpdateProfileReq;
import com.mangakousei.mangakousei_backend.dto.response.ApiResponse;
import com.mangakousei.mangakousei_backend.dto.response.UserInfoRes;
import com.mangakousei.mangakousei_backend.service.UserProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PatchMapping("/me")
    public ResponseEntity<ApiResponse<UserInfoRes>> updateMyProfile(
            @RequestBody @Valid UpdateProfileReq request
    ) {
        UserInfoRes profile = userProfileService.updateMyProfile(request);
        return ResponseEntity.ok(ApiResponse.success("Profile updated successfully", profile));
    }

    @PatchMapping("/me/password")
    public ResponseEntity<ApiResponse<Void>> changeMyPassword(
            @RequestBody @Valid ChangePasswordReq request
    ) {
        userProfileService.changeMyPassword(request);
        return ResponseEntity.ok(ApiResponse.success("Password changed successfully", null));
    }
}
