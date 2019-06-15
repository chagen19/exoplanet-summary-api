package com.chagen.exoplanet.summary.api.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Response object for timeline entry
 *
 * @author Chad Hagen
 */
@Data
@AllArgsConstructor
@JsonPropertyOrder({ "year", "smallPlanets", "mediumPlanets", "largePlanets" })
public class TimelineEntry {

	@ApiModelProperty(example = "2019", notes = "The year the exoplanets were discovered (0 if unknown)") private int year;
	@ApiModelProperty(example = "2", notes = "The number of small exoplanetes discovered") private int smallPlanets;
	@ApiModelProperty(example = "5", notes = "The number of medium exoplanetes discovered") private int mediumPlanets;
	@ApiModelProperty(example = "1", notes = "The number of large exoplanetes discovered") private int largePlanets;
}