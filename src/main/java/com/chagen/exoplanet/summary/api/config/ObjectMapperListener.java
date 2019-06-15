package com.chagen.exoplanet.summary.api.config;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import springfox.documentation.schema.configuration.ObjectMapperConfigured;

/**
 * Configure object mapper, needed because SpringFox swagger takes over object mapper and we can't customize directly
 *
 * @author Chad Hagen
 */
@Component
public class ObjectMapperListener implements ApplicationListener<ObjectMapperConfigured> {

	@Override
	public void onApplicationEvent(ObjectMapperConfigured event) {
		ObjectMapper mapper = event.getObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
	}
}
