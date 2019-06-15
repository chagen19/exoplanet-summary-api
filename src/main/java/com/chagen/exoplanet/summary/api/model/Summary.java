package com.chagen.exoplanet.summary.api.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * Response resource object for an exoplanet summary data
 *
 * @author Chad Hagen
 */
@Value
@AllArgsConstructor
@JsonPropertyOrder({ "numberOfOrphanedPlanets", "nameOfPlanetOrbitingHottestStar", "timeline", "summaryDate" })
public class Summary implements Serializable {

	@ApiModelProperty(example = "5", notes = "The number of orphan planets (no star)") private long numberOfOrphanedPlanets;
	@ApiModelProperty(example = "Planet5000", notes = "The name (planet identifier) of the planet orbiting the hottest star") private String nameOfPlanetOrbitingHottestStar;
	@ApiModelProperty(notes = "A timeline of the number of planets discovered per year grouped by size") List<TimelineEntry> timeline;
	@ApiModelProperty(notes = "Date in UTC the summary was created")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "GMT")
	private Instant summaryDate;
}
