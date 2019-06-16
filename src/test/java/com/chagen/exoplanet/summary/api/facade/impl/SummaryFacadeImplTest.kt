package com.chagen.exoplanet.summary.api.facade.impl

import com.chagen.exoplanet.summary.api.client.CatalogueClient
import com.chagen.exoplanet.summary.api.facade.SummaryFacade
import com.chagen.exoplanet.summary.api.model.TimelineEntry
import com.chagen.exoplanet.summary.api.service.SummaryService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

/**
 * Tests for SummaryFacadeImpl
 * @author Chad Hagen
 */
@SpringBootTest
class SummaryFacadeImplTest {
    @MockkBean(relaxed = true)
    private lateinit var catalogueClient: CatalogueClient

    @MockkBean
    private lateinit var summaryService: SummaryService

    @Autowired
    private lateinit var summaryFacade: SummaryFacade

    @Test
    fun `should create exoplanet summary`() {
        val timelineEntries = listOf(
                TimelineEntry(2015, 1, 2, 3),
                TimelineEntry(2019, 1, 0, 11)
        )

        every { summaryService.getOrphanCount(any())} returns 2;
        every { summaryService.findPlanetWithHottestStar(any())} returns "PlanetWithHotestStar";
        every { summaryService.createTimeline(any())} returns timelineEntries;

        val summary = summaryFacade.buildSummary();
        Assertions.assertEquals(2, summary.numberOfOrphanedPlanets)
        Assertions.assertEquals("PlanetWithHotestStar", summary.nameOfPlanetOrbitingHottestStar)
        Assertions.assertEquals(2, summary.timeline.size)
        verify { catalogueClient.catalogue }
    }
}