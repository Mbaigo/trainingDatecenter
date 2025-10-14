package com.mbaigo.trainingtools.training_tools.auth.controller;

import com.mbaigo.trainingtools.training_tools.auth.dto.*;
import com.mbaigo.trainingtools.training_tools.auth.services.AuthService;
import com.mbaigo.trainingtools.training_tools.config.IpUtils;
import com.mbaigo.trainingtools.training_tools.security.UserAgentUtils;
import com.mbaigo.trainingtools.training_tools.user.dto.ProfilRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/training/api/auth") @AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerStep1(@Valid @RequestBody RegisterFirstRequest request) {
        return ResponseEntity.status(201).body(authService.register(request));
    }

    @PutMapping("/users/{id}/details")
    public ResponseEntity<?> updateDetails(@PathVariable Long id, @Valid @RequestBody UpdateUserDetailsRequest request) {
        return ResponseEntity.ok(authService.updateDetails(id, request));
    }

    @PostMapping("/users/{id}/profil")
    public ResponseEntity<?> createProfil(@PathVariable Long id, @Valid @RequestBody ProfilRequest request) {
        return ResponseEntity.status(201).body(authService.createProfile(id, request));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest request, HttpServletRequest servletRequest) {
        String ip = IpUtils.getClientIp(servletRequest);
        String device = UserAgentUtils.getDeviceInfo(servletRequest);
        JwtResponse res = authService.login(request, ip, device);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<JwtResponse> refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
        JwtResponse res = authService.refreshToken(request.getRefreshToken());
        return ResponseEntity.ok(res);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestParam Long userId) {
        authService.logout(userId);
        return ResponseEntity.noContent().build();
    }
}


