package com.project.resourcivo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username:noreply@resourcivo.com}")
    private String fromEmail;

    @Value("${app.base-url:http://localhost:8080}")
    private String baseUrl;

    /**
     * Send verification email
     */
    public void sendVerificationEmail(String toEmail, String token) {
        String subject = "Email Verification - Resourcivo";
        String verificationUrl = baseUrl + "/api/auth/verify?token=" + token;

        String message = "Dear User,\n\n" +
                "Thank you for registering with Resourcivo!\n\n" +
                "Please click the link below to verify your email address:\n" +
                verificationUrl + "\n\n" +
                "This link will expire in 24 hours.\n\n" +
                "If you did not create an account, please ignore this email.\n\n" +
                "Best regards,\n" +
                "Resourcivo Team";

        sendEmail(toEmail, subject, message);
    }

    /**
     * Send password reset email
     */
    public void sendPasswordResetEmail(String toEmail, String token) {
        String subject = "Password Reset Request - Resourcivo";
        String resetUrl = baseUrl + "/api/auth/reset-password?token=" + token;

        String message = "Dear User,\n\n" +
                "We received a request to reset your password.\n\n" +
                "Please click the link below to reset your password:\n" +
                resetUrl + "\n\n" +
                "This link will expire in 1 hour.\n\n" +
                "If you did not request a password reset, please ignore this email.\n\n" +
                "Best regards,\n" +
                "Resourcivo Team";

        sendEmail(toEmail, subject, message);
    }

    /**
     * Send generic email
     */
    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
        System.out.println("Email sent successfully to: " + to);
    }
}
