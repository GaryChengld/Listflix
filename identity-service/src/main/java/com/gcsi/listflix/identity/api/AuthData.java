package com.gcsi.listflix.identity.api;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Gary Cheng
 */
@Data
@AllArgsConstructor
public class AuthData {
    private String token;
}
