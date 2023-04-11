package com.gcsi.listflix.identity.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author Gary Cheng
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private T data;
    private String errorMessage;

    public ApiResponse(T data) {
        this.data = data;
    }

    public ApiResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
