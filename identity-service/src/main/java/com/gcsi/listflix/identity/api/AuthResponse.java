package com.gcsi.listflix.identity.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Gary Cheng
 */
@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
}
