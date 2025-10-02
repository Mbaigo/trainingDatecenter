package com.mbaigo.trainingTools.training_tools.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TrainingApiExceptionResponse {
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private String details;
}
