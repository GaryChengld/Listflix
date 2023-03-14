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
@Table(name = "authentications")
@Data
@ToString
public class Authentication {
    @Id
    private UUID id;

    @Column("identifier")
    @NotBlank
    private String identifier;

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
    @Version
    private LocalDateTime updatedAt;

    @Transient
    public String getStringId() {
        return null == id ? null : id.toString();
    }
}
