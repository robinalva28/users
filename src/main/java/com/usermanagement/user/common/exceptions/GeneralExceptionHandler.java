package com.usermanagement.user.common.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.jboss.logging.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GeneralExceptionHandler {

    private final Logger log = Logger.getLogger(GeneralExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {

        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        log.error(e.getMessage());
        return ResponseEntity.status(409).body(errorResponse);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<List<ErrorResponse>> constraintViolationExceptionHandler(ConstraintViolationException e) {

        var errors = List.of(new ErrorResponse(e.getMessage()));
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorResponse> handleException(Exception e) {

        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        log.error(e.getMessage());
        return ResponseEntity.status(500).body(errorResponse);
    }

}
