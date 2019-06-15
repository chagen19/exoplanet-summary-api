package com.chagen.exoplanet.summary.api.exception;

import com.chagen.exoplanet.summary.api.client.CatalogueClient;

/**
 * Exception class for errors in {@link CatalogueClient}
 *
 * @author Chad Hagen
 */
public class CatalogueClientException extends RuntimeException {

	public CatalogueClientException(String message, Throwable cause) {
		super(message, cause);
	}
}
