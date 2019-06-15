package com.chagen.exoplanet.summary.api.client.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.chagen.exoplanet.summary.api.client.CatalogueClient;
import com.chagen.exoplanet.summary.api.exception.CatalogueClientException;
import com.chagen.exoplanet.summary.api.model.Exoplanet;

/**
 * Implementation of client to retrieve exoplanet catalogue
 *
 * @author Chad Hagen
 */
@Component
public class CatalogueClientImpl implements CatalogueClient {

	private final RestTemplate restTemplate;
	private final String exoplanetCatalogueUrl;

	@Autowired
	public CatalogueClientImpl(RestTemplate restTemplate, @Value("${exoplanet.catalogue.api.url}") String exoplanetCatalogueUrl) {
		this.restTemplate = restTemplate;
		this.exoplanetCatalogueUrl = exoplanetCatalogueUrl;
	}

	@Override
	public List<Exoplanet> getCatalogue() {

		ResponseEntity<List<Exoplanet>> response;
		try {
			response = restTemplate.exchange(exoplanetCatalogueUrl, HttpMethod.GET, null, new ParameterizedTypeReference<>() {

			});
		} catch (Exception e) {
			throw new CatalogueClientException("Exception while trying to access the exoplanet catalogue", e);
		}
		return response.getBody();
	}
}