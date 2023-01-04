package com.inspien.backupserver.controller;

import com.inspien.backupserver.exception.CustomException;
import com.inspien.backupserver.exception.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.inspien.backupserver")
public class ErrorController {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handle(CustomException e) {
        return ErrorResponse.toResponseEntity(e);

    }
}
