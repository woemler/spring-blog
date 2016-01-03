package me.woemler.springblog.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author woemler
 */

@Configuration
@PropertySource("classpath:test-blog.properties")
public class TestApplicationConfig {
}
