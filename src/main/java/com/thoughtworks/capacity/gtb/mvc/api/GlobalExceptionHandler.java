package com.thoughtworks.capacity.gtb.mvc.api;

import com.thoughtworks.capacity.gtb.mvc.dto.ErrorResponse;
import com.thoughtworks.capacity.gtb.mvc.exception.UserLoginFailedException;
import com.thoughtworks.capacity.gtb.mvc.exception.UserRegisterExistedException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    static Integer codeBadRequest = HttpStatus.BAD_REQUEST.value();

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult().getFieldError().getDefaultMessage();
        ErrorResponse error = new ErrorResponse(codeBadRequest, message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(ConstraintViolationException exception) {
        String message = exception.getMessage();
        String messageContent = message.split(": ", 2)[1];
        ErrorResponse error = new ErrorResponse(codeBadRequest, messageContent);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(UserRegisterExistedException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(UserRegisterExistedException exception) {
        String message = "用户已存在";
        ErrorResponse error = new ErrorResponse(codeBadRequest, message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(UserLoginFailedException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(UserLoginFailedException exception) {
        String message = "用户名或密码错误";
        ErrorResponse error = new ErrorResponse(codeBadRequest, message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
