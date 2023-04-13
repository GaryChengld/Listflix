package com.gcsi.listflix.identity.api;

import com.gcsi.listflix.identity.api.exception.UserAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
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
    public Mono<ResponseEntity<ApiResponse<?>>> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        ApiResponse<?> errorResponse = ApiResponse.withError(ex.getMessage());
        return Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse));
    }

    @ExceptionHandler(AuthenticationException.class)
    public Mono<ResponseEntity<ApiResponse<?>>> handleAuthenticationException(AuthenticationException ex) {
        log.debug(ex.getMessage());
        ApiResponse<?> errorResponse = ApiResponse.withError(ex.getMessage());
        return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse));
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Mono<ResponseEntity<ApiResponse<?>>> handleUnauthorized(AuthenticationException ex) {
        log.debug(ex.getMessage());
        ApiResponse<?> errorResponse = ApiResponse.withError("Unauthorized");
        return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ApiResponse<?>>> handleException(Exception ex) {
        log.debug(ex.getMessage());
        ApiResponse<?> errorResponse = ApiResponse.withError(ex.getMessage());
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse));
    }
}
