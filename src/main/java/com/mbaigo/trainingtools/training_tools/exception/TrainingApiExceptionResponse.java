package com.mbaigo.trainingtools.training_tools.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
public class TrainingApiExceptionResponse {
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private String details;
}
