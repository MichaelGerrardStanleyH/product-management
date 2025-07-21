package com.michael.product_management.exception;

import com.michael.product_management.dto.BaseResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new BaseResponse<>(
                        Boolean.FALSE,
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage()
                )
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new BaseResponse<>(
                        Boolean.FALSE,
                        HttpStatus.BAD_REQUEST.value(),
                        ex.getMessage()
                )
        );
    }




}
