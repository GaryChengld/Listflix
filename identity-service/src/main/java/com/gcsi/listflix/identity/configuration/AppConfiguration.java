package com.gcsi.listflix.identity.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;

/**
 * @author Gary Cheng
 */
@Configuration
@EnableR2dbcAuditing
@PropertySource("file:./env.properties")
public class AppConfiguration {
}
