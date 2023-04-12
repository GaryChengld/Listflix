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

    private ApiResponse() {
    }

    public static <T> ApiResponse<T> withData(T data) {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setData(data);
        return apiResponse;
    }

    public static <T> ApiResponse<T> withError(String errorMessage) {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setErrorMessage(errorMessage);
        return apiResponse;
    }
}
