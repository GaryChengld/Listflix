package com.gcsi.listflix.identity.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gcsi.listflix.identity.api.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Gary Cheng
 */
@Component
@Slf4j
public class CustomAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException authException) {
        log.debug(authException.getMessage());
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return response.writeWith(Mono.just(response.bufferFactory().wrap(getJsonResponse(authException))));
    }

    private byte[] getJsonResponse(AuthenticationException authException) {
        // Create a custom response object with an error message
        ApiResponse<String> apiResponse = ApiResponse.withError(authException.getMessage());
        // Serialize the response object to JSON
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsBytes(apiResponse);
        } catch (JsonProcessingException e) {
            return new byte[0];
        }
    }
}
