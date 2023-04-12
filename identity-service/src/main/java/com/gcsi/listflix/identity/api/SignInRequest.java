package com.gcsi.listflix.identity.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author Gary Cheng
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String password;

}
