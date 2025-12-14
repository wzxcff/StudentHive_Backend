package com.wzxcff.studenthive.controller;


import com.wzxcff.studenthive.dto.request.AuthRequest;
import com.wzxcff.studenthive.dto.request.RegisterRequest;
import com.wzxcff.studenthive.dto.response.AuthResponse;
import com.wzxcff.studenthive.service.AuthService;
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

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> auth(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.auth(authRequest));
    }
}
