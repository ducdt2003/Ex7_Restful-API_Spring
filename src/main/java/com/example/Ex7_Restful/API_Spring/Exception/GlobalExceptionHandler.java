package com.example.Ex7_Restful.API_Spring.Exception;

import com.example.Ex7_Restful.API_Spring.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidation(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(e ->
                errors.put(e.getField(), e.getDefaultMessage())
        );

        ApiResponse<Object> response = new ApiResponse<>(
                400,
                "Thông tin không hợp lệ",
                errors
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleException(Exception ex) {

        ApiResponse<Object> response = new ApiResponse<>(
                500,
                ex.getMessage(),
                null
        );
        return ResponseEntity.status(500).body(response);
    }


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotFound(NotFoundException ex) {

        ApiResponse<Object> response = new ApiResponse<>(
                404,
                ex.getMessage(),
                null
        );

        return ResponseEntity.status(404).body(response);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<Object>> handleBadRequest(BadRequestException ex) {

        ApiResponse<Object> response = new ApiResponse<>(
                400,
                ex.getMessage(),
                null
        );

        return ResponseEntity.badRequest().body(response);
    }



}