package com.chagen.exoplanet.summary.api.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chagen.exoplanet.summary.api.client.CatalogueClient;
import com.chagen.exoplanet.summary.api.facade.SummaryFacade;
import com.chagen.exoplanet.summary.api.model.Exoplanet;
import com.chagen.exoplanet.summary.api.model.Summary;
import com.chagen.exoplanet.summary.api.model.TimelineEntry;
import com.chagen.exoplanet.summary.api.service.SummaryService;

/**
 * Facade implementation to orchestrate tasks for creating the summary
 *
 * @author Chad Hagen
 */
@Component
public class SummaryFacadeImpl implements SummaryFacade {

	private final CatalogueClient catalogueClient;
	private final SummaryService summaryService;

	@Autowired
	public SummaryFacadeImpl(CatalogueClient catalogueClient, SummaryService summaryService) {
		this.catalogueClient = catalogueClient;
		this.summaryService = summaryService;
	}

	@Override
	public Summary buildSummary() {
		List<Exoplanet> exoplanets = catalogueClient.getCatalogue();
		long orphanCount = summaryService.getOrphanCount(exoplanets);
		String planetWithHottestStart = summaryService.findPlanetWithHottestStar(exoplanets);
		List<TimelineEntry> timeline = summaryService.createTimeline(exoplanets);
		return new Summary(orphanCount, planetWithHottestStart, timeline);
	}
}