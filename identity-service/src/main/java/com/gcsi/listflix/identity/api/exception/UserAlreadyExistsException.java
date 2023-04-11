package com.gcsi.listflix.identity.api.exception;

/**
 * @author Gary Cheng
 */
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
