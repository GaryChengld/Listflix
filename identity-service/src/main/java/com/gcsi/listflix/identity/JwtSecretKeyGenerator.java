package com.gcsi.listflix.identity;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author Gary Cheng
 */

public class JwtSecretKeyGenerator {
    public static void main(String[] args) {
        int length = 64; // length of the secret key
        String generatedSecretKey = RandomStringUtils.randomAlphanumeric(length);
        System.out.println("Generated secret key: " + generatedSecretKey);
    }
}
