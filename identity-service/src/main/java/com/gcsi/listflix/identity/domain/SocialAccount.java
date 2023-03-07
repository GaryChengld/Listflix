package com.gcsi.listflix.identity.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Gary Cheng
 */
@Entity
@Table(name = "social_accounts")
@Data
public class SocialAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private SocialProvider provider;

    @Column(name = "user_reference_id", nullable = false)
    private String userReferenceId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
