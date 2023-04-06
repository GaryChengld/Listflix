package com.gcsi.listflix.identity.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Gary Cheng
 */
@Table("users")
@Data
@ToString
public class User {

    @Id
    private UUID id;

    @Column("email")
    @NotBlank
    private String email;

    @Column("authentication_provider")
    @NotBlank
    private String authenticationProvider;

    @Column("password_hash")
    @NotBlank
    private String passwordHash;

    @Column("created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column("updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

}
