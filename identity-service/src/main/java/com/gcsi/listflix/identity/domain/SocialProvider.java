package com.gcsi.listflix.identity.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Gary Cheng
 */
@Table("social_providers")
@Data
public class SocialProvider {
    @Id
    private Long id;

    @Column("provider_name")
    private String providerName;

}
