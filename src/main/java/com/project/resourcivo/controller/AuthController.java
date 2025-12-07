package com.project.resourcivo.controller;

import com.project.resourcivo.dto.*;
import com.project.resourcivo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "APIs for user authentication and authorization")
public class AuthController {

    @Autowired
    private UserService userService;

    /**
     * Register new user
     * POST /api/auth/register
     */
    @PostMapping("/register")
    @Operation(summary = "Register user", description = "Register a new user account")
    @ApiResponse(responseCode = "201", description = "User registered successfully")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest request) {
        String message = userService.registerUser(request);
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Login user
     * POST /api/auth/login
     */
    @PostMapping("/login")
    @Operation(summary = "Login user", description = "Authenticate user and return JWT token")
    @ApiResponse(responseCode = "200", description = "Login successful")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = userService.loginUser(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Verify email
     * GET /api/auth/verify?token=xxx
     */
    @GetMapping("/verify")
    @Operation(summary = "Verify email", description = "Verify user email with token")
    @ApiResponse(responseCode = "200", description = "Email verified successfully")
    public ResponseEntity<?> verifyEmail(@RequestParam String token) {
        String message = userService.verifyEmail(token);
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return ResponseEntity.ok(response);
    }

    /**
     * Request password reset
     * POST /api/auth/forgot-password
     */
    @PostMapping("/forgot-password")
    @Operation(summary = "Request password reset", description = "Request a password reset email")
    @ApiResponse(responseCode = "200", description = "Password reset email sent")
    public ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {
        String message = userService.forgotPassword(request);
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return ResponseEntity.ok(response);
    }

    /**
     * Reset password
     * POST /api/auth/reset-password
     */
    @PostMapping("/reset-password")
    @Operation(summary = "Reset password", description = "Reset user password with token")
    @ApiResponse(responseCode = "200", description = "Password reset successfully")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
        String message = userService.resetPassword(request);
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return ResponseEntity.ok(response);
    }

    /**
     * Get current user info
     * GET /api/auth/me
     */
    @GetMapping("/me")
    @Operation(summary = "Get current user", description = "Get current authenticated user information")
    @ApiResponse(responseCode = "200", description = "User information retrieved successfully")
    public ResponseEntity<?> getCurrentUser() {
        var user = userService.getCurrentUser();
        Map<String, Object> response = new HashMap<>();
        response.put("id", user.getId());
        response.put("username", user.getUsername());
        response.put("email", user.getEmail());
        response.put("roles", user.getRoles().stream()
                .map(role -> role.getName().name())
                .toList());
        return ResponseEntity.ok(response);
    }
}
