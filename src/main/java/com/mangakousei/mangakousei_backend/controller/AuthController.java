package com.mangakousei.mangakousei_backend.controller;

import com.mangakousei.mangakousei_backend.dto.response.ApiResponse;
import com.mangakousei.mangakousei_backend.dto.request.LoginReq;
import com.mangakousei.mangakousei_backend.dto.response.LoginRes;
import com.mangakousei.mangakousei_backend.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginRes>> login(
            @RequestBody @Valid LoginReq request,
            HttpServletResponse response
    ) {
        LoginRes loginRes = authService.login(request, response);
        return ResponseEntity.ok(ApiResponse.success("Login successful", loginRes));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<LoginRes>> refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        LoginRes loginRes = authService.refreshToken(request, response);

        return ResponseEntity.ok(ApiResponse.success("generated refresh token successful", loginRes));
    }
}