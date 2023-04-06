package com.gcsi.listflix.identity.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Gary Cheng
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    private String email;
    private String password;

}
