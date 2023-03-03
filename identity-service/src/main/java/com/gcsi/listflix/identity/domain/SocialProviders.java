package com.gcsi.listflix.identity.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Gary Cheng
 */
@Entity
@Data
public class SocialProviders {
    @Id
    private Long id;
    private String providerId;
    private String clientId;
    private String clientSecret;

}
