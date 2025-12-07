package com.project.resourcivo.service;

import com.project.resourcivo.dto.ForgotPasswordRequest;
import com.project.resourcivo.dto.ResetPasswordRequest;
import com.project.resourcivo.model.PasswordResetToken;
import com.project.resourcivo.model.User;
import com.project.resourcivo.repository.PasswordResetTokenRepository;
import com.project.resourcivo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Mock
    private EmailService emailService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setUsername("testuser");
        user.setPassword("encodedOldPassword");
    }

    @Test
    void forgotPassword_ShouldGenerateTokenAndSendEmail() {
        // Arrange
        ForgotPasswordRequest request = new ForgotPasswordRequest("test@example.com");
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(passwordResetTokenRepository.findByUser(user)).thenReturn(Optional.empty());

        // Act
        String result = userService.forgotPassword(request);

        // Assert
        assertEquals("Password reset email sent. Please check your email.", result);

        ArgumentCaptor<PasswordResetToken> tokenCaptor = ArgumentCaptor.forClass(PasswordResetToken.class);
        verify(passwordResetTokenRepository).save(tokenCaptor.capture());
        PasswordResetToken savedToken = tokenCaptor.getValue();

        assertNotNull(savedToken.getToken());
        assertEquals(user, savedToken.getUser());
        assertFalse(savedToken.isUsed());
        assertTrue(savedToken.getExpiryDate().isAfter(LocalDateTime.now()));

        verify(emailService).sendPasswordResetEmail(eq("test@example.com"), eq(savedToken.getToken()));
    }

    @Test
    void resetPassword_ShouldUpdatePasswordAndMarkTokenAsUsed() {
        // Arrange
        String tokenString = "valid-token";
        String newPassword = "newPassword123";
        ResetPasswordRequest request = new ResetPasswordRequest(tokenString, newPassword);

        PasswordResetToken resetToken = new PasswordResetToken(tokenString, user);
        // Ensure token is not expired (default constructor sets expiry to +1 hour)

        when(passwordResetTokenRepository.findByToken(tokenString)).thenReturn(Optional.of(resetToken));
        when(passwordEncoder.encode(newPassword)).thenReturn("encodedNewPassword");

        // Act
        String result = userService.resetPassword(request);

        // Assert
        assertEquals("Password reset successfully. You can now login with your new password.", result);

        verify(userRepository).save(user);
        assertEquals("encodedNewPassword", user.getPassword());

        verify(passwordResetTokenRepository).save(resetToken);
        assertTrue(resetToken.isUsed());
    }

    @Test
    void resetPassword_ShouldThrowException_WhenTokenExpired() {
        // Arrange
        String tokenString = "expired-token";
        ResetPasswordRequest request = new ResetPasswordRequest(tokenString, "newPass");

        PasswordResetToken resetToken = new PasswordResetToken(tokenString, user);
        resetToken.setExpiryDate(LocalDateTime.now().minusHours(1)); // Expired

        when(passwordResetTokenRepository.findByToken(tokenString)).thenReturn(Optional.of(resetToken));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> userService.resetPassword(request));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void resetPassword_ShouldThrowException_WhenTokenAlreadyUsed() {
        // Arrange
        String tokenString = "used-token";
        ResetPasswordRequest request = new ResetPasswordRequest(tokenString, "newPass");

        PasswordResetToken resetToken = new PasswordResetToken(tokenString, user);
        resetToken.setUsed(true);

        when(passwordResetTokenRepository.findByToken(tokenString)).thenReturn(Optional.of(resetToken));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> userService.resetPassword(request));
        verify(userRepository, never()).save(any(User.class));
    }
}
