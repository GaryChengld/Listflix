package com.gcsi.listflix.identity.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * @author Gary Cheng
 */
@Table("users")
@Data
@ToString
public class User {

    @Id
    private Long id;
    @Column("username")
    private String username;

    @Column("email")
    private String email;

    @Column("passwordHash")
    private String passwordHash;

    @Column("created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column("updated_at")
    @LastModifiedDate
    @Version
    private LocalDateTime updatedAt;
}
