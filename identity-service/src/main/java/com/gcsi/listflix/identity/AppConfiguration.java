package com.gcsi.listflix.identity;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Gary Cheng
 */
@Configuration
@PropertySource("file:./env.properties")
public class AppConfiguration {
}
