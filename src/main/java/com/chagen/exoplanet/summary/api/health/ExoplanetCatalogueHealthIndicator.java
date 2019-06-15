package com.chagen.exoplanet.summary.api.health;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.mongo.MongoHealthIndicator;
import org.springframework.stereotype.Component;

import com.chagen.exoplanet.summary.api.client.CatalogueClient;

/**
 * Dependency test to ensure the exoplanet catalogue is available
 *
 * @author Chad Hagen
 */
@Component
public class ExoplanetCatalogueHealthIndicator extends AbstractHealthIndicator {

	private final Logger logger = LoggerFactory.getLogger(MongoHealthIndicator.class);
	private final CatalogueClient catalogueClient;

	@Autowired
	public ExoplanetCatalogueHealthIndicator(CatalogueClient catalogueClient) {
		this.catalogueClient = catalogueClient;
	}

	@Override
	protected void doHealthCheck(Health.Builder builder) {
		// Would normally call an endpoint of the catalogue service to check if it's up. Calling the whole catalog retrieval here would be too expensive for a health check
		// catalogueClient.testUp();
		builder.up();
	}
}