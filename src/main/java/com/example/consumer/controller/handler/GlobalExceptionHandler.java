package com.example.consumer.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.consumer.service.exception.PacienteNotFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({PacienteNotFoundException.class})
    public ResponseEntity<Object> handleStudentNotFoundException(PacienteNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(exception.getMessage());
    }

}
