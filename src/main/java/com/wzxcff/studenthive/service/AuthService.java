package com.wzxcff.studenthive.service;

import com.wzxcff.studenthive.security.JwtUtil;
import com.wzxcff.studenthive.dto.request.AuthRequest;
import com.wzxcff.studenthive.dto.request.RegisterRequest;
import com.wzxcff.studenthive.dto.response.AuthResponse;
import com.wzxcff.studenthive.model.entity.User;
import com.wzxcff.studenthive.model.enums.Role;
import com.wzxcff.studenthive.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .role(Role.STUDENT)
                .build();

        userRepository.save(user);


        var jwtToken = jwtUtil.generateToken(new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPasswordHash(),
                java.util.Collections.emptyList()
        ));

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse auth(AuthRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        var jwtToken = jwtUtil.generateToken(new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPasswordHash(),
                java.util.Collections.emptyList()
        ));

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

}
