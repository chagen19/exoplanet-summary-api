package com.chagen.exoplanet.summary.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

/**
 * Data object to hold exoplanet data from the catalogue
 *
 * @author Chad Hagen
 */
@Value
public class Exoplanet {

	@JsonProperty("PlanetIdentifier") private String planetIdentifier;
	@JsonProperty("TypeFlag") private int typeFlag;
	@JsonProperty("RadiusJpt") private double radiusJpt;
	@JsonProperty("DiscoveryYear") private int discoveryYear;
	@JsonProperty("HostStarTempK") private long hostStarTempK;
}