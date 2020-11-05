package com.thoughtworks.capacity.gtb.mvc.api;


import com.thoughtworks.capacity.gtb.mvc.dto.ErrorMessage;
import com.thoughtworks.capacity.gtb.mvc.exception.UserExistedException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> exceptionHandler(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult().getFieldError().getDefaultMessage();
        ErrorMessage error = new ErrorMessage(message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(UserExistedException.class)
    public ResponseEntity<ErrorMessage> exceptionHandler(UserExistedException exception) {
        String message = "用户已存在";
        ErrorMessage error = new ErrorMessage(message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
