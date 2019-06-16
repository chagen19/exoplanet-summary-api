package com.chagen.exoplanet.summary.api.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.chagen.exoplanet.summary.api.interceptor.RestTemplateTracingInterceptor;

/**
 * Customize the default rest template to add tracing interceptor and message converter for text_plain
 *
 * @author Chad Hagen
 */
@Component
public class CustomRestTemplateCustomizer implements RestTemplateCustomizer {

	@Override
	public void customize(RestTemplate restTemplate) {
		List<ClientHttpRequestInterceptor> interceptors
				= restTemplate.getInterceptors();
		if (CollectionUtils.isEmpty(interceptors)) {
			interceptors = new ArrayList<>();
		}
		interceptors.add(new RestTemplateTracingInterceptor());
		restTemplate.setInterceptors(interceptors);
		restTemplate.getMessageConverters().add(0, textToJsonMessageConverter());
	}

	private MappingJackson2HttpMessageConverter textToJsonMessageConverter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON));
		return converter;
	}
}
