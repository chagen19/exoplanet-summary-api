package com.chagen.exoplanet.summary.api.facade;

import com.chagen.exoplanet.summary.api.model.Summary;

/**
 * Facade to orchestrate tasks for creating the summary
 *
 * @author Chad Hagen
 */
public interface SummaryFacade {

	/**
	 * Build the summary data for the summary transaction
	 *
	 * @return summary
	 */
	Summary buildSummary();

}
