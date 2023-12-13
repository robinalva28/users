package com.usermanagement.user.common.exceptions;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GeneralExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {

        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), LocalDateTime.now(ZoneId.of("Z")));
        log.error(e.getMessage());
        return ResponseEntity.status(409).body(errorResponse);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<List<ErrorResponse>> constraintViolationExceptionHandler(ConstraintViolationException e) {

        var errors = List.of(new ErrorResponse(e.getMessage(), LocalDateTime.now(ZoneId.of("Z"))));
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }

}
