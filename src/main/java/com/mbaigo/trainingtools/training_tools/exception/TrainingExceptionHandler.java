package com.mbaigo.trainingtools.training_tools.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(TrainingExceptionHandler.class);

    @ExceptionHandler(TrainingApiException.class)
    public ResponseEntity<TrainingApiExceptionResponse> handleTrainingApiException(TrainingApiException ex, WebRequest request) {
        log.error("Handled TrainingApiException", ex);
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
        // Log the full stacktrace so we can inspect the root cause (StackOverflowError etc.)
        log.error("Unhandled exception caught by global handler", ex);
        TrainingApiExceptionResponse response = new TrainingApiExceptionResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Catch Throwable (includes Error) so we can log StackOverflowError and similar Errors
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<TrainingApiExceptionResponse> handleAllThrowables(Throwable t, WebRequest request) {
        log.error("Unhandled throwable caught by global handler", t);
        TrainingApiExceptionResponse response = new TrainingApiExceptionResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                t.getClass().getName() + ": " + t.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TokenRefreshException.class)
    public ResponseEntity<Map<String, Object>> handleTokenRefreshException(TokenRefreshException ex) {
        log.error("TokenRefreshException", ex);
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", Instant.now());
        body.put("status", HttpStatus.FORBIDDEN.value());
        body.put("error", "Refresh Token Error");
        body.put("message", ex.getMessage());
        body.put("refreshToken", ex.getRefreshToken());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
    }
}
