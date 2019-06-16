package com.chagen.exoplanet.summary.api.service.impl

import com.chagen.exoplanet.summary.api.model.Exoplanet
import org.junit.jupiter.api.Assertions.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
/**
 * Tests for SummaryServiceImpl
 * @author Chad Hagen
 */
object SummaryServiceSpek: Spek({
    val summaryService by memoized { SummaryServiceImpl() }
    val exoplanets = listOf(
            Exoplanet("Planet-1", 3, 1.2, 0, -9000 ),
            Exoplanet("Planet-2", 3, 0.524, 2010, 2500 ),
            Exoplanet("Planet-3", 1, .0123, 2004, 7050 ),
            Exoplanet("Planet-4", 1, 3.0, 2004, 4050 ),
            Exoplanet("Planet-5", 2, 2.450, 2004, 2022 ),
            Exoplanet("Planet-6", 2, 1.9, 2010, 5012 ),
            Exoplanet("Planet-7", 1, -5.0, 2004, 3000 ),
            Exoplanet("Planet-8", 1, 1.0123, 2004, 3000 ),
            Exoplanet("Planet-9", 1, 3.456, 2001, 3000 ),
            Exoplanet("Planet-10", 1, 1.123, -2001, 3000 )

)
    describe("orphan count") {

        it("should return 2") {
            assertEquals(2, summaryService.getOrphanCount(exoplanets))
        }
    }
    describe("finding planet with hottest star") {

        it("should return Planet-3") {
            assertEquals("Planet-3", summaryService.findPlanetWithHottestStar(exoplanets))
        }
    }

    describe("creating timeline") {
        val timelineEntries =  summaryService.createTimeline(exoplanets)

        it("should return 5 entries") {
            assertEquals(5, timelineEntries.size)
        }

        val timelineEntry = timelineEntries.get(3)

        it("should return 2 small planets for 2004") {
            assertEquals(2004, timelineEntry.year)
            assertEquals(2, timelineEntry.smallPlanets)
        }

        it("should return 1 medium planets for 2004") {
            assertEquals(2004, timelineEntry.year)
            assertEquals(1, timelineEntry.mediumPlanets)
        }

        it("should return 2 large planets for 2004") {
            assertEquals(2004, timelineEntry.year)
            assertEquals(2, timelineEntry.largePlanets)
        }
    }
})