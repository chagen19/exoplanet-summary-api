package com.chagen.exoplanet.summary.api.service;

import java.util.List;

import com.chagen.exoplanet.summary.api.model.Exoplanet;
import com.chagen.exoplanet.summary.api.model.TimelineEntry;

/**
 * Service for catalogue summary functions
 *
 * @author Chad Hagen
 */
public interface SummaryService {

	long getOrphanCount(List<Exoplanet> exoplanets);

	String findPlanetWithHottestStar(List<Exoplanet> exoplanets);

	List<TimelineEntry> createTimeline(List<Exoplanet> exoplanets);
}
