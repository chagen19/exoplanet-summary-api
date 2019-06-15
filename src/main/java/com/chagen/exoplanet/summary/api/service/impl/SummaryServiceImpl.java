package com.chagen.exoplanet.summary.api.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.chagen.exoplanet.summary.api.model.Exoplanet;
import com.chagen.exoplanet.summary.api.model.TimelineEntry;
import com.chagen.exoplanet.summary.api.service.SummaryService;

/**
 * Service implementation for catalogue summary functions
 *
 * @author Chad Hagen
 */
@Service
public class SummaryServiceImpl implements SummaryService {

	private static final String SMALL = "small";
	private static final String MEDIUM = "medium";
	private static final String LARGE = "large";

	/**
	 * Get count of orphan planets
	 *
	 * @param exoplanets list of exoplanets to search
	 * @return count of orphan planets
	 */
	@Override
	public long getOrphanCount(List<Exoplanet> exoplanets) {
		return exoplanets.stream().filter(p -> p.getTypeFlag() == 3).count();
	}

	/**
	 * Find planet with the hottest start
	 *
	 * @param exoplanets list of exoplanets to search
	 * @return planet identified or hottest star
	 */
	@Override
	public String findPlanetWithHottestStar(List<Exoplanet> exoplanets) {
		String planetIdentifier = "";
		Optional<Exoplanet> first = exoplanets.stream().max(Comparator.comparingDouble(Exoplanet::getHostStarTempK));
		if (first.isPresent()) {
			planetIdentifier = first.get().getPlanetIdentifier();
		}
		return planetIdentifier;
	}

	/**
	 * Create timeline from list of exoplanets
	 *
	 * @param exoplanets list of exoplanets as input for timeline
	 * @return list of timeline entries sorted by year
	 */
	@Override
	public List<TimelineEntry> createTimeline(List<Exoplanet> exoplanets) {
		Map<Integer, List<Exoplanet>> byYear = exoplanets.stream().collect(Collectors.groupingBy(Exoplanet::getDiscoveryYear));
		return byYear.entrySet().stream().map(this::createTimelineEntry).sorted(Comparator.comparing(TimelineEntry::getYear)).collect(Collectors.toList());
	}

	private TimelineEntry createTimelineEntry(Map.Entry<Integer, List<Exoplanet>> yearEntry) {
		Map<String, List<Exoplanet>> bySize = yearEntry.getValue().stream().collect(Collectors.groupingBy(this::determineSize));
		return new TimelineEntry(yearEntry.getKey(), getCountBySizeCategory(bySize, SMALL), getCountBySizeCategory(bySize, MEDIUM), getCountBySizeCategory(bySize, LARGE));
	}

	private String determineSize(Exoplanet exoplanet) {
		int radiusJpt = Double.valueOf(exoplanet.getRadiusJpt()).intValue();
		String size;
		switch (radiusJpt) {
			case 0:
				size = SMALL;
				break;
			case 1:
				size = MEDIUM;
				break;
			default:
				size = LARGE;
		}
		return size;
	}

	private int getCountBySizeCategory(Map<String, List<Exoplanet>> exoplanetMap, String sizeCategory) {
		List<Exoplanet> exoplanets = exoplanetMap.get(sizeCategory);
		return null != exoplanets ? exoplanets.size() : 0;
	}
}