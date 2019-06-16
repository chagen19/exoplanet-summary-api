package com.chagen.exoplanet.summary.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chagen.exoplanet.summary.api.facade.SummaryFacade;
import com.chagen.exoplanet.summary.api.model.Summary;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Endpoint for retrieving the exoplanet summary
 *
 * @author Chad Hagen
 */
@Api(tags = "Exoplanet Summary")
@ApiResponses({
		@ApiResponse(code = 500, message = "Unable to create summary data")
})
@RestController
@RequestMapping("/exoplanetSummary")
public class SummaryController {

	private final SummaryFacade summaryFacade;

	@Autowired
	public SummaryController(SummaryFacade summaryFacade) {
		this.summaryFacade = summaryFacade;
	}

	@GetMapping
	@ApiOperation(notes = "Endpoint to retrieve exoplanet summary information", value = "GET Summary")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Summary request completed successfully"),
	})
	public ResponseEntity<Summary> getSummary() {
		Summary summary = summaryFacade.buildSummary();
		return new ResponseEntity<>(summary, HttpStatus.OK);
	}
}