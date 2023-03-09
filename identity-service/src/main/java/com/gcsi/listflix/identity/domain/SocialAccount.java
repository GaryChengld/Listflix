package com.gcsi.listflix.identity.domain;

import lombok.Data;
import org.springframework.data.annotation.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * @author Gary Cheng
 */
@Table("social_accounts")
@Data
public class SocialAccount {
    @Id
    private Long id;
    @Column("user_id")
    private Long userId;

    @Column("provider_id")
    private Long providerId;

    @Column("user_reference_id")
    private String userReferenceId;

    @Column("created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column("updated_at")
    @LastModifiedDate
    @Version
    private LocalDateTime updatedAt;
}
