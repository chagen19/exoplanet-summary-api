package com.chagen.exoplanet.summary.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

/**
 * Application Config
 *
 * @author Chad Hagen
 */
@Configuration
@EnableRetry
public class AppConfig {

	@Bean
	public CommonsRequestLoggingFilter logFilter() {
		CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
		filter.setIncludeQueryString(true);
		filter.setIncludePayload(true);
		filter.setMaxPayloadLength(10000);
		filter.setIncludeHeaders(true);
		return filter;
	}
}
