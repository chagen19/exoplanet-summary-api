package com.chagen.exoplanet.summary.api.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import com.chagen.exoplanet.summary.api.interceptor.RestTemplateTracingInterceptor;

/**
 * Application Config
 *
 * @author Chad Hagen
 */
@Configuration
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

	@Bean
	public RestTemplate restTemplate(MappingJackson2HttpMessageConverter textToJsonMessageConverter) {
		RestTemplate restTemplate = new RestTemplate();

		List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
		if (CollectionUtils.isEmpty(interceptors)) {
			interceptors = new ArrayList<>();
		}
		interceptors.add(new RestTemplateTracingInterceptor());
		restTemplate.setInterceptors(interceptors);
		restTemplate.getMessageConverters().add(0, textToJsonMessageConverter);
		return restTemplate;
	}

	/*
		Since the exoplanet catalogue is returned with a context-type of text/plain. This is needed to add support for the text/plain media type
	*/
	@Bean
	public MappingJackson2HttpMessageConverter textToJsonMessageConverter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON));
		return converter;
	}
}
