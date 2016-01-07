package me.woemler.springblog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author woemler
 */

@Configuration
@EnableSpringDataWebSupport
public class WebAppConfig extends WebMvcConfigurerAdapter {

	@Override 
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**");
	}

}
