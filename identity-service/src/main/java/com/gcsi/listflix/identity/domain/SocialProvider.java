package com.gcsi.listflix.identity.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Gary Cheng
 */
@Entity
@Table(name = "social_providers")
@Data
public class SocialProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="provider_name", unique = true, nullable = false)
    private String providerName;

}
