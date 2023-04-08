package br.edu.ifsc.chamados.configs.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DefaultException.class)
    public ResponseEntity<ExceptionResponse> handleException(DefaultException e) {
        log.error(e.getMessage());
        ExceptionResponse error = new ExceptionResponse(e.getStatus().value(), e.getMessage());

        return new ResponseEntity<ExceptionResponse>(error, e.getStatus());
    }
}

