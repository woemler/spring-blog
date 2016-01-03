package me.woemler.springblog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author woemler
 */

@Configuration
@PropertySource({ "classpath:blog.properties" })
public class ApplicationConfig {
}
