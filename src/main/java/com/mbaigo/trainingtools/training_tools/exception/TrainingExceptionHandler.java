package com.mbaigo.trainingtools.training_tools.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class TrainingExceptionHandler {

    @ExceptionHandler(TrainingApiException.class)
    public ResponseEntity<TrainingApiExceptionResponse> handleTrainingApiException(TrainingApiException ex, WebRequest request) {
        TrainingApiExceptionResponse response = new TrainingApiExceptionResponse(
                LocalDateTime.now(),
                ex.getStatus(),
                ex.getMessage(),
                ex.getDetails() != null ? ex.getDetails() : request.getDescription(false)
        );
        return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getStatus()));
    }

    // Gestion d'autres exceptions globales si besoin
    @ExceptionHandler(Exception.class)
    public ResponseEntity<TrainingApiExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
        TrainingApiExceptionResponse response = new TrainingApiExceptionResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(TokenRefreshException.class)
    public ResponseEntity<Map<String, Object>> handleTokenRefreshException(TokenRefreshException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", Instant.now());
        body.put("status", HttpStatus.FORBIDDEN.value());
        body.put("error", "Refresh Token Error");
        body.put("message", ex.getMessage());
        body.put("refreshToken", ex.getRefreshToken());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
    }
}
