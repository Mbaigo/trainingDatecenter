package com.mbaigo.trainingtools.training_tools.controller.model;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
}