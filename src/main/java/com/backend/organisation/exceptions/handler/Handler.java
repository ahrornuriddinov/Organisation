package com.backend.organisation.exceptions.handler;

import com.backend.organisation.entity.model.ErrorBaseResponse;
import com.backend.organisation.entity.model.ErrorResponse;
import com.backend.organisation.exceptions.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class Handler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorBaseResponse> handleException(CustomException exception){
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(new ErrorBaseResponse(List.of(new ErrorResponse(exception.getStatus(), exception.getErrorMassage()))));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorBaseResponse> handleException(MethodArgumentNotValidException exception){
        return ResponseEntity
                .status(400)
                .body(new ErrorBaseResponse(
                        exception.getFieldErrors().stream()
                                .map(e -> new ErrorResponse(5002, e.getField() + " " +e.getDefaultMessage())).collect(Collectors.toList())

                ));
    }
}

