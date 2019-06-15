package com.chagen.exoplanet.summary.api.client;

import java.util.List;

import com.chagen.exoplanet.summary.api.model.Exoplanet;

/**
 * Client to retrieve exoplanet catalogue
 *
 * @author Chad Hagen
 */
public interface CatalogueClient {

	List<Exoplanet> getCatalogue();

}
