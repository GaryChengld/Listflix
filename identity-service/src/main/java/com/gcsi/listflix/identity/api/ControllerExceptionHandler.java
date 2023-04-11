package com.gcsi.listflix.identity.api;

import com.gcsi.listflix.identity.api.exception.UserAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

/**
 * @author Gary Cheng
 */
@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Mono<ResponseEntity<ApiResponse>> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        ApiResponse errorResponse = new ApiResponse(ex.getMessage());
        return Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Mono<ResponseEntity<ApiResponse>> handleException(UserAlreadyExistsException ex) {
        ApiResponse errorResponse = new ApiResponse(ex.getMessage());
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse));
    }
}
