package com.backend.organisation.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public class CustomException extends RuntimeException{
    private int statusCode;
    private int status;
    private String errorMassage;

}
