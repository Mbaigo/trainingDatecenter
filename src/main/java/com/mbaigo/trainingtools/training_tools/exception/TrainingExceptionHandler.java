package com.mbaigo.trainingtools.training_tools.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

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
}
